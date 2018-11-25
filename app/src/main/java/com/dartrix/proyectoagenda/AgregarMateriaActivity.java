package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class AgregarMateriaActivity extends Activity {

    EditText nombre, credito, prof, desc;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarmateria_layout);



    }
    public void notify (View v){
        Intent M = new Intent(v.getContext(), Notificacion.class);
        startActivity(M);
    }

    public void guardarMateria(View v){
        nombre = (EditText)findViewById(R.id.nombre);
        credito = (EditText)findViewById(R.id.credito);
        prof = (EditText)findViewById(R.id.prof);
        desc = (EditText)findViewById(R.id.descripcion);

        int min = 1;
        int max = 9;

        Random r = new Random();
        int color = r.nextInt(max - min + 1) + min;

        if (!nombre.getText().toString().equals("") || !credito.getText().toString().equals("") || !prof.getText().toString().equals("") || !desc.getText().toString().equals("") ){
            DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

            sql.insertarMateria(nombre.getText().toString() ,credito.getText().toString() ,prof.getText().toString() ,desc.getText().toString(), color, "0");

            nombre.setText("");
            credito.setText("");
            prof.setText("");
            desc.setText("");

            Toast.makeText(this,"Materia Registrada", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this,"Se deben llenar los datos", Toast.LENGTH_LONG).show();
        }



    }


}
