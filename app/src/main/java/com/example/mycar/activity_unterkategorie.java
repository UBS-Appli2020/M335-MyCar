package com.example.mycar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * Das ist die Homeseite einer Kategorie, hier kann man alle Dinge sehen die man in einer Kategorie gespeichert hat
 *
 */
public class activity_unterkategorie extends AppCompatActivity {
    Button add_Kategorie;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterkategorie);

//      Hollt sich die Daten der vorherigen Seite
        Intent intent = getIntent();

//      Setzt die Variablen
        String buttonName = intent.getStringExtra("buttonName");
        String car = intent.getStringExtra("car");

//      Sucht die Scrollview
        ScrollView app_layer = (ScrollView) findViewById (R.id.base_scrollbar);

//      Erstellt ein neues LinearLayout
        LinearLayout main_profiles = new LinearLayout(this);
        main_profiles.setOrientation(LinearLayout.VERTICAL);

//      Hollt sich die sharedpreferences
        SharedPreferences sh = getSharedPreferences(car, MODE_PRIVATE);

//      Geht durch jede Subkategorie
        int i = 1;
        while (i <= count(car,buttonName)) {

//          Hollt sich die Kategorie keys
            String kategorie_key = buttonName+String.valueOf(i);

//          Hollt sich die gespeicherte liste
            String storedHashMapString = sh.getString(kategorie_key, "");
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            HashMap<String, String> kategorie = gson.fromJson(storedHashMapString, type);

            Log.d("DEBUG",String.valueOf(kategorie));

            LinearLayout linearLayout = new LinearLayout(this);

//          Setzt das ganze Layout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
            layoutParams.rightMargin = 132;
            layoutParams.topMargin = 132;
            layoutParams.leftMargin = 132;

            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            linearLayout.setBackgroundResource(R.drawable.top_background);

            Set<String> all_keys = kategorie.keySet();

            ArrayList<String> keys = new ArrayList<String>(all_keys);

            LinearLayout.LayoutParams tv1layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1layoutParams.topMargin = 100;


//          Setzt alle Textviews mit den Daten und dem Layout
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(tv1layoutParams);
            textView1.setGravity(1);
            textView1.setTextSize(16);

            textView1.setText(String.valueOf(keys.get(0) + ": " + kategorie.get(keys.get(0))));
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
            linearLayout.addView(textView1);

            TextView textView2 = new TextView(this);
            textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView2.setGravity(1);
            textView2.setTextSize(16);
            textView2.setText(String.valueOf(keys.get(1) + ": " + kategorie.get(keys.get(1))));
            textView2.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
            linearLayout.addView(textView2);

            TextView textView3 = new TextView(this);
            textView3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView3.setGravity(1);
            textView3.setTextSize(16);
            textView3.setText(String.valueOf(keys.get(2) + ": " + kategorie.get(keys.get(2))));
            textView3.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
            linearLayout.addView(textView3);

            TextView textView4 = new TextView(this);
            textView4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView4.setGravity(1);
            textView4.setTextSize(16);
            textView4.setText(String.valueOf(keys.get(3) + ": " + kategorie.get(keys.get(3))));
            textView4.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
            linearLayout.addView(textView4);
            Log.d("DEBUG","HERE");
            TextView textView5 = new TextView(this);
            textView5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView5.setGravity(1);
            textView5.setTextSize(16);
            textView5.setText(String.valueOf(keys.get(4) + ": " + kategorie.get(keys.get(4))));
            textView5.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
            linearLayout.addView(textView5);

//          Fügt alles ins main_profiles layout
            main_profiles.addView(linearLayout);

            i++;

        }

//      Löscht alles aus der View und fügt alles hinzu
        app_layer.removeAllViews();
        app_layer.addView(main_profiles);

//      Hollt sich den Button
        add_Kategorie=(Button)findViewById(R.id.add_Kategorie);
        add_Kategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Setzt die Infrmationen für die Übergabe
                Intent intent = new Intent(activity_unterkategorie.this, AddKategorie.class);
                intent.putExtra("car",car);
                intent.putExtra("kategorie",buttonName);
                Log.d("test", buttonName); // testzwecke

//              Bei Felgen
                if (buttonName.equals("Felgen")) {
                    Log.d("test", buttonName);

//                  Setzt alle Informationen
                    String str1 = add_Kategorie.getText().toString();
                    intent.putExtra("v1", "Hersteller");
                    intent.putExtra("v2", "Felgendurchmesser");
                    intent.putExtra("v3", "Nabendurchmessen");
                    intent.putExtra("v4", "Breite");
                    intent.putExtra("v5", "Farbe/Material");
                    startActivity(intent);
                }
//              Bei Reifen
                else if(buttonName.equals("Reifen")){
                    Log.d("Button 2: ", "Passed");

//                  Setzt alle Informationen
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

    private int count(String car, String kategorie){
        while (true){
            int counter = 1;
            int previous_counter = 0;
            String name;

            while (true){
                name = kategorie + counter;

                SharedPreferences sh = getSharedPreferences(car, MODE_PRIVATE);

                if (sh.getString(name,"").equals("")){
                    counter--;
                    break;
                }else{
                    counter++;

                }

            }

            return (counter);

        }
    }


}