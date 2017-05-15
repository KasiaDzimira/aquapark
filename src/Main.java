import View.manager.ManagerView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        AppView window = new AppView("Aquapark app");
//        window.runWelcome();
//        AdminView window = new AdminView("Admin Panel");
//        window.runWelcome();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ManagerView window = new ManagerView("Manager Panel");
                window.runWelcome();
            }
        });

    }
}
