
import java.util.Scanner;

public class ContaTerminal {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ContaTerminal novaConta = new ContaTerminal();

        System.out.println("Por favor, preencha as informações solicitadas a seguir!");

        System.out.print("Informe o número da conta: ");
        novaConta.numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe o número da agência (xxx-x): ");
        novaConta.agencia = scanner.nextLine();

        System.out.print("Informe o nome do titular da conta: ");
        novaConta.nomeCliente = scanner.nextLine();

        System.out.print("Informe o saldo: ");
        novaConta.saldo = scanner.nextDouble();
        scanner.close();

        System.out.println("\n******************************************");
        System.out.printf(
                "Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %s e seu saldo %.2f já está disponível para saque.\n",
                novaConta.nomeCliente, novaConta.agencia, novaConta.numero, novaConta.saldo);
        System.out.println("******************************************");

    }

    private int numero;
    private String agencia;
    private String nomeCliente;
    private double saldo;

    public ContaTerminal() {
    }
}
