package com.covid19project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19project.Models.DonateDrug;
import com.covid19project.Models.DonateRelief;
import com.covid19project.R;

import java.util.ArrayList;
import java.util.List;

public class DonateReliefAdapter extends RecyclerView.Adapter<DonateReliefAdapter.ImageViewHolder>{

    private Context mContext;
    private List<DonateRelief> mDonate_Relief;

    public DonateReliefAdapter(Context context, List<DonateRelief> list) {
        mContext = context;
        mDonate_Relief = list;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_drug_relief, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final DonateRelief donateRelief = mDonate_Relief.get(position);

        holder.District.setText(donateRelief.getDistrict());
        holder.Mobile.setText(donateRelief.getMobile());
    }

    @Override
    public int getItemCount() {
        return mDonate_Relief.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView District, Mobile;
        public ImageViewHolder(View itemView) {
            super(itemView);

            District = itemView.findViewById(R.id.district);
            Mobile = itemView.findViewById(R.id.mobile);
        }
    }

}