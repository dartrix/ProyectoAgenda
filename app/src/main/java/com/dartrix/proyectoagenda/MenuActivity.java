package com.dartrix.proyectoagenda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    public static String TAG = "menu";
    GridLayout gl;
    FrameLayout agregar;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        agregar = (FrameLayout)findViewById(R.id.agregar);
        gl = (GridLayout)findViewById(R.id.gridlayout);

        mostrarMaterias();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

        agregar = (FrameLayout)findViewById(R.id.agregar);
        gl = (GridLayout)findViewById(R.id.gridlayout);

        mostrarMaterias();
    }

    public void mostrarMaterias(){
        DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

        ArrayList<Materia> materias = sql.TraerDatosMateria();

        //gl.addView(agregar);

        for (int i = 0; i < materias.size(); i++){
            Log.d(TAG,"g");
            agregarMateriaGrid(materias.get(i));
        }
    }

    public void agregarMateriaGrid(final Materia materia){
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.materias_item, null);

        ImageView libro = (ImageView)v.findViewById(R.id.imagenMateria);

        TextView nombre = (TextView)v.findViewById(R.id.nombreMateria);

        nombre.setText(materia.getNombreMateria());

        int color = R.color.colorDanger;
        switch(materia.getColor()){
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

        ;
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(
                GridLayout.UNDEFINED,GridLayout.FILL,1f),
                GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));


        gl.addView(v, params);

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {

                switch(e.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Intent i = new Intent(v.getContext(), MostrarMateriaActivity.class);
                        i.putExtra("id", materia.getId());
                        startActivity(i);
                        break;
                }
                return true;
            }
        });
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
