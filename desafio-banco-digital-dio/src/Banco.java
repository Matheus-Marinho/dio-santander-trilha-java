import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Banco {
    @Getter
    private String nome = "DioBank";

    private List<Conta> contas = new ArrayList<Conta>();

    @SneakyThrows
    public void menuBanco() {
        Thread.sleep(3000);
        Scanner scanner = new Scanner(System.in);
        Optional<Conta> contaOptional = null;

        System.out.println("*********************************");
        System.out.println("Bem vindo ao Banco " + this.nome);
        System.out.println("*********************************\n");
        System.out.println("Escolha a operação que deseja realizar");
        System.out.println("1 - Abrir conta");
        System.out.println("2 - Consultar saldo");
        System.out.println("3 - Efetuar saque");
        System.out.println("4 - Efetuar depósito");
        System.out.println("5 - Efetuar transferência");
        System.out.println("0 - Finalizar atendimento");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("\n\n~ Opção selecionada: %d\n", opcao);
        switch (opcao) {
            case 1:     // Abrir conta
                Cliente novoCliente = new Cliente();
                System.out.println("*** Abrir conta ***\n");
                System.out.print("Informe o nome do titular da conta: ");
                novoCliente.nome = scanner.nextLine();

                System.out.println("Informe o tipo de conta:");
                System.out.println("1 - Poupança");
                System.out.println("2 - Corrente");
                int tipoConta = scanner.nextInt();

                this.abrirConta(novoCliente, tipoConta);

                this.menuBanco();
                break;
            case 2:     // Consultar saldo
                System.out.println("*** Consultar saldo ***");
                contaOptional = this.obterContaPorCliente(scanner, false);

                contaOptional.get().infoDadosConta();

                this.menuBanco();
                break;
            case 3:     // Efetuar saque
                System.out.println("*** Efetuar saque ***");
                contaOptional = this.obterContaPorCliente(scanner, false);

                System.out.print("Informe o valor que deseja sacar: ");
                Double valorSaque = scanner.nextDouble();

                contaOptional.get().sacar(valorSaque);
                System.out.println("Saque realizado com sucesso!");

                this.menuBanco();
                break;
            case 4:     // Efetuar deposito
                System.out.println("*** Efetuar depósito ***");
                contaOptional = this.obterContaPorCliente(scanner, false);

                System.out.print("Informe o valor que deseja depositar: ");
                Double valorDeposito = scanner.nextDouble();

                contaOptional.get().depositar(valorDeposito);
                System.out.println("Deposito realizado com sucesso!");

                this.menuBanco();
                break;
            case 5:     // Efetuar transferência
                System.out.println("*** Efetuar transferência ***");
                contaOptional = this.obterContaPorCliente(scanner, false);

                System.out.println("\nInforme os dados da conta de destino");
                Optional<Conta> contaDestino = null;
                while (true) {
                    contaDestino = this.obterContaPorCliente(scanner, true);
                    if (contaDestino.isPresent()) {
                        break;
                    }
                    System.out.println("Conta não identificada! Favor informe novamente os dados!");
                }

                System.out.print("Informe o valor que deseja transferir: ");
                Double valorTrasnferencia = scanner.nextDouble();
                scanner.nextLine();

                contaOptional.get().transferir(valorTrasnferencia, contaDestino.get());

                System.out.println("Transferencia realizada com sucesso!");
                this.menuBanco();

                break;
            case 0:     // Finalizar atendimento
                System.out.println("Agradecemos a preferência!");
                System.out.println("Atendimento encerrado!");
                break;
            default:
                System.out.println("Opção inválida!");
                this.menuBanco();
                break;
        }

        scanner.close();
    }

    private void validarConta(Optional<Conta> acCliente) {
        if (!acCliente.isPresent()) {
            System.out.println("Conta não identificada!");
            this.menuBanco();
        }
    }

    @SneakyThrows
    private void abrirConta(Cliente cliente, int tipoConta) {
        Thread.sleep(1500);
        switch (tipoConta) {
            case 1:
                Conta novaCC = new ContaPoupanca(cliente);
                System.out.println("\n\nConta Poupança criada com sucesso!");
                novaCC.infoDadosConta();
                contas.add(novaCC);
                break;
            case 2:
                Conta novaPoupanca = new ContaCorrente(cliente);
                System.out.println("Conta Corrente criada com sucesso!");
                novaPoupanca.infoDadosConta();
                contas.add(novaPoupanca);
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    private Optional<Conta> obterContaPorCliente(Scanner scanner, boolean ehTrasnferencia) {
        System.out.print("Informe o nome do titular da conta: ");
        String titular = scanner.nextLine();
        System.out.print("Informe o nome do número da conta: ");
        int numConta = scanner.nextInt();
        scanner.nextLine();

        Optional<Conta> conta = this.contas.stream()
                .filter(acc -> acc.getCliente().getNome().equals(titular) &&
                        acc.getNumero() == numConta)
                .findFirst();

        if (!ehTrasnferencia) this.validarConta(conta);

        return conta;
    }

}
