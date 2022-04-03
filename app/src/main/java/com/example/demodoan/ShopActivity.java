package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";

    RelativeLayout nen2, nen3;
    Button mua, thoat, muavatpham, banggac, tuimau, manalon, manannho;
    ImageView igmMoTa;
    TextView txtMoTa, txttien, txtThongBao;
    Integer x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mua = findViewById(R.id.btnMua);
        thoat = findViewById(R.id.btnThoat);
        muavatpham = findViewById(R.id.btnMuaVatPham);
        tuimau = findViewById(R.id.btnTuiMau);
        banggac = findViewById(R.id.btnBangGac);
        manalon = findViewById(R.id.btnManaLon);
        manannho = findViewById(R.id.btnManaNho);
        nen2 = findViewById(R.id.Nen2);
        nen3 = findViewById(R.id.Nen3_MoTa);
        igmMoTa = findViewById(R.id.igvMoTa);
        txtMoTa = findViewById(R.id.txtMoTa);
        txttien = findViewById(R.id.Tien);
        txtThongBao = findViewById(R.id.txtThongBao);

        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        int tien = sqliteHelper.getTien(0);
        txttien.setText(String.valueOf(tien));
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nen2.setAlpha(1);
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        banggac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThongBao.setAlpha(0);
                nen3.setAlpha(1);
                muavatpham.setAlpha(1);
                igmMoTa.setImageResource(R.drawable.bndage);
                txtMoTa.setText(R.string.txtBangGac);
                x = 0;
            }
        });

        tuimau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThongBao.setAlpha(0);
                nen3.setAlpha(1);
                muavatpham.setAlpha(1);
                igmMoTa.setImageResource(R.drawable.tuimau);
                txtMoTa.setText(R.string.txtTuiMau);
                x = 1;
            }
        });

        manannho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThongBao.setAlpha(0);
                nen3.setAlpha(1);
                muavatpham.setAlpha(1);
                igmMoTa.setImageResource(R.drawable.manalittle);
                txtMoTa.setText(R.string.txtManaNho);
                x = 2;
            }
        });

        manalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThongBao.setAlpha(0);
                nen3.setAlpha(1);
                muavatpham.setAlpha(1);
                igmMoTa.setImageResource(R.drawable.manabig);
                txtMoTa.setText(R.string.txtManaLon);
                x = 3;
            }
        });

        muavatpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x == 0){
                    if (tien < 15){
                        txtThongBao.setAlpha(1);
                    }

                }
                if(x == 1){
                    if (tien < 50){
                        txtThongBao.setAlpha(1);
                    }
                }
                if(x == 2){
                    if (tien < 15){
                        txtThongBao.setAlpha(1);
                    }
                }
                if(x == 3){
                    if (tien < 50){
                        txtThongBao.setAlpha(1);
                    }
                }
            }
        });
    }
}