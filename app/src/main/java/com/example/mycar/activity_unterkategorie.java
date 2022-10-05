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

import java.util.Set;

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
                Intent intent = new Intent(activity_unterkategorie.this, AddKategorie.class);
                Log.d("test", buttonName); // testzwecke
                if (buttonName.equals("Felgen")){
                    Log.d("test", buttonName);

                    String str1 = add_Kategorie.getText().toString();
                    intent.putExtra("v1", "Hersteller");
                    intent.putExtra("v2", "Felgendurchmesser");
                    intent.putExtra("v3", "Nabendurchmessen");
                    intent.putExtra("v4", "Breite");
                    intent.putExtra("v5", "Farbe/Material");
                    startActivity(intent);
                }
                else if(buttonName.equals("Reifen")){
                    Log.d("Button 2: ", "Passed");
                    String str2 = add_Kategorie.getText().toString();
                    intent.putExtra("v1", "Hersteller");
                    intent.putExtra("v2", "Breite");
                    intent.putExtra("v3", "Höhe");
                    intent.putExtra("v4", "Zoll");
                    intent.putExtra("v5", "Bauart des Reifens (radial oder diagonal)");
                    startActivity(intent);
                }

            }
        });



    }


}