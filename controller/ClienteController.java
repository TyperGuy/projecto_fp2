package controller;

import mod.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;
    private static final String FILE_NAME = "clientes.dat";

    public ClienteController() {
        this.clientes = new ArrayList<>();
        lerClientesDoArquivo();
    }

    public void adicionarCliente(String nome, String sobrenome, String bi, String telefone) {
        Cliente cliente = new Cliente(nome, sobrenome, bi, telefone);
        clientes.add(cliente);
        salvarClientesNoArquivo();
    }

    public void removerCliente(String nome) {
        clientes.removeIf(cliente -> cliente.getNome().equals(nome));
        salvarClientesNoArquivo();
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    private void salvarClientesNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void lerClientesDoArquivo() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                clientes = (List<Cliente>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
