package com.example.mareu_oc_projet4.view;

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

import com.example.mareu_oc_projet4.DI.DI;
import com.example.mareu_oc_projet4.R;
import com.example.mareu_oc_projet4.model.Meeting;
import com.example.mareu_oc_projet4.model.Room;
import com.example.mareu_oc_projet4.services.MeetingApiService;
import com.example.mareu_oc_projet4.services.RoomApiService;
import com.example.mareu_oc_projet4.fragments.DatePickerFragment;
import com.example.mareu_oc_projet4.fragments.TimePickerFragment;

import java.util.List;

public class MeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private MeetingApiService mMeetingApiService;
    private List<Meeting> mMeetings;
    private RoomApiService mRoomApiService;
    private List<Room> mRooms;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);

        spinner = findViewById(R.id.spinner_room);

        mMeetingApiService = DI.getMeetingApiService();
        mMeetings = mMeetingApiService.getMeetings();
        mRoomApiService = DI.getSalleApiService();
        mRooms = mRoomApiService.getRooms();

        TextView textDate = findViewById(R.id.text_view_date);
        TextView textViewHour =  findViewById(R.id.content_hour);
        TextView textViewMinute =  findViewById(R.id.content_minutes);

        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(this,R.layout.style_spinner, mRooms);
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

        Button buttonAddMeeting = findViewById(R.id.button_validate);
        buttonAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText topicMeeting = findViewById(R.id.Text_name);
                String topic = topicMeeting.getText().toString();

                EditText participantMeeting = findViewById(R.id.editText_participants);
                String meetingParticipant = participantMeeting.getText().toString();

                String date = textDate.getText().toString();

                String hour = textViewHour.getText().toString();
                String min = textViewMinute.getText().toString();

                Room room = (Room) spinner.getSelectedItem();

                Meeting meetings = new Meeting(topic,date,hour,min, room,meetingParticipant);
                mMeetingApiService.setMeetings(meetings);

                Intent i = new Intent(MeetingActivity.this, MainActivity.class);
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
            nJour = String.format("%02d", day);
        }
        if(month < 10)
        {
            nMois = String.format("%02d", month);
        }
        textDate.setText("" + nJour + " " + nMois + " " + nYear);
    }
}
