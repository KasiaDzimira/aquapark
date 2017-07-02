import View.AppView;

/**
 * Contains main method
 */
public class Main {

    /**
     * Runs app
     * @param args application arguments
     */
    public static void main(String[] args) {
        AppView window = new AppView("Aquapark app");
        window.runWelcome();
    }
}
