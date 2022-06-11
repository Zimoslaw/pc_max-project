package pc_max;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    int min = 1;
    int max = 250;

    public void generateProblem(int m, int n) throws IOException {
        File file = new File("instances/m"+m+"n"+n+".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(Integer.toString(m)+System.lineSeparator());
        writer.write(Integer.toString(n)+System.lineSeparator());
        for(int i = 0; i < n; i++)
            writer.write(Integer.toString((int)Math.floor(Math.random()*max-min))+System.lineSeparator());
        writer.close();
    }
}
