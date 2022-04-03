package com.example.demodoan;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class CanhAfterCredit extends AppCompatActivity {

    TextView txtKetThuc1, txtKetThuc2, txtKetThuc3, txtKetThuc4, txtKetThuc5, txtKetThuc6;
    ImageView igvKetThuc1, igvKetThuc2, igvKetThuc2_PhacThao, igvKetThuc4, igvKetThuc6;
    GifImageView gifKetThuc3;

    MediaPlayer soundEndGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_after_credit);

        txtKetThuc1 = findViewById(R.id.txtKetThuc1);
        txtKetThuc2 = findViewById(R.id.txtKetThuc2);
        txtKetThuc3 = findViewById(R.id.txtKetThuc3);
        txtKetThuc4 = findViewById(R.id.txtKetThuc4);
        txtKetThuc5 = findViewById(R.id.txtKetThuc5);
        txtKetThuc6 = findViewById(R.id.txtKetThuc6);

        igvKetThuc1 = findViewById(R.id.igvKetThuc1);
        igvKetThuc2 = findViewById(R.id.igvKetThuc2);
        igvKetThuc2_PhacThao = findViewById(R.id.igvKetThuc2_phacthao);
        igvKetThuc4 = findViewById(R.id.igvKetThuc4);
        igvKetThuc6 = findViewById(R.id.igvKetThuc6);

        gifKetThuc3 = findViewById(R.id.gifKetThuc3);

        soundEndGame = MediaPlayer.create(this, R.raw.sound_eding);

        soundEndGame.start();
        txtKetThuc1.animate().alpha(1).setDuration(5000);
        igvKetThuc1.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
            @Override
            public void run() {
                txtKetThuc1.animate().alpha(0).setDuration(2500);
                igvKetThuc1.animate().alpha(0).setDuration(2500);

                igvKetThuc2.animate().alpha(1).setDuration(5000);
                igvKetThuc2_PhacThao.animate().alpha(1).setDuration(5000);
                txtKetThuc2.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        igvKetThuc2.animate().alpha(0).setDuration(2500);
                        igvKetThuc2_PhacThao.animate().alpha(0).setDuration(2500);
                        txtKetThuc2.animate().alpha(0).setDuration(2500);

                        gifKetThuc3.animate().alpha(1).setDuration(5000);
                        txtKetThuc3.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                gifKetThuc3.animate().alpha(0).setDuration(2500);
                                txtKetThuc3.animate().alpha(0).setDuration(2500);

                                txtKetThuc4.animate().alpha(1).setDuration(5000);
                                igvKetThuc4.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtKetThuc4.animate().alpha(0).setDuration(2500);
                                        igvKetThuc4.animate().alpha(0).setDuration(2500);

                                        txtKetThuc5.animate().alpha(1).setDuration(5000).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtKetThuc5.animate().alpha(0).setDuration(2500);

                                                igvKetThuc6.animate().alpha(1).setDuration(5000);
                                                txtKetThuc6.animate().alpha(1).setDuration(5000);
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