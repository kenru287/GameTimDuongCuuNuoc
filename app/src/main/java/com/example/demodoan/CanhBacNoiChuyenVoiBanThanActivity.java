package com.example.demodoan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CanhBacNoiChuyenVoiBanThanActivity extends AppCompatActivity {

    RelativeLayout khung2, khung3;
    ImageView khung, shape, shape2,canh;
    TextView date,txt1,txt2,txt3,tiep,chuyenman;
    Animation blink;

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_bac_noi_chuyen_voi_ban_than);

        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        sqliteHelper.updateSaveGame(0, 0,2);

        date = findViewById(R.id.txtDateTime);
        txt1 = findViewById(R.id.txt1);
        tiep = findViewById(R.id.txttieptuc);
        khung = findViewById(R.id.imgkhung1);
        shape = findViewById(R.id.imgshape1);
        khung2 = findViewById(R.id.imgkhung2);
        shape2 = findViewById(R.id.imgshape2);
        txt2 = findViewById(R.id.txt2);
        canh = findViewById(R.id.iv_Nen_BacNoiChuyenBanThan);
        khung3 = findViewById(R.id.igmkhung3);
        txt3 = findViewById(R.id.txt3);
        chuyenman = findViewById(R.id.txtchuyenman);

        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        chuyenman.setAnimation(blink);

        date.animate().alpha(1).setDuration(1000);
        khung.animate().alpha(1).setDuration(1000);
        shape.animate().alpha(1).setDuration(5000);
        txt1.animate().alpha(1).setDuration(7000);
        tiep.animate().alpha(1).setDuration(7000);

        khung2.setEnabled(false);
        khung3.setEnabled(false);

        khung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khung.setEnabled(false);
                khung.animate().alpha(0).setDuration(300);
                shape.animate().alpha(0).setDuration(300);
                txt1.animate().alpha(0).setDuration(300);
                tiep.animate().alpha(0).setDuration(300);
                khung2.animate().alpha(1).setDuration(3000);
                shape2.animate().alpha(1).setDuration(7000);
                txt2.animate().alpha(1).setDuration(9000);
                khung2.setEnabled(true);
            }
        });

        khung2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khung3.setEnabled(true);
                khung2.setEnabled(false);
                khung2.animate().alpha(0).setDuration(300);
                shape2.animate().alpha(0).setDuration(300);
                canh.setImageResource(R.drawable.hainguoinoichuyenthuhai);
                khung3.animate().alpha(1).setDuration(2000);
                shape.animate().alpha(1).setDuration(6000);
                txt3.animate().alpha(1).setDuration(8000);
                chuyenman.setAlpha(1);
                chuyenman.setEnabled(true);
            }
        });

        khung3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CanhBacNoiChuyenVoiBanThanActivity.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}