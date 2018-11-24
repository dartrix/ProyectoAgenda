package com.dartrix.proyectoagenda;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class asignacionesActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

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
                break;

            case R.id.nav_tarea:
                s.setText("Tareas");
                break;

            case R.id.nav_expo:
                s.setText("Exposiciones");
                break;

            case R.id.nav_proy:
                s.setText("Proyectos");
                break;

            case R.id.nav_exam:
                s.setText("Examenes");
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



}
