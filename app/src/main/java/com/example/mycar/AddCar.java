package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCar extends AppCompatActivity {
    Button button;
    EditText modell;
    EditText baujahr;
    EditText ps;
    EditText fahrzeugnummer;
    EditText getriebeart;
    EditText aufbauart;
    EditText treibstoff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcar);

        button=(Button)findViewById(R.id.add_car_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modell = (EditText) findViewById(R.id.textinput_modell);
                Log.d("TTTT", String.valueOf(modell.getText()));
                baujahr = (EditText) findViewById(R.id.textinput_baujahr);
                ps = (EditText) findViewById(R.id.textinput_ps);
                fahrzeugnummer = (EditText) findViewById(R.id.textinput_fznumber);
                getriebeart = (EditText) findViewById(R.id.textinput_getriebe);
                aufbauart = (EditText) findViewById(R.id.textinput_aufbauart);
                treibstoff = (EditText) findViewById(R.id.textinput_treibstoff);
//                Intent intent=new Intent(AddCar.this, Profilepage_overview.class);
//                startActivity(intent);
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
        Log.d("LIFECYCLE", "onResume");
    }

    @Override
    protected void onPause() {
        // call the superclass method first
        super.onPause();
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
