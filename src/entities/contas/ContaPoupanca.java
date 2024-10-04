package entities.contas;

import entities.Agencia;
import entities.Cliente;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Agencia agencia, Cliente cliente) {
        super(agencia, cliente);
    }
}
