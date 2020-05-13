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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19project.Models.ContainmentList;
import com.covid19project.Models.HealthCareList;
import com.covid19project.Models.Persons;
import com.covid19project.R;
import com.covid19project.ShowMapActivity;

import java.util.ArrayList;
import java.util.List;

public class HealthCareListAdapter extends RecyclerView.Adapter<HealthCareListAdapter.ImageViewHolder> implements Filterable {


    private Context mContext;
    private List<HealthCareList> mHealthCareList;
    private List<HealthCareList> mDefaultHealthCareList;
    private HealthCareListAdapter.SearchAdapterListener listener;

    public HealthCareListAdapter(Context context, List<HealthCareList> mHealthCareList, HealthCareListAdapter.SearchAdapterListener listener) {
        mContext = context;
        this.mHealthCareList = mHealthCareList;
        this.mDefaultHealthCareList = mHealthCareList;
        this.listener = listener;
    }

    @Override
    public HealthCareListAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_health_care_list, parent, false);
        return new HealthCareListAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HealthCareListAdapter.ImageViewHolder holder, final int position) {

        final HealthCareList persons = mHealthCareList.get(position);

        holder.District.setText(persons.getDistrict());
        holder.Hospital.setText(persons.getHospital());

        holder.Type.setText(persons.getType());

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
                        intent.putExtra("place", persons.getHospital()+", "+persons.getDistrict());
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
        return mHealthCareList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    mHealthCareList = mDefaultHealthCareList;
                } else {
                    List<HealthCareList> filteredList = new ArrayList<>();
                    for (HealthCareList row : mDefaultHealthCareList) {

                        if (row.getDistrict().toLowerCase().contains(charString) ||
                                row.getHospital().toLowerCase().contains(charSequence) ||
                                row.getType().toLowerCase().contains(charSequence)
                        ) {
                            filteredList.add(row);
                        }
                    }

                    mHealthCareList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mHealthCareList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mHealthCareList = (ArrayList<HealthCareList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView District, Hospital, Type;

        public ImageViewHolder(View itemView) {
            super(itemView);

            District = itemView.findViewById(R.id.district);
            Hospital = itemView.findViewById(R.id.hospital);
            Type = itemView.findViewById(R.id.type);

        }
    }

    public interface SearchAdapterListener {
        void onSearchSelected(HealthCareList healthCareList);
    }
}