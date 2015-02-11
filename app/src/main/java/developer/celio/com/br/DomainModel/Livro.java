package developer.celio.com.br.DomainModel;

/**
 * Created by aricelio on 03/02/15.
 */
public class Livro {

    private Long id;
    private String nome;
    private String tipo;
    private int capitulos;
    private Status status;
    private String informacao;

    // Construtor...................................................................................
    public Livro() {
    }

    // Getters e Setters............................................................................
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    @Override
    public String toString() {
        return nome + "\n" + tipo + "\n";
    }
}
