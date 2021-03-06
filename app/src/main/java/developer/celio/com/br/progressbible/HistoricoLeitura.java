package developer.celio.com.br.progressbible;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import developer.celio.com.br.DataAccess.HistoricoDAO;
import developer.celio.com.br.DataAccess.LivroDAO;
import developer.celio.com.br.DomainModel.Historico;
import developer.celio.com.br.DomainModel.Livro;


public class HistoricoLeitura extends Activity {

    private static String STR_NOME;
    private AlertDialog alerta;
    LivroDAO livroDAO = new LivroDAO(this);
    HistoricoDAO historicoDAO = new HistoricoDAO(this);
    Livro livro = new Livro();

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_leitura);

        STR_NOME = this.getIntent().getExtras().getString("Nome");
        TextView txtNomeLivro = (TextView) findViewById(R.id.txtNomeLivroHistoricoLeitura);
        TextView txtCapitulos = (TextView) findViewById(R.id.txtCapitulosHistoricoLeitura);
        EditText edCapLido    = (EditText) findViewById(R.id.edtCapituloLido);

        livro = livroDAO.filtrar(STR_NOME); // Pega  os dados do livro aberto
        Historico hist = new Historico(new Date());

        // Tenta buscar o ultimo historico do livro que foi aberto
        try {
            hist = historicoDAO.buscar(Integer.valueOf(livro.getId().toString()));
        } catch (Exception e) {
        }

        // Seta os componentes da tela
        edCapLido.setHint(String.valueOf(hist.getCapsLidos()));
        txtNomeLivro.setText("Livro de: " + livro.getNome());
        txtCapitulos.setText(livro.getCapitulos() + " Capitulo(s)");

        // Configuração para do botão Voltar na ActionBar
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Método para Salvar o Histórico...............................................................
    public void onClickSalvarHistorico(View view) {

        livro = livroDAO.filtrar(STR_NOME);
        Historico historico = new Historico(new Date());

        EditText edCapLido = (EditText) findViewById(R.id.edtCapituloLido);
        EditText edComentario = (EditText) findViewById(R.id.edtComentario);

        try {
            // Tenta converter o valor inserido para INT
            int cap = Integer.parseInt(edCapLido.getText().toString());

            // Se for um valor válido continua
            if ((cap <= livro.getCapitulos()) && (cap > 0)) {
                try {
                    historico.setCapsLidos(cap);
                    historico.setComentario(edComentario.getText().toString());
                    historico.setLivro(livro);

                    historicoDAO.salvar(historico);

                    if (cap == livro.getCapitulos())
                        livroDAO.atualizar(1, livro.getId());
                    else
                        livroDAO.atualizar(2, livro.getId());

                    exibeMensagem("Confirmação", "Histórico Salvo com Sucesso!", 1);
                } catch (Exception ex) {
                    exibeMensagem("Erro!", ex.getMessage(), 2);
                }
            } else
                exibeMensagem("Erro!", "Valor inserido para 'Capitulos Lidos' é invalido!", 2);
        } catch (Exception e) {
            exibeMensagem("Erro!", "O campo 'Capitulos Lidos' é de preenchimento obrigatório!", 2);
        }
    }

    // Método para exibir mensagem na tela..........................................................
    public void exibeMensagem(String titulo, String msg, final int tipo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(msg);

        if (tipo == 1)
            builder.setIcon(R.drawable.ic_ok);
        else
            builder.setIcon(R.drawable.ic_alerta);

        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (tipo == 1)
                    finish();
            }
        });

        alerta = builder.create();
        alerta.show();
    }

    // Método onCreateOptionsMenu...................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_historico_leitura, menu);
        return true;
    }

    // Método onOptionsItemSelected.................................................................
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se foi pressionado o botão Voltar finaliza a Activity
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
