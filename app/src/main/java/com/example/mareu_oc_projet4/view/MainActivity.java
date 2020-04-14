package com.example.mareu_oc_projet4.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.mareu_oc_projet4.DI.DI;
import com.example.mareu_oc_projet4.R;
import com.example.mareu_oc_projet4.event.DeleteMeetingEvent;
import com.example.mareu_oc_projet4.model.Meeting;
import com.example.mareu_oc_projet4.model.Room;
import com.example.mareu_oc_projet4.services.MeetingApiService;
import com.example.mareu_oc_projet4.services.RoomApiService;
import com.example.mareu_oc_projet4.fragments.DatePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FloatingActionButton btnMeeting;
    RecyclerView mRecyclerView;

    public List<Meeting> mMeetingList;
    public static List<Integer> roomsIds;
    public static List<String> roomsNames;

    private MeetingAdapter mAdapter;
    private MeetingApiService mApiServices;
    private RoomApiService mRoomApiService;
    private List<Room> mRooms;
    private String dateCompare;

    public String extraRoom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiServices = DI.getMeetingApiService();
        initList();

        roomsIds = Arrays.asList(R.id.salle1,R.id.salle2,R.id.salle3,R.id.salle4,R.id.salle5,R.id.salle6,R.id.salle7,R.id.salle8,R.id.salle9,R.id.salle10);
        roomsNames = Arrays.asList("Salle 1","Salle 2","Salle 3","Salle 4","Salle 5","Salle 6","Salle 7","Salle 8","Salle 9","Salle 10");

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MeetingAdapter(mMeetingList);
        mRecyclerView.setAdapter(mAdapter);

        mRoomApiService = DI.getSalleApiService();
        mRooms = mRoomApiService.getRooms();

        btnMeeting = findViewById(R.id.btnReunion);

        btnMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initList(){
        mMeetingList = mApiServices.getMeetings();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.tri_date){
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(),"date Picker");
        }
        else if (item.getItemId() == R.id.tri_base)
            mAdapter.resetFilter();
        else if(roomsIds.contains(item.getItemId())){ // Ce test nous assure qu'il s'agit du choix d'une salle
            Log.i("Debug","Index: " + roomsIds.indexOf(item.getItemId()));
            useRoomFilter(roomsIds.indexOf(item.getItemId()));
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String nDay = String.valueOf(day);
        month++;
        String nMonth = String.valueOf(month);
        String nYear = String.valueOf(year);
        if(day < 10)
        {
            nDay = String.format("%02d", day);
        }
        if(month < 10)
        {
            nMonth = String.format("%02d", month);
        }
        dateCompare ="" + nDay + " " + nMonth + " " + nYear;
        mAdapter.dateFilter(dateCompare);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onDeleteReunion(DeleteMeetingEvent event) {
        mApiServices.deleteMeeting(event.meeting);
        initList();
        mRecyclerView.setAdapter(new MeetingAdapter(mMeetingList));
        mAdapter = new MeetingAdapter(mMeetingList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void useRoomFilter(int extraRoom){
        Log.i("Debug","Indice: " + extraRoom);
        mAdapter.roomFilter(extraRoom);
        mAdapter.notifyDataSetChanged();
    }
}