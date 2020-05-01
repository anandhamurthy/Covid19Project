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
import com.covid19project.R;

import java.util.ArrayList;
import java.util.List;

public class DonateDrugAdapter extends RecyclerView.Adapter<DonateDrugAdapter.ImageViewHolder> {

    private Context mContext;
    private List<DonateDrug> mDonate_Drug;

    public DonateDrugAdapter(Context context, List<DonateDrug> list) {
        mContext = context;
        mDonate_Drug = list;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_drug_donate, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final DonateDrug donateDrug = mDonate_Drug.get(position);

        holder.Ware_House.setText(donateDrug.getCode()+". "+donateDrug.getWare_house());
        holder.Mobile.setText(donateDrug.getMobile());
    }

    @Override
    public int getItemCount() {
        return mDonate_Drug.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView Ware_House, Mobile;
        public ImageViewHolder(View itemView) {
            super(itemView);

            Ware_House = itemView.findViewById(R.id.ware_house);
            Mobile = itemView.findViewById(R.id.mobile);
        }
    }

}