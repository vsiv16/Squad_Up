package com.example.sivani.squad_up;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HighActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high);

        mTextView = (TextView) findViewById(R.id.high_text);
        mTextView.setText("HIGH RISK PAGE!");

        // add in the rest of the actions to execute

    }

}
