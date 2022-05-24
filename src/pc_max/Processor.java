package pc_max;

import java.util.ArrayList;

/**
 * Klasa implementująca procesor
 *
 * @author Jakub Niewiarowski
 */
public class Processor {
    ArrayList<Integer> tasks; //lista zadań przypisanych do procesora

    public Processor() {
        tasks = new ArrayList<>();
    }

    /**
     * Zwracanie listy przypisanych zadań
     * @return przypisane do procesora zadania
     */
    public ArrayList<Integer> getTasks() {
        return tasks;
    }

    /**
     * Przypisuje zadanie do procesora
     * @param task czas trwania zadania
     */
    public void addTask(int task) {
        tasks.add(task);
    }

    /**
     * Usuwa zadanie z listy przypisanych zadań procesora
     * @param index indeks zadania do usunięcia
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Zwraca czas wykonania ostatniego zadania przypisanego do procesora
     * @return czas wykonania ostatniego zadania
     */
    public int getTotalTime() {
        int totalTime = 0;
        for (int t:tasks) {
            totalTime += t;
        }
        return totalTime;
    }


}
