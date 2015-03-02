package developer.celio.com.br.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import developer.celio.com.br.DomainModel.Status;

/**
 * Created by aricelio on 03/02/15.
 */
public class StatusDAO {

    private static final String TABELA = "Status";
    private Context context;

    // Constante para Log no Logcat
    private static final String TAG = "STATUS_DAO";

    // Construtor...................................................................................
    public StatusDAO(Context context){
        this.context = context;
    }

    // Método Abrir.................................................................................
    public Status abrir(int id){
        String sql = "select * from Status where id=" + id;
        Status status = new Status();

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql,null);

        try{
            if(cursor.moveToFirst()){
                status.setId(cursor.getLong(0));
                status.setNomeStatus(cursor.getString(1));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }

        return status;
    }

    // Método  Listar...............................................................................
    public List<Status> listar() throws Exception{
        List<Status> lista = new ArrayList<Status>();

        String sql = "select * from Status";

        BDUtil bdUtil = new BDUtil(context);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql,null);

        try{
            while(cursor.moveToNext()){
                Status status = new Status();

                status.setId(cursor.getLong(0));
                status.setNomeStatus(cursor.getString(1));

                lista.add(status);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
            bdUtil.close();
        }

        Log.i(TAG, "Método LISTAR");
        return lista;
    }
}
