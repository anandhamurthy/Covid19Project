package com.covid19project.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.covid19project.Models.Test_Labs;
import com.covid19project.R;
import com.covid19project.Services.AlarmReceiver;
import com.covid19project.Services.DatabaseHelper;
import com.covid19project.Services.LoadAlarmsService;
import com.covid19project.ShowMapActivity;

import java.util.ArrayList;
import java.util.List;

public class TestLabsAdapter extends RecyclerView.Adapter<TestLabsAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Test_Labs> mTest_Labs;

    public TestLabsAdapter(Context context, List<Test_Labs> list) {
        mContext = context;
        mTest_Labs = list;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_lab_test, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final Test_Labs test_labs = mTest_Labs.get(position);

        holder.Lab_Name.setText(test_labs.getName());
        holder.Location.setText(test_labs.getLocation());
        holder.Type.setText(test_labs.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder =
                        new AlertDialog.Builder(mContext, R.style.DeleteAlarmDialogTheme);
                builder.setTitle("See Location");
                builder.setMessage("Do you want to know location ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(mContext, ShowMapActivity.class);
                        intent.putExtra("place", test_labs.getLocation());
                        mContext.startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTest_Labs.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView Lab_Name, Location, Type;
        public ImageViewHolder(View itemView) {
            super(itemView);

            Lab_Name = itemView.findViewById(R.id.lab_name);
            Location = itemView.findViewById(R.id.location);
            Type = itemView.findViewById(R.id.type);
        }
    }

}