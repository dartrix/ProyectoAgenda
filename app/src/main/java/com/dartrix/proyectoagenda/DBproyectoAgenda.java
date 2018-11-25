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
    String consulta ="Create Table Materia (id integer primary key autoincrement, nombreMateria text,  descripcionMateria text, creditoMateria text, profMateria text )";

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
            datos = new Materia(Integer.toString(c.getInt(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4));

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



    //INSERT de Materia
    public  void insertarMateria(String nombreMateria, String creditoMateria,String profMateria,String descripcionMateria){

        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "insert into Materia (nombreMateria,creditoMateria,profMateria,descripcionMateria) values ('"+nombreMateria+"','"+creditoMateria+"','"+profMateria+"','"+descripcionMateria+"')";
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
    public  void editarMateria(String id, String nombreMateria,String creditoMateria,String profMateria,String descripcionMateria){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Materia SET nombreMateria='"+nombreMateria+"',creditoMateria='"+creditoMateria+"',profMateria='"+profMateria+"' ,descripcionMateria='"+descripcionMateria+"' WHERE id='"+id+"' ";
            db.execSQL(query);

        }db.close();
    }

    //UPDATE de Asignacion
    public  void editar(String id , String nombre, String tipo, String fechalimite, String horalimite, String calificacion, String descripcion, String estados){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Asignacion SET nombre='"+nombre+"', tipo='"+tipo+"', fechalimite='"+fechalimite+"',horalimite='"+horalimite+"',calificacion='"+calificacion+"',descripcion='"+descripcion+"', estados='"+estados+"' WHERE id='"+id+"'";
            db.execSQL(query);

        }db.close();
    }

    //DELETE de Materia
    public void eliminarMateria(String id){
        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "DELETE FROM Materia WHERE id='"+id+"'";
            db.execSQL(query);

        }db.close();
    }

    //DELETE de Asignacion
    public void eliminarAsignacion(String id){
        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "DELETE FROM Asignacion WHERE id='"+id+"'";
            db.execSQL(query);

        }db.close();
    }


}
