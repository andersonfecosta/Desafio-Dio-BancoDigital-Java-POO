package entities.contas;

import entities.Agencia;
import entities.Cliente;
import interfaces.IConta;
import lombok.Data;

@Data
public abstract class Conta implements IConta {

    private static int numeroConta = 1;

    protected Agencia agencia;
    protected int numero;
    protected Cliente cliente;
    protected double saldo = 0;

    public Conta(Agencia agencia, Cliente cliente) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.numero = numeroConta;
        numeroConta++;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
        this.saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        this.saldo += valor;
    }

    @Override
    public void transferir(Conta conta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de transferência deve ser positivo.");
        }
        this.sacar(valor);
        conta.depositar(valor);
    }
}
