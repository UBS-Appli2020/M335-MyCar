package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AddKategorie extends AppCompatActivity {
TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkategorie);

        tv1=(TextView)findViewById(R.id.label_modell);
        tv2=(TextView)findViewById(R.id.label_baujahr);

        String str1 = getIntent().getStringExtra("kfv1");
        String str2 = getIntent().getStringExtra("kfv2");

        tv1.setText(str1);
        tv2.setText(str2);

    }
}