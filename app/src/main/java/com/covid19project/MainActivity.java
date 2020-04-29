package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private GridView Grid_View;
    private FloatingActionButton Call;
    String[] web = {
            "Home Treatment",
            "My Health Status",
            "Medical stores",
            "Volunteers Registration",
            "Hospital Admissions ",
            "Labs for Test",
            "Vocational Training Courses",
            "Counselling",
            "E-Pass",
            "Donate Funds",
            "Support",
            "Doctor’s Appointment"

    } ;
    int[] imageId = {
            R.drawable.home,
            R.drawable.health,
            R.drawable.medical_store,
            R.drawable.volunteer,
            R.drawable.hospital,
            R.drawable.lab,
            R.drawable.training,
            R.drawable.counselling,
            R.drawable.pass,
            R.drawable.donate,
            R.drawable.support,
            R.drawable.doctor

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridAdapter adapter = new GridAdapter(MainActivity.this, web, imageId);
        Grid_View=findViewById(R.id.grid_view);
        Call=findViewById(R.id.call);
        Grid_View.setAdapter(adapter);
        Grid_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:044-29510500"));
                startActivity(intent);
            }
        });

    }
}
