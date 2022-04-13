package pc_max;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler(); //dane problemu
        Logger logger = new Logger(); //komunikaty, logi
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //wczytywanie inputu
        String filePath; //ścieżka do pliku z problemem

        System.out.println("Podaj ścieżkę pliku:");

        filePath = reader.readLine();

        if(fileHandler.getProblem(filePath))
        {
            logger.Log(10,fileHandler.getProcessors());

            ArrayList<Integer> tasks = fileHandler.getTasks();
            logger.Log(11,tasks.size());

            logger.Log(12,"");

            for (int t:tasks) {
                System.out.println(t);
            }
        }
    }
}
