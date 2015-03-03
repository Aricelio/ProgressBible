package developer.celio.com.br.Presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import developer.celio.com.br.DataAccess.LivroDAO;
import developer.celio.com.br.DomainModel.Livro;


public class LivrosLidos extends Activity {

    private ListView lstLivrosLidos;
    private List<Livro> listaLivrosLidos;

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros_lidos);

        // Ligacao dos componentes de TELA aos atributos da Activity
        lstLivrosLidos = (ListView) findViewById(R.id.lstLivrosLidos);

        // Configuração para o botão voltar do Icone
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Método carregar Lista........................................................................
    public void carregarLista(){
        ArrayAdapter<Livro> adapter;
        int adapterLayout = android.R.layout.simple_list_item_1;

        LivroDAO dao = new LivroDAO(this);
        this.listaLivrosLidos = dao.buscar(1);

        adapter = new ArrayAdapter<Livro>(this, adapterLayout,listaLivrosLidos);

        this.lstLivrosLidos.setAdapter(adapter);
    }

    // Método onResume..............................................................................
    @Override
    protected void onResume() {
        super.onResume();
        this.carregarLista();
    }

    // Método onCreateOptionsMenu ..................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_livros_lidos, menu);
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
