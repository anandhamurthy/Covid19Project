package com.covid19project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.covid19project.Models.Area;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ContainmentZonesActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private double latitide, longitude;

    private List<Area> pointsOfInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containment_zones);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }

        getLocations();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getLocations() {
        pointsOfInterest = new ArrayList<Area>();
        pointsOfInterest.add(new Area(new LatLng(11.1389165,79.0566782), "Ariyalur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.6729725,79.962458), "Chengalpattu", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(13.0473748,79.9288011), "Chennai", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.0116775,76.8271445), "Coimbatore", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.7508644,79.7491655), "Cuddalore", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.1269508,78.1164643), "Dharmapuri", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.3623791,77.9607031), "Dindigul", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.3466179,77.6453554), "Erode", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.7355587,78.9530698), "Kallakurichi", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(8.0864019,77.5371157), "Kanyakumari", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.9651497,78.03293), "Karur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.5265657,78.2062027), "Krishnagiri", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(9.9178296,78.0527821), "Madurai", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.7795741,79.7544192), "Nagapattinam", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.2188955,78.1586027), "Namakkal", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.3832864,78.7913742), "Pudukkottai", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(9.3678273,78.8257136), "Ramanathapuram", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.9533913,79.2815914), "Ranipet", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.6442905,77.6893706), "Salem", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(9.8500115,78.4526154), "Sivagangai", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(8.9593517,77.3073542), "Tenkasi", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.7528121,79.0614079), "Thanjavur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.4483606,76.3438505), "Nilgiris", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.0133997,77.4455795), "Theni", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(13.2462211,79.5402112), "Tiruvallur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.7674362,79.6179141), "Thiruvarur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(8.7766073,78.0759759), "Thoothukudi", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(8.721555,77.6721362), "Tirunelveli", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.4991657,78.5296956), "Tirupathur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.1085742,77.2937714), "Tiruppur", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.2408546,79.0342329), "Tiruvannamalai", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(10.8158368,78.6189865), "Trichy", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(12.8992715,78.978252), "Vellore", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(11.9336745,79.4644888), "Viluppuram", "Containment Zone"));
        pointsOfInterest.add(new Area(new LatLng(9.5734344,77.8881489), "Virudhunagar", "Containment Zone"));

    }


    public void onClick(View v){

        switch (v.getId()) {
            case R.id.list:
                startActivity(new Intent(ContainmentZonesActivity.this, ContainmentListActivity.class));
                finish();
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }

    }

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }




    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

    }


    @Override
    public void onLocationChanged(Location location)
    {
        latitide = location.getLatitude();
        longitude = location.getLongitude();

        lastLocation = location;

        if (currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }

        for(int i=0;i<pointsOfInterest.size();i++){
//            CircleOptions circleOptions = new CircleOptions();
//            circleOptions.center(new LatLng(pointsOfInterest.get(i).getLocationLatLng().latitude, pointsOfInterest.get(i).getLocationLatLng().longitude));
//            circleOptions.radius(10000);
//            circleOptions.fillColor(Color.RED);
//            circleOptions.strokeWidth(6);
//            mMap.addCircle(circleOptions);
            LatLng latLng = new LatLng(pointsOfInterest.get(i).getLocationLatLng().latitude, pointsOfInterest.get(i).getLocationLatLng().longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(pointsOfInterest.get(i).getName());
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.coronalocation));
            currentUserLocationMarker = mMap.addMarker(markerOptions);

        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(1));

        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
