package pc_max;

import java.util.ArrayList;

import static pc_max.Main.tabuLvl;

/**
 * Klasa implementująca procesor
 *
 * @author Jakub Niewiarowski
 */
public class Processor {
    ArrayList<Integer> tasks; //lista zadań przypisanych do procesora
    int isTabu = 0; //poziom tabu. Jeżeli 0 - nie jest tabu.
    int tryTask=0;

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

    public void setTryTask(int time) {
        tryTask = time;
    }

    public void resetTryTask() {
        tryTask = 0;
    }

    /**
     * Zwraca poziom tabu
     * @return aktualny poziom tabu (ile kroków jeszcze będzie tabu)
     */
    public int getTabu() { return isTabu; }

    public int getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Ustawia tabu porcesora na domyślny poziom
     */
    public void setTabu() { isTabu = tabuLvl;}

    public void lowerTabu() {
        if (isTabu > 0)
            isTabu--;
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
        return totalTime+tryTask;
    }
}