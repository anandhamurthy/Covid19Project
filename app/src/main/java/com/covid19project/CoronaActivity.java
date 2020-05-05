package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19project.Adapter.CoronaAdapter;
import com.covid19project.Adapter.FAQAdapter;
import com.covid19project.Models.Corona;
import com.covid19project.Models.FAQ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoronaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Corona> viewItems;

    private CoronaAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RequestQueue mRequestQueue;
    private SearchView searchView;

    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        Back= findViewById(R.id.toolbar_icon);
        mRecyclerView = findViewById(R.id.corona_list);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        viewItems = new ArrayList<>();

        mAdapter = new CoronaAdapter(CoronaActivity.this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoronaActivity.this, MainActivity.class));
                finish();
            }
        });

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/tn.json?alt=media&token=1c558046-f612-4b87-a8f9-92a0effc85b6";

        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("districtData");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String district = hit.getString("district");
                                String confirmed = hit.getString("confirmed");

                                viewItems.add(new Corona(district, confirmed));
                                mAdapter.notifyDataSetChanged();
                            }
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
        mAdapter.notifyDataSetChanged();
    }

}
