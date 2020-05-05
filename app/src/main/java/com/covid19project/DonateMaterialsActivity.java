package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DonateMaterialsActivity extends AppCompatActivity {

    private Button Drug, Relief;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_materials);

        Intent intent = getIntent();
        final String drug_url = intent.getStringExtra("drug_url");
        final String relief_url = intent.getStringExtra("relief_url");

        Drug = findViewById(R.id.donate_drug);
        Relief = findViewById(R.id.donate_relief);
        Back = findViewById(R.id.toolbar_icon);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonateMaterialsActivity.this, MainActivity.class));
                finish();
            }
        });

        Drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonateMaterialsActivity.this, DonateDrugActivity.class);
                intent.putExtra("url",drug_url);
                startActivity(intent);
            }
        });

        Relief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonateMaterialsActivity.this, DonateReliefActivity.class);
                intent.putExtra("url",relief_url);
                startActivity(intent);
            }
        });
    }
}
