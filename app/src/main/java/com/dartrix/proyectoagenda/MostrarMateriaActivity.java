package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MostrarMateriaActivity extends Activity {

    String idinfo;
    TextView prof, nombre, cred, acumulado, txt, nota;
    ImageView libro;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vermateria_layout);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);


        if(b!=null)
        {
            idinfo =(String) b.get("id");

            Materia mat = sql.traerMateria(idinfo);

            llenarDatos(mat);

            ArrayList<Asignacion> as = sql.TraerDatosAsignacionMateria(idinfo);

            for(int i=0; i < as.size(); i++){
                agregarAsigLy(as.get(i));
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.vermateria_layout);

        DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);


            Materia mat = sql.traerMateria(idinfo);

            llenarDatos(mat);

            ArrayList<Asignacion> as = sql.TraerDatosAsignacionMateria(idinfo);

            for(int i=0; i < as.size(); i++){
                agregarAsigLy(as.get(i));
            }


    }

    public void agregarAsig(View v){
        Intent i = new Intent(this, AgregarAsignacionActivity.class);
        i.putExtra("id",idinfo);
        startActivity(i);
    }

    public void agregarAsigLy(final Asignacion s){
        LinearLayout ly = (LinearLayout)findViewById(R.id.lnrlyt);
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = vi.inflate(R.layout.asig_item_layout, null);
        TextView horafecha = (TextView)v.findViewById(R.id.hora);
        TextView titulo = (TextView)v.findViewById(R.id.titulo);

        ImageButton circulo = (ImageButton)v.findViewById(R.id.circulo);

        switch (s.getTipo()){
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

        titulo.setText(s.getNombre());
        horafecha.setText(s.getFechalimite());

        titulo.setTextColor(Color.rgb(255,255,255));
        horafecha.setTextColor(Color.rgb(255,255,255));

        ly.addView(v);
        v.findViewById(R.id.detalles).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {

                switch(e.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Intent i = new Intent(v.getContext(), MostrarAsignacionActivity.class);
                        i.putExtra("id",s.getId());
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

    }

    public void llenarDatos(Materia mat){
        prof = (TextView)findViewById(R.id.prof);
        cred = (TextView)findViewById(R.id.credito);
        nombre = (TextView)findViewById(R.id.nombreMateria);
        acumulado = (TextView)findViewById(R.id.acumulado);
        txt = (TextView)findViewById(R.id.txt2);
        nota = (TextView)findViewById(R.id.nota);

        libro = (ImageView)findViewById(R.id.imagenMateria);

        prof.setText("Prof. "+mat.getProfMateria());
        nombre.setText(mat.getNombreMateria());
        cred.setText(mat.getCreditoMateria());

        int color = R.color.colorDanger;
        switch(mat.getColor()){
            case 1:
                color= R.color.libro1;
                break;
            case 2:
                color= R.color.libro2;
                break;
            case 3:
                color= R.color.libro3;
                break;
            case 4:
                color= R.color.libro4;
                break;
            case 5:
                color= R.color.libro5;
                break;
            case 6:
                color= R.color.libro6;
                break;
            case 7:
                color= R.color.libro7;
                break;
            case 8:
                color= R.color.libro8;
                break;
            case 9:
                color= R.color.libro9;
                break;
        }

        libro.setColorFilter(ResourcesCompat.getColor(getResources(), color,null));

        acumulado.setText(mat.getAcumulado());

        int ac = Integer.parseInt(mat.getAcumulado());

        if (ac < 70){
            acumulado.setTextColor(Color.rgb(255,0,0));
        }
        if (ac >70 && ac < 80){
            acumulado.setTextColor(Color.rgb(255, 165, 0));
        }
        if (ac > 80){
            acumulado.setTextColor(Color.rgb(0,255,0));
        }

    }
}
