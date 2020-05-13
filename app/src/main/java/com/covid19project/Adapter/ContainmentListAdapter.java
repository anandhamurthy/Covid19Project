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

import com.covid19project.Models.ContainmentList;
import com.covid19project.Models.Corona;
import com.covid19project.Models.Persons;
import com.covid19project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContainmentListAdapter extends RecyclerView.Adapter<ContainmentListAdapter.ImageViewHolder> implements Filterable {


    private Context mContext;
    private List<ContainmentList> mContainmentList;
    private List<ContainmentList> mDefaultContainmentList;
    private ContainmentListAdapter.SearchAdapterListener listener;

    public ContainmentListAdapter(Context context, List<ContainmentList> mContainmentList, ContainmentListAdapter.SearchAdapterListener listener) {
        mContext = context;
        this.mContainmentList = mContainmentList;
        this.mDefaultContainmentList = mContainmentList;
        this.listener = listener;
    }

    @Override
    public ContainmentListAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_containment_list, parent, false);
        return new ContainmentListAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContainmentListAdapter.ImageViewHolder holder, final int position) {

        final ContainmentList persons = mContainmentList.get(position);

        holder.District.setText(persons.getDistrict());
        holder.Zone.setText(persons.getContainment_zones());

    }

    @Override
    public int getItemCount() {
        return mContainmentList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    mContainmentList = mDefaultContainmentList;
                } else {
                    List<ContainmentList> filteredList = new ArrayList<>();
                    for (ContainmentList row : mDefaultContainmentList) {

                        if (row.getContainment_zones().toLowerCase().contains(charString) ||
                                row.getDistrict().toLowerCase().contains(charSequence)
                        ) {
                            filteredList.add(row);
                        }
                    }

                    mContainmentList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mContainmentList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mContainmentList = (ArrayList<ContainmentList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView District, Zone;

        public ImageViewHolder(View itemView) {
            super(itemView);

            District = itemView.findViewById(R.id.district);
            Zone = itemView.findViewById(R.id.zone);

        }
    }

    public interface SearchAdapterListener {
        void onSearchSelected(ContainmentList containmentList);
    }
}