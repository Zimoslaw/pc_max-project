package pc_max;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler();

        fileHandler.getProblem("C:\\Users\\Zimek\\Desktop\\test.txt");
        System.out.println(fileHandler.getProcessors());
        ArrayList<Integer> tasks = fileHandler.getTasks();
        for (int t:tasks) {
            System.out.println(t);
        }

    }
}
