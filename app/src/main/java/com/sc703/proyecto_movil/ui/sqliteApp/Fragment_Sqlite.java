package com.sc703.proyecto_movil.ui.sqliteApp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sc703.proyecto_movil.R;
import com.sc703.proyecto_movil.AdminBD;
import com.sc703.proyecto_movil.R;

public class Fragment_SQLite extends Fragment {
    private AdminBD estructura;
    private SQLiteDatabase db;

    private Button bn_Agregar, bn_BuscarSQL, bn_Modificar, bn_Eliminar;
    private EditText et_Nombre,et_Cedula,et_Correo,et_Edad,et_Telefono,et_Direccion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sqlite, container, false);
        estructura= new AdminBD(this.getContext(),"taller",null,1);

        et_Nombre = root.findViewById(R.id.et_Nombre);
        et_Cedula = root.findViewById(R.id.et_Cedula);
        et_Correo = root.findViewById(R.id.et_Correo);
        et_Edad = root.findViewById(R.id.et_Edad);
        et_Telefono = root.findViewById(R.id.et_Telefono);
        et_Direccion = root.findViewById(R.id.et_Direccion);

        //Esto se cambia por la parte de como si fuera una publicacion
        //et_Nombre = root.findViewByID(R.id.et_Nombre);
        //et_Publicacion = root.findViewByID(R.id.et_Publicacion);



        bn_Agregar=root.findViewById(R.id.bn_Agregar);
        bn_BuscarSQL=root.findViewById(R.id.bn_BuscarSQL);
        bn_Modificar=root.findViewById(R.id.bn_Modificar);
        bn_Eliminar=root.findViewById(R.id.bn_Eiminar);

        bn_Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agregar();
            }
        });

        bn_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar();
            }
        });

        bn_BuscarSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        bn_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });

        return root;
    }
    private void Agregar(){
        db=estructura.getWritableDatabase();
        String Nombre = et_Nombre.getText().toString();
        int Cedula = Integer.parseInt(et_Cedula.getText().toString());
        String Correo = et_Correo.getText().toString();
        int Edad= Integer.parseInt(et_Edad.getText().toString());
        int Telefono = Integer.parseInt(et_Telefono.getText().toString());
        String Direccion = et_Direccion.getText().toString();

        ContentValues contenedor = new ContentValues();
        contenedor.put("Nombre",Nombre);
        contenedor.put("ID",Cedula);
        contenedor.put("Correo",Correo);
        contenedor.put("Edad",Edad);
        contenedor.put("Telefono",Telefono);
        contenedor.put("Direccion",Direccion);

        //Aqui lo mismo de eliminar los que no son importantes y los que si son importantes como
        //publicacion y su nombre. Claramente cambiar el nombre de la base de datos.

        db.insert("Estudiantes",null,contenedor);
        db.close();

        Toast.makeText(getContext(),"Datos agregados",Toast.LENGTH_SHORT).show();
    }

    private void Modificar(){

        db=estructura.getWritableDatabase();
        String Nombre = et_Nombre.getText().toString();
        int Cedula = Integer.parseInt(et_Cedula.getText().toString());
        String Correo = et_Correo.getText().toString();
        int Edad= Integer.parseInt(et_Edad.getText().toString());
        int Telefono= Integer.parseInt(et_Telefono.getText().toString());
        String Direccion = et_Direccion.getText().toString();

        ContentValues contenedor = new ContentValues();
        contenedor.put("Nombre",Nombre);
        contenedor.put("ID",Cedula);
        contenedor.put("Correo",Correo);
        contenedor.put("Edad",Edad);
        contenedor.put("Telefono",Telefono);
        contenedor.put("Direccion",Direccion);

        int n = db.update("Estudiantes",contenedor,"ID = "+ Cedula,null);
        db.close();

        if(n==1){
            Toast.makeText(getContext(),"Datos modificados",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"No se pudieron modificar los datos",Toast.LENGTH_SHORT).show();
        }
    }

    private void Buscar(){
        db=estructura.getReadableDatabase();
        int Cedula= Integer.parseInt(et_Cedula.getText().toString());
        Cursor fila = db.rawQuery("select * from Estudiantes where ID = "+Cedula,null);
        if(fila.moveToFirst()){
            et_Nombre.setText(""+fila.getString(1));
            et_Cedula.setText(""+fila.getString(2));
            et_Correo.setText(""+fila.getString(3));
            et_Edad.setText(""+fila.getString(4));
            et_Telefono.setText(""+fila.getString(5));
            et_Direccion.setText(""+fila.getString(6));

        }else{
            Toast.makeText(getContext(),"No se pudo encontrar los datos",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void Eliminar(){
        db=estructura.getWritableDatabase();
        int Cedula = Integer.parseInt(et_Cedula.getText().toString());
        int n = db.delete("Estudiantes","ID = "+ Cedula,null);
        db.close();
        if(n==1){
            Toast.makeText(getContext(),"Datos eliminados",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"No se pudieron eliminar los datos",Toast.LENGTH_SHORT).show();
        }
    }
}

//Estudiantes, serian tweets, y se pueden cambiar eliminar o modificar en su caso cada publicacion.
//Va en concorde con el fragment perfil
