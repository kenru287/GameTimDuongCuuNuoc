package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class CanhDenAnh extends AppCompatActivity {

    TextView txtChuyenCanh, txtNgayThang, txtKhungNoiDung, txtNoiDungCanhMot, txtTiepTuc, txtKhungNoiDungHai,
            txtNoiDungCanhHai, txtTiepTucHai;
    MediaPlayer soundText, soundTiepTuc, soundBackground;
    Animation animBlink;
    GifImageView gifHoatCanhAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_den_anh);

        txtChuyenCanh = findViewById(R.id.txtChuyenCanh);
        txtNgayThang = findViewById(R.id.txtNgayThang);
        txtKhungNoiDung = findViewById(R.id.txtKhungNoiDung);
        txtNoiDungCanhMot = findViewById(R.id.txtNoiDungCanhMot);
        txtTiepTuc = findViewById(R.id.txtTiepTuc);
        txtKhungNoiDungHai = findViewById(R.id.txtKhungNoiDungHai);
        txtNoiDungCanhHai = findViewById(R.id.txtNoiDungCanhHai);
        txtTiepTucHai = findViewById(R.id.txtTiepTucHai);

        soundText = MediaPlayer.create(this, R.raw.sound_talking);
        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        soundBackground = MediaPlayer.create(this, R.raw.soundbackground_canhdenanh);

        gifHoatCanhAnh = findViewById(R.id.gifHoatCanhAnh);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
        //Hoạt cảnh

        soundBackground.start();
        txtChuyenCanh.animate().alpha(0).setDuration(4000);
        txtKhungNoiDung.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                soundText.start();
                txtNoiDungCanhMot.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTuc.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                txtTiepTuc.startAnimation(animBlink);
                                txtTiepTuc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        soundTiepTuc.start();
                                        //Tắt hiệu ứng cũ
                                        txtKhungNoiDung.animate().alpha(0).setDuration(200);
                                        txtNoiDungCanhMot.animate().alpha(0).setDuration(200);
                                        txtTiepTuc.animate().alpha(0).setDuration(200);
                                        //Cảnh mới
                                        txtKhungNoiDungHai.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                soundText.start();
                                                txtNoiDungCanhHai.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        txtTiepTucHai.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                txtTiepTucHai.startAnimation(animBlink);
                                                                txtTiepTuc.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        soundTiepTuc.start();

                                                                        //Dừng hết âm thanh
                                                                        soundBackground.stop();
                                                                        Intent intent = new Intent(CanhDenAnh.this, MiniGameBoThanVaoLoActivity.class);
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
                        });
                    }
                });
            }
        });
    }
}