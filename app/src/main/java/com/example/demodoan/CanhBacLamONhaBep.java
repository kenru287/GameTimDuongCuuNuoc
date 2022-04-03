package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CanhBacLamONhaBep extends AppCompatActivity {

    TextView txtNenDen, txtNoiDungThuNhat, txtNoiDungThuHai, txtNoiDungThuBa, txtTiepTucChuyenCanh, txtKhungTamGiac,
            txtKhungNoiDung, txtNoiDungHoiThoaiMot, txtNoiDungHoiThoaiHai, txtNoiDungHoiThoaiBa, txtTiepTucHoiThoai;
    Animation animBlink;
    MediaPlayer soundTiepTuc, soundText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_bac_lam_onha_bep);

        //Truyen tham so bien

        txtNenDen = findViewById(R.id.txtNenDen);
        txtNoiDungThuNhat = findViewById(R.id.txtNoiDungThuNhat);
        txtNoiDungThuHai = findViewById(R.id.txtNoiDungThuHai);
        txtNoiDungThuBa = findViewById(R.id.txtNoiDungThuBa);
        txtTiepTucChuyenCanh = findViewById(R.id.txtTiepTucChuyenCanh);
        txtKhungTamGiac = findViewById(R.id.txtKhungNoiDungTamGiac);
        txtKhungNoiDung = findViewById(R.id.txtKhungNoiDung);
        txtNoiDungHoiThoaiMot = findViewById(R.id.txtNoiDungHoiThoaiMot);
        txtNoiDungHoiThoaiHai = findViewById(R.id.txtNoiDungHoiThoaiHai);
        txtNoiDungHoiThoaiBa = findViewById(R.id.txtNoiDungHoiThoaiBa);
        txtTiepTucHoiThoai = findViewById(R.id.txtTiepTucHoiThoai);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);

        soundText.start();
        txtNoiDungThuNhat.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                txtTiepTucChuyenCanh.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTucChuyenCanh.startAnimation(animBlink);
                        txtTiepTucChuyenCanh.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                soundTiepTuc.start();
                                txtTiepTucChuyenCanh.animate().alpha(0).setDuration(0);
                                txtNoiDungThuNhat.animate().alpha(0).setDuration(0).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        soundText.start();
                                        txtNoiDungThuHai.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtTiepTucChuyenCanh.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        txtTiepTucChuyenCanh.startAnimation(animBlink);
                                                        txtTiepTucChuyenCanh.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                soundTiepTuc.start();
                                                                txtTiepTucChuyenCanh.animate().alpha(0).setDuration(0);
                                                                txtNoiDungThuHai.animate().alpha(0).setDuration(0).withEndAction(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        soundText.start();
                                                                        txtNoiDungThuBa.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                txtTiepTucChuyenCanh.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        txtTiepTucChuyenCanh.startAnimation(animBlink);
                                                                                        txtTiepTucChuyenCanh.setOnClickListener(new View.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(View v) {
                                                                                                soundTiepTuc.start();
                                                                                                txtTiepTucChuyenCanh.animate().alpha(0).setDuration(0);
                                                                                                txtNoiDungThuBa.animate().alpha(0).setDuration(0);
                                                                                                txtNenDen.animate().alpha(0).setDuration(2000);

                                                                                                txtKhungNoiDung.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                                                                                    @Override
                                                                                                    public void run() {
                                                                                                        soundText.start();
                                                                                                        txtKhungTamGiac.animate().alpha(1).setDuration(500);
                                                                                                        txtNoiDungHoiThoaiMot.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                txtTiepTucHoiThoai.animate().alpha(1).setDuration(500).withEndAction(new Runnable() {
                                                                                                                    @Override
                                                                                                                    public void run() {
                                                                                                                        txtTiepTucHoiThoai.startAnimation(animBlink);
                                                                                                                        txtTiepTucHoiThoai.setOnClickListener(new View.OnClickListener() {
                                                                                                                            @Override
                                                                                                                            public void onClick(View v) {
                                                                                                                                txtTiepTucHoiThoai.animate().alpha(0).setDuration(0);
                                                                                                                                txtKhungTamGiac.animate().translationX(0).translationXBy(-220).setDuration(1000);
                                                                                                                                soundTiepTuc.start();
                                                                                                                                txtNoiDungHoiThoaiMot.animate().alpha(0).setDuration(0);
                                                                                                                                soundText.start();
                                                                                                                                txtNoiDungHoiThoaiHai.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        txtTiepTucHoiThoai.animate().alpha(1).setDuration(0).withEndAction(new Runnable() {
                                                                                                                                            @Override
                                                                                                                                            public void run() {
                                                                                                                                                txtTiepTucHoiThoai.startAnimation(animBlink);
                                                                                                                                                txtTiepTucHoiThoai.setOnClickListener(new View.OnClickListener() {
                                                                                                                                                    @Override
                                                                                                                                                    public void onClick(View v) {
                                                                                                                                                        txtTiepTucHoiThoai.animate().alpha(0).setDuration(0);
                                                                                                                                                        txtKhungTamGiac.animate().translationX(0).translationXBy(220).setDuration(1000);
                                                                                                                                                        soundTiepTuc.start();
                                                                                                                                                        txtNoiDungHoiThoaiHai.animate().alpha(0).setDuration(0);
                                                                                                                                                        soundText.start();
                                                                                                                                                        txtNoiDungHoiThoaiBa.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                                                                                                            @Override
                                                                                                                                                            public void run() {
                                                                                                                                                                txtTiepTucHoiThoai.animate().alpha(1).setDuration(0).withEndAction(new Runnable() {
                                                                                                                                                                    @Override
                                                                                                                                                                    public void run() {
                                                                                                                                                                        txtTiepTucHoiThoai.startAnimation(animBlink);
                                                                                                                                                                        txtTiepTucHoiThoai.setOnClickListener(new View.OnClickListener() {
                                                                                                                                                                            @Override
                                                                                                                                                                            public void onClick(View v) {
                                                                                                                                                                                soundTiepTuc.start();
                                                                                                                                                                                Intent intent = new Intent(CanhBacLamONhaBep.this, CanhNam19141915.class);
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