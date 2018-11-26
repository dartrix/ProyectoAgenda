package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MostrarAsignacionActivity extends Activity {

    final Context cnt = this;
    String s;
    Activity currentActivity = this;
    DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verasig_layout);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b!=null){
            s = (String) b.get("id");
        }
        final DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

        llenarDatos(sql.traerAsignacion(s));

        Button button = (Button) findViewById(R.id.calificar);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(cnt);
                dialog.setContentView(R.layout.custom_calificar_dialog);
                Button calificar = (Button) dialog.findViewById(R.id.calificar);
                Button cancelar = (Button) dialog.findViewById(R.id.cancelar);
                // if button is clicked, close the custom dialog
                calificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView cal = (TextView)dialog.findViewById(R.id.calificacion);
                        sql.editarAsignacionCalificacion(s, cal.getText().toString());
                        llenarDatos(sql.traerAsignacion(s));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Asignacion calificada",Toast.LENGTH_SHORT).show();
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.verasig_layout);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b!=null){
            s = (String) b.get("id");
        }
        final DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

        llenarDatos(sql.traerAsignacion(s));

        Button button = (Button) findViewById(R.id.calificar);
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(cnt);
                dialog.setContentView(R.layout.custom_calificar_dialog);
                Button calificar = (Button) dialog.findViewById(R.id.calificar);
                Button cancelar = (Button) dialog.findViewById(R.id.cancelar);
                // if button is clicked, close the custom dialog
                calificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView cal = (TextView)dialog.findViewById(R.id.calificacion);
                        sql.editarAsignacionCalificacion(s, cal.getText().toString());
                        llenarDatos(sql.traerAsignacion(s));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Asignacion calificada",Toast.LENGTH_SHORT).show();
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
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
    public void eliminarAsign(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Agendarium");
        builder1.setMessage("Desea borrar esta materia? (Se borraran todas las asignaciones)");
        builder1.setCancelable(true);


        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        sql.eliminarAsignacion(s);
                        currentActivity.finish();
                        Toast.makeText(currentActivity,"Asignacion borrada.",Toast.LENGTH_LONG).show();

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void abrir (View v){

        Intent i = new Intent(this, EditarAsignaturaActivity.class);
        i.putExtra("id",s);
        startActivity(i);
    }

}
