package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class MyHealthStatusActivity extends AppCompatActivity {

    TextInputLayout name,age;
    boolean b=false;
    RadioGroup radiogrp_caugh, radiogrp_fever, radiogrp_breath, radiogrp_travel, radiogrp_nearaffected;
    RadioButton rcough,rfever, rbreath, rtravel, rnaf;
    FloatingActionButton button;
    TextView sug,title;
    LinearLayout formlayout;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health_status);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        title=findViewById(R.id.title);
        sug=findViewById(R.id.sugestion);
        home=findViewById(R.id.home);
        radiogrp_caugh=findViewById(R.id.radiogrp_caugh);
        radiogrp_fever=findViewById(R.id.radiogrp_fever);
        radiogrp_breath=findViewById(R.id.radiogrp_breath);
        radiogrp_travel=findViewById(R.id.radiogrp_travel);
        radiogrp_nearaffected=findViewById(R.id.radiogrp_nearaffected);
        formlayout=findViewById(R.id.formlayout);
        button=findViewById(R.id.fab_submit);
//        coughyes=findViewById(R.id.coughyes);
//        coughno=findViewById(R.id.coughno);
//        feveryes=findViewById(R.id.feveryes);
//        feverno=findViewById(R.id.feverno);
//        breathyes=findViewById(R.id.breathyes);
//        breathno=findViewById(R.id.breathno);
//        travelyes=findViewById(R.id.travelyes);
//        travelno=findViewById(R.id.travelno);
//        nafyes=findViewById(R.id.nafyes);
//        nafno=findViewById(R.id.nafno);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b==true){
                    startActivity(new Intent(MyHealthStatusActivity.this,MainActivity.class));
                }
                else {
                    b = true;
                    try {
                        int caugh = radiogrp_caugh.getCheckedRadioButtonId();
                        rcough = findViewById(caugh);
                        int fever = radiogrp_fever.getCheckedRadioButtonId();
                        rfever = findViewById(fever);
                        int breath = radiogrp_breath.getCheckedRadioButtonId();
                        rbreath = findViewById(breath);
                        int travel = radiogrp_travel.getCheckedRadioButtonId();
                        rtravel = findViewById(travel);
                        int naf = radiogrp_nearaffected.getCheckedRadioButtonId();
                        rnaf = findViewById(naf);

                        int factor = 0;
                        boolean serious = false;
                        if (rcough.getText().toString().equals("Yes")) {
                            factor++;
                        }
                        if (rfever.getText().toString().equals("Yes")) {
                            factor++;
                        }
                        if (rbreath.getText().toString().equals("Yes")) {
                            factor++;
                        }
                        if (rtravel.getText().toString().equals("Yes")) {
                            factor++;
                        }
                        if (rnaf.getText().toString().equals("Yes")) {
                            factor++;
                            serious = true;
                        }
                        title.setVisibility(View.INVISIBLE);
                        formlayout.setVisibility(View.GONE);
                        sug.setVisibility(View.VISIBLE);
                        home.setVisibility(View.VISIBLE);
                        if (factor == 0) {
                            sug.setText("The risk of being affected is less. Stay safe in your home!");
                        } else if (serious == true) {
                            sug.setTextColor(Color.parseColor("#FFDD1D1D"));
                            sug.setText("You have a high risk of getting affected. Go to a nearby corono testing center immediately.");
                        } else {
                            sug.setText("You have symptoms of Corona virus. Stay quarentined in home and we recommend you to visit a nearby corona testing center.");

                        }
                    }
                    catch (java.lang.NullPointerException e){
                        Toast.makeText(MyHealthStatusActivity.this, "Please fill in all the details!", Toast.LENGTH_SHORT).show();
                        b=false;
                    }
                }
            }

        });


    }
}