package com.example.sivani.squad_up;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private TextView mWelcomeTextView;
    private Button mSpeakButton;
    private Button mUpdateButton;
    private Button mNextButton;
    private String text;
    static final int UPDATE = 1;
    static final int RESULT = 2;
    private final int REQ_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeTextView = (TextView) findViewById(R.id.welcome_text_view);
        mWelcomeTextView.setText("Welcome to SquadUp");

        mSpeakButton = (Button) findViewById(R.id.speak_button);
        mSpeakButton.setText(R.string.speak_button);

        mUpdateButton = (Button) findViewById(R.id.update_button);
        mUpdateButton.setText(R.string.update_button);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setText(R.string.next_button);
        mNextButton.setEnabled(false);

        mSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add recording and speech to text code here

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                }
                catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device is not supported",
                            Toast.LENGTH_SHORT).show();
                }
                mUpdateButton.setEnabled(false);
                mNextButton.setEnabled(true);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                i.putExtra("TEXT", text);
//                Toast.makeText(getApplicationContext(), "Location Sending", Toast.LENGTH_SHORT).show();
                startActivityForResult(i, RESULT);

            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                startActivityForResult(i, UPDATE);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text = "" + result.get(0);
                    mWelcomeTextView.setText("Code Received: " + result.get(0));       // result.get(0));
                }
                break;
            }
        }

    }
}
