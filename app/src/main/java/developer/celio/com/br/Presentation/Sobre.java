package developer.celio.com.br.Presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Sobre extends Activity {

    // Método onCreate..............................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        // Configuração para do botão Voltar na ActionBar
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Método onCreateOptionsMenu ..................................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sobre, menu);
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
