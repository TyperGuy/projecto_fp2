package mod;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;
    private String bi;
    private String telefone;

    public Cliente(String nome, String sobrenome, String bi, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.bi = bi;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getBi() {
        return bi;
    }

    public String getTelefone() {
        return telefone;
    }
}
