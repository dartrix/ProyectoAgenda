package com.dartrix.proyectoagenda;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBproyectoAgenda extends SQLiteOpenHelper {

    public static String TAG = "tag";

    //Tabla Materia

    String consulta ="Create Table Materia (id integer primary key autoincrement, nombreMateria text,  descripcionMateria text, creditoMateria text, profMateria text, color int, acumulado text)";

    //Tabla Asignacion
    String consulaDos = "Create Table Asignacion (id integer primary key autoincrement, materiaFK integer, nombre text, descripcion text, calificacion text, fechalimite text, horalimite text, tipo text, estados text)";

    public DBproyectoAgenda(Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(consulta);
        db.execSQL(consulaDos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Materia");

        db.execSQL("DROP TABLE IF EXISTS Asignacion");
        onCreate(db);

    }

    //Traer todos los datos de la clase Materia
    public ArrayList<Materia> TraerDatosMateria() {
        ArrayList<Materia> arreglosSelect = new ArrayList<>();
        String selectQuery = "SELECT * FROM Materia";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Materia datos = new Materia();

        while(c.moveToNext()){
            datos = new Materia(Integer.toString(c.getInt(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4), c.getInt(5), c.getString(6));
            arreglosSelect.add(datos);
        }
        c.close();
        db.close();
        return arreglosSelect;
    }


    //Traer todos los datos de la clase Asignacion
    public ArrayList<Asignacion> TraerDatosAsignacion() {
        ArrayList<Asignacion> arreglosSelect = new ArrayList<>();
        String selectQuery = "SELECT * FROM Asignacion";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Asignacion datos = new Asignacion();

        while(c.moveToNext()){
            datos = new Asignacion(Integer.toString(c.getInt(0)), c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8));

            arreglosSelect.add(datos);
        }
        c.close();
        db.close();
        return arreglosSelect;
    }
    public ArrayList<Asignacion> TraerDatosAsignacion(boolean estado) {
        String es;
        if (estado){
            es = "completo";
        }
        else{
            es = "incompleto";
        }
        ArrayList<Asignacion> arreglosSelect = new ArrayList<>();
        String selectQuery = "SELECT * FROM Asignacion where estados = '"+es+"'";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Asignacion datos = new Asignacion();

        while(c.moveToNext()){
            datos = new Asignacion(Integer.toString(c.getInt(0)), c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8));
            Log.d("query", datos.getEstados());
            arreglosSelect.add(datos);
        }
        c.close();
        db.close();
        return arreglosSelect;
    }

    public ArrayList<Asignacion> TraerDatosAsignacion(String tipo, boolean estado) {
        String es;
        if (estado){
            es = "completo";
        }
        else{
            es = "incompleto";
        }
        ArrayList<Asignacion> arreglosSelect = new ArrayList<>();
        String selectQuery = "SELECT * FROM Asignacion where tipo = '"+tipo+"' and estados = '"+es+"'";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Asignacion datos = new Asignacion();

        while(c.moveToNext()){
            datos = new Asignacion(Integer.toString(c.getInt(0)), c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8));

            arreglosSelect.add(datos);
        }
        c.close();
        db.close();
        return arreglosSelect;
    }

    //Traer todos los datos de la clase Asignacion
    public ArrayList<Asignacion> TraerDatosAsignacionMateria(String id) {
        ArrayList<Asignacion> arreglosSelect = new ArrayList<>();
        String selectQuery = "SELECT * FROM Asignacion WHERE materiaFK = "+id+" ORDER BY id desc";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Asignacion datos = new Asignacion();

        while(c.moveToNext()){
            datos = new Asignacion(Integer.toString(c.getInt(0)), c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8));

            arreglosSelect.add(datos);
        }
        c.close();
        db.close();
        return arreglosSelect;
    }

    //Read
    public Materia traerMateria(String id){
        Materia m = new Materia();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select * from Materia where id = "+id+" Limit 1",null);

        while(c.moveToNext())   {
            m = new Materia(Integer.toString(c.getInt(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4), c.getInt(5), c.getString(6));
        }

        c.close();
        db.close();

        return m;
    }

    //Read
    public Asignacion traerAsignacion(String id){
        Asignacion m = new Asignacion();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select * from Asignacion where id = "+id+" Limit 1",null);

        while(c.moveToNext()) {
            m = new Asignacion(c.getString(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8));
        }

        c.close();
        db.close();

        return m;
    }



    //INSERT de Materia

    public  void insertarMateria(String nombreMateria, String creditoMateria,String profMateria,String descripcionMateria, int color, String acumulado){

        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "insert into Materia (nombreMateria,creditoMateria,profMateria,descripcionMateria,color,acumulado) values ('"+nombreMateria+"','"+creditoMateria+"','"+profMateria+"','"+descripcionMateria+"','"+color+"','"+acumulado+"')";
            db.execSQL(query);

        } db.close();
    }

    //INSERT de asignaciones
    public  void insertarAsignacion(int  materiaFK, String nombre,String tipo,String fechalimite,String horalimite,String calificacion,String descripcion,String estados ){

        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "insert into Asignacion (materiaFK, nombre, tipo, fechalimite, horalimite, calificacion, descripcion, estados) values ('"+materiaFK+"','"+nombre+"','"+tipo+"','"+fechalimite+"','"+horalimite+"','"+calificacion+"','"+descripcion+"','"+estados+"')";
            db.execSQL(query);

        } db.close();
    }

    //UPDATE de Materia
    public  void editarMateria(Materia m){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Materia SET nombreMateria='"+m.getNombreMateria()+"',creditoMateria='"+m.getCreditoMateria()+"',profMateria='"+m.getProfMateria()+"' ,descripcionMateria='"+m.getDescripcionMateria()+"',acumulado='"+m.getAcumulado()+"' WHERE id='"+m.getId()+"' ";
            db.execSQL(query);

        }db.close();
    }

    public void actualizarAcumuladoMateria(String id){

        int acumulado=0;

        ArrayList<Asignacion> to = TraerDatosAsignacionMateria(id);

        for (int i = 0; i < to.size(); i++){
            acumulado += Integer.parseInt(to.get(i).getCalificacion());
        }

        Log.d("TAG",Integer.toString(acumulado));

        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Materia SET acumulado = '"+acumulado+"' WHERE id='"+id+"' ";
            db.execSQL(query);

        }db.close();



    }

    public  void editarAsignacion(Asignacion as){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Asignacion SET nombre='"+as.getNombre()+"', tipo='"+as.getTipo()+"', fechalimite='"+as.getFechalimite()+"',horalimite='"+as.getHoralimite()+"',calificacion='"+as.getCalificacion()+"',descripcion='"+as.getDescripcion()+"', estados='"+as.getEstados()+"' WHERE id='"+as.getId()+"'";
            db.execSQL(query);

        }db.close();
    }

    public  void editarAsignacionCalificacion(String id,String calificado){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Asignacion SET calificacion = '"+calificado+"', estados = 'completo' where id = "+id;
            db.execSQL(query);

        }db.close();
    }

    //DELETE de Materia
    public void eliminarMateria(String id){
        SQLiteDatabase db = getWritableDatabase();

        if (db != null){
            String query = "DELETE FROM Materia WHERE id='"+id+"'";
            db.execSQL(query);

        }
        db.close();
    }

    public void eliminarAsignacionesMateria(String materiafk){
        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "DELETE FROM Asignacion WHERE materiaFK = "+materiafk+"";
            db.execSQL(query);

        }db.close();
    }

    //DELETE de Asignacion
    public void eliminarAsignacion(String id){
        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "DELETE FROM Asignacion WHERE id="+id;
            db.execSQL(query);

        }db.close();
    }


}
