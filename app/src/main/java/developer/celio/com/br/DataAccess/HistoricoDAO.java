package developer.celio.com.br.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import developer.celio.com.br.DomainModel.Historico;

/**
 * Created by aricelio on 03/02/15.
 */
public class HistoricoDAO {

    private static final String TABELA = "Historicos";
    private Context context;

    // Constante para Log no Logcat
    private static final String TAG = "HISTORICO_DAO";

    // Construtor...................................................................................
    public HistoricoDAO(Context context){
        this.context = context;
    }

    // Método Salvar................................................................................
    public void salvar(Historico historico) throws Exception{
        try {
            ContentValues valores = new ContentValues();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            valores.put("capLidos", historico.getCapsLidos());
            valores.put("comentario", historico.getComentario());
            valores.put("data", formatter.format(historico.getData()));
            valores.put("idLivro", historico.getLivro().getId());

            BDUtil bdUtil = new BDUtil(context);
            bdUtil.getWritableDatabase().insert(TABELA, null, valores);
            bdUtil.close();

            Log.i(TAG, "Metodo Salvar - Historico do Livro: " + historico.getLivro().getNome() + " Salvo");
        }
        catch(Exception e){
            throw new Exception("Falha ao tentar Salvar!");
        }
    }

    // Método Atualizar.............................................................................
    public Historico buscar(int idLivro) throws Exception{
        Historico historico = new Historico(new Date());
        String sql = "select MAX(id),capLidos  from Historicos where idLivro= " + idLivro;

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql, null);

        try {
            if(cursor.moveToFirst()) {
                historico.setId(cursor.getLong(0));
                historico.setCapsLidos(cursor.getInt(1));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }
        return historico;
    }

    // Método Deletar...............................................................................
    public void deletar(Historico historico){
        String[] args = { historico.getId().toString() };

        BDUtil bdUtil = new BDUtil(context);
        bdUtil.getWritableDatabase().delete(TABELA, "id=?", args);
        bdUtil.close();

        Log.i(TAG, "Metodo Deletar - Historico do livro: " + historico.getLivro().getNome() + " Apagado");
    }



}
