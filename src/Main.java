import View.AdminView;
import View.AppView;
import View.manager.ManagerView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppView window = new AppView("Aquapark app");
        window.runWelcome();
    }
}
