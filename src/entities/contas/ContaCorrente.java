package entities.contas;

import entities.Agencia;
import entities.Cliente;

public class ContaCorrente extends Conta{

    public ContaCorrente(Agencia agencia, Cliente cliente) {
        super(agencia, cliente);
    }
}
