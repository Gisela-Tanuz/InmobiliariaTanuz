package com.tanuz.inmobiliariatanuz.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tanuz.inmobiliariatanuz.R;
import com.tanuz.inmobiliariatanuz.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private InicioViewModel homeViewModel;
    private FragmentInicioBinding binding;
    private GoogleMap mapa;
    private final static LatLng inmob = new LatLng(-33.29690, -66.33100);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(new MapaActual());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapa = googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.addMarker(new MarkerOptions().position(inmob).title("Inmobiliaria Tanúz"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(inmob).zoom(20).bearing(45).tilt(70).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mapa.animateCamera(cameraUpdate);
        }
    }
}