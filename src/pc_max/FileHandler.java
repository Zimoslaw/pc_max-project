package pc_max;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * Klasa do zarządzania plikami z danymi problemu i rozwiązaniem problemu
 *
 * @author Jakub Niewiarowski
 */
public class FileHandler {

    private int processors = 0; //liczba procesorów
    private int tasksNum = 0; //liczba zadań
    private ArrayList<Integer> tasks = new ArrayList<>(); //tablica z czasami wykonywania zadań

    /**
     * odczytuje plik z danymi problemu PC||Max. Odczytuje poprawnie tylko plik w formacie ze strony Machowiaka
     * <ul>
     *  <li>Pierwsza linia: liczba procesorów</li>
     *  <li>Druga linia: liczba zadań</li>
     *  <li>Pozostałe linie: czasy wykonywania zadań (odczytywane jest tylko tyle lini ile wskazuje liczba zadań)</li>
     * </ul>
     * @param filePath Ścieżka absolutna do pliku z danymi problemu
     * @return true - odczytanie pliku sie powiodło. false - odczytanie pliku się nie powiodło
     * @throws Exception
     */
    public boolean getProblem(String filePath) throws Exception {

        File file = null;

        if(!new File(filePath).exists())
        {
            // log/komunikat
            return false;
        }

        try {
            file = new File(filePath);
        }
        catch (Exception e) {
            // log/komuniakt
            return false;
        }
        finally {
            try {

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                //odczytanie liczby procesorów
                line = reader.readLine();

                processors = Integer.parseInt(line);

                //odczytanie liczby zadań
                line = reader.readLine();
                tasksNum = Integer.parseInt(line);

                //odczytanie czasów zadań
                for(int i = 0; i <= tasksNum; i++) {
                    line = reader.readLine();
                    tasks.add(Integer.parseInt(line));
                }
            }
            catch(Exception e) {
                // log/komuniakt
                return false;
            }
            finally {
                return true;
            }
        }

    }

    /**
     * Zwraca liczbę procesorów
     * @return Liczba procesorów
     */
    public int getProcessors()
    {
        return processors;
    }

    /**
     * Zwraca tablicę czasów wykonywania zadań
     * @return Tablica czasów wykonywania zadań
     */
    public ArrayList<Integer> getTasks()
    {
        return tasks;
    }

    /**
     * Zapisuje rozwiązanie problemu PC||Max do pliku
     * @param filePath Ścieżka absolutna pliku, do którego ma zostać zapisane rozwiązanie
     * @return true - zapisanie się powiodło. false - zapisanie się nie powiodło
     */
    public boolean saveResults(String filePath)
    {
        return true;
    }
}
