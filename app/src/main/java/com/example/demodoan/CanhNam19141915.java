package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CanhNam19141915 extends AppCompatActivity {

    TextView txtNenDen, txtPhanNam, txtNoiDungDanTruyen, txtTiepTucChuyenCanh;
    Animation animBlink;
    MediaPlayer soundTiepTuc, soundText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_nam19141915);

        txtNenDen = findViewById(R.id.txtNenDen);
        txtPhanNam = findViewById(R.id.txtPhanNam);
        txtNoiDungDanTruyen = findViewById(R.id.txtNoiDungDanTruyen);
        txtTiepTucChuyenCanh = findViewById(R.id.txtTiepTucChuyenCanh);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);

//        Hoạt cảnh
        txtPhanNam.animate().alpha(1).setDuration(4000).withEndAction(new Runnable() {
            @Override
            public void run() {
                txtPhanNam.animate().alpha(0).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        soundText.start();
                        txtNoiDungDanTruyen.animate().alpha(1).setDuration(4000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                txtTiepTucChuyenCanh.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtTiepTucChuyenCanh.startAnimation(animBlink);
                                        txtTiepTucChuyenCanh.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                soundTiepTuc.start();
                                                Intent intent = new Intent(CanhNam19141915.this, CanhCuoiCungCuaGame.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}