package com.example.demodoan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BachodayhocActivity extends AppCompatActivity {

    ImageView ivCloud;
    TextView txtDateTime, ivTextKhoangThangHai;
    Button btnBoQua;

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    final  String TABLE_NAME="TBL_SAVE_GAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bachodayhoc);

        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        sqliteHelper.updateSaveGame(0, 0,1);

        ivCloud = findViewById(R.id.ivCloud);
        ivTextKhoangThangHai = findViewById(R.id.txtKhoangThangHai);
        txtDateTime = findViewById(R.id.txtDateTime);
        btnBoQua = findViewById(R.id.btnBoQua);
        //Hoạt ảnh
        txtDateTime.animate().alpha(1).setDuration(1000);
        ivCloud.animate().translationXBy(10000).setDuration(200000);
        ivTextKhoangThangHai.animate().alpha(1).setDuration(5000);
        btnBoQua.animate().alpha(1).setDuration(5000);

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BachodayhocActivity.this, CanhBacNoiChuyenVoiBanThanActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}