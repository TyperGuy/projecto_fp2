package view;

import controller.ReservaController;
import controller.SalaController;
import controller.ClienteController;

import javax.swing.*;

import components.datePicker.DatePicker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private SalaController salaController;
    private ClienteController clienteController;
    private ReservaController reservaController;

    public MainView(SalaController salaController, ClienteController clienteController,
            ReservaController reservaController) {
        this.salaController = salaController;
        this.clienteController = clienteController;
        this.reservaController = reservaController;

        setTitle("Sistema de Gestão de Co-Working");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1));

        JButton manageSalasButton = new JButton("Gerenciar Salas");
        manageSalasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame salaView = new SalaView(salaController);
                salaView.setVisible(true);
            }
        });
        add(manageSalasButton);

        JButton manageUsuariosButton = new JButton("Gerenciar Clientes");
        manageUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame usuarioView = new ClienteView(clienteController);
                usuarioView.setVisible(true);
            }
        });
        add(manageUsuariosButton);

        JButton manageReservasButton = new JButton("Gerenciar Reservas");
        manageReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reservaView = new ReservaView(reservaController, salaController, clienteController);
                reservaView.setVisible(true);
            }
        });
        add(manageReservasButton);

        JButton searchButton = new JButton("Pesquisas");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reservaView = new ReservaView(reservaController, salaController, clienteController);
                reservaView.setVisible(true);
            }
        });
        add(searchButton);

    }
}
