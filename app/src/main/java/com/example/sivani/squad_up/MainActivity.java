package com.example.sivani.squad_up;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private TextView mWelcomeTextView;
    private Button mSpeakButton;
    private Button mNextButton;
    private Button mLowButton;
    private Button mHighButton;
    static final int LOW = 1;
    static final int HIGH = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        mWelcomeTextView.setText("Welcome to SquadUp");

        mSpeakButton = (Button) findViewById(R.id.speak_button);
        mSpeakButton.setText(R.string.speak_button);

        mLowButton = (Button)findViewById(R.id.low_button);
        mLowButton.setText(R.string.low_button);

        mHighButton = (Button)findViewById(R.id.high_button);
        mHighButton.setText(R.string.high_button);

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setText(R.string.next_button);
        mNextButton.setEnabled(false);

        mSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add recording and speech to text code here
            }
        });

        mLowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LowActivity.class);
                // send over the text data (put extra)
                startActivityForResult(i, LOW);

            }
        });

        mHighButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HighActivity.class);
                // send over the text data (put extra)
                startActivityForResult(i, HIGH);

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LowActivity.class);
                // send over the text data (put extra)
                // startActivityForResult(i, ACTION);

            }
        });

    }


}
