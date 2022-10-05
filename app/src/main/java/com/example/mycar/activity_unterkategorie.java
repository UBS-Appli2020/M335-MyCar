package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class activity_unterkategorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterkategorie);

        TextView tv =(TextView)findViewById(R.id.textView);

        String str5 = getIntent().getStringExtra("Felgen");
        tv.setText(str5);



    }
}