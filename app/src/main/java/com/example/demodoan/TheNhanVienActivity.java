package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TheNhanVienActivity extends AppCompatActivity {
    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    LinearLayout m;
    TextView t;
    Animation blink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_nhan_vien);
        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        sqliteHelper.updateSaveGame(0, 1,1);
        m = findViewById(R.id.manhinh);
        t = findViewById(R.id.txttieptuc);
        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        t.setAnimation(blink);
        t.animate().alpha(1).setDuration(500);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheNhanVienActivity.this,CanhThayNhanThuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}