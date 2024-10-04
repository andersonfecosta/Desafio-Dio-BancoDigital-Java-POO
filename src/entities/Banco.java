package entities;

import entities.contas.Conta;
import entities.contas.ContaCorrente;
import entities.contas.ContaPoupanca;
import entities.contas.TipoConta;
import lombok.Data;

import java.util.*;

@Data
public class Banco {

    private static Banco instance;
    private static final String NOME_BANCO = "Banco Central";
    private static final String CODIGO_BANCO = "001";
    private static Set<Agencia> agencias = new HashSet<>();
    private static Set<Cliente> clientes = new HashSet<>();
    private static Set<Conta> contas = new HashSet<>();

    private Banco() {}

    public static Banco getInstance() {
        if (instance == null) {
            instance = new Banco();
        }
        return instance;
    }

    public static Set<Conta> getContas() {
        return contas;
    }

    public static void registroCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public static void criarConta(String nomeCliente,int identidadeCliente, int numeroAgencia, TipoConta tipoConta) {
        Conta novaConta;
        Cliente cliente = new Cliente(identidadeCliente, nomeCliente);
        registroCliente(cliente);

        Agencia agencia = agencias.stream()
                .filter(a -> a.getNumero() == numeroAgencia)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada."));

        switch (tipoConta) {
            case CORRENTE -> novaConta = new ContaCorrente(agencia, cliente);
            case POUPANCA -> novaConta = new ContaPoupanca(agencia, cliente);
            default -> throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        contas.add(novaConta);
        agencia.addCliente(cliente);

    }

    public static void criarAgencia() {
        Random random = new Random();
        int numeroAgencia;

        do {
            numeroAgencia = random.nextInt(9000) + 1000; // Gera um número aleatório
        } while (!agencias.add(new Agencia(numeroAgencia)));

        System.out.println("Agência criada com sucesso! Número: " + numeroAgencia);
    }

    public static void listarAgencias() {
        agencias.stream()
                .map(Agencia::getNumero)
                .forEach(System.out::println);
    }
}