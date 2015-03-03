package developer.celio.com.br.Presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import developer.celio.com.br.DataAccess.HistoricoDAO;
import developer.celio.com.br.DataAccess.LivroDAO;
import developer.celio.com.br.DomainModel.Historico;
import developer.celio.com.br.DomainModel.Livro;


public class ListaHistoricos extends Activity {

    private ListView lstListaHistoricos;
    private ArrayAdapter<Historico> adapter;
    private int adapterLayout = android.R.layout.simple_list_item_1;
    private List<Historico> listaHistorico = new ArrayList<Historico>();
    private HistoricoDAO histDAO = new HistoricoDAO(this);
    private static int ID_LIVRO;
    Livro livro = new Livro();
    LivroDAO livroDAO = new LivroDAO(this);
    private static String STR_NOME;

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historicos);

        lstListaHistoricos = (ListView) findViewById(R.id.lstHistoricoLeitura);

        STR_NOME = this.getIntent().getExtras().getString("Nome");
        livro = livroDAO.filtrar(STR_NOME); // Pega  os dados do livro aberto
        ID_LIVRO = Integer.parseInt(livro.getId().toString());

        // Configuração para o botão voltar do Icone
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Método para Carregar a lista de Históricos do livro que  foi aberto..........................
    private void carregarLista(){
        listaHistorico = histDAO.listar(ID_LIVRO);
        this.adapter = new ArrayAdapter<Historico>(this, adapterLayout, listaHistorico);
        this.lstListaHistoricos.setAdapter(adapter);
    }

    // Método onResume..............................................................................
    @Override
    protected void onResume(){
        super.onResume();
        this.carregarLista();
    }

    // Método onCreateOptionsMenu...................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_historicos, menu);
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
