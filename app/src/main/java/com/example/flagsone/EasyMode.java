package com.example.flagsone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class EasyMode extends AppCompatActivity {
    Random rand;
    private TextView countdownText;
    private CountDownTimer count_down_timer;
    private long timeLeftInMillSeconds = 10000; //10
    private boolean timerRunning;
    private boolean isTimeOut;
    private ImageButton btnTopLeft;
    private ImageButton btnTopRight;
    private ImageButton btnBottomLeft;
    private ImageButton btnBottomRight;
    private Map<String, Integer> easy_countries_map;
    private int[] all_countries;
    private int[] easy_countries_int;
    private  String[] easy_countries_names;
    private ImageView heart1, heart2, heart3;
    private TextView countryName;
    private  ImageButton[] buttons;
    private int[] four_flags;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rand = new Random();
        setContentView(R.layout.activity_easy_mode);

        countdownText = findViewById(R.id.twTimer);
        btnBottomLeft = findViewById(R.id.btnBottomLeft);
        btnBottomRight = findViewById(R.id.btnBottomRight);
        btnTopLeft = findViewById(R.id.btnTopLeft);
        btnTopRight = findViewById(R.id.btnTopRight);
        heart1 = findViewById(R.id.ivHeart1);
        heart2 = findViewById(R.id.ivHeart2);
        heart3 = findViewById(R.id.ivHeart3);
        countryName = findViewById(R.id.twCountryName);
        isTimeOut = false;
        four_flags = new int[4];
        buttons = new ImageButton[]{btnBottomLeft,btnBottomRight,btnTopLeft,btnTopRight};


        easy_countries_map = new HashMap<>();
        all_countries = new int[]{R.drawable.ar, R.drawable.at, R.drawable.au, R.drawable.ax, R.drawable.az, R.drawable.ba, R.drawable.bb, R.drawable.be, R.drawable.bg,
                R.drawable.bt, R.drawable.ca, R.drawable.ch, R.drawable.cl, R.drawable.cr, R.drawable.cu, R.drawable.cz, R.drawable.de, R.drawable.dk,
                R.drawable.fi, R.drawable.fr, R.drawable.ge, R.drawable.gn, R.drawable.gr, R.drawable.gw, R.drawable.it, R.drawable.jm, R.drawable.jp,
                R.drawable.ne, R.drawable.ng, R.drawable.pr, R.drawable.sc, R.drawable.sd, R.drawable.se, R.drawable.sl, R.drawable.sr, R.drawable.td,
                R.drawable.tg, R.drawable.th, R.drawable.tj, R.drawable.tr, R.drawable.ua, R.drawable.uk, R.drawable.us, R.drawable.ve, R.drawable.vn,R.drawable.ru};
        easy_countries_int = new int[]{R.drawable.br,R.drawable.mx,R.drawable.es,R.drawable.de,R.drawable.uk, R.drawable.us, R.drawable.jp,R.drawable.au, R.drawable.ca, R.drawable.cn ,R.drawable.ru,R.drawable.in};
        easy_countries_names = new String[]{"Brazil","Mexico","Spain","Germany","United Kingdom", "USA", "Japan","Australia","Canada","China","Russia","India"};

        for (int i = 0; i< easy_countries_names.length; i++){
            easy_countries_map.put(easy_countries_names[i],easy_countries_int[i]);
        }

        //------------------------------------------------------------------------
        createRound();
    }

    public void initAgain()
    {
        easy_countries_int = new int[]{R.drawable.br,R.drawable.mx,R.drawable.es,R.drawable.de,R.drawable.uk, R.drawable.us, R.drawable.jp,R.drawable.au, R.drawable.ca, R.drawable.cn ,R.drawable.ru,R.drawable.in};

    }


    public void adjustFlagsButtons() {
        int x;
        if(easy_countries_names.length != 0){
            initAgain();
            x = rand.nextInt(easy_countries_names.length);
            countryName.setText(getKey(easy_countries_names[x]));
            easy_countries_int = ArrayUtils.removeElement(easy_countries_int,easy_countries_map.get(easy_countries_names[x]));
            easy_countries_names = ArrayUtils.removeElement(easy_countries_names, easy_countries_names[x]);

            for (int i=0; i< 4; i++){
                x = rand.nextInt(easy_countries_int.length);
                buttons[i].setImageResource(easy_countries_int[x]);
                four_flags[i] = easy_countries_int[x];
                easy_countries_int = ArrayUtils.removeElement(easy_countries_int,easy_countries_int[x]);
            }

            int num; // שם דגל מתוך הארבעה שיהיה זהה למדינה המבוקשת
            num = rand.nextInt(4);
            set_win_button(num);
            wrong_answers(num);}
        else
            won_level();


    }

    public void set_win_button(int num ){
        buttons[num].setImageResource(easy_countries_map.get((String) countryName.getText()));

        buttons[num].setOnClickListener(new View.OnClickListener() { // תשובה נכונה

            @Override
            public void onClick(View v) {
                // פעולה שיוצרת שלב הבא במידה ובוחרים את הכפתור הנכון
                next_level();
                if(easy_countries_names.length != 0)
                    easy_countries_names = ArrayUtils.removeElement(easy_countries_names,countryName.getText());
            }
        });
    }

    public void won_level(){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(EasyMode.this);
        myAlertBuilder.setTitle("Easy level completed");
        myAlertBuilder.setMessage("Would you like to move on to the next level?");
        myAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EasyMode.this, "Difficulty - Medium", Toast.LENGTH_SHORT).show();
                Intent mediumLevel = new Intent(EasyMode.this, MediumMode.class);
                startActivity((mediumLevel));
            }
        });
        myAlertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EasyMode.this, "Back to Menu", Toast.LENGTH_SHORT).show();
                Intent backToMenu = new Intent(EasyMode.this, MainActivity.class);
                startActivity((backToMenu));

            }
        });
        myAlertBuilder.show();

    }
    public void wrong_answers(int num){
        // תשובות לא נכונות
        int[] other_buttons = all_numbers_but_num(num, buttons.length);
        wrong_buttons(other_buttons);
    }

    public void wrong_buttons(int[] a){
        for (int i=0; i< a.length; i++){
            buttons[a[i]].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mistake();
                }
            });
        }
    }
    public void mistake(){
        stopTimer();
        startTimer();
        downHp();
    }
    public void next_level(){
        stopTimer();
        startTimer();
        adjustFlagsButtons();
    }

    public int[] all_numbers_but_num(int num, int length){
        int[] a;
        int j;
        j = 0;
        a = new int[length-1];
        for (int i = 0; i < length; i++){
            if(i != num){
                a[j] = i;
                j++;
            }
        }
        return  a;
    }

    public String getKey(String key)
    {
        if(easy_countries_map.containsKey(key))
        {
            return key;
        }
        return null;
    }
    public void startTimer() {
        timeLeftInMillSeconds = 10000;
        count_down_timer = new CountDownTimer(timeLeftInMillSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillSeconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer() {
        count_down_timer.cancel();
        timerRunning = false;
        timeLeftInMillSeconds = 0;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMillSeconds / 10000;
        int seconds = (int) timeLeftInMillSeconds % 10000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);

        if (minutes == 0 && seconds == 0) {
            isTimeOut = true;
            downHp();
            startTimer();
        }
    }

    public void downHp() {
        if (heart1.getVisibility() == View.VISIBLE) {
            heart1.setVisibility(View.INVISIBLE);
            createAnotherRound();
            } else if (heart2.getVisibility() == View.VISIBLE) {
                heart2.setVisibility(View.INVISIBLE);
                createAnotherRound();
                } else if (heart3.getVisibility() == View.VISIBLE){
                    heart3.setVisibility(View.INVISIBLE);
                    anotherAttemptDialog();
                }
        }

    public void anotherAttemptDialog() {

            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(EasyMode.this);
            myAlertBuilder.setTitle("Nice try");
            myAlertBuilder.setMessage("Would you like to try again?");
            myAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(EasyMode.this, "Good luck", Toast.LENGTH_SHORT).show();
                    stopTimer();
                    createRound();
                }
            });
            myAlertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(EasyMode.this, "Back to Menu", Toast.LENGTH_SHORT).show();
                    Intent backToMenu = new Intent(EasyMode.this, MainActivity.class);
                    startActivity((backToMenu));

                }
            });
            myAlertBuilder.show();

    }
    public void createRound(){
        startTimer();
        adjustFlagsButtons();
        adjustHearts();
        initNames();
    }
    public void initNames(){
        easy_countries_names = new String[]{"Brazil","Mexico","Spain","Germany","United Kingdom", "USA", "Japan","Australia","Canada","China","Russia","India"};
    }

    public void createAnotherRound(){
        adjustFlagsButtons();
    }
    public void adjustHearts(){
        heart1.setVisibility(View.VISIBLE);
        heart2.setVisibility(View.VISIBLE);
        heart3.setVisibility(View.VISIBLE);
    }
}