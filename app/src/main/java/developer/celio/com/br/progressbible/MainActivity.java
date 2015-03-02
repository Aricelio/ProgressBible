package developer.celio.com.br.progressbible;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import developer.celio.com.br.DataAccess.HistoricoDAO;
import developer.celio.com.br.DomainModel.Historico;


public class MainActivity extends Activity {

    // Constantes...................................................................................
    // Antigo Testamento
    private final int[] CAP_ANTIGO_TEST = new int[39];
    private final int[] CAP_NOVO_TEST = new int[27];
    private final String COR_VERDE = "#04B404";
    private final String COR_AMARELA = "#FFFF00";
    private final String COR_AZUL = "#013ADF";
    private final String COR_LARANJA = "#FF8000";

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração das Tabs
        TabHost th = (TabHost) findViewById(R.id.tabhost);
        th.setup();

        // Tab Antigo Testamento
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tabAntigoTestamento);
        specs.setIndicator("Antigo Testamento");
        th.addTab(specs);

        // Tab Novo Testamento
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tabNovoTestamento);
        specs.setIndicator("Novo Testamento");
        th.addTab(specs);

        // Instanciação dos valores dos capitulos dos livros do Antigo Testamento
        CAP_ANTIGO_TEST[0] = 50;
        CAP_ANTIGO_TEST[1] = 40;
        CAP_ANTIGO_TEST[2] = 27;
        CAP_ANTIGO_TEST[3] = 36;
        CAP_ANTIGO_TEST[4] = 34;
        CAP_ANTIGO_TEST[5] = 24;
        CAP_ANTIGO_TEST[6] = 21;
        CAP_ANTIGO_TEST[7] = 4;
        CAP_ANTIGO_TEST[8] = 31;
        CAP_ANTIGO_TEST[9] = 24;
        CAP_ANTIGO_TEST[10] = 22;
        CAP_ANTIGO_TEST[11] = 25;
        CAP_ANTIGO_TEST[12] = 29;
        CAP_ANTIGO_TEST[13] = 36;
        CAP_ANTIGO_TEST[14] = 10;
        CAP_ANTIGO_TEST[15] = 13;
        CAP_ANTIGO_TEST[16] = 10;
        CAP_ANTIGO_TEST[17] = 42;
        CAP_ANTIGO_TEST[18] = 150;
        CAP_ANTIGO_TEST[19] = 31;
        CAP_ANTIGO_TEST[20] = 12;
        CAP_ANTIGO_TEST[21] = 8;
        CAP_ANTIGO_TEST[22] = 66;
        CAP_ANTIGO_TEST[23] = 52;
        CAP_ANTIGO_TEST[24] = 5;
        CAP_ANTIGO_TEST[25] = 48;
        CAP_ANTIGO_TEST[26] = 12;
        CAP_ANTIGO_TEST[27] = 14;
        CAP_ANTIGO_TEST[28] = 3;
        CAP_ANTIGO_TEST[29] = 9;
        CAP_ANTIGO_TEST[30] = 1;
        CAP_ANTIGO_TEST[31] = 4;
        CAP_ANTIGO_TEST[32] = 7;
        CAP_ANTIGO_TEST[33] = 3;
        CAP_ANTIGO_TEST[34] = 3;
        CAP_ANTIGO_TEST[35] = 3;
        CAP_ANTIGO_TEST[36] = 2;
        CAP_ANTIGO_TEST[37] = 14;
        CAP_ANTIGO_TEST[38] = 4;

        // Instanciação dos valores dos capitulos dos livros do novo Testamento
        CAP_NOVO_TEST[0] = 28;
        CAP_NOVO_TEST[1] = 16;
        CAP_NOVO_TEST[2] = 24;
        CAP_NOVO_TEST[3] = 21;
        CAP_NOVO_TEST[4] = 28;
        CAP_NOVO_TEST[5] = 16;
        CAP_NOVO_TEST[6] = 16;
        CAP_NOVO_TEST[7] = 13;
        CAP_NOVO_TEST[8] = 6;
        CAP_NOVO_TEST[9] = 6;
        CAP_NOVO_TEST[10] = 4;
        CAP_NOVO_TEST[11] = 4;
        CAP_NOVO_TEST[12] = 5;
        CAP_NOVO_TEST[13] = 3;
        CAP_NOVO_TEST[14] = 6;
        CAP_NOVO_TEST[15] = 4;
        CAP_NOVO_TEST[16] = 3;
        CAP_NOVO_TEST[17] = 1;
        CAP_NOVO_TEST[18] = 13;
        CAP_NOVO_TEST[19] = 5;
        CAP_NOVO_TEST[20] = 5;
        CAP_NOVO_TEST[21] = 3;
        CAP_NOVO_TEST[22] = 5;
        CAP_NOVO_TEST[23] = 1;
        CAP_NOVO_TEST[24] = 1;
        CAP_NOVO_TEST[25] = 1;
        CAP_NOVO_TEST[26] = 22;
    }

    // Método onResume..............................................................................
    @Override
    protected void onResume() {
        try {
            super.onResume();
            this.carregaComponentes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que seta as configurações das barras de progresso.....................................
    public void carregaComponentes() throws Exception {

        // Instanciação  dos TXTs e Barras do Antigo  Testamento....................
        TextView[] txtCapAT = new TextView[39];
        TextView[] txtCapNT = new TextView[27];

        TextView[] txtLivroAT = new TextView[39];
        TextView[] txtLivroNT = new TextView[27];

        ProgressBar[] pbCapAT = new ProgressBar[39];
        ProgressBar[] pbCapNT = new ProgressBar[27];

        txtCapAT[0] = (TextView) findViewById(R.id.txtCap_Genesis);
        pbCapAT[0] = (ProgressBar) findViewById(R.id.pbGenesis);
        txtLivroAT[0] = (TextView) findViewById(R.id.txtGenesis);

        txtCapAT[1] = (TextView) findViewById(R.id.txtCap_Exodo);
        pbCapAT[1] = (ProgressBar) findViewById(R.id.pbExodo);
        txtLivroAT[1] = (TextView) findViewById(R.id.txtExodo);

        txtCapAT[2] = (TextView) findViewById(R.id.txtCap_Levitico);
        pbCapAT[2] = (ProgressBar) findViewById(R.id.pbLevitico);
        txtLivroAT[2] = (TextView) findViewById(R.id.txtLevitico);

        txtCapAT[3] = (TextView) findViewById(R.id.txtCap_Numeros);
        pbCapAT[3] = (ProgressBar) findViewById(R.id.pbNumeros);
        txtLivroAT[3] = (TextView) findViewById(R.id.txtNumeros);

        txtCapAT[4] = (TextView) findViewById(R.id.txtCap_Deuteromonio);
        pbCapAT[4] = (ProgressBar) findViewById(R.id.pbDeuteromonio);
        txtLivroAT[4] = (TextView) findViewById(R.id.txtDeuteromonio);

        txtCapAT[5] = (TextView) findViewById(R.id.txtCap_Josue);
        pbCapAT[5] = (ProgressBar) findViewById(R.id.pbJosue);
        txtLivroAT[5] = (TextView) findViewById(R.id.txtJosue);

        txtCapAT[6] = (TextView) findViewById(R.id.txtCap_Juizes);
        pbCapAT[6] = (ProgressBar) findViewById(R.id.pbJuizes);
        txtLivroAT[6] = (TextView) findViewById(R.id.txtJuizes);

        txtCapAT[7] = (TextView) findViewById(R.id.txtCap_Rute);
        pbCapAT[7] = (ProgressBar) findViewById(R.id.pbRute);
        txtLivroAT[7] = (TextView) findViewById(R.id.txtRute);

        txtCapAT[8] = (TextView) findViewById(R.id.txtCap_1Samuel);
        pbCapAT[8] = (ProgressBar) findViewById(R.id.pb1Samuel);
        txtLivroAT[8] = (TextView) findViewById(R.id.txt1Samuel);

        txtCapAT[9] = (TextView) findViewById(R.id.txtCap_2Samuel);
        pbCapAT[9] = (ProgressBar) findViewById(R.id.pb2Samuel);
        txtLivroAT[9] = (TextView) findViewById(R.id.txt2Samuel);

        txtCapAT[10] = (TextView) findViewById(R.id.txtCap_1Reis);
        pbCapAT[10] = (ProgressBar) findViewById(R.id.pb1Reis);
        txtLivroAT[10] = (TextView) findViewById(R.id.txt1Reis);

        txtCapAT[11] = (TextView) findViewById(R.id.txtCap_2Reis);
        pbCapAT[11] = (ProgressBar) findViewById(R.id.pb2Reis);
        txtLivroAT[11] = (TextView) findViewById(R.id.txt2Reis);

        txtCapAT[12] = (TextView) findViewById(R.id.txtCap_1Cronicas);
        pbCapAT[12] = (ProgressBar) findViewById(R.id.pb1Cronicas);
        txtLivroAT[12] = (TextView) findViewById(R.id.txt1Cronicas);

        txtCapAT[13] = (TextView) findViewById(R.id.txtCap_2Cronicas);
        pbCapAT[13] = (ProgressBar) findViewById(R.id.pb2Cronicas);
        txtLivroAT[13] = (TextView) findViewById(R.id.txt2Cronicas);

        txtCapAT[14] = (TextView) findViewById(R.id.txtCap_Esdras);
        pbCapAT[14] = (ProgressBar) findViewById(R.id.pbEsdras);
        txtLivroAT[14] = (TextView) findViewById(R.id.txtEsdras);

        txtCapAT[15] = (TextView) findViewById(R.id.txtCap_Neemias);
        pbCapAT[15] = (ProgressBar) findViewById(R.id.pbNeemias);
        txtLivroAT[15] = (TextView) findViewById(R.id.txtNeemias);

        txtCapAT[16] = (TextView) findViewById(R.id.txtCap_Ester);
        pbCapAT[16] = (ProgressBar) findViewById(R.id.pbEster);
        txtLivroAT[16] = (TextView) findViewById(R.id.txtEster);

        txtCapAT[17] = (TextView) findViewById(R.id.txtCap_Jo);
        pbCapAT[17] = (ProgressBar) findViewById(R.id.pbJo);
        txtLivroAT[17] = (TextView) findViewById(R.id.txtJo);

        txtCapAT[18] = (TextView) findViewById(R.id.txtCap_Salmos);
        pbCapAT[18] = (ProgressBar) findViewById(R.id.pbSalmos);
        txtLivroAT[18] = (TextView) findViewById(R.id.txtSalmos);

        txtCapAT[19] = (TextView) findViewById(R.id.txtCap_Proverbios);
        pbCapAT[19] = (ProgressBar) findViewById(R.id.pbProverbios);
        txtLivroAT[19] = (TextView) findViewById(R.id.txtProverbios);

        txtCapAT[20] = (TextView) findViewById(R.id.txtCap_Eclesiastes);
        pbCapAT[20] = (ProgressBar) findViewById(R.id.pbEclesiastes);
        txtLivroAT[20] = (TextView) findViewById(R.id.txtEclesiastes);

        txtCapAT[21] = (TextView) findViewById(R.id.txtCap_Cantares);
        pbCapAT[21] = (ProgressBar) findViewById(R.id.pbCantares);
        txtLivroAT[21] = (TextView) findViewById(R.id.txtCantares);

        txtCapAT[22] = (TextView) findViewById(R.id.txtCap_Isaias);
        pbCapAT[22] = (ProgressBar) findViewById(R.id.pbIsaias);
        txtLivroAT[22] = (TextView) findViewById(R.id.txtIsaias);

        txtCapAT[23] = (TextView) findViewById(R.id.txtCap_Jeremias);
        pbCapAT[23] = (ProgressBar) findViewById(R.id.pbJeremias);
        txtLivroAT[23] = (TextView) findViewById(R.id.txtJeremias);

        txtCapAT[24] = (TextView) findViewById(R.id.txtCap_Lamentacoes);
        pbCapAT[24] = (ProgressBar) findViewById(R.id.pbLamentacoes);
        txtLivroAT[24] = (TextView) findViewById(R.id.txtLamentacoes);

        txtCapAT[25] = (TextView) findViewById(R.id.txtCap_Ezequiel);
        pbCapAT[25] = (ProgressBar) findViewById(R.id.pbEzequiel);
        txtLivroAT[25] = (TextView) findViewById(R.id.txtEzequiel);

        txtCapAT[26] = (TextView) findViewById(R.id.txtCap_Daniel);
        pbCapAT[26] = (ProgressBar) findViewById(R.id.pbDaniel);
        txtLivroAT[26] = (TextView) findViewById(R.id.txtDaniel);

        txtCapAT[27] = (TextView) findViewById(R.id.txtCap_Oseias);
        pbCapAT[27] = (ProgressBar) findViewById(R.id.pbOseias);
        txtLivroAT[27] = (TextView) findViewById(R.id.txtOseias);

        txtCapAT[28] = (TextView) findViewById(R.id.txtCap_Joel);
        pbCapAT[28] = (ProgressBar) findViewById(R.id.pbJoel);
        txtLivroAT[28] = (TextView) findViewById(R.id.txtJoel);

        txtCapAT[29] = (TextView) findViewById(R.id.txtCap_Amos);
        pbCapAT[29] = (ProgressBar) findViewById(R.id.pbAmos);
        txtLivroAT[29] = (TextView) findViewById(R.id.txtAmos);

        txtCapAT[30] = (TextView) findViewById(R.id.txtCap_Obadias);
        pbCapAT[30] = (ProgressBar) findViewById(R.id.pbObadias);
        txtLivroAT[30] = (TextView) findViewById(R.id.txtObadias);

        txtCapAT[31] = (TextView) findViewById(R.id.txtCap_Jonas);
        pbCapAT[31] = (ProgressBar) findViewById(R.id.pbJonas);
        txtLivroAT[31] = (TextView) findViewById(R.id.txtJonas);

        txtCapAT[32] = (TextView) findViewById(R.id.txtCap_Miqueias);
        pbCapAT[32] = (ProgressBar) findViewById(R.id.pbMiqueias);
        txtLivroAT[32] = (TextView) findViewById(R.id.txtMiqueias);

        txtCapAT[33] = (TextView) findViewById(R.id.txtCap_Naum);
        pbCapAT[33] = (ProgressBar) findViewById(R.id.pbNaum);
        txtLivroAT[33] = (TextView) findViewById(R.id.txtNaum);

        txtCapAT[34] = (TextView) findViewById(R.id.txtCap_Habacuque);
        pbCapAT[34] = (ProgressBar) findViewById(R.id.pbHabacuque);
        txtLivroAT[34] = (TextView) findViewById(R.id.txtHabacuque);

        txtCapAT[35] = (TextView) findViewById(R.id.txtCap_Sofonias);
        pbCapAT[35] = (ProgressBar) findViewById(R.id.pbSofonias);
        txtLivroAT[35] = (TextView) findViewById(R.id.txtSofonias);

        txtCapAT[36] = (TextView) findViewById(R.id.txtCap_Ageu);
        pbCapAT[36] = (ProgressBar) findViewById(R.id.pbAgeu);
        txtLivroAT[36] = (TextView) findViewById(R.id.txtAgeu);

        txtCapAT[37] = (TextView) findViewById(R.id.txtCap_Zacarias);
        pbCapAT[37] = (ProgressBar) findViewById(R.id.pbZacarias);
        txtLivroAT[37] = (TextView) findViewById(R.id.txtZacarias);

        txtCapAT[38] = (TextView) findViewById(R.id.txtCap_Malaquias);
        pbCapAT[38] = (ProgressBar) findViewById(R.id.pbMalaquias);
        txtLivroAT[38] = (TextView) findViewById(R.id.txtMalaquias);

        // Novo Testamento..........................................................................
        txtCapNT[0] = (TextView) findViewById(R.id.txtCap_Mateus);
        pbCapNT[0] = (ProgressBar) findViewById(R.id.pbMateus);
        txtLivroNT[0] = (TextView) findViewById(R.id.txtMateus);

        txtCapNT[1] = (TextView) findViewById(R.id.txtCap_Marcos);
        pbCapNT[1] = (ProgressBar) findViewById(R.id.pbMarcos);
        txtLivroNT[1] = (TextView) findViewById(R.id.txtMarcos);

        txtCapNT[2] = (TextView) findViewById(R.id.txtCap_Lucas);
        pbCapNT[2] = (ProgressBar) findViewById(R.id.pbLucas);
        txtLivroNT[2] = (TextView) findViewById(R.id.txtLucas);

        txtCapNT[3] = (TextView) findViewById(R.id.txtCap_Joao);
        pbCapNT[3] = (ProgressBar) findViewById(R.id.pbJoao);
        txtLivroNT[3] = (TextView) findViewById(R.id.txtJoao);

        txtCapNT[4] = (TextView) findViewById(R.id.txtCap_Atos);
        pbCapNT[4] = (ProgressBar) findViewById(R.id.pbAtos);
        txtLivroNT[4] = (TextView) findViewById(R.id.txtAtos);

        txtCapNT[5] = (TextView) findViewById(R.id.txtCap_Romanos);
        pbCapNT[5] = (ProgressBar) findViewById(R.id.pbRomanos);
        txtLivroNT[5] = (TextView) findViewById(R.id.txtRomanos);

        txtCapNT[6] = (TextView) findViewById(R.id.txtCap_1Corintios);
        pbCapNT[6] = (ProgressBar) findViewById(R.id.pb1Corintios);
        txtLivroNT[6] = (TextView) findViewById(R.id.txt1Corintios);

        txtCapNT[7] = (TextView) findViewById(R.id.txtCap_2Corintios);
        pbCapNT[7] = (ProgressBar) findViewById(R.id.pb2Corintios);
        txtLivroNT[7] = (TextView) findViewById(R.id.txt2Corintios);

        txtCapNT[8] = (TextView) findViewById(R.id.txtCap_Galatas);
        pbCapNT[8] = (ProgressBar) findViewById(R.id.pbGalatas);
        txtLivroNT[8] = (TextView) findViewById(R.id.txtGalatas);

        txtCapNT[9] = (TextView) findViewById(R.id.txtCap_Efesios);
        pbCapNT[9] = (ProgressBar) findViewById(R.id.pbEfesios);
        txtLivroNT[9] = (TextView) findViewById(R.id.txtEfesios);

        txtCapNT[10] = (TextView) findViewById(R.id.txtCap_Filipenses);
        pbCapNT[10] = (ProgressBar) findViewById(R.id.pbFilipenses);
        txtLivroNT[10] = (TextView) findViewById(R.id.txtFilipenses);

        txtCapNT[11] = (TextView) findViewById(R.id.txtCap_Colossenses);
        pbCapNT[11] = (ProgressBar) findViewById(R.id.pbColossenses);
        txtLivroNT[11] = (TextView) findViewById(R.id.txtColossenses);

        txtCapNT[12] = (TextView) findViewById(R.id.txtCap_1Tessalonicenses);
        pbCapNT[12] = (ProgressBar) findViewById(R.id.pb1Tessalonicenses);
        txtLivroNT[12] = (TextView) findViewById(R.id.txt1Tessalonicenses);

        txtCapNT[13] = (TextView) findViewById(R.id.txtCap_2Tessalonicenses);
        pbCapNT[13] = (ProgressBar) findViewById(R.id.pb2Tessalonicenses);
        txtLivroNT[13] = (TextView) findViewById(R.id.txt2Tessalonicenses);

        txtCapNT[14] = (TextView) findViewById(R.id.txtCap_1Timoteo);
        pbCapNT[14] = (ProgressBar) findViewById(R.id.pb1Timoteo);
        txtLivroNT[14] = (TextView) findViewById(R.id.txt1Timoteo);

        txtCapNT[15] = (TextView) findViewById(R.id.txtCap_2Timoteo);
        pbCapNT[15] = (ProgressBar) findViewById(R.id.pb2Timoteo);
        txtLivroNT[15] = (TextView) findViewById(R.id.txt2Timoteo);

        txtCapNT[16] = (TextView) findViewById(R.id.txtCap_Tito);
        pbCapNT[16] = (ProgressBar) findViewById(R.id.pbTito);
        txtLivroNT[16] = (TextView) findViewById(R.id.txtTito);

        txtCapNT[17] = (TextView) findViewById(R.id.txtCap_Filemom);
        pbCapNT[17] = (ProgressBar) findViewById(R.id.pbFilemom);
        txtLivroNT[18] = (TextView) findViewById(R.id.txtFilemom);

        txtCapNT[18] = (TextView) findViewById(R.id.txtCap_Hebreus);
        pbCapNT[18] = (ProgressBar) findViewById(R.id.pbHebreus);
        txtLivroNT[18] = (TextView) findViewById(R.id.txtHebreus);

        txtCapNT[19] = (TextView) findViewById(R.id.txtCap_Tiago);
        pbCapNT[19] = (ProgressBar) findViewById(R.id.pbTiago);
        txtLivroNT[19] = (TextView) findViewById(R.id.txtTiago);

        txtCapNT[20] = (TextView) findViewById(R.id.txtCap_1Pedro);
        pbCapNT[20] = (ProgressBar) findViewById(R.id.pb1Pedro);
        txtLivroNT[20] = (TextView) findViewById(R.id.txt1Pedro);

        txtCapNT[21] = (TextView) findViewById(R.id.txtCap_2Pedro);
        pbCapNT[21] = (ProgressBar) findViewById(R.id.pb2Pedro);
        txtLivroNT[21] = (TextView) findViewById(R.id.txt2Pedro);

        txtCapNT[22] = (TextView) findViewById(R.id.txtCap_1Joao);
        pbCapNT[22] = (ProgressBar) findViewById(R.id.pb1Joao);
        txtLivroNT[22] = (TextView) findViewById(R.id.txt1Joao);

        txtCapNT[23] = (TextView) findViewById(R.id.txtCap_2Joao);
        pbCapNT[23] = (ProgressBar) findViewById(R.id.pb2Joao);
        txtLivroNT[23] = (TextView) findViewById(R.id.txt2Joao);

        txtCapNT[24] = (TextView) findViewById(R.id.txtCap_3Joao);
        pbCapNT[24] = (ProgressBar) findViewById(R.id.pb3Joao);
        txtLivroNT[24] = (TextView) findViewById(R.id.txt3Joao);

        txtCapNT[25] = (TextView) findViewById(R.id.txtCap_Judas);
        pbCapNT[25] = (ProgressBar) findViewById(R.id.pbJudas);
        txtLivroNT[25] = (TextView) findViewById(R.id.txtJudas);

        txtCapNT[26] = (TextView) findViewById(R.id.txtCap_Apocalipse);
        pbCapNT[26] = (ProgressBar) findViewById(R.id.pbApocalipse);
        txtLivroNT[26] = (TextView) findViewById(R.id.txtApocalipse);


        HistoricoDAO historicoDAO = new HistoricoDAO(this);
        Historico[] historico = new Historico[39];
        String[] strHistorico = new String[39];
        int[] porcentagem = new int[39];


        // Seta os dados dos componentes da TAB Antigo Testamento
        for(int i=0; i < 39; i++){
            historico[i] = historicoDAO.buscar(i+1);


            // Se há Historico para o livro
            if(historico[i].getId() != 0){

                if(historico[i].getCapsLidos() == CAP_ANTIGO_TEST[i])
                    txtLivroAT[i].setTextColor(Color.parseColor(COR_VERDE));
                else{
                    if(historicoDAO.verificaOpcaoRelendo(i+1, CAP_ANTIGO_TEST[i]))
                        txtLivroAT[i].setTextColor(Color.parseColor(COR_LARANJA));
                    else
                        txtLivroAT[i].setTextColor(Color.parseColor(COR_AMARELA));
                }

                // Calcula a porcentagem lida do livro
                porcentagem[i] = (historico[i].getCapsLidos() * 100) / CAP_ANTIGO_TEST[i];

                // Configura a String do Historico
                strHistorico[i] = historico[i].getCapsLidos() + "/" + CAP_ANTIGO_TEST[i]
                        + " - " + porcentagem[i] + " %";


                // Seta o texto e barra do livro
                txtCapAT[i].setText(strHistorico[i]);
                pbCapAT[i].setProgress(historico[i].getCapsLidos());
            }
            // Senão há historico para o livro seu status é "Quero ler"
            else
                txtLivroAT[i].setTextColor(Color.parseColor(COR_AZUL));

        }

        // Seta os dados dos componentes da TAB Novo Testamento
        for(int i=0; i < 27; i++){
            historico[i] = historicoDAO.buscar(i+40);
            if(historico[i].getId() != 0){

                // Calcula a porcentagem lida do livro
                porcentagem[i] = (historico[i].getCapsLidos() * 100) / CAP_NOVO_TEST[i];

                // Configura a String do Historico
                strHistorico[i] = historico[i].getCapsLidos() + "/" + CAP_NOVO_TEST[i]
                        + " - " + porcentagem[i] + " %";

                if(historico[i].getCapsLidos() < CAP_NOVO_TEST[i])
                    txtLivroNT[i].setTextColor(Color.parseColor("#ff009688"));
                else
                    txtLivroNT[i].setTextColor(Color.GREEN);

                // Seta o texto e barra do livro
                txtCapNT[i].setText(strHistorico[i]);
                pbCapNT[i].setProgress(historico[i].getCapsLidos());
            }
        }
    }


    //Método onCreateOptionsMenu....................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    // Método onOptionsItemSelected.................................................................
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Verifica  o item  do menu selecionado
        switch (item.getItemId()){

            //Se foi selecionado o item Sobre
            case R.id.menu_sobre:
                Intent itSobre = new Intent(MainActivity.this, Sobre.class);
                MainActivity.this.startActivity(itSobre);
                return true;

            //Se foi selecionado o item Update
            case R.id.menu_update_leitura:
                Intent itUpdate = new Intent(MainActivity.this, ListaLivros.class);
                MainActivity.this.startActivity(itUpdate);
                return true;

            case R.id.menu_livros_lido:
                Intent intentLidos = new Intent(MainActivity.this, LivrosLidos.class);
                MainActivity.this.startActivity(intentLidos);
                return true;

            case R.id.menu_livros_lendo:
                Intent intentLendo = new Intent(MainActivity.this, LivrosLendo.class);
                MainActivity.this.startActivity(intentLendo);
                return true;

            case R.id.menu_livros_vouler:
                Intent intentVouLer = new Intent(MainActivity.this, LivrosVouLer.class);
                MainActivity.this.startActivity(intentVouLer);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
