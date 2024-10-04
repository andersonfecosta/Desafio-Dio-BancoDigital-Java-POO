package interfaces;

import entities.contas.Conta;

public interface IConta {

    void sacar(double valor);
    void depositar(double valor);
    void transferir(Conta conta, double valor);
}
