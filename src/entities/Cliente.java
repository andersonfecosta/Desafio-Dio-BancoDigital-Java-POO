package entities;

import lombok.Data;

@Data
public class Cliente {

    private String nome;
    private int identidade;

    public Cliente(int identidade, String nome) {
        this.identidade = identidade;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "\n" +
                ", nome='" + nome +
                "\n" +
                "identidade:'" + identidade;
    }
}
