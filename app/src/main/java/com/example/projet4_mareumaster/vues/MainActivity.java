package com.example.projet4_mareumaster.vues;

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

import com.example.projet4_mareumaster.DI.DI;
import com.example.projet4_mareumaster.R;
import com.example.projet4_mareumaster.event.DeleteReunionEvent;
import com.example.projet4_mareumaster.model.Reunion;
import com.example.projet4_mareumaster.model.Salle;
import com.example.projet4_mareumaster.services.ReunionApiService;
import com.example.projet4_mareumaster.services.SalleApiService;
import com.example.projet4_mareumaster.time_picker_fragment.DatePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FloatingActionButton btnReunion;
    RecyclerView mRecyclerView;

    public List<Reunion> mReunionList;
    private ReunionAdapter mAdapter;
    private ReunionApiService mApiServices;
    private SalleApiService mSalleApiService;
    private List<Salle> mSalles;
    private String dateCompare;

    public String extraSalle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiServices = DI.getReunionApiService();
        initList();

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ReunionAdapter(mReunionList);
        mRecyclerView.setAdapter(mAdapter);

        mSalleApiService = DI.getSalleApiService();
        mSalles = mSalleApiService.getSalles();

        btnReunion = findViewById(R.id.btnReunion);

        btnReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReunionActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initList(){
        mReunionList = mApiServices.getReunions();
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
                extraSalle ="Salle 1";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle2:
                extraSalle ="Salle 2";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle3:
                extraSalle ="Salle 3";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle4:
                extraSalle ="Salle 4";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle5:
                extraSalle ="Salle 5";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle6:
                extraSalle ="Salle 6";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle7:
                extraSalle ="Salle 7";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle8:
                extraSalle ="Salle 8";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle9:
                extraSalle ="Salle 9";
                mAdapter.salleFilter(extraSalle);
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.salle10:
                extraSalle ="Salle 10";
                mAdapter.salleFilter(extraSalle);
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
        dateCompare ="" + nJour + " " + nMois + " " + nYear;
        mAdapter.dateFilter(dateCompare);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mApiServices.deleteReunion(event.reunion);
        initList();
        mRecyclerView.setAdapter(new ReunionAdapter(mReunionList));
        mAdapter = new ReunionAdapter(mReunionList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}