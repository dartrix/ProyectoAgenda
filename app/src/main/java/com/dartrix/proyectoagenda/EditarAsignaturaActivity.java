package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

        String[] items = new String[]{"Tarea", "Exposicion", "Proyecto", "Examen"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        stipo.setAdapter(adapter);

        enombre.setText(as.getNombre());
        stipo.setSelection(tipo);
        ehora.setText(as.getHoralimite());
        fecha.setText(as.getFechalimite());
        edescripcion.setText(as.getDescripcion());



    }

    public void actualizarAsig(View v){

        if (!enombre.getText().toString().equals("") && !ehora.getText().toString().equals("") && !fecha.getText().toString().equals("") && !edescripcion.getText().toString().equals("")){
            asignacion.setNombre(enombre.getText().toString());
            asignacion.setTipo(stipo.getSelectedItem().toString());
            asignacion.setHoralimite(ehora.getText().toString());
            asignacion.setFechalimite(fecha.getText().toString());
            asignacion.setDescripcion(edescripcion.getText().toString());

            sql.editarAsignacion(asignacion);

            Toast.makeText(this,"La asignacion ha sido actualizada", Toast.LENGTH_LONG).show();
            this.finish();
        }else {
            Toast.makeText(this,"Se deben llenar todos los datos", Toast.LENGTH_LONG).show();
        }
    }

}



