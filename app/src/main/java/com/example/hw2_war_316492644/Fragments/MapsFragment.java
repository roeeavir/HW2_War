package com.example.hw2_war_316492644.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw2_war_316492644.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private GoogleMap gm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - MapsFragment");

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gm = googleMap;
                gm.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng); // Set position of marker

                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        gm.clear();

                        // Zooms on click
                        gm.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));

                        // Adds a marker on the map
                        gm.addMarker(markerOptions);
                    }
                });
            }
        });

        return view;
    }

    // Method for showing and zooming into a player's record saved location
    public void showLocationOnMap(double lon, double lat){
        Log.d("pttt", "Showing selected player location on google maps");
        LatLng latLng = new LatLng(lat, lon);
        gm.addMarker(new MarkerOptions().position((latLng)));
        gm.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}