package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissionMatch3Activity extends AppCompatActivity {
    private SqliteHelper sqliteHelper;
    final String DB_NAME="DB_Demo";
    final String TABLE_SAVEGAME="TBL_SAVE_GAME";
    //Thoi Gian
    private CountDownTimer countDownTimer;
    private TextView countdownText;
    private long Milliseconds;
    //Khoi tao Match3
    int[] icon = {
            R.drawable.khoi_time,
            R.drawable.khoi_sach,
            R.drawable.khoi_suckhoe,
            R.drawable.khoi_sucmanh,
            R.drawable.khoi_tien,
            R.drawable.khoi_tocom
    };
    int witdhOfBlock, noOfBlocks = 8, widthOfScreen;
    ArrayList<ImageView> icons = new ArrayList<>();
    int iconToBeDragged, iconToBeReplaced;
    int notIcon = R.drawable.transparent;
    Handler mHandler;
    int interval = 100;
    //Dialog
    Dialog epicDialog;
    Button tiep, choilai, trove;
    ImageView epicImg1, epicImg2, epicImg3, epicImg4;
    TextView epicTXT1, epicTXT2, epicTXT3, epicTXT4;
    //Noi Dung Man Choi
    private TextView txt1, txt2, txt3, txt4, Tong;
    private ImageView check1, check2, check3, check4, img1, img2, img3, img4;
    TextView Tien;
    int total = 0, totalTien, totalSach;
    int score, score_atk, score_mau, score_time, score_tien, score_sach, score_tocom;
    int iconmau = R.drawable.khoi_suckhoe;
    int iconatk = R.drawable.khoi_sucmanh;
    int icontien = R.drawable.khoi_tien;
    int iconsach = R.drawable.khoi_sach;
    int icontime = R.drawable.khoi_time;
    int icontocom = R.drawable.khoi_tocom;
    boolean bd = false, stop = false;
    //Skill
    ImageView igvManaBigBang1, igvManaBigBang2, igvManaBigBang3, igvManaBigBang4, igvManaBB1, igvManaBB2, igvManaBB3;
    ImageView igvManaStopTime1, igvManaStopTime2, igvManaStopTime3, igvManaStopTime4, igvManaST1, igvManaST2, igvManaST3;
    int manaTime, manaBigBang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_match3);
        //txt Thoi gian
        countdownText =findViewById(R.id.time);
        //txt diem muc tieu
        txt1 = findViewById(R.id.score1);
        txt2 = findViewById(R.id.score2);
        txt3 = findViewById(R.id.score3);
        txt4 = findViewById(R.id.score4);
        Tong = findViewById(R.id.total);
        Tong.setText(String.valueOf(total));
        //Image Muc tieu
        img1 = findViewById(R.id.igvMucTieu1);
        img2 = findViewById(R.id.igvMucTieu2);
        img3 = findViewById(R.id.igvMucTieu3);
        img4 = findViewById(R.id.igvMucTieu4);
        //dau check
        check1 = findViewById(R.id.finish1);
        check2 = findViewById(R.id.finish2);
        check3 = findViewById(R.id.finish3);
        check4 = findViewById(R.id.finish4);
        check1.setImageAlpha(0);
        check2.setImageAlpha(0);
        check3.setImageAlpha(0);
        check4.setImageAlpha(0);
        //Skill
        igvManaBigBang1 = findViewById(R.id.igvManaBigBang1_fill);
        igvManaBigBang2 = findViewById(R.id.igvManaBigBang2_fill);
        igvManaBigBang3 = findViewById(R.id.igvManaBigBang3_fill);
        igvManaBigBang4 = findViewById(R.id.igvManaBigBang4_fill);
        igvManaStopTime1 = findViewById(R.id.igvManaStopTime1_fill);
        igvManaStopTime2 = findViewById(R.id.igvManaStopTime2_fill);
        igvManaStopTime3 = findViewById(R.id.igvManaStopTime3_fill);
        igvManaStopTime4 = findViewById(R.id.igvManaStopTime4_fill);
        igvManaBB1 = findViewById(R.id.igvManaBigBang_1);
        igvManaBB2 = findViewById(R.id.igvManaBigBang_2);
        igvManaBB3 = findViewById(R.id.igvManaBigBang_3);
        igvManaST1 = findViewById(R.id.igvManaStopTime_1);
        igvManaST2 = findViewById(R.id.igvManaStopTime_2);
        igvManaST3 = findViewById(R.id.igvManaStopTime_3);
        igvManaBB1.setImageAlpha(0);
        igvManaBB2.setImageAlpha(0);
        igvManaBB3.setImageAlpha(0);
        igvManaST1.setImageAlpha(0);
        igvManaST2.setImageAlpha(0);
        igvManaST3.setImageAlpha(0);

        //create Board
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        witdhOfBlock = widthOfScreen / noOfBlocks;
        createBoard();
        //Di chuyen icon
        for(final ImageView imageView : icons){
            imageView.setOnTouchListener(new OnSwipeListener(this)
            {
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    iconToBeDragged = imageView.getId();
                    iconToBeReplaced = iconToBeDragged - 1;
                    iconInterchange();
                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    iconToBeDragged = imageView.getId();
                    iconToBeReplaced = iconToBeDragged + 1;
                    iconInterchange();
                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    iconToBeDragged = imageView.getId();
                    iconToBeReplaced = iconToBeDragged - noOfBlocks;
                    iconInterchange();
                }

                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    iconToBeDragged = imageView.getId();
                    iconToBeReplaced = iconToBeDragged + noOfBlocks;
                    iconInterchange();
                }
            });
        }
        //sql
        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        //dialog
        epicDialog = new Dialog(this);
        showBatDau();
        //Loop
        mHandler = new Handler();
        startRepeat();
        //Hien thoi gian
        updateTimer();
    }

    //Money
    public void Money(){
        int chia = totalTien % 10;
        if (chia >= 5)
            totalTien = totalTien / 10 + 1;
        else totalTien = totalTien / 10;
        sqliteHelper.updateTien(0,totalTien + sqliteHelper.getTien(0));
    }

    //Skill
    public void Skill(int x){
        if (manaTime == 0) {
            igvManaStopTime1.setImageResource(R.drawable.thanh_trong1);
            igvManaStopTime2.setImageResource(R.drawable.thanh_trong2);
            igvManaStopTime3.setImageResource(R.drawable.thanh_trong2);
            igvManaStopTime4.setImageResource(R.drawable.thanh_trong3);
            igvManaST1.setImageAlpha(0);
            igvManaST2.setImageAlpha(0);
            igvManaST3.setImageAlpha(0);
        }

        if (manaBigBang == 0){
            igvManaBigBang1.setImageResource(R.drawable.thanh_trong1);
            igvManaBigBang2.setImageResource(R.drawable.thanh_trong2);
            igvManaBigBang3.setImageResource(R.drawable.thanh_trong2);
            igvManaBigBang4.setImageResource(R.drawable.thanh_trong3);
            igvManaBB1.setImageAlpha(0);
            igvManaBB2.setImageAlpha(0);
            igvManaBB3.setImageAlpha(0);
        }
        //Mức 1
        if (manaTime >= 5) igvManaStopTime1.setImageResource(R.drawable.mana1);
        if(manaTime >= 10) igvManaStopTime1.setImageResource(R.drawable.mana2);
        if (manaTime >= 15) igvManaStopTime1.setImageResource(R.drawable.mana3);
        if(manaTime >= 20) igvManaStopTime1.setImageResource(R.drawable.mana4);
        if (manaTime >= 25) igvManaStopTime1.setImageResource(R.drawable.mana5);
        if (manaTime >= 25) igvManaST1.setImageAlpha(255);

        if (manaBigBang >= 5) igvManaBigBang1.setImageResource(R.drawable.mana1);
        if(manaBigBang >= 10) igvManaBigBang1.setImageResource(R.drawable.mana2);
        if (manaBigBang >= 15) igvManaBigBang1.setImageResource(R.drawable.mana3);
        if(manaBigBang >= 20) igvManaBigBang1.setImageResource(R.drawable.mana4);
        if (manaBigBang >= 25) igvManaBigBang1.setImageResource(R.drawable.mana5);
        if (manaBigBang >= 25) igvManaBB1.setImageAlpha(255);

        //Mức 2
        if (manaTime >= 30) igvManaStopTime2.setImageResource(R.drawable.mana1);
        if(manaTime >= 35) igvManaStopTime2.setImageResource(R.drawable.mana2);
        if (manaTime >= 40) igvManaStopTime2.setImageResource(R.drawable.mana3);
        if(manaTime >= 45) igvManaStopTime2.setImageResource(R.drawable.mana4);
        if(manaTime >= 50) igvManaStopTime2.setImageResource(R.drawable.mana5);
        if (manaTime >= 50) igvManaST2.setImageAlpha(255);

        if (manaBigBang >= 30) igvManaBigBang2.setImageResource(R.drawable.mana1);
        if(manaBigBang >= 35) igvManaBigBang2.setImageResource(R.drawable.mana2);
        if (manaBigBang >= 40) igvManaBigBang2.setImageResource(R.drawable.mana3);
        if(manaBigBang >= 45) igvManaBigBang2.setImageResource(R.drawable.mana4);
        if(manaBigBang >= 50) igvManaBigBang2.setImageResource(R.drawable.mana5);
        if (manaBigBang >= 50) igvManaBB2.setImageAlpha(255);

        //Mức 3
        if(manaTime >= 55) igvManaStopTime3.setImageResource(R.drawable.mana1);
        if (manaTime >= 60) igvManaStopTime3.setImageResource(R.drawable.mana2);
        if(manaTime >= 65) igvManaStopTime3.setImageResource(R.drawable.mana3);
        if (manaTime >= 70) igvManaStopTime3.setImageResource(R.drawable.mana4);
        if(manaTime >= 75) igvManaStopTime3.setImageResource(R.drawable.mana5);
        if (manaTime >= 75) igvManaST3.setImageAlpha(255);

        if(manaBigBang >= 55) igvManaBigBang3.setImageResource(R.drawable.mana1);
        if (manaBigBang >= 60) igvManaBigBang3.setImageResource(R.drawable.mana2);
        if(manaBigBang >= 65) igvManaBigBang3.setImageResource(R.drawable.mana3);
        if (manaBigBang >= 70) igvManaBigBang3.setImageResource(R.drawable.mana4);
        if(manaBigBang >= 75) igvManaBigBang3.setImageResource(R.drawable.mana5);
        if (manaBigBang >= 75) igvManaBB3.setImageAlpha(255);

        //Mức 4
        if (manaTime >= 80) igvManaStopTime4.setImageResource(R.drawable.mana1);
        if(manaTime >= 85) igvManaStopTime4.setImageResource(R.drawable.mana2);
        if (manaTime >= 90) igvManaStopTime4.setImageResource(R.drawable.mana3);
        if(manaTime >= 95) igvManaStopTime4.setImageResource(R.drawable.mana4);
        if(manaTime >= 100) igvManaStopTime4.setImageResource(R.drawable.mana5);

        if (manaBigBang >= 80) igvManaBigBang4.setImageResource(R.drawable.mana1);
        if(manaBigBang >= 85) igvManaBigBang4.setImageResource(R.drawable.mana2);
        if (manaBigBang >= 90) igvManaBigBang4.setImageResource(R.drawable.mana3);
        if(manaBigBang >= 95) igvManaBigBang4.setImageResource(R.drawable.mana4);
        if(manaBigBang >= 100) igvManaBigBang4.setImageResource(R.drawable.mana5);
    }

    public void showBatDau(){
        epicDialog.setContentView(R.layout.dialog_batdau_m3);
        epicImg1 = epicDialog.findViewById(R.id.igvMucTieu1);
        epicImg2 = epicDialog.findViewById(R.id.igvMucTieu2);
        epicImg3 = epicDialog.findViewById(R.id.igvMucTieu3);
        epicImg4 = epicDialog.findViewById(R.id.igvMucTieu4);
        epicTXT1 = epicDialog.findViewById(R.id.txtMucTieu1);
        epicTXT2 = epicDialog.findViewById(R.id.txtMucTieu2);
        epicTXT3 = epicDialog.findViewById(R.id.txtMucTieu3);
        epicTXT4 = epicDialog.findViewById(R.id.txtMucTieu4);
        tiep = epicDialog.findViewById(R.id.btnBatDau);
        if(sqliteHelper.getSaveGame(0,0,3)){
            Man1();
        }
        if(sqliteHelper.getSaveGame(0,1,2)){
            Man2();
        }
        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chay thoi gian
                manaBigBang = 0;
                manaTime = 0;
                manaBigBang = 0;
                startTimer();
                updateTimer();
                epicDialog.dismiss();
                bd = true;
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void Man1(){
        Milliseconds = 60000;
        score_mau = 20;
        score_atk = 10;
        txt1.setText("20");
        txt2.setText("10");
        epicTXT1.setText("20");
        epicTXT2.setText("10");
        img1.setImageResource(iconmau);
        img2.setImageResource(iconatk);
        epicImg1.setImageResource(iconmau);
        epicImg2.setImageResource(iconatk);
    }

    public void Man2(){
        Milliseconds = 90000;
        score_sach = 10;
        score_mau = 20;
        score_tien = 30;
        txt1.setText("10");
        txt2.setText("20");
        txt3.setText("30");
        epicTXT1.setText("10");
        epicTXT2.setText("20");
        epicTXT3.setText("30");
        img1.setImageResource(iconsach);
        img2.setImageResource(iconmau);
        img3.setImageResource(icontien);
        epicImg1.setImageResource(iconsach);
        epicImg2.setImageResource(iconmau);
        epicImg3.setImageResource(icontien);
    }

    public void showHoanThanh(){
        Money();
        epicDialog.setContentView(R.layout.dialog_finish);
        tiep = epicDialog.findViewById(R.id.btnTiepTuc);
        Tien = epicDialog.findViewById(R.id.txtTien);
        Tien.setText(String.valueOf(totalTien));
        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(sqliteHelper.getSaveGame(0,0,3)){
                    sqliteHelper.updateSaveGame(0, 1,0);
                    intent = new Intent(MissionMatch3Activity.this, TheNhanVienActivity.class);
                }
                if(sqliteHelper.getSaveGame(0,1,2)){
                    sqliteHelper.updateSaveGame(0, 2,0);
                    intent = new Intent(MissionMatch3Activity.this, MapActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void showThatBai(){
        total = totalTien = totalSach = 0;
        epicDialog.setContentView(R.layout.dialog_timeout);
        trove = epicDialog.findViewById(R.id.btnTroVe);
        choilai = epicDialog.findViewById(R.id.btnChoiLai);
        choilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tong.setText(String.valueOf(total));
                bd = false;
                resetBoard();
                epicDialog.dismiss();
                createBoard();
                showBatDau();
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MissionMatch3Activity.this, MapActivity.class);
                startActivity(intent);
                finish();
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(Milliseconds,1000) {
            @Override
            public void onTick(long l) {
                Milliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish(){
                if (bd)
                    try {
                        showThatBai();
                    }
                    finally {
                        mHandler.postDelayed(this::onFinish,interval);
                    }
            }
        }.start();
    }

    public void updateTimer() {
        int minutes = (int) Milliseconds / 60000;
        int seconds = (int) Milliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }

    private boolean CheckiconInterchange(){
        int background = (int) icons.get(iconToBeDragged).getTag();
        int background1 = (int) icons.get(iconToBeReplaced).getTag();
        int left = (int) icons.get(iconToBeReplaced - 1).getTag();
        int left2 = (int) icons.get(iconToBeReplaced - 2).getTag();
        int right = (int) icons.get(iconToBeReplaced + 1).getTag();
        int right2 = (int) icons.get(iconToBeReplaced + 2).getTag();
        int top = (int) icons.get(iconToBeReplaced - noOfBlocks).getTag();
        int top2 = (int) icons.get(iconToBeReplaced - (noOfBlocks + noOfBlocks)).getTag();
        int bottom = (int) icons.get(iconToBeReplaced + noOfBlocks).getTag();
        int bottom2 = (int) icons.get(iconToBeReplaced + noOfBlocks + noOfBlocks).getTag();

        int left_1 = (int) icons.get(iconToBeDragged - 1).getTag();
        int left_2 = (int) icons.get(iconToBeDragged - 2).getTag();
        int right_1 = (int) icons.get(iconToBeDragged + 1).getTag();
        int right_2 = (int) icons.get(iconToBeDragged + 2).getTag();
        int top_1 = (int) icons.get(iconToBeDragged - noOfBlocks).getTag();
        int top_2 = (int) icons.get(iconToBeDragged - (noOfBlocks + noOfBlocks)).getTag();
        int bottom_1 = (int) icons.get(iconToBeDragged + noOfBlocks).getTag();
        int bottom_2 = (int) icons.get(iconToBeDragged + noOfBlocks + noOfBlocks).getTag();

        if((left == background && left2 == background) || (right == background && right2 == background) || (top == background && top2 == background) || (bottom == background && bottom2 == background)) return true;
        if((right == background && left == background) || (top == background && bottom == background)) return true;
        if((left_1 == background1 && left_2 == background1) || (right_1 == background1 && right_2 == background1) || (top_1 == background1 && top_2 == background1) || (bottom_1 == background1 && bottom_2 == background1)) return true;
        if((right_1 == background1 && left_1 == background1) || (top_1 == background1 && bottom_1 == background1)) return true;
        return false;
    }

    private  void iconInterchange(){
        //if (CheckiconInterchange()){
            int background = (int) icons.get(iconToBeReplaced).getTag();
            int background1 = (int) icons.get(iconToBeDragged).getTag();
            icons.get(iconToBeDragged).setImageResource(background);
            icons.get(iconToBeReplaced).setImageResource(background1);
            icons.get(iconToBeDragged).setTag(background);
            icons.get(iconToBeReplaced).setTag(background1);
        //}
    }

    private void resetBoard(){
        for(int i = 0; i < noOfBlocks * noOfBlocks; i++){
            icons.get(i).setImageResource(notIcon);
            icons.get(i).setTag(notIcon);
        }
    }

    private void createBoard(){
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);
        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = widthOfScreen;
        for(int i = 0; i < noOfBlocks * noOfBlocks; i++){
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(witdhOfBlock,witdhOfBlock));
            imageView.setMaxHeight(witdhOfBlock);
            imageView.setMaxWidth(witdhOfBlock);
            int randomicon = (int) Math.floor(Math.random() * icon.length);
            imageView.setImageResource(icon[randomicon]);
            imageView.setTag(icon[randomicon]);
            icons.add(imageView);
            gridLayout.addView(imageView);
        }
    }

    private void moveDownIcon(){
        Integer[] firstRow = {0,1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(firstRow);
        for(int i = 55; i >= 0; i--){
            if((int) icons.get(i + noOfBlocks).getTag()==notIcon){
                icons.get(i+noOfBlocks).setImageResource((int)icons.get(i).getTag());
                icons.get(i+noOfBlocks).setTag(icons.get(i).getTag());
                icons.get(i).setImageResource(notIcon);
                icons.get(i).setTag(notIcon);

                if (list.contains(i) && (int) icons.get(i).getTag() == notIcon){
                    int randomIcon = (int) Math.floor(Math.random() * icon.length);
                    icons.get(i).setImageResource(icon[randomIcon]);
                    icons.get(i).setTag(icon[randomIcon]);
                }
            }
        }
        for(int i = 0; i<8;i++){
            if((int) icons.get(i).getTag() == notIcon){
                int randomIcon = (int) Math.floor(Math.random() * icon.length);
                icons.get(i).setImageResource(icon[randomIcon]);
                icons.get(i).setTag(icon[randomIcon]);
            }
        }
    }

    Runnable repeatChecher = new Runnable() {
        @Override
        public void run() {
            if (!stop){
                try {
                    checkRowForSix();
                    checkRowForFive();
                    checkRowForFour();
                    checkRowForThree();
                    checkColumn();
                    moveDownIcon();
                    if (bd){
                        checkMucTieu();
                    }
                }
                finally {
                    mHandler.postDelayed(repeatChecher,interval);
                }
            }
        }
    };
    void startRepeat() {repeatChecher.run();}

    private void checkMucTieu(){
        if(sqliteHelper.getSaveGame(0,0,3)){
            CheckMan1();
        }
        if(sqliteHelper.getSaveGame(0,1,2)){
            CheckMan2();
        }
    }

    public void CheckMan1(){
        if(score_mau <= 0){
            txt1.setAlpha(0);
            check1.setImageAlpha(255);
        }
        if(score_atk <= 0){
            txt2.setAlpha(0);
            check2.setImageAlpha(255);
        }
        if(score_atk <= 0 && score_mau <= 0){
            stop = true;
            showHoanThanh();
            countDownTimer.cancel();
        }
        txt1.setText(String.valueOf(score_mau));
        txt2.setText(String.valueOf(score_atk));
    }

    public void CheckMan2(){
        if(score_sach <= 0){
            txt1.setAlpha(0);
            check1.setImageAlpha(255);
        }
        if(score_mau <= 0){
            txt2.setAlpha(0);
            check2.setImageAlpha(255);
        }
        if(score_tien <= 0){
            txt3.setAlpha(0);
            check3.setImageAlpha(255);
        }
        if(score_tien <= 0 && score_mau <= 0 && score_sach <= 0){
            stop = true;
            showHoanThanh();
            countDownTimer.cancel();
        }
        txt1.setText(String.valueOf(score_sach));
        txt2.setText(String.valueOf(score_mau));
        txt3.setText(String.valueOf(score_tien));
    }

    public void CheckDiemIcon(int x){
        if(bd){
            if(score_mau > 0){
                if(x == iconmau) score_mau = score_mau - score;
            }
            if(score_atk > 0){
                if(x == iconatk) score_atk = score_atk - score;
            }
            if(x == iconsach){
                totalSach = totalSach + score;
                if(score_sach > 0) score_sach = score_sach - score;
            }
            if(x == icontien){
                totalTien = totalTien + score;
                if(score_tien > 0) score_tien = score_tien - score;
            }
            if(x == icontime) {
                if (score_time > 0) score_time = score_time - score;
                manaTime = manaTime + score;
            }
            if(x == icontocom) {
                if (score_tocom > 0) score_tocom = score_tocom - score;
                manaBigBang = manaBigBang + score;
            }
            Skill(x);
            total = total + score;
        }
        Tong.setText(String.valueOf(total));
    }

    private void checkRowForThree(){
        for (int i = 0; i <62; i++){
            score = 0;
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            Integer[] notValid = {6,7,14,15,22,23,30,31,38,39,46,47,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x = i;
                if((int) icons.get(x++).getTag() == chosedIcon && !isBlank && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x).getTag() == chosedIcon)
                {
                    Column(1);
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    score = score + 3;
                    CheckDiemIcon(chosedIcon);
                }
            }
        }
        moveDownIcon();
    }

    private void checkRowForFour(){
        for (int i = 0; i <61; i++){
            score = 0;
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            Integer[] notValid = {5,6,7,13,14,15,21,22,23,29,30,31,37,38,39,45,46,47,53,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x = i;
                if((int) icons.get(x++).getTag() == chosedIcon && !isBlank && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x).getTag() == chosedIcon)
                {
                    score = score + 4;
                    CheckDiemIcon(chosedIcon);
                    Column(1);
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                }
            }
        }
        moveDownIcon();
    }

    private void checkRowForFive(){
        for (int i = 0; i <60; i++){
            score = 0;
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            Integer[] notValid = {4,5,6,7,12,13,14,15,20,21,22,23,28,29,30,31,36,37,38,39,44,45,46,47,52,53,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x = i;
                if((int) icons.get(x++).getTag() == chosedIcon && !isBlank && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x).getTag() == chosedIcon)
                {
                    score = score + 5;
                    CheckDiemIcon(chosedIcon);
                    Column(1);
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                }
            }
        }
        moveDownIcon();
    }

    private void checkRowForSix(){
        for (int i = 0; i <59; i++){
            score = 0;
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            Integer[] notValid = {3,4,5,6,7,11,12,13,14,15,19,20,21,22,23,27,28,29,30,31,35,36,37,38,39,43,44,45,46,47,51,52,53,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x = i;
                if((int) icons.get(x++).getTag() == chosedIcon && !isBlank && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x).getTag() == chosedIcon)
                {
                    score = score + 6;
                    CheckDiemIcon(chosedIcon);
                    Column(1);
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                }
            }
        }
        moveDownIcon();
    }

    private void checkRowForSeven(){
        for (int i = 0; i <58; i++){
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            Integer[] notValid = {6,7,14,15,22,23,30,31,38,39,46,47,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x = i;
                if((int) icons.get(x++).getTag() == chosedIcon && !isBlank && (int) icons.get(x++).getTag() == chosedIcon
                        && (int) icons.get(x++).getTag() == chosedIcon && (int) icons.get(x).getTag() == chosedIcon)
                {
                    Column(1);
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                }
            }
        }
        moveDownIcon();
    }

    private void checkColumn(){
        Column(0);
        moveDownIcon();
    }

    private void Column(int a){
        for (int i = 0; i < 48; i++){
            score = 0;
            int chosedIcon = (int) icons.get(i).getTag();
            boolean isBlank = (int) icons.get(i).getTag() == notIcon;
            int x = i;
            if((int) icons.get(x).getTag() == chosedIcon && !isBlank && (int) icons.get(x + noOfBlocks).getTag() == chosedIcon && (int) icons.get(x + 2 * noOfBlocks).getTag() == chosedIcon)
            {
                if(i < 40 && (int) icons.get(x + 3 * noOfBlocks).getTag() == chosedIcon){
                    if(i < 32 && (int) icons.get(x + 4 * noOfBlocks).getTag() == chosedIcon){
                        if(i < 24 && (int) icons.get(x + 5 * noOfBlocks).getTag() == chosedIcon){
                            if(i < 16 && (int) icons.get(x + 6 * noOfBlocks).getTag() == chosedIcon){
                                if(i < 8 && (int) icons.get(x + 7 * noOfBlocks).getTag() == chosedIcon){
                                    icons.get(x).setImageResource(notIcon);
                                    icons.get(x).setTag(notIcon);
                                    x = x + noOfBlocks;
                                    score = score +1;
                                }
                                icons.get(x).setImageResource(notIcon);
                                icons.get(x).setTag(notIcon);
                                x = x + noOfBlocks;
                                score = score +1;
                            }
                            icons.get(x).setImageResource(notIcon);
                            icons.get(x).setTag(notIcon);
                            x = x + noOfBlocks;
                            score = score +1;
                        }
                        icons.get(x).setImageResource(notIcon);
                        icons.get(x).setTag(notIcon);
                        x = x + noOfBlocks;
                        score = score +1;
                    }
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x = x + noOfBlocks;
                    score = score +1;
                }
                icons.get(x).setImageResource(notIcon);
                icons.get(x).setTag(notIcon);
                x = x + noOfBlocks;
                icons.get(x).setImageResource(notIcon);
                icons.get(x).setTag(notIcon);
                x = x + noOfBlocks;
                icons.get(x).setImageResource(notIcon);
                icons.get(x).setTag(notIcon);
                score = score + 3;
                if (a == 1) score = score - 1;
                CheckDiemIcon(chosedIcon);
            }
        }
    }
}