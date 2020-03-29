package com.example.mareu_oc_projet4.vues;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.mareu_oc_projet4.time_picker_fragment.DatePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FloatingActionButton btnMeeting;
    RecyclerView mRecyclerView;

    public List<Meeting> mMeetingList;
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

        mApiServices = DI.getReunionApiService();
        initList();

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
        switch (item.getItemId()){
            case R.id.tri_date:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date Picker");
                return true;

            case R.id.salle1:
                extraRoom = getString(R.string.room1);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle2:
                extraRoom = getString(R.string.room2);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle3:
                extraRoom = getString(R.string.room3);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle4:
                extraRoom = getString(R.string.room4);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle5:
                extraRoom = getString(R.string.room5);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle6:
                extraRoom = getString(R.string.room6);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle7:
                extraRoom = getString(R.string.room7);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle8:
                extraRoom = getString(R.string.room8);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle9:
                extraRoom = getString(R.string.room9);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle10:
                extraRoom = getString(R.string.room10);
                mAdapter.roomFilter(extraRoom);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.tri_base:
                mAdapter.resetFilter();
                return true;
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

}