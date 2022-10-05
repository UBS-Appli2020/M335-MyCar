package com.example.mycar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profilepage_home extends AppCompatActivity {
    TextView button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage_home);

        Intent intent = getIntent();
        String car = intent.getStringExtra("car");

        SharedPreferences sh = getSharedPreferences(car,MODE_PRIVATE);
        String text = sh.getString("modell","");

        button5=(TextView)findViewById(R.id.modell_name);
        button5.setText(text);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Profilepage_home.this, activity_unterkategorie.class);
//                String str5 = String.valueOf(button5.getId());
//                intent.putExtra("buttonName",str5);
//                startActivity(intent);
//            }
//        });
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
