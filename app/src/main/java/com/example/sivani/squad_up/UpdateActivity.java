package com.example.sivani.squad_up;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.*;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mName, mNumber, mCode;
    private Button mSubmitButton;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mName = (EditText) findViewById((R.id.name));
        mNumber = (EditText) findViewById((R.id.number));
        mCode = (EditText) findViewById((R.id.code));

        mTextView = (TextView) findViewById(R.id.update_text);
        mTextView.setText("Update Contacts");

        mTextView = (TextView) findViewById(R.id.name_text);
        mTextView.setText("Enter Name:");

        mTextView = (TextView) findViewById(R.id.number_text);
        mTextView.setText("Enter Number:");

        mTextView = (TextView) findViewById(R.id.code_text);
        mTextView.setText("Enter Code:");

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setText("Submit");

        initFirestore();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference c1 = mFirestore.collection("contact one");
//                CollectionReference c2 = mFirestore.collection("contact two");

                Date date1 = new Date();
                long time1 = date1.getTime();

                Contact con1 = new Contact(mName.getText().toString(),
                        mNumber.getText().toString(), mCode.getText().toString(), time1);
                c1.add(con1);

                Toast.makeText(getApplicationContext(), "Update Submitted!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
    }

}