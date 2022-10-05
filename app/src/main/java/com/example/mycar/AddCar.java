package com.example.mycar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCar extends AppCompatActivity{
    Button button, cambutton;
    EditText modell, baujahr, ps, fahrzeugnummer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcar);

        Intent intent= getIntent();
        String number = intent.getStringExtra("number_car");
        Spinner dropdown = findViewById(R.id.textinput_getriebeart);
        String[] items = new String[]{"Bitte wählen","Automatikgetriebe", "Schaltgetriebe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = findViewById(R.id.textinput_aufbauart);
        String[] items2 = new String[]{"Bitte wählen","Bus", "Cabriolet","Coupé","Kleinwagen","Kombi","Minivan","Limousine","Pick-up","SUV"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Spinner dropdown3 = findViewById(R.id.textinput_treibstoff);
        String[] items3 = new String[]{"Bitte wählen","Benzin", "Diesel","Elektro","Hybrid"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);

        button=(Button)findViewById(R.id.add_car_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                printAll_informations(dropdown,dropdown2,dropdown3);

                String savename = "Car" + String.valueOf(Integer. parseInt(number) + 1);
                SharedPreferences sh = getSharedPreferences(savename, MODE_PRIVATE);

                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEdit = sh.edit();
                if(!modell.getText().toString().equals("") && !baujahr.getText().toString().equals("") && !ps.getText().toString().equals("") && !fahrzeugnummer.getText().toString().equals("") && !dropdown.getSelectedItem().toString().equals("") && !dropdown2.getSelectedItem().toString().equals("") && !dropdown3.getSelectedItem().toString().equals("")) {
                    myEdit.putString("modell", modell.getText().toString());
                    myEdit.putString("baujahr", baujahr.getText().toString());
                    myEdit.putString("ps", ps.getText().toString());
                    myEdit.putString("fahrzeugnummer", fahrzeugnummer.getText().toString());
                    myEdit.putString("getriebeart", dropdown.getSelectedItem().toString());
                    myEdit.putString("aufbauartart", dropdown2.getSelectedItem().toString());
                    myEdit.putString("treibstoff", dropdown3.getSelectedItem().toString());

                    myEdit.apply();

                    Intent intent = new Intent(AddCar.this, Profilepage_overview.class);

                    intent.putExtra("number_car", String.valueOf(Integer.parseInt(number) + 1));
                    startActivity(intent);
                } else {
                    fehlermeldung = (TextView) findViewById(R.id.fehlermeldung);
                    fehlermeldung.setText("Bitte alle Felder ausfuellen!!");
                    fehlermeldung.setBackgroundColor(Color.parseColor("#f82c00"));
                }
            }
        });

        cambutton=(Button)findViewById(R.id.upload_picture_button);
        cambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });



    }

    private void printAll_informations(Spinner dropdown,Spinner dropdown2,Spinner dropdown3){
        modell = (EditText) findViewById(R.id.textinput_modell);
        Log.d("VALUE", String.valueOf(modell.getText()));
        baujahr = (EditText) findViewById(R.id.textinput_baujahr);
        Log.d("VALUE", String.valueOf(baujahr.getText()));
        ps = (EditText) findViewById(R.id.textinput_ps);
        Log.d("VALUE", String.valueOf(ps.getText()));
        fahrzeugnummer = (EditText) findViewById(R.id.textinput_fznumber);
        Log.d("VALUE", String.valueOf(fahrzeugnummer.getText()));
        String getriebeart = dropdown.getSelectedItem().toString();
        Log.d("VALUE G", getriebeart);
        String aufbauart = dropdown2.getSelectedItem().toString();
        Log.d("VALUE A", aufbauart);
        String treibstoff = dropdown3.getSelectedItem().toString();
        Log.d("VALUE T", treibstoff);
    }

    // This callback is called only when there is a saved instance that is previously saved by using
    // onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LIFECYCLE", "onRestoreInstanceState");
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LIFECYCLE", "onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        // call the superclass method first
        super.onStart();
        Log.d("LIFECYCLE", "onStart");
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
        Log.d("LIFECYCLE", "onStop");
    }

    @Override
    protected void onResume() {
        // call the superclass method first
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);

        // Setting the fetched data
        // in the EditTexts
//        name.setText(s1);
//        age.setText(String.valueOf(a));
        Log.d("LIFECYCLE", "onResume");
    }

    @Override
    protected void onPause() {
        // call the superclass method first
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
//        myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
//        myEdit.apply();
        Log.d("LIFECYCLE", "onPause");
    }

    @Override
    protected void onRestart() {
        // call the superclass method first
        super.onRestart();
        Log.d("LIFECYCLE", "onRestart");
    }

    @Override
    protected void onDestroy() {
        // call the superclass method first
        super.onDestroy();
        Log.d("LIFECYCLE", "onDestroy");
    }
}
