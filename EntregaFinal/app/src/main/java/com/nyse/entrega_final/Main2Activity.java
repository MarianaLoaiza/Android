package com.nyse.entrega_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main2Activity extends AppCompatActivity {
    private Button FLoatButton;
    private StorageReference myStorage;
    private static final int GALERY_INTENT = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myStorage = FirebaseStorage.getInstance().getReference();

        FLoatButton = findViewById(R.id.buttonSubir);

        FLoatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALERY_INTENT );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALERY_INTENT && requestCode == RESULT_OK){

            Uri uri = data.getData();

            StorageReference filepath = myStorage.child("fotos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Main2Activity.this, "Se subio exitosamente la foto", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();

        }
    }
}
