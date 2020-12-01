package com.sc703.proyecto_movil.ui.ForoApp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sc703.proyecto_movil.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class Fragment_Foro extends Fragment {

    ImageView imgpost1;
    private final int CODIGO_GALERIA = 1;
    File ArchivoImagen;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_foro, container, false);

        imgpost1 = root.findViewById(R.id.imgpost1);
        imgpost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        return root;
    }
    //Este metodo permite abrir la galeria y seleccionar una imagen
    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //startActivityForResult recibe dos parametros, el intent que se inicializo anteriormente
        //y tambien un numero request que puede ser un numero entero que dice que va a ejecutar el usuario
        startActivityForResult(intent,CODIGO_GALERIA);
    }
    //En este metodo se espera que el usuario realice la accion, esta accion seria
    //dirigirse a la galeria y escoger una foto
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //O sea que, la accion que ejecuta el usuario es igual a seleccionar una imagen de la galeria
        // Y "&&" que la seleccion de la imagen se hizo bien
        if (requestCode == CODIGO_GALERIA && resultCode == RESULT_OK);
        try {
            //Esto nos va transformar la URI en este archivo que esta aqui colocado.
            ArchivoImagen = UtilidadFile.from(this,data.getData());
            //Para que la imagen cargue en uno de los recuadros de la interfaz grafica se hara
            //esta implementacion en la cual recibe la ruta de la imagen.
            imgpost1.setImageBitmap(BitmapFactory.decodeFile(ArchivoImagen.getAbsolutePath()));
        }catch (Exception ex){
            Log.d("ERROR", "Ha ocurrido un error "+ ex.getMessage());
        }
    }
}