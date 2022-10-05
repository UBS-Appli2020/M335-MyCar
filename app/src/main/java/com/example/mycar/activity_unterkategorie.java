package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_unterkategorie extends AppCompatActivity {
    Button add_Kategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterkategorie);

        Intent intent = getIntent();
        String buttonName = intent.getStringExtra("buttonName");

        add_Kategorie=(Button)findViewById(R.id.add_Kategorie);
        add_Kategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_unterkategorie.this, add_Kategorie.getClass());
                Log.d("test",buttonName);
                startActivity(intent);
            }
        });



    }


}