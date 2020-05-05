package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.covid19project.Models.Counselling;

public class CounsellingActivity extends AppCompatActivity {

    private Button Counselling_NO_1, Counselling_NO_2;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselling);

        Counselling_NO_1 = findViewById(R.id.counselling_no_1);
        Counselling_NO_2 = findViewById(R.id.counselling_no_2);
        Back = findViewById(R.id.toolbar_icon);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CounsellingActivity.this, MainActivity.class));
                finish();
            }
        });

        Counselling_NO_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:044-28590804"));
                startActivity(intent);
            }
        });

        Counselling_NO_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:044-28599188"));
                startActivity(intent);

            }
        });

    }
}
