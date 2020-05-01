package com.covid19project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.covid19project.Adapter.ImageSliderAdapter;
import com.covid19project.Adapter.LinkAdapter;
import com.covid19project.Adapter.VideoAdapter;
import com.covid19project.Models.Image_Slider;
import com.covid19project.Models.Link;
import com.covid19project.Models.Video;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeTreamentActivity extends AppCompatActivity {

    private SliderView sliderView;
    ImageSliderAdapter imageSliderAdapter;
    VideoAdapter videoAdapter;
    LinkAdapter linkAdapter;
    private RecyclerView Link_List;
    private List<Image_Slider> image_sliders = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private RequestQueue mRequestQueue;

    private RecyclerView.LayoutManager layoutManagerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_treament);

        Intent intent = getIntent();
        String image_url = intent.getStringExtra("image_url");
        String link_url = intent.getStringExtra("link_url");

        sliderView = findViewById(R.id.image_slider);
        Link_List = findViewById(R.id.link_list);
        Link_List.setHasFixedSize(true);
        layoutManagerLink = new LinearLayoutManager(this);
        Link_List.setLayoutManager(layoutManagerLink);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

        final ProgressDialog Dialog = new ProgressDialog(HomeTreamentActivity.this);
        Dialog.setMessage("Loading...");
        Dialog.setCanceledOnTouchOutside(false);
        Dialog.show();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSONImages(image_url);
      //  parseJSONVideos();
        parseJSONLinks(link_url);

        Dialog.hide();
    }

    private void parseJSONImages(String image_url) {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/images.json?alt=media&token=d8124108-9b4f-43fb-ba7e-44f0008838ed";

        JsonObjectRequest request = new JsonObjectRequest(image_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("image_data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String image = hit.getString("image");

                                image_sliders.add(new Image_Slider(image));
                            }

                            imageSliderAdapter = new ImageSliderAdapter(HomeTreamentActivity.this, image_sliders);
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
//
//    private void parseJSONVideos() {
//        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/videos.json?alt=media&token=fd0245b9-9805-4542-b671-aeaa7039a25d";
//
//        JsonObjectRequest request = new JsonObjectRequest(url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray1 = response.getJSONArray("video_data");
//                            Toast.makeText(HomeTreamentActivity.this, jsonArray1.toString(), Toast.LENGTH_SHORT).show();
//
//                            for (int i = 0; i < jsonArray1.length(); i++) {
//                                JSONObject hit = jsonArray1.getJSONObject(i);
//                                String video = hit.getString("video");
//
//                                videos.add(new Video(video));
//                            }
//
//                            videoAdapter = new VideoAdapter(HomeTreamentActivity.this, videos);
//                            Video_List.setAdapter(videoAdapter);
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        mRequestQueue.add(request);
//    }

    private void parseJSONLinks(String link_url) {
        String url = "https://firebasestorage.googleapis.com/v0/b/covid19-project-c24e6.appspot.com/o/links.json?alt=media&token=96128da8-4ed4-409a-9c32-1ac0071a24dd";

        JsonObjectRequest request = new JsonObjectRequest(link_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray2 = response.getJSONArray("link_data");

                            for (int i = 0; i < jsonArray2.length(); i++) {
                                JSONObject hit = jsonArray2.getJSONObject(i);
                                String title = hit.getString("title");
                                String link = hit.getString("link");

                                links.add(new Link(title, link));
                            }

                            linkAdapter = new LinkAdapter(HomeTreamentActivity.this, links);
                            Link_List.setAdapter(linkAdapter);

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
