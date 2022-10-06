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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class activity_unterkategorie extends AppCompatActivity {
    Button add_Kategorie;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unterkategorie);

        Intent intent = getIntent();
        String buttonName = intent.getStringExtra("buttonName");
        String car = intent.getStringExtra("car");

        ScrollView app_layer = (ScrollView) findViewById (R.id.base_scrollbar);

        LinearLayout main_profiles = new LinearLayout(this);
        main_profiles.setOrientation(LinearLayout.VERTICAL);

        SharedPreferences sh = getSharedPreferences(car, MODE_PRIVATE);

        int i = 1;
        while (i <= count(car,buttonName)) {

            String kategorie_key = buttonName+String.valueOf(i);
            Log.d("DEBUG",String.valueOf(kategorie_key));

            String storedHashMapString = sh.getString(kategorie_key, "");
            Log.d("DEBUG",storedHashMapString);
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            HashMap<String, String> kategorie = gson.fromJson(storedHashMapString, type);


            Log.d("DEBUG",String.valueOf(kategorie));
            if (String.valueOf(kategorie) != null){
                LinearLayout linearLayout = new LinearLayout(this);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
                layoutParams.rightMargin = 132;
                layoutParams.topMargin = 132;
                layoutParams.leftMargin = 132;

                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                linearLayout.setBackgroundResource(R.drawable.top_background);

                Set<String> all_keys = kategorie.keySet();

                int j = 1;
                for (Iterator<String> it = all_keys.iterator(); it.hasNext(); ) {
                    String f = it.next();
                    String keyname = "tv"+String.valueOf(j);
                    TextView textView1 = new TextView(this);
                    textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                    textView1.setGravity(1);
                    textView1.setTextSize(32);

                    textView1.setText(f);
                    textView1.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB

                    linearLayout.addView(textView1);

                    TextView textView2 = new TextView(this);
                    textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                    textView2.setGravity(1);
                    textView2.setTextSize(32);
                    textView2.setText(kategorie.get(keyname));
                    textView2.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB
                    linearLayout.addView(textView2);
                    j++;
                }

                main_profiles.addView(linearLayout);

            }else{
                break;
            }


            i++;



        }
        app_layer.removeAllViews();
        app_layer.addView(main_profiles);


        add_Kategorie=(Button)findViewById(R.id.add_Kategorie);
        add_Kategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_unterkategorie.this, AddKategorie.class);
                intent.putExtra("car",car);
                intent.putExtra("kategorie",buttonName);
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

    private int count(String car, String kategorie){
        while (true){
            int counter = 1;
            int previous_counter = 0;
            String name;

            while (true){
                name = kategorie + counter;

                SharedPreferences sh = getSharedPreferences(car, MODE_PRIVATE);

                if (sh.getString(name,"").equals("")){

                    break;
                }else{
                    counter++;

                }

            }

            return counter;

        }
    }


}