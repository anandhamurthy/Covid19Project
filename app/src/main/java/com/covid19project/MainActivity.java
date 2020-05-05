package com.covid19project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.covid19project.Adapter.GridAdapter;
import com.covid19project.Login.LoginActivity;
import com.covid19project.Login.ProfileActivity;
import com.covid19project.Models.Jsons;
import com.covid19project.Models.Users;
import com.covid19project.OrphanageSupport.FreeFoodActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mJsonDatabase,mUsersDatabase;

    private GridView Grid_View;

    private FirebaseAuth mAuth;

    private ImageView Profile, Menu;

    String[] web = {
            "Corona Status",
            "Self Report",
            "Home Treatment",
            "Toll Free Numbers",
            "My Health Status",
            "Health Care for Covid19",
            "Medical stores",
            "Doctor Online Appointment",
            "Hospital Admissions",
            "Volunteers & Donates",
            "Food Supply",
            "Labs for Test",
            "Support Orphans and vulnerable",
            "E-Pass",
            "Donate Funds",
            "Donate Relief Material",
            "Application Tracker",
            "Counselling",
            "Non Resident People",
            "Tweets",
            "Videos",
            "FAQs"

    } ;
    int[] imageId = {
            R.drawable.corona,
            R.drawable.selfreport,
            R.drawable.home,
            R.drawable.toll_numbers,
            R.drawable.health,
            R.drawable.healthcare,
            R.drawable.medical_store,
            R.drawable.doctor,
            R.drawable.hospital,
            R.drawable.volunteer,
            R.drawable.food,
            R.drawable.lab,
            R.drawable.support,
            R.drawable.pass,
            R.drawable.donate,
            R.drawable.donate_relief,
            R.drawable.tracker,
            R.drawable.counselling,
            R.drawable.nonresident,
            R.drawable.tweet,
            R.drawable.video,
            R.drawable.faq

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        Grid_View=findViewById(R.id.grid_view);
        Profile = findViewById(R.id.toolbar_profile);
        Menu = findViewById(R.id.toolbar_menu);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("access","true");
                startActivity(intent);
            }
        });

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                sendToLogin();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.menu_main);
                popupMenu.show();
            }
        });

        GridAdapter adapter = new GridAdapter(MainActivity.this, web, imageId);

        final ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        Dialog.setMessage("Loading...");
        Dialog.setCanceledOnTouchOutside(false);
        Dialog.show();

        mJsonDatabase = FirebaseDatabase.getInstance().getReference().child("Jsons");
        mJsonDatabase.keepSynced(true);

        Grid_View.setAdapter(adapter);

        if (mAuth.getCurrentUser() != null) {

            mJsonDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Jsons jsons = dataSnapshot.getValue(Jsons.class);

                    Grid_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            switch (position) {
                                case 0:
                                    Intent intent0 = new Intent(MainActivity.this, CoronaActivity.class);
                                    intent0.putExtra("url", jsons.getCorona());
                                    startActivity(intent0);
                                    break;
                                case 1:
                                    Intent selfintent = new Intent(Intent.ACTION_VIEW);
                                    selfintent.setData(Uri.parse(jsons.getSelfreport()));
                                    startActivity(selfintent);
                                    break;
                                case 2:
                                    Intent intent = new Intent(MainActivity.this, HomeTreamentActivity.class);
                                    intent.putExtra("image_url", jsons.getHome_treatment_images());
                                    intent.putExtra("link_url", jsons.getHome_treatment_links());
                                    startActivity(intent);
                                    break;

                                case 3:
                                    Intent intent1 = new Intent(MainActivity.this, TollNumbersActivity.class);
                                    intent1.putExtra("url", jsons.getToll_numbers());
                                    startActivity(intent1);
                                    break;

                                case 4:
                                    Intent intent2 = new Intent(MainActivity.this, MyHealthStatusActivity.class);
                                    startActivity(intent2);
                                    break;

                                case 5:
                                    Intent intent3 = new Intent(MainActivity.this, HealthCareActivity.class);
                                    startActivity(intent3);
                                    break;

                                case 6:
                                    Intent intent4 = new Intent(MainActivity.this, MedicalStoresActivity.class);
                                    startActivity(intent4);
                                    break;
                                case 7:
                                    Intent intent5 = new Intent(MainActivity.this, OnlineDoctorsActivity.class);
                                    startActivity(intent5);
                                    break;
                                case 8:
                                    Intent intent6 = new Intent(MainActivity.this, HospitalAdmissionActivity.class);
                                    startActivity(intent6);
                                    break;

                                case 9:
                                    Intent intent7 = new Intent(MainActivity.this, VolunteersActivity.class);
                                    startActivity(intent7);
                                    break;
                                case 10:
                                    Intent intent10 = new Intent(MainActivity.this, FreeFoodActivity.class);
                                    startActivity(intent10);
                                    break;

                                case 11:
                                    Intent intent8 = new Intent(MainActivity.this, TestLabsActivity.class);
                                    intent8.putExtra("url", jsons.getLab_test());
                                    startActivity(intent8);
                                    break;

                                case 12:
                                    Intent intent9 = new Intent(MainActivity.this, OrphanageSupportActivity.class);
                                    startActivity(intent9);
                                    break;

                                case 13:
                                    Intent intent13 = new Intent(Intent.ACTION_VIEW);
                                    intent13.setData(Uri.parse(jsons.getEpass()));
                                    startActivity(intent13);
                                    break;

                                case 14:
                                    Intent intent12 = new Intent(MainActivity.this, DonateFundsActivity.class);
                                    startActivity(intent12);
                                    break;

                                case 15:
                                    Intent intent15 = new Intent(MainActivity.this, DonateMaterialsActivity.class);
                                    intent15.putExtra("drug_url", jsons.getDonate_drug());
                                    intent15.putExtra("relief_url", jsons.getDonate_relief());
                                    startActivity(intent15);
                                    break;
                                case 16:
                                    Intent intent14 = new Intent(Intent.ACTION_VIEW);
                                    intent14.setData(Uri.parse(jsons.getTracker()));
                                    startActivity(intent14);
                                    break;
                                case 17:
                                    Intent intent17 = new Intent(MainActivity.this, CounsellingActivity.class);
                                    startActivity(intent17);
                                    break;
                                case 18:
                                    Intent intent18 = new Intent(Intent.ACTION_VIEW);
                                    intent18.setData(Uri.parse(jsons.getMigrant()));
                                    startActivity(intent18);
                                    break;
                                case 19:
                                    Intent intent19 = new Intent(MainActivity.this, TweetsActivity.class);
                                    intent19.putExtra("url", jsons.getTweets());
                                    startActivity(intent19);
                                    break;
                                case 20:
                                    Intent intent20 = new Intent(Intent.ACTION_VIEW);
                                    intent20.setData(Uri.parse(jsons.getVideos()));
                                    startActivity(intent20);
                                    break;
                                case 21:
                                    Intent intent21 = new Intent(MainActivity.this, FAQsActivity.class);
                                    intent21.putExtra("url", jsons.getFaq());
                                    startActivity(intent21);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mCurrentUser = mAuth.getCurrentUser();

        if (mCurrentUser == null) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
        else{
            FirebaseAuth auth = FirebaseAuth.getInstance();
            mUsersDatabase = FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid());
            mUsersDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Users user = dataSnapshot.getValue(Users.class);
                    if (user.getVerified().equals("false")) {
                        Intent setupIntent = new Intent(MainActivity.this, ProfileActivity.class);
                        setupIntent.putExtra("access","false");
                        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(setupIntent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

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

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
