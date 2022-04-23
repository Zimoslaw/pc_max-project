package pc_max;

import java.util.ArrayList;

public class Main {
    
    static int procNum = 0; //Liczba procesorów
    static ArrayList<Integer> tasks; //Lista czasów wszystkich zadań
    static int Tmax = 0; //Czas wykonania ostatniego zadania

    public static void main(String[] args) throws Exception {

<<<<<<< Updated upstream
        FileHandler fileHandler = new FileHandler();

        fileHandler.getProblem("C:\\Users\\Zimek\\Desktop\\test.txt");
        System.out.println(fileHandler.getProcessors());
        ArrayList<Integer> tasks = fileHandler.getTasks();
        for (int t:tasks) {
            System.out.println(t);
=======
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
            
            Greedy();
            logger.Log(13, Tmax); //Wydrukowanie wyniku
>>>>>>> Stashed changes
        }

    }

    /**
     * Algorytm zachłanny
     */
    private static void Greedy() {
        Logger logger = new Logger();
        int[][] T = new int[procNum][tasks.size()];
            
            int procI = 0;
            int taskI = 0;
            for(int t:tasks) {
                for(int i = 0; i < tasks.size(); i++)
                    if(T[procI][i] == 0)
                    {
                        T[procI][i] = t;
                        break;
                    } 
                        
                procI++;
                if(procI == procNum) {
                    procI = 0;
                    taskI = 0;
                }
            }
            
            for(int i = 0; i < procNum; i++) {
                int sum = 0;
                for(int j = 0; j < tasks.size(); j++)
                    sum += T[i][j];
                logger.Log(0,sum);
                if(sum > Tmax)
                    Tmax = sum;
            }
    }
}
