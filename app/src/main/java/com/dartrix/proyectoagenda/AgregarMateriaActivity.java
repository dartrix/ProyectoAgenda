package com.dartrix.proyectoagenda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AgregarMateriaActivity extends Activity {

    EditText efecha, ehora;
    private int dia, mes, ano, hora, minutos;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarmateria_layout);

        efecha =(EditText)findViewById(R.id.editfecha);
        ehora=(EditText)findViewById(R.id.edithora);


    }

    public void selfecha(View v){
        final Calendar c = Calendar.getInstance();
        dia =c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                efecha.setText(year+"/"+(month+1)+"/"+dayOfMonth);
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
                ehora.setText(hourOfDay+":"+minute);

            }
        }
                ,hora,minutos,false);
        timePickerDialog.show();
    }

}
