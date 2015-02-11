package developer.celio.com.br.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import developer.celio.com.br.DomainModel.Livro;

/**
 * Created by aricelio on 03/02/15.
 */
public class LivroDAO {

    // Constantes para controlar as versões
    private static final String TABELA = "Livros";
    private Context context;

    // Constante para Log no Logcat
    private static final String TAG = "LIVRO_DAO";

    // Construtor...................................................................................
    public LivroDAO(Context context){
        this.context = context;
    }

    // Método Salvar................................................................................
    public void salvar(Livro livro){
        ContentValues valores = new ContentValues();

        valores.put("nome", livro.getNome());
        valores.put("capitulos", livro.getCapitulos());
        valores.put("informacaoLivro", livro.getInformacao());
        valores.put("tipo", livro.getTipo());
        valores.put("idStatus", livro.getStatus().getId());

        BDUtil bdUtil = new BDUtil(context);
        bdUtil.getWritableDatabase().insert(TABELA, null, valores);
        bdUtil.close();

        Log.i(TAG, "Metodo Salvar - " + livro.getNome() + " Salvo");
    }

    // Método Atualizar.............................................................................
    public void atualizar(int status, Long idLivro){
        ContentValues valores = new ContentValues();

        valores.put("idStatus", status);

        String where = "id = ?";
        String args[] = {idLivro.toString()};

        BDUtil bdUtil = new BDUtil(context);
        bdUtil.getWritableDatabase().update(TABELA, valores, where, args);
        bdUtil.close();

        Log.i(TAG, "Metodo Atualizar");
    }

    // Método Buscar................................................................................
    public List<Livro> buscar(int status){
        List<Livro> lista = new ArrayList<Livro>();

        String sql = "select * from Livros where idStatus=" + status;

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql,null);

        try{
            while(cursor.moveToNext()){
                Livro livro = new Livro();

                livro.setId(cursor.getLong(0));
                livro.setNome(cursor.getString(1));
                livro.setCapitulos(cursor.getInt(2));
                livro.setTipo(cursor.getString(4));

                lista.add(livro);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }

        Log.i(TAG, "Método BUSCAR");
        return lista;
    }

    // Método FILTRAR...............................................................................
    public Livro filtrar(String nome){
        String sql = "select * from Livros where nome like '" + nome + "'";
        Livro livro = new Livro();

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql,null);

        try{
            if(cursor.moveToFirst()){
                livro.setId(cursor.getLong(0));
                livro.setNome(cursor.getString(1));
                livro.setCapitulos(cursor.getInt(2));
                livro.setTipo(cursor.getString(4));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }

        Log.i(TAG, "Método FILTRAR");
        return livro;
    }



}
