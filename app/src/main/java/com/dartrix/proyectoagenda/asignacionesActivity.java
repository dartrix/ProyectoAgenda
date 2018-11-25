package com.dartrix.proyectoagenda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class asignacionesActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asigmenu_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        actionbar.setTitle("Asignaciones");


        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.getMenu().getItem(0).setChecked(true);

        mostrarTipoAsig(sql.TraerDatosAsignacion());

        cambiarVista(R.id.nav_todo);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        cambiarVista(menuItem.getItemId());
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();



                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });



    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.asigmenu_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        actionbar.setTitle("Asignaciones");


        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.getMenu().getItem(0).setChecked(true);

        mostrarTipoAsig(sql.TraerDatosAsignacion(false));

        cambiarVista(R.id.nav_todo);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        cambiarVista(menuItem.getItemId());
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();



                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


    }

    public void cambiarVista(int id){
        TextView s =(TextView)findViewById(R.id.txt);

        switch (id){
            case R.id.nav_todo:
                s.setText("Todo");
                mostrarTipoAsig(sql.TraerDatosAsignacion());
                break;

            case R.id.nav_tarea:
                s.setText("Tareas");
                mostrarTipoAsig(sql.TraerDatosAsignacion("Tarea",false));
                break;

            case R.id.nav_expo:
                s.setText("Exposiciones");
                mostrarTipoAsig(sql.TraerDatosAsignacion("Exposicion",false));
                break;

            case R.id.nav_proy:
                s.setText("Proyectos");
                mostrarTipoAsig(sql.TraerDatosAsignacion("Proyecto",false));
                break;

            case R.id.nav_exam:
                s.setText("Examenes");
                mostrarTipoAsig(sql.TraerDatosAsignacion("Examen",false));

                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void abrirMenu(View v){
        mDrawerLayout.openDrawer(GravityCompat.END);
    }

    public void mostrarTipoAsig(ArrayList<Asignacion> as){
        LinearLayout ly = (LinearLayout)findViewById(R.id.asig_layout_items);
        ly.removeAllViews();

        for(int i = 0; i < as.size(); i++){
            agregarAsigLy(as.get(i));
        }

    }

    public void agregarAsigLy(final Asignacion s){
        LinearLayout ly = (LinearLayout)findViewById(R.id.asig_layout_items);
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



}
