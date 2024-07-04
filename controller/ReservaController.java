package controller;

import mod.Reserva;
import mod.Sala;
import mod.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaController {
    private List<Reserva> reservas;
    private static final String FILE_NAME = "reservas.dat";

    public ReservaController() {
        this.reservas = new ArrayList<>();
        lerReservasDoArquivo();
    }

    public void adicionarReserva(Sala sala, Cliente cliente, Date dataInicio, Date dataFim) {
        int id = reservas.size() + 1; // ID simples baseado no tamanho da lista
        Reserva reserva = new Reserva(id, sala, cliente, dataInicio, dataFim);
        reservas.add(reserva);
        salvarReservasNoArquivo();
    }

    public void cancelarReserva(int id) {
        reservas.removeIf(reserva -> reserva.getId() == id);
        salvarReservasNoArquivo();
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    public boolean isSalaDisponivel(Sala sala, Date dataInicio, Date dataFim) {
        for (Reserva reserva : listarReservas()) {
            if (reserva.getSala().equals(sala)) {
                // Verifica se o período da nova reserva sobrepõe com alguma reserva existente
                if (dataInicio.before(reserva.getDataFim()) && dataFim.after(reserva.getDataInicio())) {
                    return false; // Sala não está disponível
                }
            }
        }
        return true; // Sala está disponível
    }

    private void salvarReservasNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void lerReservasDoArquivo() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reservas = (List<Reserva>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
