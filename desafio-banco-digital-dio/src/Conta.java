import lombok.Getter;
import lombok.Setter;

public abstract class Conta {

    private static final int AGENCIA_PADRAO = 222;
    private static int SEQUENCIAL = 1000;

    @Getter
    private int agencia;

    @Getter
    private int numero;

    @Getter
    private double saldo = 0.0;

    @Getter
    @Setter
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        if (possuiSaldo(valor)) {
            this.saldo -= valor;
        } else {
            System.out.println("O valor informado excede o saldo disponível!");
        }
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void transferir(double valor, Conta destino) {
        if (possuiSaldo(valor)) {
            this.sacar(valor);
            destino.depositar(valor);
        }else {
            System.out.println("O valor informado excede o saldo disponível!");
        }
    }

    protected void infoDadosConta() {
        System.out.println("~~~ Informações da conta ~~~");
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Conta: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }

    private boolean possuiSaldo(double valor) {
        if (this.saldo < valor) {
            System.out.println("Saldo insuficiente!");
        }
        return this.saldo >= valor;
    }

}
