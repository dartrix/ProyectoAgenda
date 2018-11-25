package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        }
    }
    public void agregarAsig(View v){
        Intent i = new Intent(this, AgregarAsignacionActivity.class);
        i.putExtra("id",idinfo);
        startActivity(i);
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
