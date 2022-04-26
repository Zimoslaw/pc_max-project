package pc_max;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static int procNum = 0; //Liczba procesorów
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
            logger.Log(10,procNum);

            tasks = fileHandler.getTasks();
            logger.Log(11,tasks.size());

            /*logger.Log(12,"");

            for (int t:tasks) {
                System.out.println(t);
            }*/
            
            LPT();
            logger.Log(13, Tmax); //Wydrukowanie wyniku
        }

    }

    /**
     * Algorytm zachłanny LPT
     */
    private static void LPT() {
        Logger logger = new Logger();
        int[][] T = new int[procNum][tasks.size()];

        tasks.sort(Comparator.reverseOrder());

            int Pmin; //obecny procesor z najmniejszym czasem wykonania ostatniego zadania
            int Tmin = 0; //czas wykonania ostatniego zadania powyższego procesora
            for(int t:tasks) {
                Pmin = 0;
                for(int p = 0; p < procNum; p++) { //znajdź najmniej obciążony procesor
                    int sum = 0;
                    for(int i = 0; i < tasks.size(); i++) //oblicz czas wykonania ostatniego zadania
                        sum += T[p][i];
                    if(p == 0)
                        Tmin = sum;
                    else if(sum < Tmin)
                    {
                        Pmin = p;
                        Tmin = sum;
                    }
                }

                for(int i = 0; i < tasks.size(); i++) { //przypisanie zadania do procesora
                    if (T[Pmin][i] == 0) {
                        T[Pmin][i] = t;
                        break;
                    }
                }
            }
            
            for(int i = 0; i < procNum; i++) { //obliczanie czasu wykonania ostatniego zadania wszystkich procesorów
                int sum = 0;
                for(int j = 0; j < tasks.size(); j++)
                    sum += T[i][j];
                //logger.Log(0,sum);
                if(sum > Tmax)
                    Tmax = sum;
            }
    }
}
