package padula.delaiglesia.dam.isi.frsf.lab05parte1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TareaDao {

    private Context contexto;
    private TareaDBHelper dbHelper;

    public TareaDao(Context c){
        this.contexto= c;
        dbHelper = new TareaDBHelper(c);
    }

    @JavascriptInterface
    public void crearTarea(String nombre,String detalle){
        // Obtener la base de datos en modo escritura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // crear los valores para la tarea
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", detalle);
        // Insertar la tarea
        long newRowId = db.insert("TAREA", null, values);
    }

    @JavascriptInterface
    public String listaJson(){
        JSONArray arregloJSON = new JSONArray();
        // obtener la base de datos para lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _id,nombre,descripcion FROM TAREA",null);

        // procesar el cursor y por cada fila del cursor crear un objeto JSONObject q se agrega al arregloJSON
        while(c.moveToNext()) {
            JSONObject fila = new JSONObject();
            try {
                fila.put("id", c.getInt(c.getColumnIndex("_id")));
                fila.put("nombre", c.getString(c.getColumnIndex("nombre")));
                fila.put("descripcion", c.getString(c.getColumnIndex("descripcion")));
                arregloJSON.put(fila);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return  arregloJSON.toString();
    }
}
