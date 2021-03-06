package developer.celio.com.br.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import developer.celio.com.br.DomainModel.Historico;
import developer.celio.com.br.DomainModel.Livro;

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

    // Método Buscar.......................................................................
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

    // Método Listar................................................................................
    public List<Historico> listar(int idLivro){
        List<Historico> lista = new ArrayList<Historico>();

        Livro livro = new Livro();
        LivroDAO livroDAO = new LivroDAO(context);

        livro = livroDAO.abrir((long)idLivro);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String sql = "Select * from Historicos where idLivro= " + idLivro;

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql, null);

        try{
            while (cursor.moveToNext()){
                Historico historico = new Historico(new Date());
                historico.setId(cursor.getLong(0));
                historico.setCapsLidos(cursor.getInt(1));
                historico.setComentario(cursor.getString(2));
                historico.setData(formatter.parse(cursor.getString(3)));
                historico.setLivro(livro);

                lista.add(historico);
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }

        return lista;
    }
}
