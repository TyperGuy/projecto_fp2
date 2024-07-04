package view;

import controller.ReservaController;
import controller.SalaController;
import controller.ClienteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SalaController salaController = new SalaController();
                ClienteController clienteController = new ClienteController();
                ReservaController reservaController = new ReservaController();

                JFrame mainView = new MainView(salaController, clienteController, reservaController);
                mainView.setVisible(true);
            }
        });
    }
}
