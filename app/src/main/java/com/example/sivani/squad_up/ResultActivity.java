package com.example.sivani.squad_up;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class ResultActivity extends FragmentActivity{ 

    public TextView mTextView;
    private FusedLocationProviderClient client;
    private FirebaseFirestore mFirestore;
    private static final String TAG = "Results";
    //    private ArrayList<String> phone_numbers;
    public String newString = "";
    public String phone_number;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        client = LocationServices.getFusedLocationProviderClient(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

         mTextView = (TextView) findViewById(R.id.result_text);

        mFirestore = FirebaseFirestore.getInstance();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("TEXT");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("TEXT");
        }

//        mTextView.setText(newString);
        getDocs();
//        sendLocation();

    }

    public void getDocs() {
        Log.d(TAG, "hi!");
        mFirestore.collection("contact one").whereEqualTo("code", newString)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "in for loop");
                                phone_number = ""+(document.getData().values().toArray())[1];
//                                mTextView.setText("Phone Number:" + (document.getData().values().toArray())[1]);

                                if (ActivityCompat.checkSelfPermission(ResultActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                client.getLastLocation().addOnSuccessListener(ResultActivity.this, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        if (location != null) {
                                            // mTextView.setText(location.toString());
                                            latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                    String phoneNumber = phone_number;
                                            String myLatitude = String.valueOf(location.getAltitude());
                                            String myLongitude = String.valueOf(location.getLongitude());

                                            String message = "I feel unsafe, my location is: \n Latitude = " + myLatitude + " Longitude = " + myLongitude;
//                                            mTextView.setText(message);
                                            mTextView.setText("Sending message to " + phone_number);
                                            SmsManager smsManager = SmsManager.getDefault();
                                            smsManager.sendTextMessage(phone_number, null, message, null, null);
                                            Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
