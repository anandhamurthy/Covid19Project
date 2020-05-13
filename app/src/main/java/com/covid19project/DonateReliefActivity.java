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
import com.covid19project.Adapter.DonateReliefAdapter;
import com.covid19project.Models.DonateDrug;
import com.covid19project.Models.DonateRelief;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DonateReliefActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private List<DonateRelief> viewItems;

    private DonateReliefAdapter mAdapter;
    private RequestQueue mRequestQueue;

    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_relief);

        Intent intent = getIntent();
        final String relief_url = intent.getStringExtra("url");

        Back= findViewById(R.id.toolbar_icon);
        mRecyclerView = findViewById(R.id.donate_relief_list);
        mRecyclerView.setHasFixedSize(true);
        viewItems = new ArrayList<>();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON(relief_url);

        mAdapter = new DonateReliefAdapter(DonateReliefActivity.this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void parseJSON(String relief_url) {
        JsonObjectRequest request = new JsonObjectRequest(relief_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String district = hit.getString("district");
                                String mobile = hit.getString("mobile");
                                viewItems.add(new DonateRelief(district, mobile));
                            }
                            mAdapter = new DonateReliefAdapter(DonateReliefActivity.this, viewItems);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                            mAdapter.notifyDataSetChanged();

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