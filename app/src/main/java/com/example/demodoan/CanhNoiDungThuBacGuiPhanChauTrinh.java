package com.example.demodoan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class CanhNoiDungThuBacGuiPhanChauTrinh extends AppCompatActivity {

    //Tạo biến
    GifImageView gifCanhBacGuiThu , gifNoidungThu;
    TextView txtNoiDungDanTruyen, txtTiepTuc, txtNenNoiDungThu, txtNoiDungThu, txtTiepTucHai, txtTiepTucBa,
            txtKhungNoiDungLon, txtNoiDungHai, txtNenDen, txtPhanBon, txtHoiThoiSangAnh;
    ImageView igvKhungDanTruyen, igvKhungThu;
    MediaPlayer soundText, soundText1, soundText2, soundTiepTuc, soundWrtiting;
    Animation animBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_noi_dung_thu_bac_gui_phan_chau_trinh);

        //Truyền view
        gifCanhBacGuiThu = findViewById(R.id.gifCanhBacGuiThu);
        gifNoidungThu = findViewById(R.id.gifNoidungThu);

        txtNoiDungDanTruyen = findViewById(R.id.txtNoiDungDanTruyen);
        txtNoiDungHai = findViewById(R.id.txtNoiDungThuHai);
        txtTiepTuc = findViewById(R.id.txtTiepTuc);
        txtNenNoiDungThu = findViewById(R.id.txtNenNoiDungThu);
        txtNoiDungThu = findViewById(R.id.txtNoiDungThu);
        txtKhungNoiDungLon = findViewById(R.id.txtKhungNoiDungLon);
        txtTiepTucHai = findViewById(R.id.txtTiepTucHai);
        txtTiepTucBa = findViewById(R.id.txtTiepTucBa);
        txtPhanBon = findViewById(R.id.txtPhanBon);
        txtNenDen = findViewById(R.id.txtNenDen);
        txtHoiThoiSangAnh = findViewById(R.id.txtHoiThoiSangAnh);

        igvKhungDanTruyen = findViewById(R.id.igvKhungDanTruyen);
        igvKhungThu = findViewById(R.id.igvKhungThu);

        soundText1 = MediaPlayer.create(this, R.raw.sound_talking);
        soundText2 = MediaPlayer.create(this, R.raw.sound_talking);
        soundText = MediaPlayer.create(this, R.raw.sound_talking);
        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);
        soundWrtiting = MediaPlayer.create(this, R.raw.soundpicelwrting);

        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundWrtiting.start();
        soundWrtiting.setLooping(true);
        igvKhungDanTruyen.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
            @Override
            public void run() {
                soundText.start();
                //Hẹn giờ cho âm thanh
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        soundText.stop();
                    }
                }, 2 * 1000);
                txtNoiDungDanTruyen.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtTiepTuc.animate().alpha(1).setDuration(1000);
                        txtTiepTuc.startAnimation(animBlink);
                        txtTiepTuc.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                soundWrtiting.stop();
                                soundTiepTuc.start();
                                txtNenNoiDungThu.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        gifNoidungThu.animate().alpha(1).setDuration(1000);
                                        igvKhungThu.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                soundText1.start();
                                                igvKhungThu.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        txtNoiDungThu.animate().alpha(1).setDuration(1000);
                                                        txtTiepTucHai.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                txtTiepTucHai.startAnimation(animBlink);
                                                                txtTiepTucHai.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        soundTiepTuc.start();
                                                                        igvKhungThu.animate().alpha(0).setDuration(500);
                                                                        txtKhungNoiDungLon.animate().alpha(1).setDuration(800).withEndAction(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                soundText2.start();
                                                                                txtNoiDungHai.animate().alpha(1).setDuration(1000);
                                                                                txtTiepTucBa.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        txtTiepTucBa.startAnimation(animBlink);
                                                                                        txtTiepTucBa.setOnClickListener(new View.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(View v) {
                                                                                                soundTiepTuc.start();
                                                                                                txtNenDen.animate().alpha(1).setDuration(2000);
                                                                                                txtPhanBon.animate().alpha(1).setDuration(2000).withEndAction(new Runnable() {
                                                                                                    @Override
                                                                                                    public void run() {
                                                                                                        txtPhanBon.animate().alpha(0).setDuration(2000);
                                                                                                        txtHoiThoiSangAnh.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                Intent intent = new Intent(CanhNoiDungThuBacGuiPhanChauTrinh.this, CanhDenAnh.class);
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
}