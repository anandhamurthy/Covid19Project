package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.covid19project.Adapter.AlarmsAdapter;
import com.covid19project.Alarms.AddEditAlarmActivity;
import com.covid19project.Alarms.EmptyRecyclerView;
import com.covid19project.Models.Alarm;
import com.covid19project.Services.AlarmUtils;
import com.covid19project.Services.LoadAlarmsReceiver;
import com.covid19project.Services.LoadAlarmsService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.covid19project.Alarms.AddEditAlarmActivity.ADD_ALARM;
import static com.covid19project.Alarms.AddEditAlarmActivity.buildAddEditAlarmActivityIntent;

public class AlarmActivity extends AppCompatActivity implements LoadAlarmsReceiver.OnAlarmsLoadedListener{

    private EmptyRecyclerView Alarm_List;
    private LoadAlarmsReceiver mReceiver;
    private AlarmsAdapter mAdapter;
    private FloatingActionButton Add_Alarm;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mReceiver = new LoadAlarmsReceiver(this);

        Alarm_List = findViewById(R.id.alarm_list);
        Back=findViewById(R.id.toolbar_icon);
        mAdapter = new AlarmsAdapter();
        Alarm_List.setEmptyView(findViewById(R.id.empty_view));
        Alarm_List.setAdapter(mAdapter);
        Alarm_List.setLayoutManager(new LinearLayoutManager(AlarmActivity.this));
        Add_Alarm = findViewById(R.id.add_alarm);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Add_Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmUtils.checkAlarmPermissions(AlarmActivity.this);
                final Intent i = buildAddEditAlarmActivityIntent(AlarmActivity.this, ADD_ALARM);
                startActivity(i);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        final IntentFilter filter = new IntentFilter(LoadAlarmsService.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(AlarmActivity.this).registerReceiver(mReceiver, filter);
        LoadAlarmsService.launchLoadAlarmsService(AlarmActivity.this);
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(AlarmActivity.this).unregisterReceiver(mReceiver);
    }

    @Override
    public void onAlarmsLoaded(ArrayList<Alarm> alarms) {
        mAdapter.setAlarms(alarms);
    }

}
