package developer.celio.com.br.progressbible;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import java.util.List;


public class ListaLivros extends Activity {

    // Variaveis....................................................................................
    private ListView lstListaLivrosAntigoTestamento;
    private ListView lstListaLivrosNovoTestamento;
    private ArrayAdapter<String> adapterNovo;
    private ArrayAdapter<String> adapterAntigo;
    private int adapterLayout = android.R.layout.simple_list_item_1;

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);

        // Configuração das Tabs
        TabHost th = (TabHost) findViewById(R.id.tabhostListaLivros);
        th.setup();

        // Tab Antigo Testamento
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tabAntigoTestamentoListaLivros);
        specs.setIndicator("Antigo Testamento");
        th.addTab(specs);

        // Tab Novo Testamento
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tabNovoTestamentoListaLivros);
        specs.setIndicator("Novo Testamento");
        th.addTab(specs);

        // Configuração das Listas
        lstListaLivrosAntigoTestamento = (ListView) findViewById(R.id.lstLivrosAntigoTestamento);
        lstListaLivrosNovoTestamento = (ListView) findViewById(R.id.lstLivrosNovoTestamento);

        registerForContextMenu(lstListaLivrosAntigoTestamento);
        registerForContextMenu(lstListaLivrosNovoTestamento);

        // Configuração para o botão voltar do Icone
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Carrega as listas de Livros..................................................................
    public void carregarListas(){
        List<String> listaAntigo = new ArrayList<String>();
        List<String> listaNovo = new ArrayList<String>();

        listaAntigo = preencheAntigoTestamento();
        listaNovo = preencheNovoTestamento();

        this.adapterAntigo = new ArrayAdapter<String>(this, adapterLayout, listaAntigo);
        this.adapterNovo = new ArrayAdapter<String>(this, adapterLayout, listaNovo);

        this.lstListaLivrosAntigoTestamento.setAdapter(adapterAntigo);
        this.lstListaLivrosNovoTestamento.setAdapter(adapterNovo);


        // Método para um clique em um item da lista de livros do Antigo Testamento
        this.lstListaLivrosAntigoTestamento.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaLivros.this, HistoricoLeitura.class);
                String livro = retornaLivro(1,i);
                intent.putExtra("Nome", livro);
                ListaLivros.this.startActivity(intent);
            }
        });

        // Método para um clique em um item da lista de livros do novo Testamento
        this.lstListaLivrosNovoTestamento.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaLivros.this, HistoricoLeitura.class);
                String livro = retornaLivro(2,i);
                intent.putExtra("Nome", livro);
                ListaLivros.this.startActivity(intent);
            }
        });
    }

    // Método onResume..............................................................................
    @Override
    protected void onResume(){
        super.onResume();
        this.carregarListas();
    }

    // Método que retorna  o nome do Livro com base em sua posição..................................
    private String retornaLivro(int testamento, int posicaoLivro){
        List<String> lista = new ArrayList<String>();
        String str;

        // Se '1' procura no Antigo  Testamento
        if(testamento == 1){
            lista = preencheAntigoTestamento();
            str = lista.get(posicaoLivro);
            return str;
        }

        // Se '2' procura no Novo Testamento
        else{
            lista = preencheNovoTestamento();
            str = lista.get(posicaoLivro);
            return str;
        }
    }

    // Método que prenche a lista de livros do Antigo  Testamento...................................
    private List<String> preencheAntigoTestamento(){
        List<String> lista = new ArrayList<String>();
        lista.add("Gênesis");
        lista.add("Êxodo");
        lista.add("Levítico");
        lista.add("Números");
        lista.add("Deuteronômio");
        lista.add("Josué");
        lista.add("Juízes");
        lista.add("Rute");
        lista.add("1 Samuel");
        lista.add("2 Samuel");
        lista.add("1 Reis");
        lista.add("2 Reis");
        lista.add("1 Crônicas");
        lista.add("2 Crônicas");
        lista.add("Esdras");
        lista.add("Neemias");
        lista.add("Ester");
        lista.add("Jó");
        lista.add("Salmos");
        lista.add("Provérbios");
        lista.add("Eclesiastes");
        lista.add("Cantares de Salomão");
        lista.add("Isaías");
        lista.add("Jeremias");
        lista.add("Lamentações de Jeremias");
        lista.add("Ezequiel");
        lista.add("Daniel");
        lista.add("Oseias");
        lista.add("Joel");
        lista.add("Amós");
        lista.add("Obadias");
        lista.add("Jonas");
        lista.add("Miqueias");
        lista.add("Naum");
        lista.add("Habacuque");
        lista.add("Sofonias");
        lista.add("Ageu");
        lista.add("Zacarias");
        lista.add("Malaquias");

        return lista;
    }

    // Método que prenche a lista de livros do Novo  Testamento...................................
    private List<String> preencheNovoTestamento(){
        List<String> lista = new ArrayList<String>();
        lista.add("Mateus");
        lista.add("Marcos");
        lista.add("Lucas");
        lista.add("João");
        lista.add("Atos dos Apóstolos");
        lista.add("Romanos");
        lista.add("1 Coríntios");
        lista.add("2 Coríntios");
        lista.add("Gálatas");
        lista.add("Efésios");
        lista.add("Filipenses");
        lista.add("Colossenses");
        lista.add("1 Tessalonicenses");
        lista.add("2 Tessalonicenses");
        lista.add("1 Timóteo");
        lista.add("2 Timóteo");
        lista.add("Tito");
        lista.add("Filemon");
        lista.add("Hebreus");
        lista.add("Tiago");
        lista.add("1 Pedro");
        lista.add("2 Pedro");
        lista.add("1 João");
        lista.add("2 João");
        lista.add("3 João");
        lista.add("Judas");
        lista.add("Apocalipse");

        return lista;
    }
    //Método onCreateOptionsMenu....................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_livros, menu);
        return true;
    }

    //Método onCreateOptionsMenu....................................................................
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se foi pressionado o botão Voltar finaliza a Activity
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
