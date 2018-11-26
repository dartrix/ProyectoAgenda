package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarMateriaActivity extends Activity {

    EditText nombre, prof, cred, desc;
    String id;
    Materia materia;
    DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editarmateria_layout);
        Intent i = getIntent();
        Bundle b = i.getExtras();

        nombre = (EditText)findViewById(R.id.nombre);
        prof = (EditText)findViewById(R.id.prof);
        cred = (EditText)findViewById(R.id.credito);
        desc = (EditText)findViewById(R.id.descripcion);

        id = (String) b.get("id");


        materia = sql.traerMateria(id);

        llenarDatos(materia);
    }

    public void llenarDatos(Materia m){
        nombre.setText(m.getNombreMateria());
        prof.setText(m.getProfMateria());
        cred.setText(m.getCreditoMateria());
        desc.setText(m.getDescripcionMateria());
    }

    public void actualizarMateria(View v){


        if (!nombre.getText().toString().equals("") && !prof.getText().toString().equals("") && !cred.getText().toString().equals("") && !desc.getText().toString().equals("")){
            materia.setNombreMateria(nombre.getText().toString());
            materia.setProfMateria(prof.getText().toString());
            materia.setCreditoMateria(cred.getText().toString());
            materia.setDescripcionMateria(desc.getText().toString());

            sql.editarMateria(materia);

            Toast.makeText(this,"Materia Actualizada.",Toast.LENGTH_SHORT).show();

            this.finish();

        }else{
            Toast.makeText(this,"Llenar todos los datos.",Toast.LENGTH_LONG).show();
        }
    }
}
