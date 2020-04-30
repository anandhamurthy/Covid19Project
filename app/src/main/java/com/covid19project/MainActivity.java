package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19project.Adapter.ImageSliderAdapter;
import com.covid19project.Models.Image_Slider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView Grid_View;
    private SliderView sliderView;
    private FloatingActionButton Call;
    ImageSliderAdapter imageSliderAdapter;
    private List<Image_Slider> image_sliders = new ArrayList<>();
    private RequestQueue mRequestQueue;
    String[] web = {
            "Home Treatment",
            "Toll Free Numbers",
            "My Health Status",
            "Medical stores",
            "Volunteers Registration",
            "Hospital Admissions ",
            "Labs for Test",
            "Vocational Training Courses",
            "Counselling",
            "E-Pass",
            "Donate Funds",
            "Donate Relief Material",
            "Support",
            "Doctor’s Appointment",
            "FAQ"

    } ;
    int[] imageId = {
            R.drawable.home,
            R.drawable.toll_numbers,
            R.drawable.health,
            R.drawable.medical_store,
            R.drawable.volunteer,
            R.drawable.hospital,
            R.drawable.lab,
            R.drawable.training,
            R.drawable.counselling,
            R.drawable.pass,
            R.drawable.donate,
            R.drawable.donate_relief,
            R.drawable.support,
            R.drawable.doctor,
            R.drawable.faq

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridAdapter adapter = new GridAdapter(MainActivity.this, web, imageId);
        Grid_View=findViewById(R.id.grid_view);
        Call=findViewById(R.id.call);
        sliderView = findViewById(R.id.image_slider);
        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

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
                        Intent intent1 = new Intent(MainActivity.this, TollNumbersActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, MyHealthStatusActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, MedicalStoresActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, VolunteersRegistrationActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, HospitalAdmissionsActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, TestLabsActivity.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(MainActivity.this, VocationalTrainingCoursesActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(MainActivity.this, CounsellingActivity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(MainActivity.this, EPassActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent11);
                        break;
                    case 12:
                        Intent intent12 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent12);
                        break;
                    case 13:
                        Intent intent13 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14 = new Intent(MainActivity.this, DonateFundsActivity.class);
                        startActivity(intent14);
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

    private void parseJSON() {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/image_slider.json?alt=media&token=d4725d04-e0cc-4945-9551-72db4cf8e848";

        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("main");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String image = hit.getString("image");

                                image_sliders.add(new Image_Slider(image));
                            }

                            imageSliderAdapter = new ImageSliderAdapter(MainActivity.this, image_sliders);
                            sliderView.setSliderAdapter(imageSliderAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
