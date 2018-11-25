package com.dartrix.proyectoagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void agregarMateria(View v){
        Intent i = new Intent(this, AgregarMateriaActivity.class);
        startActivity(i);
    }

    public void mostarAsignaciones(View v){
        Intent i = new Intent(this, asignacionesActivity.class);
        startActivity(i);
    }

}
