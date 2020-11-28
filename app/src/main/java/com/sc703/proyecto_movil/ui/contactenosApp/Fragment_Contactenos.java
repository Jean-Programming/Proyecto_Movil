package com.sc703.proyecto_movil.ui.contactenosApp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.sc703.proyecto_movil.ActivityWeb;
import com.sc703.proyecto_movil.MainActivity;
import com.sc703.proyecto_movil.R;
import com.sc703.proyecto_movil.activity_maps;

public class Fragment_Contactenos extends Fragment {
    private Button bn_map, bn_llamada,bn_web;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contactenos, container, false);
        bn_map = root.findViewById(R.id.bn_Mapa);
        bn_llamada =root.findViewById(R.id.bn_llamada);
        bn_web = root.findViewById(R.id.bn_Web);
        bn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), ActivityWeb.class);
                startActivity(intent);
            }
        });
        bn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), activity_maps.class);
                startActivity(i);
            }
        });

        bn_llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:22337895"));
                startActivity(i);
            }
        });

        return root;
    }
}