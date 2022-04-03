package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BenCangActivity extends AppCompatActivity {
    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    RelativeLayout cang;
    Animation nhapcang;
    ImageView thuyen;
    Button cuahang, khoihanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_cang);

        cang = findViewById(R.id.layoutBenCang);
        khoihanh = findViewById(R.id.btnKhoiHanh);
        cuahang = findViewById(R.id.btnCuaHang);
        thuyen = findViewById(R.id.igvThuyen);
        nhapcang = AnimationUtils.loadAnimation(this,R.anim.thuyennhapcang);
        thuyen.setAnimation(nhapcang);

        cuahang.animate().setDuration(5000).alpha(1);
        khoihanh.animate().setDuration(5000).alpha(1);

        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        if(sqliteHelper.getSaveGame(0,2,0)){
            sqliteHelper.updateSaveGame(0, 2,1);
        }

        cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BenCangActivity.this,ShopActivity.class);
                startActivity(intent);
                cang.setBackgroundResource(R.drawable.nhapcang2);
                thuyen.setImageAlpha(0);
            }
        });

        khoihanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}