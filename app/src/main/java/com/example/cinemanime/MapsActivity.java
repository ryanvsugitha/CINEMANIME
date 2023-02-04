package com.example.cinemanime;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cinemanime.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng CGP_A = new LatLng(-6.193924061113853, 106.78813220277623);
        mMap.addMarker(new MarkerOptions().position(CGP_A).title("CGP Alpha"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(CGP_A));

        LatLng CGP_B = new LatLng(-6.20175020412279, 106.78223868546155);
        mMap.addMarker(new MarkerOptions().position(CGP_B).title("CGP Beta"));

        LatLng Mid = new LatLng((-6.193924061113853 + -6.20175020412279)/2, (106.78813220277623 + 106.78223868546155)/2);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mid, 15));

    }
}