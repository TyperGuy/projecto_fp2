package controller;

import mod.Sala;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalaController {
    private List<Sala> salas;
    private static final String FILE_NAME = "salas.dat";

    public SalaController() {
        this.salas = new ArrayList<>();
        lerSalasDoArquivo();
    }

    public void adicionarSala(String nome, int capacidade, double preco) {
        int id = salas.size() + 1; // ID simples baseado no tamanho da lista
        Sala sala = new Sala(id, nome, capacidade, preco);
        salas.add(sala);
        salvarSalasNoArquivo();
    }

    public void removerSala(int id) {
        salas.removeIf(sala -> sala.getId() == id);
        salvarSalasNoArquivo();
    }

    public List<Sala> listarSalas() {
        return new ArrayList<>(salas);
    }

    private void salvarSalasNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(salas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void lerSalasDoArquivo() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                salas = (List<Sala>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
