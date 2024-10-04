package entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Agencia {

    private int numero;
    private List<Cliente> clientes;

    public Agencia(int numero) {
        this.numero = numero;
        this.clientes = new ArrayList<>();
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public String toString() {
        return
                "Agencia: " + numero +
                "\n" +
                "clientes: " + clientes;
    }
}
