package com.example.mycar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
        setContentView(R.layout.profilepage_overview);

        Intent intent= getIntent();

        String number = intent.getStringExtra("number_car");
        ScrollView app_layer = (ScrollView) findViewById (R.id.base_scrollbar);

//        LinearLayout app_layer = (LinearLayout) findViewById (R.id.base_linearlayout);

        LinearLayout main_profiles = new LinearLayout(this);
        main_profiles.setOrientation(LinearLayout.VERTICAL);

//        SharedPreferences sh2 = getSharedPreferences("Car4", MODE_PRIVATE);
//
//        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEdit = sh2.edit();
//        myEdit.putString("modell","Gt 500");
//        myEdit.apply();

        Log.d("TTT",number);
        int i = 1;
        while (i <= Integer.parseInt(number)) {

            String name = "Car" + i;
            Log.d("TTT",String.valueOf(i));
            SharedPreferences sh = getSharedPreferences(name, MODE_PRIVATE);




            LinearLayout linearLayout = new LinearLayout(this);
//            linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//                    LayoutParams.MATCH_PARENT));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 600);
            layoutParams.rightMargin = 132;
            layoutParams.topMargin = 132;
            layoutParams.leftMargin = 132;

            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
//            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            linearLayout.setBackgroundResource(R.drawable.top_background);

            LinearLayout.LayoutParams layoutParams2 =new LinearLayout.LayoutParams(300, 300);
            layoutParams2.gravity=Gravity.CENTER;

            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");
            ImageView imageview = new ImageView(this);
//            TODO
            imageview.setImageBitmap(bitmap);
            imageview.setLayoutParams(layoutParams2);






            linearLayout.addView(imageview);

            // Add textviews
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
            textView1.setGravity(1);
            textView1.setTextSize(32);
            textView1.setText(sh.getString("modell",""));
            textView1.setBackgroundColor(Color.parseColor("#FFFFFF")); // hex color 0xAARRGGBB

            ImageView sui = new ImageView(this);


            linearLayout.addView(textView1);

            main_profiles.addView(linearLayout);

//            TextView textView2 = new TextView(this);
//            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                    LayoutParams.WRAP_CONTENT);
//            layoutParams.gravity = Gravity.RIGHT;
//            layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
//            textView2.setLayoutParams(layoutParams);
//            textView2.setText("programmatically created TextView2");
//            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//            textView2.setBackgroundColor(0xffffdbdb); // hex color 0xAARRGGBB
//            linearLayout.addView(textView2);

//            app_layer.(linearLayout);
//            setContentView(linearLayout);
//            base_scrollbar.addView(linearLayout);

            i++;

            linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TESTUS","PRESSED");
                gotoProfile(name);
            }
        });

        }
        app_layer.removeAllViews();
        app_layer.addView(main_profiles);


//        String modell_name =  sh.getString("modell","");
//        txtview = (TextView) findViewById(R.id.modell_name);
//        txtview.setText(modell_name);


        button=(Button)findViewById(R.id.first_add_car);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddcar(number.toString());

            }
        });

//        LinearLayout app_layer = (LinearLayout) findViewById (R.id.car_1);
//        app_layer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TESTUS","PRESSED");
//                gotoProfile();
//            }
//        });
    }

    private void gotoAddcar(String number){
        Intent intent=new Intent(Profilepage_overview.this,AddCar.class);
        intent.putExtra("number_car",number);
        startActivity(intent);
    }

    private void gotoProfile(String carname){
        Intent intent=new Intent(Profilepage_overview.this,Profilepage_home.class);
        Log.d("uebergabe",carname);
        intent.putExtra("car",carname);
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
