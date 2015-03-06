package developer.celio.com.br.Presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import java.util.List;

import developer.celio.com.br.DataAccess.HistoricoDAO;
import developer.celio.com.br.DataAccess.LivroDAO;
import developer.celio.com.br.DomainModel.Historico;
import developer.celio.com.br.DomainModel.Livro;


public class ListaLivros extends Activity {

    // Variaveis....................................................................................
    private AlertDialog alerta;
    private ListView lstListaLivrosAntigoTestamento;
    private ListView lstListaLivrosNovoTestamento;
    private ArrayAdapter<String> adapterNovo;
    private ArrayAdapter<String> adapterAntigo;
    private int adapterLayout = android.R.layout.simple_list_item_1;
    private static int ID_LISTVIEW;

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
        specs.setIndicator(getString(R.string.tabAntigoTestamento));
        th.addTab(specs);

        // Tab Novo Testamento
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tabNovoTestamentoListaLivros);
        specs.setIndicator(getString(R.string.tabNovoTestamento));
        th.addTab(specs);

        // Configuração das Listas
        lstListaLivrosAntigoTestamento = (ListView) findViewById(R.id.lstLivrosAntigoTestamento);
        lstListaLivrosNovoTestamento = (ListView) findViewById(R.id.lstLivrosNovoTestamento);

        // Informa que as ListViews tem Menu de Contexto
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
//        this.lstListaLivrosAntigoTestamento.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ListaLivros.this, HistoricoLeitura.class);
//                String livro = retornaLivro(1,i);
//                intent.putExtra("Nome", livro);
//                ListaLivros.this.startActivity(intent);
//            }
//        });
//
//        // Método para um clique em um item da lista de livros do novo Testamento
//        this.lstListaLivrosNovoTestamento.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ListaLivros.this, HistoricoLeitura.class);
//                String livro = retornaLivro(2,i);
//                intent.putExtra("Nome", livro);
//                ListaLivros.this.startActivity(intent);
//            }
//        });
    }

    // Método onResume..............................................................................
    @Override
    protected void onResume(){
        super.onResume();
        this.carregarListas();
    }

    // Método para o evento de click longo em um item da lista......................................
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.lstLivrosAntigoTestamento){
            ID_LISTVIEW = 1;
        } else if(v.getId() == R.id.lstLivrosNovoTestamento){
            ID_LISTVIEW = 2;
        }

        menu.setHeaderIcon(R.drawable.ic_sobre);
        menu.setHeaderTitle("Selecione uma Opção: ");
        menu.add(0, v.getId(), 0, "Atualizar Leitura"); // groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Histórico de Leitura");
    }

    // Método para configuração da opção selecionada nas opções da lista............................
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Livro livro = new Livro();
        List<Historico> listHistorico = new ArrayList<Historico>();
        LivroDAO livroDAO = new LivroDAO(this);
        HistoricoDAO histDAO = new HistoricoDAO(this);

        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        long selectId = menuinfo.id;
        String strLivro = "";

        if(ID_LISTVIEW == 1)
            strLivro = retornaLivro(1, (int) selectId);
        else if(ID_LISTVIEW == 2)
            strLivro = retornaLivro(2, (int) selectId);

        livro = livroDAO.filtrar(strLivro);
        try {
            listHistorico = histDAO.listar(Integer.parseInt(livro.getId().toString()));
        } catch(Exception e){
        }

        // Se escolher a opção ATUALIZAR..........................................
        if (item.getTitle().equals("Atualizar Leitura")) {
            Intent intent = new Intent(ListaLivros.this, HistoricoLeitura.class);
            intent.putExtra("Nome", strLivro);
            ListaLivros.this.startActivity(intent);
        }
        // Se escolher a opção HISTORICO..........................................
        else if (item.getTitle().equals("Histórico de Leitura")) {
            if(listHistorico.isEmpty()){
                exibeMensagem("Alerta!", "Não há histórico para o Livro de " + livro.getNome(), 2);
            }
            else{
                Intent intentHistoricos = new Intent(ListaLivros.this, ListaHistoricos.class);
                intentHistoricos.putExtra("Nome", strLivro);
                ListaLivros.this.startActivity(intentHistoricos);
            }
        } else {
            return false;
        }
        return true;
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
