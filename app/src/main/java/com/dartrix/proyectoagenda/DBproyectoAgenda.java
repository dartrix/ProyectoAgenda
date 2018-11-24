package com.dartrix.proyectoagenda;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBproyectoAgenda extends SQLiteOpenHelper {

    public static String TAG = "tag";

    //Tabla Materia
    String consulta ="Create Table Materia (id integer primary key autoincrement, nombreMateria text, fechaMateria text, horaMateria text, descripcionMateria text, creditoMateria text, profMateria text )";

    //Tabla Asignacion
    String consulaDos = "Create Table Asignacion (id integer primary key autoincrement, materiaFK text, nombre text, descripcion text, calificacion text, fechalimite text, horalimite text, tipo text, estados text)";

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

    //INSERT de Materia
    public  void insertarMateria(String nombreMateria,String fechaMateria,String horaMateria,String creditoMateria,String profMateria,String descripcionMateria){

        SQLiteDatabase db = getWritableDatabase();

        if (db != null){

            String query = "insert into Materia (nombreMateria,fechaMateria,horaMateria,creditoMateria,profMateria,descripcionMateria) values ('"+nombreMateria+"','"+fechaMateria+"','"+horaMateria+"','"+creditoMateria+"','"+profMateria+"','"+descripcionMateria+"')";
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
    public  void editarMateria(String id, String nombreMateria,String fechaMateria,String horaMateria,String creditoMateria,String profMateria,String descripcionMateria){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){

            String query = "UPDATE Materia SET nombreMateria='"+nombreMateria+"', fechaMateria='"+fechaMateria+"', horaMateria='"+horaMateria+"',creditoMateria='"+creditoMateria+"',profMateria='"+profMateria+"' ,descripcionMateria='"+descripcionMateria+"' WHERE id='"+id+"' ";
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
