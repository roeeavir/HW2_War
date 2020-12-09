package com.example.hw2_war_316492644.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
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
                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        gm.clear();

                        // Zoom animation
                        gm.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));

                        gm.addMarker(markerOptions);
                    }
                });
            }
        });

        return view;
    }

    public void showLocationOnMap(double lon, double lat){
        LatLng latLng = new LatLng(lat, lon);
        gm.addMarker(new MarkerOptions().position((latLng)));
        gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}