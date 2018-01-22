package com.example.bear.geolocationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Location example";
    private static final int REQUEST_LOCATION_PERMISSION = 200;

    private Button mButton1;
    private Button mButton2;
    private EditText mEditText;

    private Location mLocation;
    private Address mAddress;
    private LatLng mLatLng;

    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geocoder = new Geocoder(this);

        mEditText = (EditText) findViewById(R.id.editTextLocation);

        mButton1 = (Button) findViewById(R.id.button);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = mEditText.getText().toString();
                try
                {
                    mAddress = (Address) geocoder.getFromLocationName(location,1).get(0);
                    mLatLng = new LatLng(mAddress.getLatitude(), mAddress.getLongitude());
                }
                catch (Exception e)
                {
                    Log.e("Location Error",e.toString());
                }

                if (mLatLng!=null)
                {
                    Intent checkOnMapIntent = new Intent(MainActivity.this, Zad1Activity.class);
                    Log.d("Location Error", mLatLng.toString());
                    checkOnMapIntent.putExtra(Constants.EXTRA_LOCATION, mLatLng);
                    startActivity(checkOnMapIntent);
                }

            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = mEditText.getText().toString();
                String locationWFiA = "plac Maxa Borna 9, Wrocław";
                try
                {
                    mAddress = (Address) geocoder.getFromLocationName(location,1).get(0);
                    Location loc1 = new Location("");
                    loc1.setLatitude(mAddress.getLatitude());
                    loc1.setLongitude(mAddress.getLongitude());

                    Address mAdressWFiA = (Address) geocoder.getFromLocationName(locationWFiA,1).get(0);
                    Location loc2 = new Location("");
                    loc2.setLatitude(mAdressWFiA.getLatitude());
                    loc2.setLongitude(mAdressWFiA.getLongitude());

                    float dist = loc1.distanceTo(loc2);
                    String s = "Distance to WFiA: "+Float.toString((dist/1000)) + "km";
                    Toast toast = Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch (Exception e)
                {
                    Log.e("Location Error",e.toString());
                }
            }
        });
    }
}
