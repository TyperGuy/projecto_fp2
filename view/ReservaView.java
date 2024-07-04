package view;

import controller.ReservaController;
import controller.SalaController;
import controller.ClienteController;
import mod.Reserva;
import mod.Sala;
import mod.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import components.datePicker.DatePicker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaView extends JFrame {
    private ReservaController reservaController;
    private SalaController salaController;
    private ClienteController clienteController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> salaComboBox;
    private JComboBox<String> clienteComboBox;
    private DatePicker dataInicioPicker;
    private DatePicker dataFimPicker;
    private SimpleDateFormat dateFormat;

    public ReservaView(ReservaController reservaController, SalaController salaController,
            ClienteController clienteController) {
        this.reservaController = reservaController;
        this.salaController = salaController;
        this.clienteController = clienteController;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        setTitle("Gestão de Reservas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Tabela de reservas
        tableModel = new DefaultTableModel(new Object[] { "ID", "Sala", "Usuário", "Data Início", "Data Fim" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));

        inputPanel.add(new JLabel("Sala:"));
        salaComboBox = new JComboBox<>();
        atualizarSalas();
        inputPanel.add(salaComboBox);

        inputPanel.add(new JLabel("Clientes:"));
        clienteComboBox = new JComboBox<>();
        atualizarClientes();
        inputPanel.add(clienteComboBox);

        inputPanel.add(new JLabel("Data Início (dd/MM/yyyy HH:mm):"));
        dataInicioPicker = new DatePicker();
        inputPanel.add(dataInicioPicker);

        inputPanel.add(new JLabel("Data Fim (dd/MM/yyyy HH:mm):"));
        dataFimPicker = new DatePicker();
        inputPanel.add(dataFimPicker);

        JButton addButton = new JButton("Adicionar Reserva");
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remover Reserva");
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Ação do botão Adicionar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sala sala = salaController.listarSalas().get(salaComboBox.getSelectedIndex());
                    Cliente cliente = clienteController.listarClientes().get(clienteComboBox.getSelectedIndex());
                    Date dataInicio = dataInicioPicker.getSelectedDate();
                    Date dataFim = dataFimPicker.getSelectedDate();
                    if (reservaController.isSalaDisponivel(sala, dataInicio, dataFim)) {
                        reservaController.adicionarReserva(sala, cliente, dataInicio, dataFim);
                        atualizarTabela();
                        dataInicioPicker.setSelectedDate(null);
                        dataFimPicker.setSelectedDate(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sala não disponível para o período selecionado.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar reserva: " + ex.getMessage());
                }
            }
        });

        // Ação do botão Remover
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    reservaController.cancelarReserva(id);
                    atualizarTabela();
                }
            }
        });

        atualizarTabela();
    }

    private void atualizarSalas() {
        salaComboBox.removeAllItems();
        for (Sala sala : salaController.listarSalas()) {
            salaComboBox.addItem(sala.getNome());
        }
    }

    private void atualizarClientes() {
        clienteComboBox.removeAllItems();
        for (Cliente cliente : clienteController.listarClientes()) {
            clienteComboBox.addItem(cliente.getNome());
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Reserva reserva : reservaController.listarReservas()) {
            tableModel.addRow(new Object[] {
                    reserva.getId(),
                    reserva.getSala().getNome(),
                    reserva.getCliente().getNome(),
                    dateFormat.format(reserva.getDataInicio()),
                    dateFormat.format(reserva.getDataFim())
            });
        }
    }
}
