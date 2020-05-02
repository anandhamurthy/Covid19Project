package com.covid19project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19project.Adapter.ImageSliderAdapter;
import com.covid19project.Models.DonateRelief;
import com.covid19project.Models.Image_Slider;
import com.covid19project.Models.Jsons;
import com.covid19project.Models.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mJsonDatabase;

    private GridView Grid_View;
    private SliderView sliderView;
    private FloatingActionButton Call;
    ImageSliderAdapter imageSliderAdapter;
    private List<Image_Slider> image_sliders = new ArrayList<>();
    private RequestQueue mRequestQueue;
    String[] web = {
            "Corona Status",
            "Home Treatment",
            "Toll Free Numbers",
            "My Health Status",
            "Medical stores",
            "Volunteers Registration",
            "Labs for Test",
            "Counselling",
            "E-Pass",
            "Donate Funds",
            "Donate Relief Material",
            "Application Tracker",
            "FAQs"

    } ;
    int[] imageId = {
            R.drawable.corona,
            R.drawable.home,
            R.drawable.toll_numbers,
            R.drawable.health,
            R.drawable.medical_store,
            R.drawable.volunteer,
            R.drawable.lab,
            R.drawable.counselling,
            R.drawable.pass,
            R.drawable.donate,
            R.drawable.donate_relief,
            R.drawable.tracker,
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
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

        final ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        Dialog.setMessage("Loading...");
        Dialog.setCanceledOnTouchOutside(false);
        Dialog.show();

        mJsonDatabase = FirebaseDatabase.getInstance().getReference().child("Jsons");
        mJsonDatabase.keepSynced(true);

        Grid_View.setAdapter(adapter);

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:044-29510500"));
                startActivity(intent);
            }
        });

        mJsonDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Jsons jsons = dataSnapshot.getValue(Jsons.class);
                mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                parseJSON(jsons.getImage_slider());

                Grid_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        switch (position) {
                            case 0:
                                Intent intent0 = new Intent(MainActivity.this, CoronaActivity.class);
                                startActivity(intent0);
                                break;
                            case 1:
                                Intent intent = new Intent(MainActivity.this, HomeTreamentActivity.class);
                                intent.putExtra("image_url",jsons.getHome_treatment_images());
                                intent.putExtra("link_url",jsons.getHome_treatment_links());
                                startActivity(intent);
                                break;

                            case 2:
                                Intent intent1 = new Intent(MainActivity.this, TollNumbersActivity.class);
                                intent1.putExtra("url",jsons.getToll_numbers());
                                startActivity(intent1);
                                break;

                            case 3:
                                Intent intent2 = new Intent(MainActivity.this, MyHealthStatusActivity.class);
                                startActivity(intent2);
                                break;

                            case 4:
                                Intent intent3 = new Intent(MainActivity.this, MedicalStoresActivity.class);
                                startActivity(intent3);
                                break;

                            case 5:
                                String vol = "https://stopcorona.xenovex.com/login";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(vol));
                                startActivity(i);
                                break;

                            case 6:
                                Intent intent5 = new Intent(MainActivity.this, TestLabsActivity.class);
                                intent5.putExtra("url",jsons.getLab_test());
                                startActivity(intent5);
                                break;

                            case 7:
                                Intent intent6 = new Intent(MainActivity.this, CounsellingActivity.class);
                                startActivity(intent6);
                                break;

                            case 8:
                                String url = "https://serviceonline.gov.in/tamilnadu/directApply.do?serviceId=721";
                                Intent intent7 = new Intent(Intent.ACTION_VIEW);
                                intent7.setData(Uri.parse(url));
                                startActivity(intent7);
                                break;

                            case 9:
                                Intent intent8 = new Intent(MainActivity.this, DonateFundsActivity.class);
                                startActivity(intent8);
                                break;

                            case 10:
                                Intent intent9 = new Intent(MainActivity.this, DonateMaterialsActivity.class);
                                intent9.putExtra("drug_url",jsons.getDonate_drug());
                                intent9.putExtra("relief_url",jsons.getDonate_relief());
                                startActivity(intent9);
                                break;
                            case 11:
                                String tracker = "https://serviceonline.gov.in/tamilnadu/citizenApplication.html";
                                Intent intent10 = new Intent(Intent.ACTION_VIEW);
                                intent10.setData(Uri.parse(tracker));
                                startActivity(intent10);
                                break;
                            case 12:
                                String faq = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/FAQ_COVID-19_25.03.2020.pdf?alt=media&token=ce1a5574-5c16-449d-b094-5f102b84b051";
                                Intent intent11 = new Intent(Intent.ACTION_VIEW);
                                intent11.setData(Uri.parse(faq));
                                startActivity(intent11);
                                break;

                        }

                    }
                });


                Dialog.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void parseJSON(String image_slider) {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/image_slider.json?alt=media&token=d4725d04-e0cc-4945-9551-72db4cf8e848";

        JsonObjectRequest request = new JsonObjectRequest(image_slider, null,
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser mCurrentUser = mAuth.getCurrentUser();
//
//        if (mCurrentUser == null) {
//
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//
//        }
//        else{
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            mUsersDatabase = FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid());
//            mUsersDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Users user = dataSnapshot.getValue(Users.class);
//                    if (user.getVerified().equals("false")) {
//                        Intent setupIntent = new Intent(MainActivity.this, ProfileActivity.class);
//                        setupIntent.putExtra("access","false");
//                        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(setupIntent);
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id){
//            case R.id.profile:
//                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                intent.putExtra("access","true");
//                startActivity(intent);
//                return true;
//            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
//                sendToLogin();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
//
//    private void sendToLogin() {
//        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(loginIntent);
//        finish();
//    }

}
