package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

public class CanhBacGuiThuTinhHinhThangMuoiHai extends AppCompatActivity {

    ImageView imgKhungTruyen;
    TextView txtNoiDungCanh, txtNoiDungCanhHai, txtNoiDungCanhBa, txtNoiDungCanhBon, txtTiepTuc, txtTiepTucHai, txtTiepTucBa, txtTiepTucBon, txtNgayThang, txtPhanBa;
    Animation animBlink;
    Intent intent;
    GifImageView gifHoatAnh;
    GifImageButton buttonGifImage;
    MediaPlayer soundGhiThu, soundText, soundTiepTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_bac_gui_thu_tinh_hinh_thang_muoi_hai);

        //Lấy biến
        imgKhungTruyen = findViewById(R.id.imgKhungTruyen);

        txtNoiDungCanh = findViewById(R.id.txtNoiDungCanhMot);
        txtNoiDungCanhHai = findViewById(R.id.txtNoiDungCanhHai);
        txtNoiDungCanhBa = findViewById(R.id.txtNoiDungCanhBa);
        txtNoiDungCanhBon = findViewById(R.id.txtNoiDungCanhBon);
        txtTiepTuc = findViewById(R.id.txtTiepTuc);
        txtTiepTucHai = findViewById(R.id.txtTiepTucHai);
        txtTiepTucBa = findViewById(R.id.txtTiepTucBa);
        txtTiepTucBon = findViewById(R.id.txtTiepTucBon);
        txtNgayThang = findViewById(R.id.txtNgayThang);
        txtPhanBa = findViewById(R.id.txtPhanBa);

        gifHoatAnh = findViewById(R.id.gifHoatCanh);
        buttonGifImage = new GifImageButton(this);
        buttonGifImage.setBackgroundResource(R.drawable.canhbacguithu);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundGhiThu = MediaPlayer.create(this, R.raw.soundpicelwrting);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);
        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        //Thiết kế hoạt ảnh

        soundGhiThu.start();
        soundGhiThu.setLooping(true);
        imgKhungTruyen.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                soundText.start();
//                Handler handler=new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        soundText.stop();
//                    }
//                }, 1*1000);
                txtNoiDungCanh.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTuc.animate().alpha(1);
                        txtTiepTuc.startAnimation(animBlink);
                        txtTiepTuc.animate().withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                txtTiepTuc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        soundTiepTuc.start();
                                        //Cảnh một
                                        soundText.start();
//                                        Handler handler=new Handler();
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                soundText.stop();
//                                            }
//                                        }, 1*1000);
                                        txtTiepTuc.animate().alpha(0).setDuration(0);
                                        txtNoiDungCanh.animate().alpha(0).setDuration(800);
                                        txtNoiDungCanhHai.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtTiepTucHai.animate().alpha(1).setDuration(1000);
                                                txtTiepTucHai.startAnimation(animBlink);
                                                txtTiepTucHai.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        soundText.start();
//                                                        Handler handler=new Handler();
//                                                        handler.postDelayed(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//                                                                soundText.stop();
//                                                            }
//                                                        }, 1*1000);
                                                        soundTiepTuc.start();
                                                        txtTiepTucHai.animate().alpha(0).setDuration(0);
                                                        txtNoiDungCanhHai.animate().alpha(0).setDuration(800);
                                                        txtNoiDungCanhBa.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                txtTiepTucBa.animate().alpha(1).setDuration(1000);
                                                                txtTiepTucBa.startAnimation(animBlink);
                                                                txtTiepTucBa.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        soundText.start();
//                                                                        Handler handler=new Handler();
//                                                                        handler.postDelayed(new Runnable() {
//                                                                            @Override
//                                                                            public void run() {
//                                                                                soundText.stop();
//                                                                            }
//                                                                        }, 1*1000);
                                                                        soundTiepTuc.start();
                                                                        txtTiepTucBa.animate().alpha(0).setDuration(0);
                                                                        txtNoiDungCanhBa.animate().alpha(0).setDuration(800);
                                                                        txtNoiDungCanhBon.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                txtTiepTucBon.animate().alpha(1).setDuration(1000);
                                                                                txtTiepTucBon.startAnimation(animBlink);
                                                                                txtTiepTucBon.setOnClickListener(new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View v) {
                                                                                        soundTiepTuc.start();
//                                                                                        Intent intent = new Intent(CanhBacGuiThuTinhHinhThangMuoiHai.this, MainActivity.class);
//                                                                                        startActivity(intent);
//                                                                                        finish();

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

    private void makeAnimation (View view,int anim)
    {
        Animation animation = AnimationUtils.loadAnimation(this, anim);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }
}