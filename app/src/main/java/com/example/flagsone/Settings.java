package com.example.flagsone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Settings extends AppCompatActivity {
    RecyclerView rec;
    String s1[];
    int images[] = {R.drawable.ad,R.drawable.af,R.drawable.ag,R.drawable.ai,R.drawable.al,R.drawable.am,R.drawable.ao,R.drawable.aq,R.drawable.ar,R.drawable.as,R.drawable.at,
            R.drawable.au,R.drawable.aw,R.drawable.ax,
            R.drawable.az,R.drawable.bd,R.drawable.ba,R.drawable.bb,R.drawable.be,R.drawable.bg,R.drawable.br,
            R.drawable.ca,R.drawable.ch,R.drawable.cl,R.drawable.cn,R.drawable.cr,R.drawable.cu,R.drawable.cz,R.drawable.de,R.drawable.dk,R.drawable.es,R.drawable.fi,R.drawable.fr,R.drawable.ge,R.drawable.gn,R.drawable.gr,R.drawable.gw,
            R.drawable.it,R.drawable.in,R.drawable.ie,R.drawable.jm,R.drawable.jp,R.drawable.mx,R.drawable.ne,R.drawable.ng,R.drawable.pr,R.drawable.ru,R.drawable.sc,R.drawable.sd,R.drawable.se,R.drawable.sl,R.drawable.sr,R.drawable.td,R.drawable.tg,R.drawable.th,
            R.drawable.tj,R.drawable.tr,R.drawable.ua,R.drawable.ae,R.drawable.uk,R.drawable.us,R.drawable.ve,R.drawable.vn};;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rec = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.countries);

        RecAdapter myAdapter = new RecAdapter(this, s1, images);
        rec.setAdapter(myAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }

}
