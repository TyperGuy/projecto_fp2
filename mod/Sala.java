package mod;

import java.io.Serializable;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private int capacidade;
    private double preco;

    public Sala(int id, String nome, int capacidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPreco() {
        return preco;
    }
}
