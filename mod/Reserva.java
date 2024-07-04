package mod;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Sala sala;
    private Cliente cliente;
    private Date dataInicio;
    private Date dataFim;

    public Reserva(int id, Sala sala, Cliente cliente, Date dataInicio, Date dataFim) {
        this.id = id;
        this.sala = sala;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
}
