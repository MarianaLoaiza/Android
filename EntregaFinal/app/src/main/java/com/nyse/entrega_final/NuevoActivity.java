package com.nyse.entrega_final;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyse.entrega_final.model.ContactosModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class NuevoActivity extends AppCompatActivity {
    private EditText txt_nombre, txt_apellido, txt_password, txt_correo;
    private FloatingActionButton fab_nuevo_guardar;
    private ContactosModel model;

    private final String txt_reference = "contactos";

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("txt_reference");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        Toolbar toolbar = findViewById(R.id.toolbar_nuevo);
        setSupportActionBar(toolbar);

        txt_nombre = findViewById(R.id.txt_Nombrep1);
        txt_apellido =findViewById(R.id.apellido);
        txt_password =findViewById(R.id.password);
        txt_correo =findViewById(R.id.correo);
        fab_nuevo_guardar =findViewById(R.id.fab_nuevo_guardar);


        fab_nuevo_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String nombre = txt_nombre.getText().toString();
                String apellido =txt_apellido.getText().toString();
                String correo = txt_correo.getText().toString();
                String password = txt_password.getText().toString();

                if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !password.isEmpty()){
                    String id = reference.push().getKey();
                    if (id != null){

                        model = new ContactosModel(id, nombre, apellido, correo, password);

                        reference.child(id).setValue(model)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent lista = new Intent(NuevoActivity.this, MainActivity.class);
                                        startActivity(lista);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(view, "no puede guardar revisa la informacion", Snackbar.LENGTH_LONG);
                                    }
                                });
                    }else{
                        Snackbar.make(view, "Problemas al crear id de la base de satos", Snackbar.LENGTH_LONG);
                    }

                }else{
                    Toast.makeText(NuevoActivity.this, "ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Siguiente(View view){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }

}
