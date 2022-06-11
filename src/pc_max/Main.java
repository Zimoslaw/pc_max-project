package pc_max;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int procNum = 0; //Liczba procesorów
    static int tabuLvl = 3; //

    static ArrayList<Processor> processors; //lista procesorów
    static ArrayList<Integer> tasks; //Lista czasów wszystkich zadań

    static int[][] tabuList;

    static int Tmax = 0; //Czas wykonania ostatniego zadania

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler(); //dane problemu
        Logger logger = new Logger(); //komunikaty, logi
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //wczytywanie inputu
        String filePath; //ścieżka do pliku z problemem

        Generator g = new Generator();

        System.out.println("Podaj sciezke pliku:");

        filePath = reader.readLine();

        if(fileHandler.getProblem(filePath)) //wczytywanie problemu
        {
            procNum = fileHandler.getProcessors();
            createProcessors(procNum);
            logger.Log(10,processors.size());

            tasks = fileHandler.getTasks();
            logger.Log(11,tasks.size());

            //LPT();
            Greedy();
            Tmax = getTimeOfLastTask(); //obliczenie wyniku
            logger.Log(13, "(Zachlanny): "+Tmax);

            tasks.clear();
            processors.clear();
            fileHandler.getProblem(filePath);
            createProcessors(procNum);
            tasks = fileHandler.getTasks();

            Tabu();
            Tmax = getTimeOfLastTask();
            logger.Log(13, "(Tabu): "+Tmax); //Wydrukowanie wyniku algorytmu Tabu

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

    private static int getTimeOfLastTask() {
        int max = 0;
        for(Processor p : processors) {
            if(p.getTotalTime() > max) {
                max = p.getTotalTime();
            }
        }
        return max;
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
    }

    private static void Greedy() {
        Logger logger = new Logger();

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
    }

    private static void Tabu() {
        Logger logger = new Logger();

        while(!tasks.isEmpty()) { //funkcja celu - przypisanie wszystkich zadań
            int taskIndex = 0; //indeks zadania do przypisania
            int maxOpportunity = Integer.MIN_VALUE; //największa korzyść
            int chosenProcIndex = 0; //indeks wybranego procesora
            int currentTimeOfLastTask = getTimeOfLastTask(); //czas wykonaia się wszystkich zadań dla obecnego kroku

            for(int t : tasks) { //znajdź krok z największą korzyścią
                for(Processor p : processors) {
                    int opportunity = currentTimeOfLastTask - p.getTotalTime() + t;
                    if(p.getTabu() == 0) { //jeżeli procesor nie jest tabu
                        if(opportunity > maxOpportunity) {
                            maxOpportunity = opportunity;
                            chosenProcIndex = processors.indexOf(p);
                            taskIndex = tasks.indexOf(t);
                        }
                    }
                    else { //jeżeli jest tabu, korzyść musi być wyjątkowo dobra
                        if(opportunity > maxOpportunity + maxOpportunity/10) {
                            maxOpportunity = opportunity;
                            chosenProcIndex = processors.indexOf(p);
                            taskIndex = tasks.indexOf(t);
                        }
                    }
                }
            }

            for(Processor p : processors) //obniżenie poziomu tabu na końcu kroku
            {
                p.lowerTabu();
            }
            processors.get(chosenProcIndex).addTask(tasks.get(taskIndex)); //przypisz zadanie do procesora z największą korzyścią
            tasks.remove(taskIndex); //usuń zadanie z listy
            processors.get(chosenProcIndex).setTabu(); //oznacz go jako tabu


        }
    }
}