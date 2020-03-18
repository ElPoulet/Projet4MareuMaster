package com.example.projet4_mareumaster.vues;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.projet4_mareumaster.DI.DI;
import com.example.projet4_mareumaster.R;
import com.example.projet4_mareumaster.model.Reunion;
import com.example.projet4_mareumaster.model.Salle;
import com.example.projet4_mareumaster.services.ReunionApiService;
import com.example.projet4_mareumaster.services.SalleApiService;
import com.example.projet4_mareumaster.time_picker_fragment.DatePickerFragment;
import com.example.projet4_mareumaster.time_picker_fragment.TimePickerFragment;

import java.util.List;

public class ReunionActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private ReunionApiService mReunionApiService;
    private List<Reunion> mReunions;
    private SalleApiService mSalleApiService;
    private List<Salle> mSalles;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);

        spinner = findViewById(R.id.spinner_room);

        mReunionApiService = DI.getReunionApiService();
        mReunions = mReunionApiService.getReunions();
        mSalleApiService = DI.getSalleApiService();
        mSalles = mSalleApiService.getSalles();

        TextView textDate = findViewById(R.id.text_view_date);
        TextView textViewHour =  findViewById(R.id.content_hour);
        TextView textViewMinute =  findViewById(R.id.content_minutes);

        ArrayAdapter<Salle> adapter = new ArrayAdapter<Salle>(this,R.layout.style_spinner, mSalles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button buttonAddHour = (Button) findViewById(R.id.button_add_hour);
        buttonAddHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time Picker");
            }
        });

        Button btnDate = (Button) findViewById(R.id.btn_add_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date Picker");
            }
        });

        Button buttonAddReunion = findViewById(R.id.button_validate);
        buttonAddReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText sujReunion = findViewById(R.id.Text_name);
                String subject = sujReunion.getText().toString();

                EditText participantsReunion = findViewById(R.id.editText_participants);
                String partiReunions = participantsReunion.getText().toString();

                String date = textDate.getText().toString();

                String heure = textViewHour.getText().toString();
                String min = textViewMinute.getText().toString();

                Salle salle = (Salle) spinner.getSelectedItem();

                Reunion Reunions = new Reunion(subject,date,heure,min,salle,partiReunions);
                mReunionApiService.setReunions(Reunions);

                Intent i = new Intent(ReunionActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textViewHour =  findViewById(R.id.content_hour);
        TextView textViewMinute =  findViewById(R.id.content_minutes);
        textViewHour.setText(""+ hourOfDay);
        textViewMinute.setText(""+ minute);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        TextView textDate = findViewById(R.id.text_view_date);
        String nJour = String.valueOf(day);
        month++;
        String nMois = String.valueOf(month);
        String nYear = String.valueOf(year);
        if(day < 10)
        {
            nJour = "0" + day;
        }
        if(month < 10)
        {
            nMois = "0" + month;
        }
        textDate.setText("" + nJour + " " + nMois + " " + nYear);
    }
}