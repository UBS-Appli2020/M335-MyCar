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
                if (buttonName.equals("2131361895")){
                    Intent intent = new Intent(activity_unterkategorie.this, AddKategorie.class);
                    Log.d("test", buttonName);
                    String str1 = add_Kategorie.getText().toString();
                    intent.putExtra("kfv1", "Hersteller");
                    intent.putExtra("kfv2", "Felgendurchmesser");
                    startActivity(intent);
                }
                else{
                    Log.d("FAIL", "FAIL");
                }

            }
        });



    }


}