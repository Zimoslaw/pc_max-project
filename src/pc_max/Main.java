package pc_max;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler(); //dane problemu
        Logger logger = new Logger(); //komunikaty, logi

        fileHandler.getProblem("C:\\Users\\Zimek\\Desktop\\test.txt");

        logger.Log(10,fileHandler.getProcessors());

        ArrayList<Integer> tasks = fileHandler.getTasks();
        logger.Log(11,tasks.size());

        logger.Log(12,"");

        for (int t:tasks) {
            System.out.println(t);
        }
    }
}
