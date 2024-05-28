import lombok.Getter;
import lombok.Setter;

public class Cliente {

    @Getter
    @Setter
    String nome;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }
}
