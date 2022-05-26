package pc_max;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static int procNum = 0; //Liczba procesorów
    static ArrayList<Processor> processors; //lista procesorów
    static ArrayList<Integer> tasks; //Lista czasów wszystkich zadań
    static int Tmax = 0; //Czas wykonania ostatniego zadania

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler(); //dane problemu
        Logger logger = new Logger(); //komunikaty, logi
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //wczytywanie inputu
        String filePath; //ścieżka do pliku z problemem
        

        System.out.println("Podaj ścieżkę pliku:");

        filePath = reader.readLine();

        if(fileHandler.getProblem(filePath)) //wczytywanie problemu
        {
            procNum = fileHandler.getProcessors();
            createProcessors(procNum);
            logger.Log(10,processors.size());

            tasks = fileHandler.getTasks();
            logger.Log(11,tasks.size());

            /*logger.Log(12,"");

            for (int t:tasks) {
                System.out.println(t);
            }*/
            
            LPT();
            logger.Log(13, "(LPT): "+Tmax); //Wydrukowanie wyniku
        }
    }

    /**
     * Tworzenie listy pustych procesorów
     * @param processorNum liczba procesorów do stworzenia
     */
    private static void createProcessors(int processorNum) {
        processors = new ArrayList<>();
        for(int i = 0; i < processorNum; i++) {
            processors.add(new Processor());
        }
    }

    /**
     * Algorytm zachłanny LPT
     */
    private static void LPT() {
        Logger logger = new Logger();

        tasks.sort(Comparator.reverseOrder());

            Processor Pmin = processors.get(0); //obecny procesor z najmniejszym czasem wykonania ostatniego zadania
            int Tmin = 0; //czas wykonania ostatniego zadania powyższego procesora
            for(int t:tasks) {
                for(Processor p:processors) { //znajdź najmniej obciążony procesor
                    if(p.getTotalTime() < Tmin)
                    {
                        Pmin = p;
                        Tmin = p.getTotalTime();
                    }
                }

                Pmin.addTask(t);
                Tmin = Pmin.getTotalTime();
            }

            for(Processor p : processors) {  //obliczanie czasu wykonania ostatniego zadania wszystkich procesorów
                if(p.getTotalTime() > Tmax) {
                    Tmax = p.getTotalTime();
                }
            }
    }
}
