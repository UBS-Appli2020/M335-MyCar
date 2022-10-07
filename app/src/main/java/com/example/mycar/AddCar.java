package com.example.mycar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class AddCar extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    Uri imageView_uri;
    Button button, cambutton;
    EditText modell, baujahr, ps, fahrzeugnummer;
    TextView fehlermeldung;
    ImageView imageView3;

    Intent camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // Holt die mitgegebenen Informationen
        Intent intent = getIntent();
        String number = intent.getStringExtra("number_car");

        // Hollt dropdown Element und setzt alle values
        Spinner dropdown = findViewById(R.id.textinput_getriebeart);
        String[] items = new String[]{"Bitte wählen", "Automatikgetriebe", "Schaltgetriebe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        // Hollt dropdown Element und setzt alle values
        Spinner dropdown2 = findViewById(R.id.textinput_aufbauart);
        String[] items2 = new String[]{"Bitte wählen", "Bus", "Cabriolet", "Coupé", "Kleinwagen", "Kombi", "Minivan", "Limousine", "Pick-up", "SUV"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        // Hollt dropdown Element und setzt alle values
        Spinner dropdown3 = findViewById(R.id.textinput_treibstoff);
        String[] items3 = new String[]{"Bitte wählen", "Benzin", "Diesel", "Elektro", "Hybrid"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);

        // Hollt Imageview und Button
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        button = (Button) findViewById(R.id.add_car_button);

        // Setzt Button Listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                printAll_informations(dropdown, dropdown2, dropdown3);

                // Bitmap convert to ByteArray
                imageView3.setDrawingCacheEnabled(true);
                Bitmap bm = imageView3.getDrawingCache();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byte[] compressImage = baos.toByteArray();
                String sEncodedImage = Base64.encodeToString(compressImage, Base64.DEFAULT);

                // Kontrolliert das alle Felder ausgefüllt wurden
                if (!modell.getText().toString().equals("") && !baujahr.getText().toString().equals("") && !ps.getText().toString().equals("") && !fahrzeugnummer.getText().toString().equals("") && !dropdown.getSelectedItem().toString().equals("") && !dropdown2.getSelectedItem().toString().equals("") && !dropdown3.getSelectedItem().toString().equals("")) {
                    // Speicher Name für sharedpreference
                    String savename = "Car" + String.valueOf(Integer.parseInt(number) + 1);

                    // Erstellt Sharedpreferene
                    SharedPreferences sh = getSharedPreferences(savename, MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEdit = sh.edit();

                    // Setzt alle Eingegebenen Daten in die sharedpreference
                    myEdit.putString("modell", modell.getText().toString());
                    myEdit.putString("baujahr", baujahr.getText().toString());
                    myEdit.putString("ps", ps.getText().toString());
                    myEdit.putString("fahrzeugnummer", fahrzeugnummer.getText().toString());
                    myEdit.putString("getriebeart", dropdown.getSelectedItem().toString());
                    myEdit.putString("aufbauartart", dropdown2.getSelectedItem().toString());
                    myEdit.putString("treibstoff", dropdown3.getSelectedItem().toString());
                    myEdit.putString("bitmap", sEncodedImage);
                    myEdit.apply();

                    // Leitet züruck eif die Overview Seite
                    Intent intent = new Intent(AddCar.this, Profilepage_overview.class);
                    intent.putExtra("number_car", String.valueOf(Integer.parseInt(number) + 1));
                    startActivity(intent);

                } else {
                    // Gibt eine Fehlermeldung aus wenn nicht alle Daten eingegeben wurden
                    fehlermeldung = (TextView) findViewById(R.id.fehlermeldung);
                    fehlermeldung.setText("Bitte alle Felder ausfuellen!!");
                    fehlermeldung.setBackgroundColor(Color.parseColor("#f82c00"));
                }
            }
        });

        // Kamera
        cambutton = (Button) findViewById(R.id.upload_picture_button);
        cambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {

                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();

                    }
                } else {
                    openCamera();
                }
            }
        });
    }

    private void openCamera() {
        /*
         * Diese Funktion öffnet die Kamera
         *
         * */
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageView_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent camintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camintent.putExtra(MediaStore.EXTRA_OUTPUT, imageView_uri);
        startActivityForResult(camintent, CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /*
         * Diese Funktion fragt nach Berechtigungen
         *
         * */
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Zugriff verweigert!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /*
         * Diese Funktion setzt das Bild im Imageview wenn status RESULT_OK
         *
         * */
        if (resultCode == RESULT_OK) {
            imageView3.setImageURI(imageView_uri);

        }
    }



    private void printAll_informations(Spinner dropdown, Spinner dropdown2, Spinner dropdown3) {
        /*
         * Diese Funktion gibt alle geholten Informationen aus
         *
         * */

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
}
