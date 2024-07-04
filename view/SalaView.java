package view;

import controller.SalaController;
import mod.Sala;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaView extends JFrame {
    private SalaController salaController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField;
    private JTextField capacidadeField;
    private JTextField precoField;

    public SalaView(SalaController salaController) {
        this.salaController = salaController;
        setTitle("Gestão de Salas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Tabela de salas
        tableModel = new DefaultTableModel(new Object[] { "ID", "Nome", "Capacidade", "Preço por Hora" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Capacidade:"));
        capacidadeField = new JTextField();
        inputPanel.add(capacidadeField);

        inputPanel.add(new JLabel("Preço por hora:"));
        precoField = new JTextField();
        inputPanel.add(precoField);

        JButton addButton = new JButton("Adicionar Sala");
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remover Sala");
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Ação do botão Adicionar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int capacidade = Integer.parseInt(capacidadeField.getText());
                double preco = Double.parseDouble(precoField.getText());
                salaController.adicionarSala(nome, capacidade, preco);
                atualizarTabela();
                nomeField.setText("");
                capacidadeField.setText("");
                precoField.setText("");
            }
        });

        // Ação do botão Remover
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    salaController.removerSala(id);
                    atualizarTabela();
                }
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Sala sala : salaController.listarSalas()) {
            tableModel.addRow(new Object[] { sala.getId(), sala.getNome(), sala.getCapacidade(), sala.getPreco() });
        }
    }
}
