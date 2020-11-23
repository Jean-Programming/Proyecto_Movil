package com.sc703.proyecto_movil.ui.salirApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.sc703.proyecto_movil.MainActivity;
import com.sc703.proyecto_movil.R;

public class Fragment_Salir extends Fragment {
    Button bn_Salir;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_salirapp, container, false);
        bn_Salir = root.findViewById(R.id.bn_Salir);

        bn_Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(),"Se ha desconectado del servidor",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}