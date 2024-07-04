package view;

import controller.ClienteController;
import mod.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteView extends JFrame {
    private ClienteController clienteController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField;
    private JTextField sobrenomeField;
    private JTextField nifField;
    private JTextField telefoneField;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
        setTitle("Gestão de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Tabela de usuários
        tableModel = new DefaultTableModel(new Object[] { "Nome", "Sobrenome", "Bilhete", "Telefone" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Sobrenome:"));
        sobrenomeField = new JTextField();
        inputPanel.add(sobrenomeField);

        inputPanel.add(new JLabel("Bi:"));
        nifField = new JTextField();
        inputPanel.add(nifField);

        inputPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        inputPanel.add(telefoneField);

        JButton addButton = new JButton("Adicionar cliente");
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remover cliente");
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Ação do botão Adicionar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String sobrenome = sobrenomeField.getText();
                String nif = nifField.getText();
                String telefone = telefoneField.getText();
                clienteController.adicionarCliente(nome, sobrenome, nif, telefone);
                atualizarTabela();

                nomeField.setText("");
                sobrenomeField.setText("");
                nifField.setText("");
                telefoneField.setText("");

            }
        });

        // Ação do botão Remover
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nome = (String) tableModel.getValueAt(selectedRow, 0);
                    clienteController.removerCliente(nome);
                    atualizarTabela();
                }
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Cliente cliente : clienteController.listarClientes()) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getSobrenome(), cliente.getBi(),
                    cliente.getTelefone() });
        }
    }
}
