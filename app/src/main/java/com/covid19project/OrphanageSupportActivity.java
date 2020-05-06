package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.covid19project.Adapter.GridAdapter;
import com.covid19project.Adapter.ImageSliderAdapter;
import com.covid19project.Models.Image_Slider;
import com.covid19project.OrphanageSupport.DeliveryActivity;
import com.covid19project.OrphanageSupport.FreeFoodActivity;
import com.covid19project.OrphanageSupport.HomesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class OrphanageSupportActivity extends AppCompatActivity {


    private GridView Grid_View;

    String[] web = {
            "Free Food",
            "Homes & Accommodation",
            "Delivery of Medicine & Grocery"

    } ;
    int[] imageId = {
            R.drawable.food,
            R.drawable.homes,
            R.drawable.delivery

    };

    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphanage_support);

        Back = findViewById(R.id.toolbar_icon);

        Grid_View=findViewById(R.id.grid_view);
        GridAdapter adapter = new GridAdapter(OrphanageSupportActivity.this, web, imageId);
        Grid_View.setAdapter(adapter);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Grid_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(OrphanageSupportActivity.this, FreeFoodActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent = new Intent(OrphanageSupportActivity.this, HomesActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        Intent intent1 = new Intent(OrphanageSupportActivity.this, DeliveryActivity.class);
                        startActivity(intent1);
                        break;

                }

            }
        });


    }
}
