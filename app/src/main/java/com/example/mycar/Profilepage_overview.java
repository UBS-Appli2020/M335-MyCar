package com.example.mycar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Profilepage_overview extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage_overview);

        button=(Button)findViewById(R.id.first_add_car);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddcar();

            }
        });

        LinearLayout app_layer = (LinearLayout) findViewById (R.id.car_1);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TESTUS","PRESSED");
                gotoProfile();
            }
        });
    }

    private void gotoAddcar(){
        Intent intent=new Intent(Profilepage_overview.this,AddCar.class);
        startActivity(intent);
    }

    private void gotoProfile(){
        Intent intent=new Intent(Profilepage_overview.this,Profilepage_home.class);
        startActivity(intent);
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
