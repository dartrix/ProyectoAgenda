package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AgregarAsignacionActivity extends Activity {

    EditText efecha, ehora;
    private int dia, mes, ano, hora, minutos;

    EditText nombre, fecha, horat, desc;
    String idinfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarasig_layout);

        Spinner dropdown = findViewById(R.id.spTipo);
        efecha =(EditText)findViewById(R.id.editfecha);
        ehora=(EditText)findViewById(R.id.edithora);

        String[] items = new String[]{"Tarea", "Exposicion", "Proyecto", "Examen"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            idinfo =(String) b.get("id");

        }
    }

    public void agregarAsignatura(View v){
        nombre = (EditText)findViewById(R.id.nombre);
        fecha = (EditText)findViewById(R.id.editfecha);
        horat = (EditText)findViewById(R.id.edithora);
        desc = (EditText)findViewById(R.id.desc);
        Spinner tipo = findViewById(R.id.spTipo);

        if (!nombre.getText().toString().equals(" ") && !fecha.getText().toString().equals(" ") && !horat.getText().toString().equals(" ") && !desc.getText().toString().equals(" ") && !tipo.getSelectedItem().toString().equals(" ") ){
            DBproyectoAgenda sql = new DBproyectoAgenda(this, "Agendarium", null, 1);
            sql.insertarAsignacion(Integer.parseInt(idinfo),nombre.getText().toString(),tipo.getSelectedItem().toString(),fecha.getText().toString(), horat.getText().toString(),"0",desc.getText().toString(),"incompleto");
            nombre.setText("");
            fecha.setText("");
            horat.setText("");
            desc.setText("");
            tipo.setSelection(0);

            Toast.makeText(this,"Asignatura agregada.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Debes llenar todos los datos", Toast.LENGTH_LONG).show();
        }
    }

    public void selfecha(View v){
        final Calendar c = Calendar.getInstance();
        dia =c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                efecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
                ,ano,mes,dia);
        datePickerDialog.show();

    }

    public void selhora (View v){

        final Calendar c = Calendar.getInstance();
        hora =c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;

                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    hourOfDay = hourOfDay-12;
                    AM_PM = "PM";
                }

                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf("0" + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf("0" + minute):String.valueOf(minute);

                ehora.setText(horaFormateada + ":" + minutoFormateado + " " + AM_PM);
            }
        }, hora, minutos, false);
        timePickerDialog.show();
    }
}
