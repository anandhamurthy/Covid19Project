package com.covid19project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19project.Models.Test_Labs;
import com.covid19project.Models.Toll_Numbers;
import com.covid19project.R;

import java.util.ArrayList;
import java.util.List;

public class TollNumbersAdapter extends RecyclerView.Adapter<TollNumbersAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Toll_Numbers> mToll_Numbers;

    public TollNumbersAdapter(Context context, List<Toll_Numbers> list) {
        mContext = context;
        mToll_Numbers = list;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_toll_numbers, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final Toll_Numbers test_labs = mToll_Numbers.get(position);

        holder.District.setText(test_labs.getDistrict());
        holder.Emergency_Number.setText("Emergency Number : "+test_labs.getEmergency_number());
        holder.LandLine_Number.setText("LandLine Number : "+test_labs.getLandline_number());
    }

    @Override
    public int getItemCount() {
        return mToll_Numbers.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView District, Emergency_Number, LandLine_Number;
        public ImageViewHolder(View itemView) {
            super(itemView);

            District = itemView.findViewById(R.id.district);
            Emergency_Number = itemView.findViewById(R.id.emergency_number);
            LandLine_Number = itemView.findViewById(R.id.landline);
        }
    }

}