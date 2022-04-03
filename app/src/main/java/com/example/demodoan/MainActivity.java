package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Animation slDown, slUp, blink;
    ImageView timdcn, intro;
    TextView skip, t;
    Button bd, choimoi;
    Dialog DialogSaveGame;

    private SqliteHelper sqliteHelper;
    final  String DB_NAME = "DB_Demo";
    final  String TABLE_NUT = "TBL_NUT";
    final  String TABLE_SAVEGAME = "TBL_SAVE_GAME";
    final  String TABLE_VATPHAM = "TBL_VAT_PHAM";
    final  String TABLE_NGUOICHOI = "TBL_NGUOI_CHOI";
    final  String TABLE_CT_SAVEGAME = "TBL_CT_SAVE_GAME";
    final String TABLE_TUIDO = "TBL_TUI_DO";

    String CreateTableSaveGame, CreateTableTuiDo, CreateTableVatPham, CreateTableNguoiChoi, CreateTableNut, CreateTableCTSG;
    int id, ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        DialogSaveGame = new Dialog(this);
        //Gán Id
        t = findViewById(R.id.test);
        timdcn = findViewById(R.id.image);
        bd = findViewById(R.id.btnBatDau);
        intro = findViewById(R.id.ivIntro);
        skip = findViewById(R.id.textview2);

        //Animations
        slDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        slUp = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        blink = AnimationUtils.loadAnimation(this,R.anim.blink);

        timdcn.setAnimation(slDown);
        bd.setAnimation(slUp);
        skip.setAnimation(blink);
        skip.setAlpha(0);
        skip.setEnabled(false);

        //Xử lý và sử dụng SqliteHelper
        sqliteHelper = new SqliteHelper(this,DB_NAME,1);

        CreateTableNut = "CREATE TABLE TBL_NUT (\n" +
                "    NUT INT PRIMARY KEY\n" +
                ")";

        CreateTableSaveGame = "CREATE TABLE TBL_SAVE_GAME (\n" +
                "    ID           INT      PRIMARY KEY\n" +
                "                          NOT NULL,\n" +
                "    NUT          INT REFERENCES TBL_NUT (NUT),\n" +
                "    CANH         INT,\n" +
                "    NGAY         STRING,\n" +
                "    THOIGIANCHOI STRING\n" +
                "    TIEN           INT,\n" +
                ")";

        CreateTableVatPham = "CREATE TABLE TBL_VAT_PHAM (\n" +
                "    MAVATPHAM  INT     PRIMARY KEY\n" +
                "                       NOT NULL,\n" +
                "    TENVATPHAM STRING,\n" +
                "    KICHCO     BOOLEAN,\n" +
                "    SL         BOOLEAN\n" +
                ")";

        CreateTableTuiDo = "CREATE TABLE TBL_TUI_DO (\n" +
                "    MAVATPHAM INT     NOT NULL,\n" +
                "    ID   INT     NOT NULL,\n" +
                "    SOLUONG   INT,\n" +
                "    FOREIGN KEY (\n" +
                "        MANHANVAT\n" +
                "    )\n" +
                "    REFERENCES TBL_SAVE_GAME (ID),\n" +
                "    FOREIGN KEY (\n" +
                "        ID\n" +
                "    )\n" +
                "    REFERENCES TBL_VAT_PHAM (MAVATPHAM),\n" +
                "    PRIMARY KEY (\n" +
                "        MAVATPHAM,\n" +
                "        ID\n" +
                "    )\n" +
                ")";

        CreateTableNguoiChoi = "CREATE TABLE TBL_NGUOI_CHOI (\n" +
                "    ID         INT PRIMARY KEY,\n" +
                "    IDSAVEGAME INT,\n" +
                "    NUT        INT\n" +
                ")";

        CreateTableCTSG = "CREATE TABLE TBL_CT_SAVE_GAME (\n" +
                "    ID   INT     NOT NULL,\n" +
                "    NUT  INT     NOT NULL,\n" +
                "    DIEM INTEGER,\n" +
                "    PRIMARY KEY (\n" +
                "        ID,\n" +
                "        NUT\n" +
                "    ),\n" +
                "    FOREIGN KEY (\n" +
                "        NUT\n" +
                "    )\n" +
                "    REFERENCES TBL_NUT (NUT),\n" +
                "    FOREIGN KEY (\n" +
                "        ID\n" +
                "    )\n" +
                "    REFERENCES TBL_SAVE_GAME (ID) \n" +
                ")";

        //id = sqliteHelper.getIDNguoiChoi();
       // ma = sqliteHelper.getMaNguoiChoi();

        /*sqliteHelper.deleteTable(TABLE_CT_SAVEGAME);
        sqliteHelper.deleteTable(TABLE_SAVEGAME);
        sqliteHelper.deleteTable(TABLE_VATPHAM);
        sqliteHelper.deleteTable(TABLE_NUT);
        sqliteHelper.deleteTable(TABLE_NGUOICHOI);*/


        sqliteHelper.createTable(CreateTableNut);
        sqliteHelper.createTable(CreateTableSaveGame);
        sqliteHelper.createTable(CreateTableCTSG);
        sqliteHelper.createTable(CreateTableVatPham);
        sqliteHelper.createTable(CreateTableTuiDo);
        sqliteHelper.createTable(CreateTableNguoiChoi);

        /*sqliteHelper.insertNut(TABLE_NUT,0);
        sqliteHelper.insertNut(TABLE_NUT,1);
        sqliteHelper.insertNut(TABLE_NUT,2);
        sqliteHelper.insertNut(TABLE_NUT,3);
        sqliteHelper.insertNut(TABLE_NUT,4);
        sqliteHelper.insertNut(TABLE_NUT,5);
        sqliteHelper.insertNut(TABLE_NUT,6);
        sqliteHelper.insertNut(TABLE_NUT,7);
        sqliteHelper.insertVatPham(TABLE_VATPHAM,0,"Băng gạc",false,false);
        sqliteHelper.insertVatPham(TABLE_VATPHAM,1,"Túi máu",false,false);
        sqliteHelper.insertVatPham(TABLE_VATPHAM,2,"Lương khô",false,false);
        sqliteHelper.insertVatPham(TABLE_VATPHAM,3,"Lương khô lớn",false,false);*/
        /*if(sqliteHelper.insertSaveGame(TABLE_SAVEGAME, 0,0,0,"12-10-2021","1-2-1911",0))
        {
            if(!sqliteHelper.getSaveGame(0,0,0)){
                bd.setAlpha(0);
                bd.setEnabled(false);
                choimoi.setAlpha(1);
                choimoi.setAnimation(slUp);
                choimoi.setEnabled(true);
            }
        }*/
    /*sqliteHelper.insertSaveGame(TABLE_SAVEGAME, 0,0,0,"12-10-2021","1-2-1911",0);
    sqliteHelper.insertSaveGame(TABLE_SAVEGAME, 1,0,0,"1-3-2021","20-3-1918",0);
    sqliteHelper.insertSaveGame(TABLE_SAVEGAME, 2, 0,0,"5-12-2021","12-10-1911",0);*/

    //t.setText(String.valueOf(sqliteHelper.getCount(TABLE_SAVEGAME)));
        //t.setText(String.valueOf(sqliteHelper.getTuiDo(0,0)));

        //sqliteHelper.insertTuiDo(TABLE_TUIDO,0,0,5);
        //t = findViewById(R.id.test1);
        //t.setText(sqliteHelper.getTenVatPham(2));
        //t.setText(String.valueOf(sqliteHelper.getCount(TABLE_TUIDO)));
        //t.setText(String.valueOf(sqliteHelper.getIDSG()));
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaveGame();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BachodayhocActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*choitiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(sqliteHelper.getSaveGame(0,0,1))
                    intent = new Intent(MainActivity.this, BachodayhocActivity.class);
                if(sqliteHelper.getSaveGame(0,0,2))
                    intent = new Intent(MainActivity.this, CanhBacNoiChuyenVoiBanThanActivity.class);
                if(sqliteHelper.getSaveGame(0,0,3))
                    intent = new Intent(MainActivity.this, MapActivity.class);
                //Hoàn thành nút 1 VietNam
                if(sqliteHelper.getSaveGame(0,1,0)) // cảnh nhận thẻ nhân viên
                    intent = new Intent(MainActivity.this, TheNhanVienActivity.class);
                if(sqliteHelper.getSaveGame(0,1,1)) // cảnh thầy giáo nhận thư
                    intent = new Intent(MainActivity.this, CanhThayNhanThuActivity.class);
                if(sqliteHelper.getSaveGame(0,1,2)) // quay lại MAP nút 1 thành màu vàng và xuất hiện nút 2
                    intent = new Intent(MainActivity.this, MapActivity.class);
                //Hoàn thành nút 2 Singapore
                if(sqliteHelper.getSaveGame(0,2,0)) // quay lại MAP nút 2 thành màu vàng và xuất hiện nút 3 + Hướng dẫn vào cửa hàng
                    intent = new Intent(MainActivity.this, MapActivity.class);
                if(sqliteHelper.getSaveGame(0,2,1)) // Hoàn thành hướng dẫn vào cửa hàng
                    intent = new Intent(MainActivity.this, MapActivity.class);
                //Hoàn thành nút 3 Colombo
                if(sqliteHelper.getSaveGame(0,3,0)) // quay lại MAP nút 3 thành màu vàng và xuất hiện nút 4
                    intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });*/

        /*choimoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip.setAlpha(1);
                skip.setEnabled(true);
                choimoi.setEnabled(false);
                choimoi.animate().alpha(0).setDuration(1000);
                timdcn.animate().alpha(0).setDuration(1000);
                intro.animate().translationYBy(-10000).setDuration(80000);
                //sqliteHelper.updateSaveGame(TABLE_SAVEGAME, 0, 0,0);
                sqliteHelper.updateNhanVat(TABLE_NHANVAT,0,0,0,0,0,0);
            }
        });*/
    }

    public void showSaveGame(){
        DialogSaveGame.setContentView(R.layout.dialog_savegame);
        choimoi = DialogSaveGame.findViewById(R.id.btnChoiMoi);
        if (sqliteHelper.getCount(TABLE_SAVEGAME) >= 0){
            choimoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChoiMoi(0);
                    DialogSaveGame.dismiss();
                }
            });
            if (sqliteHelper.getCount(TABLE_SAVEGAME) >= 1){
                RelativeLayout layout1 = DialogSaveGame.findViewById(R.id.layout1);
                layout1.setAlpha(1);
                TextView txtngaychoi1 = findViewById(R.id.txtngaychoi1);
                TextView txtngaygame1 = findViewById(R.id.txtngaygame1);
                TextView txttien1 = findViewById(R.id.txttien1);
                //txtngaychoi1.setText(sqliteHelper.getNgayChoi(0));
                //txtngaygame1.setText(sqliteHelper.getTime(0));
                //txttien1.setText(String.valueOf(sqliteHelper.getTien(0)));
                Button button1 = DialogSaveGame.findViewById(R.id.btnSave1);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                choimoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ChoiMoi(1);
                        DialogSaveGame.dismiss();
                    }
                });
                /*if (sqliteHelper.getCount(TABLE_SAVEGAME) >= 2){
                    RelativeLayout layout2 = DialogSaveGame.findViewById(R.id.layout2);
                    layout2.setAlpha(1);
                    TextView txtngaychoi2 = findViewById(R.id.txtngaychoi2);
                    TextView txtngaygame2 = findViewById(R.id.txtngaygame2);
                    TextView txttien2 = findViewById(R.id.txttien2);
                    txtngaychoi2.setText(sqliteHelper.getNgayChoi(1));
                    txtngaygame2.setText(sqliteHelper.getTime(1));
                    txttien2.setText(String.valueOf(sqliteHelper.getTien(1)));
                    Button button2 = DialogSaveGame.findViewById(R.id.btnSave2);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    choimoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChoiMoi(2);
                            DialogSaveGame.dismiss();
                        }
                    });
                    if (sqliteHelper.getCount(TABLE_SAVEGAME) >= 3){
                        RelativeLayout layout3 = DialogSaveGame.findViewById(R.id.layout3);
                        layout3.setAlpha(1);
                        TextView txtngaychoi3 = findViewById(R.id.txtngaychoi3);
                        TextView txtngaygame3 = findViewById(R.id.txtngaygame3);
                        TextView txttien3 = findViewById(R.id.txttien3);
                        txtngaychoi3.setText(sqliteHelper.getNgayChoi(2));
                        txtngaygame3.setText(sqliteHelper.getTime(2));
                        txttien3.setText(String.valueOf(sqliteHelper.getTien(2)));
                        Button button3 = DialogSaveGame.findViewById(R.id.btnSave3);
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        choimoi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                }*/
            }
        }
        DialogSaveGame.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DialogSaveGame.show();
    }

    public void ChoiMoi(int id){
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        skip.setAlpha(1);
        skip.setEnabled(true);
        bd.setEnabled(false);
        bd.animate().alpha(0).setDuration(1000);
        timdcn.animate().alpha(0).setDuration(1000);
        intro.animate().translationYBy(-10000).setDuration(80000);
        sqliteHelper.insertNguoiChoi(TABLE_NGUOICHOI,id,0);
        sqliteHelper.insertSaveGame(TABLE_SAVEGAME, id,0,0,date,null,0);
    }
}