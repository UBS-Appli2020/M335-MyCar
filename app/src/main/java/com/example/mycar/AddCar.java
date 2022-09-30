package com.example.mycar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddCar extends AppCompatActivity{
    Button button;
    EditText modell;
    EditText baujahr;
    EditText ps;
    EditText fahrzeugnummer;
    String getriebeart;
    String aufbauart;
    String treibstoff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcar);

        //get the spinner from the xml.
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
                modell = (EditText) findViewById(R.id.textinput_modell);
                Log.d("VALUE", String.valueOf(modell.getText()));
                baujahr = (EditText) findViewById(R.id.textinput_baujahr);
                Log.d("VALUE", String.valueOf(baujahr.getText()));
                ps = (EditText) findViewById(R.id.textinput_ps);
                Log.d("VALUE", String.valueOf(ps.getText()));
                fahrzeugnummer = (EditText) findViewById(R.id.textinput_fznumber);
                Log.d("VALUE", String.valueOf(fahrzeugnummer.getText()));
//                getriebeart = (EditText) findViewById(R.id.textinput_getriebe);
                String getriebeart = dropdown.getSelectedItem().toString();
                Log.d("VALUE G", getriebeart);
//                aufbauart = (EditText) findViewById(R.id.textinput_aufbauart);
                String aufbauart = dropdown2.getSelectedItem().toString();
                Log.d("VALUE A", aufbauart);
//                treibstoff = (EditText) findViewById(R.id.textinput_treibstoff);
                String treibstoff = dropdown3.getSelectedItem().toString();
                Log.d("VALUE T", treibstoff);

                Intent intent=new Intent(AddCar.this, Profilepage_overview.class);
                startActivity(intent);
            }
        });



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
