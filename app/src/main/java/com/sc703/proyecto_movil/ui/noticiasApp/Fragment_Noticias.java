package com.sc703.proyecto_movil.ui.noticiasApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sc703.proyecto_movil.R;

public class Fragment_Noticias extends Fragment {
        TextView tv_noticiasapp;
        TextView tv_noticiasapp2;
        FirebaseDatabase BD = FirebaseDatabase.getInstance();
        DatabaseReference Referencia = BD.getInstance().getReference();
        DatabaseReference Noticias = Referencia.child("Noticia");
        DatabaseReference Noticias2= Referencia.child("Noticia2");



        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_noticiasapp, container, false);

            tv_noticiasapp = root.findViewById(R.id.tv_noticiasapp);
            tv_noticiasapp2 = root.findViewById(R.id.tv_noticiasapp2);

            return root;
        }

        @Override
        public void onStart() {
            super.onStart();

            Noticias.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String textoNoticia = snapshot.getValue().toString();
                    tv_noticiasapp.setText(textoNoticia);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Noticias2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String textoNoticia2 = snapshot.getValue().toString();
                    tv_noticiasapp2.setText(textoNoticia2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}
