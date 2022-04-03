package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CanhThayNhanThuActivity extends AppCompatActivity {

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    LinearLayout manhinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canh_thay_nhan_thu);
        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        manhinh = findViewById(R.id.manhinh);
        manhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteHelper.updateSaveGame( 0, 1, 2);
                Intent intent = new Intent(CanhThayNhanThuActivity.this, MapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}