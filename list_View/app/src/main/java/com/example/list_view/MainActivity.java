package com.example.list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private ListView lv1;

    private String nombres [] = {"Samuel", "Valentina", "Mariana", "Camila", "Sara", "Manuela", "Maria"};
    private String edades[] = {"18", "20", "30", "50", "85", "12", "36"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        lv1 =(ListView)findViewById(R.id.lv1);{

            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_itm_geekipedia,nombres);
            lv1.setAdapter(adapter);

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tv1.setText("La edad de "+lv1.getItemAtPosition(i) + "Es " +edades[i] + "Años");
                }
            });
        }
    }
}
