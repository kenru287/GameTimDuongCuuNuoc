package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CanhBacDenNewYork extends AppCompatActivity {

    TextView txtMoDau, txtNoiDungCanh, txtTiepTuc;
    Animation animBlink;
    MediaPlayer soundWind, soundTrain, soundText,eftButtonTiepTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_bac_den_new_york);

        txtMoDau = findViewById(R.id.txtMoDau);
        txtNoiDungCanh = findViewById(R.id.txtNoidungCanh);
        txtTiepTuc = findViewById(R.id.txtTiepTuc);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundWind = MediaPlayer.create(this, R.raw.soundwind);
        soundTrain = MediaPlayer.create(this, R.raw.soundtrain);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);
        eftButtonTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);

        txtMoDau.animate().alpha(0).setDuration(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                soundWind.start();
                soundTrain.start();

                txtNoiDungCanh.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTuc.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                txtTiepTuc.startAnimation(animBlink);
                                txtTiepTuc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        eftButtonTiepTuc.start();
                                        Intent intent = new Intent(CanhBacDenNewYork.this,CanhBacDenNewYork.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                });
                //Set giờ cho âm thanh
                soundText.start();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        soundText.stop();
                    }
                }, 2 * 1000);
            }
        });

    }
}