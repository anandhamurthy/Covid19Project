package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19project.Adapter.DonateDrugAdapter;
import com.covid19project.Adapter.TollNumbersAdapter;
import com.covid19project.Models.DonateDrug;
import com.covid19project.Models.DonateRelief;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DonateDrugActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<DonateDrug> viewItems = new ArrayList<>();

    private DonateDrugAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue mRequestQueue;

    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_drug);
        Intent intent = getIntent();
        final String drug_url = intent.getStringExtra("url");

        Back = findViewById(R.id.toolbar_icon);
        mRecyclerView = findViewById(R.id.donate_drug_list);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        viewItems = new ArrayList<>();

        final ProgressDialog Dialog = new ProgressDialog(DonateDrugActivity.this);
        Dialog.setMessage("Loading...");
        Dialog.setCanceledOnTouchOutside(false);
        Dialog.show();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonateDrugActivity.this, MainActivity.class));
                finish();
            }
        });
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON(drug_url);

        mAdapter = new DonateDrugAdapter(DonateDrugActivity.this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        Dialog.hide();
    }

    private void parseJSON(String drug_url) {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/test_lab.json?alt=media&token=dafd6942-2cb1-497a-8fd0-3dfc6525db6e";

        JsonObjectRequest request = new JsonObjectRequest(drug_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String code = hit.getString("code");
                                String ware_house = hit.getString("ware_house");
                                String mobile = hit.getString("mobile");

                                viewItems.add(new DonateDrug(code, ware_house, mobile));
                            }

                            mAdapter = new DonateDrugAdapter(DonateDrugActivity.this, viewItems);
                            mRecyclerView.setAdapter(mAdapter);

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