package com.covid19project.Services;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.covid19project.Models.Alarm;

import java.util.List;
import java.util.concurrent.Executors;

import static android.content.Intent.ACTION_BOOT_COMPLETED;
import static com.covid19project.Services.AlarmReceiver.setReminderAlarms;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    final List<Alarm> alarms = DatabaseHelper.getInstance(context).getAlarms();
                    setReminderAlarms(context, alarms);
                }
            });
        }
    }

}
