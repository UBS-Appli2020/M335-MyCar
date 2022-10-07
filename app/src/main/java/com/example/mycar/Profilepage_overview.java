package com.example.mycar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class Profilepage_overview extends AppCompatActivity {
    Button button;
    TextView txtview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage_overview);

        Intent intent= getIntent();

        String number = intent.getStringExtra("number_car");
        ScrollView app_layer = (ScrollView) findViewById (R.id.base_scrollbar);

        LinearLayout main_profiles = new LinearLayout(this);
        main_profiles.setOrientation(LinearLayout.VERTICAL);

        Log.d("TTT",number);
        int i = 1;
        while (i <= Integer.parseInt(number)) {

            String name = "Car" + i;
            Log.d("TTT",String.valueOf(i));
            SharedPreferences sh = getSharedPreferences(name, MODE_PRIVATE);



            // Erstellt ein neues LinearLayout
            LinearLayout linearLayout = new LinearLayout(this);

            // Erstellt das Layout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 600);
            layoutParams.rightMargin = 132;
            layoutParams.topMargin = 132;
            layoutParams.leftMargin = 132;

            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundResource(R.drawable.top_background);

            LinearLayout.LayoutParams layoutParams2 =new LinearLayout.LayoutParams(300, 300);
            layoutParams2.gravity=Gravity.CENTER;

            // Setzt das Bild in ein Imageview
            ImageView imageview = new ImageView(this);

            // Kontrolliert es ein gespeichertes Bild gibt
            if(sh.contains("bitmap"))
            {
                // Hollt das Bild als String aus dem Sharedpreference
                String encodedImage = sh.getString("bitmap",null);

                // Decoded es zu byte
                byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);

                // Decoded es zu einer Bitmap
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(b, 0, b.length);

            // Setzt das Layout
            imageview.setImageBitmap(bitmap2);
            imageview.setLayoutParams(layoutParams2);



            linearLayout.addView(imageview);

            // Erstellt ein Layout
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams3.bottomMargin=70;

            // Erstellt das Textview mit layout
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(layoutParams3);
            textView1.setGravity(1);
            textView1.setTextSize(32);

            textView1.setText(sh.getString("modell",""));
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB

            ImageView sui = new ImageView(this);

            // Fügt alles ins linearLayout
            linearLayout.addView(textView1);
            }

            main_profiles.addView(linearLayout);

            i++;

            linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TESTUS","PRESSED");
                gotoProfile(name);
            }
        });

        }

        // Fügt alles ins scrollview
        app_layer.removeAllViews();
        app_layer.addView(main_profiles);

        // Hollt button und setzt einen Listener
        button=(Button)findViewById(R.id.first_add_car);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddcar(number.toString());

            }
        });

    }

    private void gotoAddcar(String number){
        /*
        * Diese Function leitet auf die Klasse AddCar
        * Mitgegeben wird der key zum sharedpreference des Autos
        *
        * */
        Intent intent=new Intent(Profilepage_overview.this,AddCar.class);
        intent.putExtra("number_car",number);
        startActivity(intent);
    }

    private void gotoProfile(String carname){
        /*
         * Diese Function leitet auf die Klasse Profilepage_home
         * Mitgegeben wird der key zum sharedpreference des Autos
         *
         * */
        Intent intent=new Intent(Profilepage_overview.this,Profilepage_home.class);
        Log.d("uebergabe",carname);
        intent.putExtra("car",carname);
        startActivity(intent);
    }
}
