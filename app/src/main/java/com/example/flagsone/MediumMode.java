package com.example.flagsone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MediumMode extends AppCompatActivity {

    Random rand;
    private TextView countdownText, mode, countryName;
    private CountDownTimer count_down_timer;
    private long timeLeftInMillSeconds = 10000; //10
    private boolean timerRunning,isTimeOut;
    private ImageButton btnTopLeft, btnTopRight, btnBottomLeft, btnBottomRight;
    private Map<String, Integer> medium_countries_map;
    private int[] medium_countries_int,four_flags;
    private  String[] medium_countries_names, all_countries, countries;
    private ImageView heart1, heart2, heart3;
    private  ImageButton[] buttons;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        rand = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_mode);

        mode = findViewById(R.id.twDifficulty_medium);
        countdownText = findViewById(R.id.twTimer_medium);
        btnBottomLeft = findViewById(R.id.btnBottomLeft_medium);
        btnBottomRight = findViewById(R.id.btnBottomRight_medium);
        btnTopLeft = findViewById(R.id.btnTopLeft_medium);
        btnTopRight = findViewById(R.id.btnTopRight_medium);
        heart1 = findViewById(R.id.ivHeart1_medium);
        heart2 = findViewById(R.id.ivHeart2_medium);
        heart3 = findViewById(R.id.ivHeart3_medium);
        countryName = findViewById(R.id.twCountryName_medium);
        isTimeOut = false;
        four_flags = new int[4];
        buttons = new ImageButton[]{btnBottomLeft, btnBottomRight, btnTopLeft, btnTopRight};
        medium_countries_map = new HashMap<>();
        all_countries = new String[255];
        countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
                "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
                "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
                "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
                "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
                "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
                "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
                "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
                "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
                "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
                "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
                "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
                "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
                "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
                "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
                "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
                "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
                "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
                "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
                "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
                "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
                "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
                "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
                "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
                "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga",
                "Trinidad and Tobago", "Tunisia", "Türkiye", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
                "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay",
                "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
                "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

        //Locale[] locales = Locale.getAvailableLocales();
   //     for (int i = 0 ; i<255; i++){
    //        System.out.println(locales[i].getISO3Language());
   //     }
    //    for (Locale locale : locales) {
    //        System.out.println(locale.getISO3Country());
     //   }


        initAgain();
        initNames();
        medium_countries_names_adjust();

        //------------------------------------------------------------------------
        createRound();
    }
    public void initAgain()
    {
        medium_countries_int = new int[]{R.drawable.ad,R.drawable.ae,R.drawable.am,R.drawable.ar,R.drawable.at,R.drawable.bb,R.drawable.be,R.drawable.bg,R.drawable.ie,
                R.drawable.ch,R.drawable.cl,R.drawable.cr,R.drawable.cu,R.drawable.cz,R.drawable.dk,R.drawable.fi,R.drawable.fr,R.drawable.ge,R.drawable.gr,
                R.drawable.it,R.drawable.jm,R.drawable.ne,R.drawable.ng,R.drawable.pr,R.drawable.sd,R.drawable.se,R.drawable.th,
                R.drawable.tr,R.drawable.ua,R.drawable.ve,R.drawable.vn};
    }

    public void initNames(){
        medium_countries_names = new String[]{"Andorra","United Arab Emirates","Armenia","Argentina","Austria","Barbados","Belgium","Bulgaria","Ireland",
                "Switzerland","Chile","Costa Rica","Cuba","Czech Republic","Denmark","Finland","France","Georgia","Greece","Italy","Jamaica","Niger","Nigeria","Puerto Rico","Sudan","Sweden",
                "Thailand","Turkey","Ukraine","Venezuela","Vietnam"};
    }

    public void medium_countries_names_adjust(){
        for (int i = 0; i< medium_countries_names.length; i++){
            medium_countries_map.put(medium_countries_names[i], medium_countries_int[i]);
        }

    }

    public void adjustFlagsButtons() {
        int x;
        if(medium_countries_names.length != 0){
            initAgain();
            x = rand.nextInt(medium_countries_names.length);
            countryName.setText(getKey(medium_countries_names[x]));
            medium_countries_int = ArrayUtils.removeElement(medium_countries_int, medium_countries_map.get(medium_countries_names[x]));
            medium_countries_names = ArrayUtils.removeElement(medium_countries_names, countryName.getText());

            for (int i=0; i< 4; i++){
                x = rand.nextInt(medium_countries_int.length);
                buttons[i].setImageResource(medium_countries_int[x]);
                four_flags[i] = medium_countries_int[x];
                medium_countries_int = ArrayUtils.removeElement(medium_countries_int, medium_countries_int[x]);
            }

            int num; // שם דגל מתוך הארבעה שיהיה זהה למדינה המבוקשת
            num = rand.nextInt(buttons.length);
            set_win_button(num);
            wrong_answers(num);}
        else
            won_level();


    }

    public void set_win_button(int num ){
        buttons[num].setImageResource(medium_countries_map.get((String) countryName.getText()));

        buttons[num].setOnClickListener(new View.OnClickListener() { // תשובה נכונה

            @Override
            public void onClick(View v) {
                // פעולה שיוצרת שלב הבא במידה ובוחרים את הכפתור הנכון
                next_level();
                if(medium_countries_names.length != 0)
                    medium_countries_names = ArrayUtils.removeElement(medium_countries_names,countryName.getText());
            }
        });
    }

    public void won_level(){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MediumMode.this);
        myAlertBuilder.setTitle("Medium level completed");
        myAlertBuilder.setMessage("Would you like to move on to the next level?");
        myAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MediumMode.this, "Difficulty - Hard", Toast.LENGTH_SHORT).show();
                Intent mediumLevel = new Intent(MediumMode.this, HardMode.class);
                startActivity((mediumLevel));
                //finish();
            }
        });
        myAlertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MediumMode.this, "Back to Menu", Toast.LENGTH_SHORT).show();
                Intent backToMenu = new Intent(MediumMode.this, MainActivity.class);
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
        if(medium_countries_map.containsKey(key))
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

        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MediumMode.this);
        myAlertBuilder.setTitle("Nice try");
        myAlertBuilder.setMessage("Would you like to try again?");
        myAlertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MediumMode.this, "Good luck", Toast.LENGTH_SHORT).show();
                stopTimer();
                createRound();
            }
        });
        myAlertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MediumMode.this, "Back to Menu", Toast.LENGTH_SHORT).show();
                Intent backToMenu = new Intent(MediumMode.this, MainActivity.class);
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


    public void createAnotherRound(){
        adjustFlagsButtons();
    }
    public void adjustHearts(){
        heart1.setVisibility(View.VISIBLE);
        heart2.setVisibility(View.VISIBLE);
        heart3.setVisibility(View.VISIBLE);
    }
}
