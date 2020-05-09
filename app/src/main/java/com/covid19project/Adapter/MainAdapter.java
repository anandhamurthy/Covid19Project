package com.covid19project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.covid19project.CoronaActivity;
import com.covid19project.CounsellingActivity;
import com.covid19project.DonateFundsActivity;
import com.covid19project.DonateMaterialsActivity;
import com.covid19project.FAQsActivity;
import com.covid19project.HealthCareActivity;
import com.covid19project.HomeTreamentActivity;
import com.covid19project.HospitalAdmissionActivity;
import com.covid19project.MainActivity;
import com.covid19project.MedicalStoresActivity;
import com.covid19project.Models.Corona;
import com.covid19project.Models.Jsons;
import com.covid19project.MyHealthStatusActivity;
import com.covid19project.OnlineDoctorsActivity;
import com.covid19project.OrphanageSupport.FreeFoodActivity;
import com.covid19project.OrphanageSupportActivity;
import com.covid19project.R;
import com.covid19project.TestLabsActivity;
import com.covid19project.TollNumbersActivity;
import com.covid19project.TweetsActivity;
import com.covid19project.VolunteersActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ImageViewHolder> {

    private Context mContext;
    private int[] Image;
    private String[] Title;
    private DatabaseReference mJsonDatabase;

    public MainAdapter(Context context, String[] titles, int[] images) {
        mContext = context;
        Image = images;
        Title = titles;
        mJsonDatabase = FirebaseDatabase.getInstance().getReference().child("Jsons");
        mJsonDatabase.keepSynced(true);
    }


    @NonNull
    @Override
    public MainAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_grid_item, parent, false);
        return new MainAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ImageViewHolder holder, final int position) {

        holder.Title.setText(Title[position]);
        Glide.with(mContext)
                .load(Image[position])
                .into(holder.Image);
        mJsonDatabase = FirebaseDatabase.getInstance().getReference().child("Jsons");
        mJsonDatabase.keepSynced(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJsonDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final Jsons jsons = dataSnapshot.getValue(Jsons.class);
                        switch (holder.getAdapterPosition()) {
                            case 0:
                                Intent intent0 = new Intent(mContext, CoronaActivity.class);
                                intent0.putExtra("url", jsons.getCorona());
                                mContext.startActivity(intent0);
                                break;
                            case 1:
                                Intent selfintent = new Intent(Intent.ACTION_VIEW);
                                selfintent.setData(Uri.parse(jsons.getSelfreport()));
                                mContext.startActivity(selfintent);
                                break;
                            case 2:
                                Intent intent = new Intent(mContext, HomeTreamentActivity.class);
                                intent.putExtra("image_url", jsons.getHome_treatment_images());
                                intent.putExtra("link_url", jsons.getHome_treatment_links());
                                mContext.startActivity(intent);
                                break;

                            case 3:
                                Intent intent1 = new Intent(mContext, TollNumbersActivity.class);
                                intent1.putExtra("url", jsons.getToll_numbers());
                                mContext.startActivity(intent1);
                                break;

                            case 4:
                                Intent intent2 = new Intent(mContext, MyHealthStatusActivity.class);
                                mContext.startActivity(intent2);
                                break;

                            case 5:
                                Intent intent3 = new Intent(mContext, HealthCareActivity.class);
                                mContext.startActivity(intent3);
                                break;

                            case 6:
                                Intent intent4 = new Intent(mContext, MedicalStoresActivity.class);
                                mContext.startActivity(intent4);
                                break;
                            case 7:
                                Intent intent5 = new Intent(mContext, OnlineDoctorsActivity.class);
                                mContext.startActivity(intent5);
                                break;
                            case 8:
                                Intent intent6 = new Intent(mContext, HospitalAdmissionActivity.class);
                                mContext.startActivity(intent6);
                                break;

                            case 9:
                                Intent intent7 = new Intent(mContext, VolunteersActivity.class);
                                mContext.startActivity(intent7);
                                break;
                            case 10:
                                Intent intent10 = new Intent(mContext, FreeFoodActivity.class);
                                mContext.startActivity(intent10);
                                break;

                            case 11:
                                Intent intent8 = new Intent(mContext, TestLabsActivity.class);
                                intent8.putExtra("url", jsons.getLab_test());
                                mContext.startActivity(intent8);
                                break;

                            case 12:
                                Intent intent9 = new Intent(mContext, OrphanageSupportActivity.class);
                                mContext.startActivity(intent9);
                                break;

                            case 13:
                                Intent intent13 = new Intent(Intent.ACTION_VIEW);
                                intent13.setData(Uri.parse(jsons.getEpass()));
                                mContext.startActivity(intent13);
                                break;

                            case 14:
                                Intent intent12 = new Intent(mContext, DonateFundsActivity.class);
                                mContext.startActivity(intent12);
                                break;

                            case 15:
                                Intent intent15 = new Intent(mContext, DonateMaterialsActivity.class);
                                intent15.putExtra("drug_url", jsons.getDonate_drug());
                                intent15.putExtra("relief_url", jsons.getDonate_relief());
                                mContext.startActivity(intent15);
                                break;
                            case 16:
                                Intent intent14 = new Intent(Intent.ACTION_VIEW);
                                intent14.setData(Uri.parse(jsons.getTracker()));
                                mContext.startActivity(intent14);
                                break;
                            case 17:
                                Intent intent17 = new Intent(mContext, CounsellingActivity.class);
                                mContext.startActivity(intent17);
                                break;
                            case 18:
                                Intent intent18 = new Intent(Intent.ACTION_VIEW);
                                intent18.setData(Uri.parse(jsons.getMigrant()));
                                mContext.startActivity(intent18);
                                break;
                            case 19:
                                Intent intent19 = new Intent(mContext, TweetsActivity.class);
                                intent19.putExtra("url", jsons.getTweets());
                                mContext.startActivity(intent19);
                                break;
                            case 20:
                                Intent intent20 = new Intent(Intent.ACTION_VIEW);
                                intent20.setData(Uri.parse(jsons.getVideos()));
                                mContext.startActivity(intent20);
                                break;
                            case 21:
                                Intent intent21 = new Intent(mContext, FAQsActivity.class);
                                intent21.putExtra("url", jsons.getFaq());
                                mContext.startActivity(intent21);
                                break;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return Title.length;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView Title;
        public ImageView Image;
        public ImageViewHolder(View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.label);
            Image = itemView.findViewById(R.id.image);
        }
    }
}