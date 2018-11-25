package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MostrarAsignacionActivity extends Activity {

    String id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verasig_layout);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b!=null){
            id = (String) b.get("id");
        }
        DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

        llenarDatos(sql.traerAsignacion(id));
    }

    public void llenarDatos(Asignacion as){

        TextView titulo = (TextView)findViewById(R.id.titulo);
        TextView fechahora = (TextView)findViewById(R.id.fechahora);
        TextView materia = (TextView)findViewById(R.id.materia);
        TextView tipo = (TextView)findViewById(R.id.tipo);
        TextView calificacion = (TextView)findViewById(R.id.calificacion);
        TextView desc = (TextView)findViewById(R.id.descripcion);

        Log.d("dat",as.getNombre());
        titulo.setText(as.getNombre());

        fechahora.setText(as.getFechalimite() + " " + as.getHoralimite());

        DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

        Materia m = sql.traerMateria(Integer.toString(as.getMateriaFK()));

        materia.setText(m.getNombreMateria());

        tipo.setText(as.getTipo());

        if (as.getCalificacion().equals("0")){
            calificacion.setText("Sin calificar");
        }else{
            calificacion.setText(as.getCalificacion());
        }

        Button circulo = (Button)findViewById(R.id.circulo);

        switch (as.getTipo()){
            case "Tarea":
                circulo.setBackgroundTintList(getResources().getColorStateList(R.color.colorTarea));
                break;
            case "Exposicion":
                circulo.setBackgroundTintList(getResources().getColorStateList(R.color.colorExposicion));
                break;
            case "Proyecto":
                circulo.setBackgroundTintList(getResources().getColorStateList(R.color.colorProyecto));
                break;
            case "Examen":
                circulo.setBackgroundTintList(getResources().getColorStateList(R.color.colorExamen));
                break;

        }

        desc.setText(as.getDescripcion());
    }

}
