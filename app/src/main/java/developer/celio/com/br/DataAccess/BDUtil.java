package developer.celio.com.br.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aricelio on 03/02/15.
 */
public class BDUtil extends SQLiteOpenHelper {

    // Variaveis....................................................................................
    private static final int VERSAO = 1;
    private static final String DATABASE = "PROGRESSBIBLE";
    private static final String TABELA_LIVRO = "Livros";
    private static final String TABELA_HISTORICO = "Historicos";
    private static final String TABELA_STATUS = "Status";

    // Constante para Log no LogCat
    private static final String TAG = "CRIACAO_BANCO_DE_DADOS";

    // Construtor...................................................................................
    public BDUtil(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    // Método onCreate..............................................................................
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Criação das tabelas......................................................
        String ddlStatus = "CREATE TABLE " + TABELA_STATUS + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT " +
                ")";

        String ddlLivro = "CREATE TABLE " + TABELA_LIVRO + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "capitulos INTEGER, " +
                "informacaoLivro TEXT, " +
                "tipo TEXT, " +
                "idStatus INTEGER, " +
                "FOREIGN KEY (idStatus) REFERENCES " + TABELA_STATUS + "(id) " +
                " )";

        String ddlHistorico = "CREATE TABLE " + TABELA_HISTORICO + "( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "capLidos INTEGER, " +
                "comentario TEXT, " +
                "data DATE, " +
                "idLivro INTEGER, " +
                "FOREIGN KEY (idLivro) REFERENCES " + TABELA_LIVRO + "(id) " +
                " )";

        sqLiteDatabase.execSQL(ddlStatus);
        sqLiteDatabase.execSQL(ddlLivro);
        sqLiteDatabase.execSQL(ddlHistorico);

        Log.i(TAG, "Banco de Dados Criado");

        // Inserção dos dados da tabela Status.....................................
        String ddlInsertStatus1 = "INSERT INTO Status (nome) VALUES ('Lido');";
        String ddlInsertStatus2 = "INSERT INTO Status (nome) VALUES ('Lendo');";
        String ddlInsertStatus3 = "INSERT INTO Status (nome) VALUES ('Vou Ler');";

        sqLiteDatabase.execSQL(ddlInsertStatus1);
        sqLiteDatabase.execSQL(ddlInsertStatus2);
        sqLiteDatabase.execSQL(ddlInsertStatus3);
        Log.i(TAG, "Inserção dos dados da Tabela 'STATUS'");

        // Inserção dos dados dos  livros do antigo testamento.....................
        List<String> lista = retornaListaAntigoTestamento();

        for(String str : lista){
            sqLiteDatabase.execSQL(str);
        }

        // Inserção dos dados dos  livros do novo testamento.......................
        lista = retornaListaNovoTestamento();

        for(String str : lista){
            sqLiteDatabase.execSQL(str);
        }

        Log.i(TAG, "Inserção dos Livros na Tabela 'LIVRO'");
    }

    // Método que retorna a lista com as instruções SQL de inserção dos livros do Antigo Testamento.
    private List<String> retornaListaAntigoTestamento(){
        List<String> lista = new ArrayList<String>();

        String ddlInsertGenesis = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Gênesis',50, 'Antigo Testamento', 2);";
        lista.add(ddlInsertGenesis);

        String ddlInsertExodo = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Êxodo',40, 'Antigo Testamento', 3);";
        lista.add(ddlInsertExodo);

        String ddlInsertLevitico = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Levítico',27, 'Antigo Testamento', 3);";
        lista.add(ddlInsertLevitico);

        String ddlInsertNumeros = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Números',36, 'Antigo Testamento', 3);";
        lista.add(ddlInsertNumeros);

        String ddlInsertDeuteronomio = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Deuteronômio',34, 'Antigo Testamento', 3);";
        lista.add(ddlInsertDeuteronomio);

        String ddlInsertJosue = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Josué',24, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJosue);

        String ddlInsertJuizes = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Juízes',21, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJuizes);

        String ddlInsertRute = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Rute',4, 'Antigo Testamento', 3);";
        lista.add(ddlInsertRute);

        String ddlInsert1Samuel = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Samuel',31, 'Antigo Testamento', 3);";
        lista.add(ddlInsert1Samuel);

        String ddlInsert2Samuel = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Samuel',24, 'Antigo Testamento', 3);";
        lista.add(ddlInsert2Samuel);

        String ddlInsert1Reis = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Reis',22, 'Antigo Testamento', 3);";
        lista.add(ddlInsert1Reis);

        String ddlInsert2Reis = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Reis',25, 'Antigo Testamento', 3);";
        lista.add(ddlInsert2Reis);

        String ddlInsert1Cronicas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Crônicas',29, 'Antigo Testamento', 3);";
        lista.add(ddlInsert1Cronicas);

        String ddlInsert2Cronicas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Crônicas',36, 'Antigo Testamento', 3);";
        lista.add(ddlInsert2Cronicas);

        String ddlInsertEsdras = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Esdras',10, 'Antigo Testamento', 3);";
        lista.add(ddlInsertEsdras);

        String ddlInsertNeemias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Neemias',13, 'Antigo Testamento', 3);";
        lista.add(ddlInsertNeemias);

        String ddlInsertEster = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Ester',10, 'Antigo Testamento', 3);";
        lista.add(ddlInsertEster);

        String ddlInsertJo = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Jó',42, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJo);

        String ddlInsertSalmos = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Salmos',150, 'Antigo Testamento', 2);";
        lista.add(ddlInsertSalmos);

        String ddlInsertProverbios = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Provérbios',31, 'Antigo Testamento', 2);";
        lista.add(ddlInsertProverbios);

        String ddlInsertEclesiastes = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Eclesiastes',12, 'Antigo Testamento', 3);";
        lista.add(ddlInsertEclesiastes);

        String ddlInsertCantares = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Cantares de Salomão',8, 'Antigo Testamento', 3);";
        lista.add(ddlInsertCantares);

        String ddlInsertIsaias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Isaías',66, 'Antigo Testamento', 3);";
        lista.add(ddlInsertIsaias);

        String ddlInsertJeremias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Jeremias',52, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJeremias);

        String ddlInsertLamentacoes = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Lamentações de Jeremias',5, 'Antigo Testamento', 3);";
        lista.add(ddlInsertLamentacoes);

        String ddlInsertEzequiel = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Ezequiel',48, 'Antigo Testamento', 3);";
        lista.add(ddlInsertEzequiel);

        String ddlInsertDaniel = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Daniel',12, 'Antigo Testamento', 3);";
        lista.add(ddlInsertDaniel);

        String ddlInsertOseias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Oseias',14, 'Antigo Testamento', 3);";
        lista.add(ddlInsertOseias);

        String ddlInsertJoel = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Joel',3, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJoel);

        String ddlInsertAmos = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Amós',9, 'Antigo Testamento', 3);";
        lista.add(ddlInsertAmos);

        String ddlInsertObadias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Obadias',1, 'Antigo Testamento', 3);";
        lista.add(ddlInsertObadias);

        String ddlInsertJonas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Jonas',4, 'Antigo Testamento', 3);";
        lista.add(ddlInsertJonas);

        String ddlInsertMiqueias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Miqueias',7, 'Antigo Testamento', 3);";
        lista.add(ddlInsertMiqueias);

        String ddlInsertNaum = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Naum',3, 'Antigo Testamento', 3);";
        lista.add(ddlInsertNaum);

        String ddlInsertHabacuque = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Habacuque',3, 'Antigo Testamento', 3);";
        lista.add(ddlInsertHabacuque);

        String ddlInsertSofonias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Sofonias',3, 'Antigo Testamento', 3);";
        lista.add(ddlInsertSofonias);

        String ddlInsertAgeu = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Ageu',2, 'Antigo Testamento', 3);";
        lista.add(ddlInsertAgeu);

        String ddlInsertZacarias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Zacarias',14, 'Antigo Testamento', 3);";
        lista.add(ddlInsertZacarias);

        String ddlInsertMalaquias = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Malaquias',4, 'Antigo Testamento', 3);";
        lista.add(ddlInsertMalaquias);

        return lista;
    }

    // Método que retorna a lista com as instruções SQL de inserção dos livros do Novo Testamento...
    private List<String> retornaListaNovoTestamento(){
        List<String> lista = new ArrayList<String>();

        String ddlInsertMateus = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Mateus',28, 'Novo Testamento', 2);";
        lista.add(ddlInsertMateus);

        String ddlInsertMarcos = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Marcos',16, 'Novo Testamento', 3);";
        lista.add(ddlInsertMarcos);

        String ddlInsertLucas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Lucas',24, 'Novo Testamento', 3);";
        lista.add(ddlInsertLucas);

        String ddlInsertJoao = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('João',21, 'Novo Testamento', 3);";
        lista.add(ddlInsertJoao);

        String ddlInsertAtos = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Atos dos Apóstolos',28, 'Novo Testamento', 2);";
        lista.add(ddlInsertAtos);

        String ddlInsertRomanos = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Romanos',16, 'Novo Testamento', 3);";
        lista.add(ddlInsertRomanos);

        String ddlInsert1Corintios = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Coríntios',16, 'Novo Testamento', 3);";
        lista.add(ddlInsert1Corintios);

        String ddlInsert2Corintios = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Coríntios',13, 'Novo Testamento', 3);";
        lista.add(ddlInsert2Corintios);

        String ddlInsertGalatas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Gálatas',6, 'Novo Testamento', 3);";
        lista.add(ddlInsertGalatas);

        String ddlInsertEfesios = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Efésios',6, 'Novo Testamento', 3);";
        lista.add(ddlInsertEfesios);

        String ddlInsertFilipenses = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Filipenses',4, 'Novo Testamento', 3);";
        lista.add(ddlInsertFilipenses);

        String ddlInsertColossences = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Colossences',4, 'Novo Testamento', 3);";
        lista.add(ddlInsertColossences);

        String ddlInsert1Tessalonicenses = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Tessalonicenses',5, 'Novo Testamento', 3);";
        lista.add(ddlInsert1Tessalonicenses);

        String ddlInsert2Tessalonicenses = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Tessalonicenses',3, 'Novo Testamento', 3);";
        lista.add(ddlInsert2Tessalonicenses);

        String ddlInsert1Timoteo = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Timóteo',6, 'Novo Testamento', 3);";
        lista.add(ddlInsert1Timoteo);

        String ddlInsert2Timoteo = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Timóteo',4, 'Novo Testamento', 3);";
        lista.add(ddlInsert2Timoteo);

        String ddlInsertTito = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Tito',3, 'Novo Testamento', 3);";
        lista.add(ddlInsertTito);

        String ddlInsertFilemon = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Filemon',1, 'Novo Testamento', 3);";
        lista.add(ddlInsertFilemon);

        String ddlInsertHebreus = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Hebreus',13, 'Novo Testamento', 3);";
        lista.add(ddlInsertHebreus);

        String ddlInsertTiago = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Tiago',5, 'Novo Testamento', 3);";
        lista.add(ddlInsertTiago);

        String ddlInsert1Pedro = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 Pedro',5, 'Novo Testamento', 3);";
        lista.add(ddlInsert1Pedro);

        String ddlInsert2Pedro = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 Pedro',3, 'Novo Testamento', 3);";
        lista.add(ddlInsert2Pedro);

        String ddlInsert1Joao = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('1 João',5, 'Novo Testamento', 3);";
        lista.add(ddlInsert1Joao);

        String ddlInsert2Joao = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('2 João',1, 'Novo Testamento', 3);";
        lista.add(ddlInsert2Joao);

        String ddlInsert3Joao = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('3 João',1, 'Novo Testamento', 3);";
        lista.add(ddlInsert3Joao);

        String ddlInsertJudas = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Judas',1, 'Novo Testamento', 3);";
        lista.add(ddlInsertJudas);

        String ddlInsertApocalipse = "INSERT INTO Livros (nome, capitulos, tipo, idStatus) " +
                "VALUES ('Apocalipse',22, 'Novo Testamento', 3);";
        lista.add(ddlInsertApocalipse);

        return lista;
    }


    //Método onUpgrade..............................................................................
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
