package pc_max;

/**
 * Klasa do wyświetlania wszelkich komunikatów w konsoli
 *
 * @author Jakub Niewiarowski
 */

public class Logger {

    /**
     * Wyświetlanie komunikatów w konsoli
     * @param errNum numer błędu/informacji
     * @param msg dodatkowa informacja do wyświetlenia
     */
    public void Log(int errNum, String msg) {
        _Printout(errNum, msg);
    }

    /**
     * Wyświetlanie komunikatów w konsoli
     * @param errNum numer błędu/informacji
     * @param msg dodatkowa informacja do wyświetlenia
     */
    public void Log(int errNum, int msg) {
        _Printout(errNum, Integer.toString(msg));
    }

    /**
     * Ostateczne wyświetlanie komunikatów w konsoli
     * @param e numer błędu/informacji
     * @param m dodatkowa informacja do wyświetlenia
     */
    private void _Printout(int e, String m) {
        String err = null;

        switch(e) {
            //-----Błędy-----
            case 0:
                err = "TEST";
                break;
            case 1:
                err = "Błąd pliku z problemem";
                break;
            //-----Info-----
            case 10:
                err = "Liczba procesorów";
                break;
            case 11:
                err = "Liczba zadań";
                break;
            case 12:
                err = "Czasy zadań";
                break;
            case 13:
                err = "Wynik";
                break;
            default:
                err = "Nieznany błąd";
                break;
        }

        System.out.println(err+": "+m);
    }
}
