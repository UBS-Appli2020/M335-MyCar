package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Das ist die Seite, die das Form enthält wo man eine neue Kategorie hinzufügen kann.
 * Diese Daten werden ausgelesen und anschliessend im Sharedpreference des jeweilgen Profils gespeichert werden.
 *
 */
public class AddKategorie extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, fehlermeldung;
    EditText v1, v2, v3, v4, v5;
    Button add_kategorie_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addkategorie);
        // Holt sich die mitgegebenen Daten
        Intent intent = getIntent();
        String car = intent.getStringExtra("car");
        String kategorie = intent.getStringExtra("kategorie");

        // Setzt die Keys Texte
        String str1 = getIntent().getStringExtra("v1");
        String str2 = getIntent().getStringExtra("v2");
        String str3 = getIntent().getStringExtra("v3");
        String str4 = getIntent().getStringExtra("v4");
        String str5 = getIntent().getStringExtra("v5");

        // Holt sich alle TextViews für die Keys
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);

        tv1.setText(str1);
        tv2.setText(str2);
        tv3.setText(str3);
        tv4.setText(str4);
        tv5.setText(str5);

        // Holt alle EditText der Eingaben
        v1 = (EditText) findViewById(R.id.v1);
        v2 = (EditText) findViewById(R.id.v2);
        v3 = (EditText) findViewById(R.id.v3);
        v4 = (EditText) findViewById(R.id.v4);
        v5 = (EditText) findViewById(R.id.v5);

        // Holt sich den hinzufügen button und fügt einen Listener hinzu
        add_kategorie_button = (Button) findViewById(R.id.add_kategorie_button);
        add_kategorie_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Kontrolliert das alle Felder ausgefüllt wurden
                if (!v1.getText().toString().equals("") && !v2.getText().toString().equals("") && !v3.getText().toString().equals("") && !v4.getText().toString().equals("") && !v5.getText().toString().equals("")) {
                    Log.d("TTT","ALL ENTERED");

                    // Holt das gespeicherte Sharedpreference
                    SharedPreferences sh = getSharedPreferences(car,MODE_PRIVATE);

                    // Erstellt eine HashMap um alle Eingaben zu speichern
                    HashMap<String,String> saved_data = new HashMap<String, String>();
                    saved_data.put(tv1.getText().toString(),v1.getText().toString());
                    saved_data.put(tv2.getText().toString(),v2.getText().toString());
                    saved_data.put(tv3.getText().toString(),v3.getText().toString());
                    saved_data.put(tv4.getText().toString(),v4.getText().toString());
                    saved_data.put(tv5.getText().toString(),v5.getText().toString());

                    Log.d("TTT",String.valueOf(saved_data));

                    // Converted die HashMap zum JSON
                    Gson gson = new Gson();
                    String hashmapasString = gson.toJson(saved_data);

                    String kategorie_key = loopthroughallkategories(car,kategorie);

                    // Speichert das JSON
                    sh.edit().putString(kategorie_key,hashmapasString).apply();

                    // Leitet zurück auf die Overview Seite
                    Intent intent = new Intent(AddKategorie.this, activity_unterkategorie.class);
                    intent.putExtra("car",car);
                    intent.putExtra("buttonName",kategorie);
                    startActivity(intent);

                } else {
                    // Gibt eine Fehlermeldung aus wenn nicht alle Daten eingegeben wurden
                    fehlermeldung = (TextView) findViewById(R.id.fehlermeldung);
                    fehlermeldung.setText("Bitte alle Felder ausfuellen!!");
                    fehlermeldung.setBackgroundColor(Color.parseColor("#f82c00"));
                }
            }
        });


    }

    private String loopthroughallkategories(String car, String kategorie){
        /*
         * Diese Funktion setzt den speicerung key für die Kategorie
         *
         * */
        while (true){
            int counter = 1;
            int previous_counter = 0;
            String name;

            while (true){
                name = kategorie + counter;

                Log.d("TTT",name);

                SharedPreferences sh = getSharedPreferences(car, MODE_PRIVATE);


                if (sh.getString(name,"").equals("")){

                    Log.d("TTT","There is/are just " + counter +"in " + kategorie);
                    break;
                }else{
                    counter++;
                    previous_counter++;
                }

            }

            Log.d("TTT",String.valueOf(counter));
            Log.d("TTT",name);
            return name;

        }
    }
}