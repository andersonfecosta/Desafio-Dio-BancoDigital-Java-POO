import entities.Banco;
import entities.contas.Conta;
import entities.contas.TipoConta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        apresentar();
        exibirMenuInicial();
    }

    public void apresentar() {
        System.out.println("========================");
        System.out.println("=====SEJA BEM VINDO=====");
        System.out.println("========================");
    }

    public void exibirMenuInicial() {
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Já Sou Cliente");
        System.out.println("[2] Criar Conta");
        System.out.println("[3] Menu Admin");
        System.out.println("[4] Sair");

        int opcao = obterOpcao();
        executarOpcaoMenuInicial(opcao);
    }


    public void executarOpcaoMenuInicial(int opcao) {
        switch (opcao) {
            case 1 -> exibirMenuCliente();
            case 2 -> exibirMenuCriarConta();
            case 3 -> exibirMenuAdmin();
            case 4 -> {
                System.out.println("Saindo do sistema");
                scanner.close();
                System.exit(0);
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public int logInCliente() {
        System.out.println("Digite o numero da sua Identidade: ");
        return obterOpcao();
    }

    public void exibirMenuCliente() {
        System.out.println("[1] Consultar Saldo");
        System.out.println("[2] Sacar");
        System.out.println("[3] Depositar");
        System.out.println("[4] Transferir");
        System.out.println("[5] Sair");

        int opcao = obterOpcao();
        executarOpcaoMenuCliente(opcao);
    }

    public void executarOpcaoMenuCliente (int opcao) {
        int identidadeCliente = logInCliente();
        Conta conta = Banco.getContas().stream()
                .filter(a -> a.getCliente().getIdentidade() == identidadeCliente)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

        switch (opcao) {
            case 1 -> System.out.println("Saldo: " + conta.getSaldo());
            case 2 -> {
                System.out.println("Digite o valor a ser sacado: ");
                conta.sacar(obterValor());
                System.out.println("Valor sacado com sucesso!");
            }
            case 3 -> {
                System.out.println("Digite o valor a ser depositado: ");
                conta.depositar(obterValor());
                System.out.println("Valor depositado com sucesso!");
            }
            case 4 -> {
                System.out.println("Digite a Conta destino: ");
                int numeroContaDestino = scanner.nextInt();
                scanner.nextLine();
                Conta contaDestino = Banco.getContas().stream()
                        .filter(a -> a.getNumero() == numeroContaDestino)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
                System.out.println("Conta Destino");
                System.out.println(contaDestino);
                System.out.println("Valor a transferir: ");
                conta.transferir(contaDestino, obterValor());
                System.out.println("Valor transferido com sucesso para " + contaDestino.getCliente().getNome());
            }
            case 5 -> {
                System.out.println("Saindo do sistema");
                System.exit(0);
            }
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    public void exibirMenuCriarConta() {
        System.out.println("Digite Seu Nome: ");
        String nomeCliente = scanner.nextLine();
        System.out.println("Digite sua Identidade: ");
        int identidadeCliente = obterOpcao();

        System.out.println("Qual agência de sua preferência? escolha abaixo :");
        Banco.listarAgencias();
        int numeroAgencia = obterOpcao();

        System.out.println("Qual tipo de conta deseja abrir?");
        System.out.println("[1] Conta poupança");
        System.out.println("[2] Conta Corrente");
        int opc = scanner.nextInt();

        TipoConta tipoConta;
        switch (opc) {
            case 1 -> tipoConta = TipoConta.POUPANCA;
            case 2 -> tipoConta = TipoConta.CORRENTE;
            default -> {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                return;
            }
        }

        Banco.criarConta(nomeCliente,identidadeCliente, numeroAgencia, tipoConta);
        System.out.println("Conta Criada com sucesso! numero : ");
        System.out.println("Pressione Enter para voltar ao menu inicial...");
        scanner.nextLine();
        exibirMenuInicial();
    }

    public void exibirMenuAdmin() {
        System.out.println("[1] Criar Agência");
        System.out.println("[2] Sair");

        int opcao = obterOpcao();
        executarOpcaoMenuAdmin(opcao);
    }

    public void executarOpcaoMenuAdmin(int opcao) {

        switch (opcao) {
            case 1 -> {
                Banco.criarAgencia();
                System.out.println("Pressione Enter para voltar ao menu inicial...");
                scanner.nextLine();
                exibirMenuInicial();
            }
            case 2 -> {
                System.out.println("Saindo do sistema");
                System.exit(0);
            }
            default -> {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                return;
            }
        }
    }

    public int obterOpcao() {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    public double obterValor() {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                scanner.nextLine();
            }
        }
    }
}



