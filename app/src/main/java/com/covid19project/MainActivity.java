package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;
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
                switch(position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this, HomeTreamentActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, MyHealthStatusActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, MedicalStoresActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, VolunteersRegistrationActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, HospitalAdmissionsActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, TestLabsActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, VocationalTrainingCoursesActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
//                        Intent intent7 = new Intent(MainActivity.this, CounsellingActivity.class);
//                        startActivity(intent7);
//                        break;
                        String url = "http://maps.google.co.uk/maps?q=Pharmacy&hl=en";
                        Intent intent7 = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                        intent7.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                        startActivity(intent7);
                    case 8:
                        Intent intent8 = new Intent(MainActivity.this, EPassActivity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(MainActivity.this, EPassActivity.class);
                        startActivity(intent10);
                        break;
                }

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
