package com.example.demodoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Objects;

public class ShipActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    Boolean condition=false;
    ObjectAnimator ngang,doc,xoay;
    ImageView thuyen,moc1,moc2,moc3,moc4,thuyenbien;
    int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        moc1 = findViewById(R.id.imagemoc1);
        moc2 = findViewById(R.id.imagemoc2);
        moc3 = findViewById(R.id.imagemoc3);
        moc4 = findViewById(R.id.imagemoc4);
        thuyen = findViewById(R.id.imageship);
        thuyenbien = findViewById(R.id.thuyenbien);

        moc2.setEnabled(false);
        moc3.setEnabled(false);
        moc4.setEnabled(false);
        moc2.setImageAlpha(0);
        moc3.setImageAlpha(0);
        moc4.setImageAlpha(0);

        moc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuyen.animate().y(moc1.getY()).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        moc1.setImageResource(R.drawable.mocvang);
                        moc2.setImageAlpha(255);
                        moc2.setEnabled(true);
                    }
                });
            }
        });
        moc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuyen.setImageResource(R.drawable.thuyen2);
                xoay = ObjectAnimator.ofFloat(thuyen,"rotation",180);
                xoay.start();
                thuyen.animate().y(moc2.getY()).setDuration(3000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        moc2.setImageResource(R.drawable.mocvang);
                        moc3.setImageAlpha(255);
                        moc3.setEnabled(true);
                    }
                });
            }
        });
        moc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuyen.setImageResource(R.drawable.thuyen);
                thuyen.animate().x(moc3.getX()).setDuration(1500).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        thuyen.setImageResource(R.drawable.thuyen2);
                        thuyen.animate().y(moc3.getY()).setDuration(1500).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                moc3.setImageResource(R.drawable.mocvang);
                                moc4.setImageAlpha(255);
                                moc4.setEnabled(true);
                            }
                        });
                    }
                });
            }
        });
        moc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuyen.setImageResource(R.drawable.thuyen);
                thuyen.animate().x(moc4.getX()).setDuration(3000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        thuyen.setImageResource(R.drawable.thuyen2);
                        thuyen.animate().y(moc4.getY()).setDuration(1500).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                moc4.setImageResource(R.drawable.mocvang);
                            }
                        });
                    }
                });
            }
        });
    }

    public void dichuyen(ImageView m, ImageView n){
        Runnable rdichuyen = new Runnable() {
            @Override
            public void run() {
                try{
                    x = (int) m.getX();
                    y = (int) m.getY();
                    ngang = ObjectAnimator.ofFloat(thuyen,"x",x);
                    doc = ObjectAnimator.ofFloat(thuyen,"y",y);
                    xoay = ObjectAnimator.ofFloat(thuyen,"rotation",180);
                    if (x - thuyen.getX() == 0 ){
                        thuyen.setImageResource(R.drawable.thuyen2);
                        if (y > thuyen.getY()){
                            xoay.start();
                        }
                        doc.setDuration(2000);
                        doc.start();
                        thuyen.animate().rotation(360).setStartDelay(2000);
                    }
                    else
                    {
                        thuyen.setImageResource(R.drawable.thuyen);
                        ngang.setDuration(2000);
                        ngang.start();
                    }
                    if(x == thuyen.getX() && y == thuyen.getY()){
                        mHandler.removeCallbacks(this);
                        condition=true;
                    }
                }
                finally {
                    mHandler.postDelayed(this,10000);
                }
            }
        };
        final AlertDialog.Builder builder = new AlertDialog.Builder(ShipActivity.this,R.style.AlertDialogTheme);
            builder.setTitle("Bạn có muốn di chuyển?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    rdichuyen.run();
                    m.setImageResource(R.drawable.mocvang);
                    n.setImageAlpha(255);
                    n.setEnabled(true);
                    dialog.dismiss();
                }
            })
            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
    }

    public void slideUp(final View view, final  View llDomestic){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view,"translationY",y);
        animation.setDuration(3000);
        llDomestic.setVisibility(View.GONE);
        animation.start();
    }
    public void slideDown(View view,View llDomestic){
        llDomestic.setVisibility(View.VISIBLE);
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY",   y);
        animation.setDuration(3000);
        animation.start();
    }
}