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

import pl.droidsonroids.gif.GifImageView;

public class CanhBacGuiKhamNhoChuyenSoTien extends AppCompatActivity {

    ImageView imgKhungTruyen;
    TextView txtNoiDungCanh, txtTiepTuc, txtNgayThang, txtPhanBa;
    Animation animBlink;
    Intent intent;
    GifImageView gifHoatAnh;

    MediaPlayer soundPicelWrtiting, soundTiepTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_bac_gui_kham_nho_chuyen_so_tien);

        //Lấy biến
        imgKhungTruyen = findViewById(R.id.imgKhungTruyen);
        txtNoiDungCanh = findViewById(R.id.txtNoiDungCanh);
        txtTiepTuc = findViewById(R.id.txtTiepTuc);
        txtNgayThang = findViewById(R.id.txtNgayThang);
        txtPhanBa = findViewById(R.id.txtPhanBa);
        gifHoatAnh = findViewById(R.id.gifHoatCanh);
        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);

        soundPicelWrtiting = MediaPlayer.create(this, R.raw.soundpicelwrting);
        soundTiepTuc = MediaPlayer.create(this, R.raw.btn_tieptuc);

        //Thiết kế hoạt ảnh
        soundPicelWrtiting.start();
        soundPicelWrtiting.setLooping(true);
        imgKhungTruyen.animate().alpha(1).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
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
                                        soundPicelWrtiting.stop();
                                        txtPhanBa.animate().alpha(0).alphaBy(1).setDuration(2000).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtPhanBa.animate().alpha(1).alphaBy(0).setDuration(2000).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        intent = new Intent(CanhBacGuiKhamNhoChuyenSoTien.this, MainActivity.class);
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

    private void makeAnimation (View view,int anim)
    {
        Animation animation = AnimationUtils.loadAnimation(this, anim);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }
}