package com.example.flagsone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonEasy;
    private Button buttonMedium;
    private Button buttonHard;
    private Button buttonSettings;
    private ImageButton buttonMute;
    private boolean muteBool;
    private AudioManager am;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEasy = findViewById(R.id.btnEasy);
        buttonMedium = findViewById(R.id.btnMedium);
        buttonHard = findViewById(R.id.btnHard);
        buttonSettings = findViewById(R.id.btnSettings);
        buttonMute = findViewById(R.id.btnMute);
        muteBool = false;
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        buttonMute.setOnClickListener(new View.OnClickListener() { //MuteMusic
            public void onClick(View v) {
                if(muteBool == false){
                    muteBool = true;
                    buttonMute.setImageResource(R.drawable.mute);
                    muteBool = true;
                    am.setStreamMute(AudioManager.STREAM_NOTIFICATION, true); //MUTES
                    Toast.makeText(MainActivity.this,
                            "Muted", Toast.LENGTH_SHORT).show();

                }
                else {
                    buttonMute.setImageResource(R.drawable.round_volume_up_black_18dp);
                    muteBool = false;
                    am.setStreamMute(AudioManager.STREAM_NOTIFICATION, false); // UN MUTE
                    Toast.makeText(MainActivity.this,
                            "Unmuted", Toast.LENGTH_SHORT).show();

                }
            }
        });

        buttonEasy.setOnClickListener(new View.OnClickListener() { //switching to EASY mode

            public void onClick(View v) {
                Intent intentEasy = new Intent(MainActivity.this, EasyMode.class);
                startActivity((intentEasy));
            }
        });

        buttonMedium.setOnClickListener(new View.OnClickListener() { //switching to MEDIUM mode

            public void onClick(View v) {
                Intent intentMedium = new Intent(MainActivity.this, MediumMode.class);
                startActivity((intentMedium));
            }
        });

        buttonHard.setOnClickListener(new View.OnClickListener() { //switching to HARD mode

            public void onClick(View v) {
                Intent intentHard = new Intent(MainActivity.this, HardMode.class);
                startActivity((intentHard));
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() { //switching to SETTINGS

            public void onClick(View v) {
                Intent intentSettings = new Intent(MainActivity.this, Settings.class);
                startActivity((intentSettings));
            }
        });

    }
}
