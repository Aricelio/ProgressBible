package developer.celio.com.br.DomainModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aricelio on 03/02/15.
 */
public class Historico {

    private Long id;
    private int capsLidos;
    private String comentario;
    private Date data;
    private Livro livro;

    // Construtor...................................................................................
    public Historico(Date data) {
        this.data = new Date();
    }

    // Getters e Setters............................................................................
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapsLidos() {
        return capsLidos;
    }

    public void setCapsLidos(int capsLidos) {
        this.capsLidos = capsLidos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return mostraHistorico();
    }

    private String mostraHistorico() {
        String str = "";
        String strData;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        strData = formatter.format(data);
        int percent = (capsLidos * 100) / livro.getCapitulos();

        return ". " + strData + " - " + percent + "% (" + capsLidos + " de " + livro.getCapitulos() + ")\n\n    " + comentario + "\n";
    }
}
