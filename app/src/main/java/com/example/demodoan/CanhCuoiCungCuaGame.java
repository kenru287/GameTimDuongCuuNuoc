package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CanhCuoiCungCuaGame extends AppCompatActivity {

    TextView txtNenDen, txtNoiDungDanTruyen, txtTiepTucChuyenCanh, txtKhungDanTruyen, txtNoiDungThuMot,txtNoiDungThuHai, txtTiepTucHoiThoai;
    Animation animBlink;
    MediaPlayer soundTiepTuc, soundText, soundText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_cuoi_cung_cua_game);

        txtNenDen = findViewById(R.id.txtNenDen);
        txtNoiDungDanTruyen = findViewById(R.id.txtNoiDungDanTruyen);
        txtTiepTucChuyenCanh = findViewById(R.id.txtTiepTucChuyenCanh);
        txtKhungDanTruyen = findViewById(R.id.txtKhungDanTruyen);
        txtNoiDungThuMot = findViewById(R.id.txtNoiDungThuMot);
        txtNoiDungThuHai = findViewById(R.id.txtNoiDungThuHai);
        txtTiepTucHoiThoai = findViewById(R.id.txtTiepTucHoiThoai);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);
        soundText2 = MediaPlayer.create(this, R.raw.sound_talking);

//        Hoạt cảnh
        soundText.start();
        txtNoiDungDanTruyen.animate().alpha(1).setDuration(4000).withEndAction(new Runnable() {
            @Override
            public void run() {
                txtTiepTucChuyenCanh.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTucChuyenCanh.startAnimation(animBlink);
                        txtTiepTucChuyenCanh.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                soundTiepTuc.start();
                                txtNenDen.animate().alpha(0).setDuration(500);
                                txtNoiDungDanTruyen.animate().alpha(0).setDuration(500);
                                txtTiepTucChuyenCanh.animate().alpha(0).setDuration(500).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtKhungDanTruyen.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                soundText2.start();
                                                txtNoiDungThuMot.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        txtTiepTucHoiThoai.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                txtTiepTucHoiThoai.startAnimation(animBlink);
                                                                txtTiepTucHoiThoai.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        soundTiepTuc.start();
                                                                        txtTiepTucHoiThoai.animate().alpha(0).setDuration(0);
                                                                        txtNoiDungThuMot.animate().alpha(0).setDuration(500);
                                                                        soundText.start();
                                                                        txtNoiDungThuHai.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                txtTiepTucHoiThoai.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        txtTiepTucHoiThoai.startAnimation(animBlink);
                                                                                        txtTiepTucHoiThoai.setOnClickListener(new View.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(View v) {
                                                                                                soundTiepTuc.start();
                                                                                                txtNenDen.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                                                                                                    @Override
                                                                                                    public void run() {
                                                                                                        Intent intent = new Intent(CanhCuoiCungCuaGame.this, CanhAfterCredit.class);
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
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}