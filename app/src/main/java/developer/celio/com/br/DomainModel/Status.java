package developer.celio.com.br.DomainModel;

/**
 * Created by aricelio on 03/02/15.
 */
public class Status {

    private long id;
    private String nomeStatus;

    // Construtor...................................................................................
    public Status() {
        this.nomeStatus = "Vou ler";
    }

    // Getters e Setters............................................................................
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }
}
