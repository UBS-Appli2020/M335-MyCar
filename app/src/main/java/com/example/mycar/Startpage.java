package com.example.mycar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Startpage extends AppCompatActivity {
    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        // Holt sich start button und fügt einen Listener hinzu
        start_button =(Button)findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                delete_all();

                // Holt sich anzahl gepeicherter Autos
                String number = check_how_many_cars();
                Intent intent=new Intent(Startpage.this, Profilepage_overview.class);
                intent.putExtra("number_car",number);
                startActivity(intent);
            }
        });
    }

    private void delete_all(){
        /*
         * Löscht alle gespeicherten Sharedpreferences
         *
         * */
        File sharedPreferenceFile = new File("/data/data/"+ getPackageName()+ "/shared_prefs/");
        File[] listFiles = sharedPreferenceFile.listFiles();
        for (File file : listFiles) {
            file.delete();
        }
    }

    private String check_how_many_cars(){
        /*
         * Diese Funktion zählt alle gespeichereten Autos und gibt diese nummer als String zurück
         *
         * */
        int counter = 1;
        int previous_counter = 0;

        while (true){
            String name = "Car" + counter;

            SharedPreferences sh = getSharedPreferences(name, MODE_PRIVATE);


            if (sh.getString("modell","").equals("")){
                counter--;
                Log.d("TTT","There is/are just " + counter +" Cars.");
                break;
            }else{
                counter++;
                previous_counter++;
            }

        }
        if (previous_counter == 0){
             return "0";
        }else{
            return String.valueOf(counter);
        }

    }

}
