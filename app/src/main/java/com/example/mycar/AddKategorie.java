package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AddKategorie extends AppCompatActivity {
TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkategorie);

        tv1=(TextView)findViewById(R.id.label_modell);
        tv2=(TextView)findViewById(R.id.label_baujahr);
        tv3=(TextView)findViewById(R.id.label_ps);
        tv4=(TextView)findViewById(R.id.label_fznumber);
        tv5=(TextView)findViewById(R.id.textview5);

        String str1 = getIntent().getStringExtra("v1");
        String str2 = getIntent().getStringExtra("v2");
        String str3 = getIntent().getStringExtra("v3");
        String str4 = getIntent().getStringExtra("v4");
        String str5 = getIntent().getStringExtra("v5");

        tv1.setText(str1);
        tv2.setText(str2);
        tv3.setText(str3);
        tv4.setText(str4);
        tv5.setText(str5);

    }
}