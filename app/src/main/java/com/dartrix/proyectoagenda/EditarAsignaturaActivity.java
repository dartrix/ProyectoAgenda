package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarAsignaturaActivity extends Activity {

    Spinner stipo;
    EditText enombre, ehora, fecha, edescripcion;
    String id;
    Asignacion asignacion;
    DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editarasignatura_layout);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        enombre = (EditText)findViewById(R.id.nombre);
        stipo = (Spinner)findViewById(R.id.spTipo);
        ehora = (EditText)findViewById(R.id.edithora);
        fecha = (EditText)findViewById(R.id.editfecha);
        edescripcion = (EditText)findViewById(R.id.desc);

        id = (String) b.get("id");

        asignacion = sql.traerAsignacion(id);

        llenarDatos(asignacion);

    }
    public void llenarDatos(Asignacion as){
        int tipo=0;
        switch (as.getTipo()){
            case "Tarea":
                tipo =0;
                break;
            case "Exposicion":
                tipo =1;
                break;
            case "Proyecto":
                tipo =2;
                break;
            case "Examen":
                tipo =3;
                break;
        }

        enombre.setText(as.getNombre());
        stipo.setSelection(tipo);
        ehora.setText(as.getHoralimite());
        fecha.setText(as.getFechalimite());
        edescripcion.setText(as.getDescripcion());

    }

}



