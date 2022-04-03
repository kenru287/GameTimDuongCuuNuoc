package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Match3Activity extends AppCompatActivity {

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    final  String TABLE_NAME="TBL_SAVE_GAME";

    private TextView countdownText;

    private CountDownTimer countDownTimer;
    private long Milliseconds = 60000; //1 mins

    Dialog epicDialog;
    Button tiep,choilai,trove;

    int[] icon = {
            R.drawable.khoi_time,
            R.drawable.khoi_sach,
            R.drawable.khoi_suckhoe,
            R.drawable.khoi_sucmanh,
            R.drawable.khoi_tien
    };
    int witdhOfBlock, noOfBlocks = 8, widthOfScreen;
    ArrayList<ImageView> icons = new ArrayList<>();
    int iconToBeDragged, iconToBeReplaced;
    int notIcon = R.drawable.transparent;
    Handler mHandler;
    int interval = 100;
    TextView mau,atk,Tong;
    ImageView muctieu1,muctieu2;
    int score;
    int total = 0, score_atk, score_mau, score_time, score_tien, score_sach;
    int iconmau = R.drawable.khoi_suckhoe;
    int iconatk = R.drawable.khoi_sucmanh;
    int icontien = R.drawable.khoi_tien;
    int iconsach = R.drawable.khoi_sach;
    int icontime = R.drawable.khoi_time;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);
        countdownText =findViewById(R.id.time);
        mau = findViewById(R.id.score_mau);
        atk = findViewById(R.id.score_atk);
        Tong = findViewById(R.id.total);
        muctieu1 = findViewById(R.id.finish1);
        muctieu2 = findViewById(R.id.finish2);
        muctieu1.setImageAlpha(0);
        muctieu2.setImageAlpha(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        witdhOfBlock = widthOfScreen / noOfBlocks;
        createBoard();
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
        mHandler = new Handler();
        //dialog
        epicDialog = new Dialog(this);
        //sql
        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        if(sqliteHelper.getSaveGame(0,0,3)){
            Man1();
        }
        showBatDau();
        startRepeat();
    }

    public void Man1(){
        score_atk = 30;
        score_mau = 30;
        mau.setText("30");
        atk.setText("30");
    }

    public void showBatDau(){
        epicDialog.setContentView(R.layout.dialog_batdau_m3);
        tiep = findViewById(R.id.btnBatDau);
        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chay thoi gian
                startTimer();
                updateTimer(60000);
            }
        });
    }
    public void showHoanThanh(){
        epicDialog.setContentView(R.layout.dialog_finish);
        tiep = epicDialog.findViewById(R.id.btnTiepTuc);
        tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteHelper.updateSaveGame(0, 1,1);
                finish();
                Intent intent = new Intent(Match3Activity.this, CanhThayNhanThuActivity.class);
                startActivity(intent);
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    public void showThatBai(){
        epicDialog.setContentView(R.layout.dialog_timeout);
        trove = epicDialog.findViewById(R.id.btnTroVe);
        choilai = epicDialog.findViewById(R.id.btnChoiLai);
        choilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                Intent intent = new Intent(Match3Activity.this, Match3Activity.class);
                finish();
                startActivity(intent);
            }
        });
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteHelper.updateSaveGame(0, 0,3);
                Intent intent = new Intent(Match3Activity.this, MapActivity.class);
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
                updateTimer(60000);
            }

            @Override
            public void onFinish() {
                showThatBai();
            }
        }.start();
    }

    public void updateTimer(int s) {
        int minutes = (int) Milliseconds / s;
        int seconds = (int) Milliseconds % s / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);

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
                    Column();
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    x--;
                    icons.get(x).setImageResource(notIcon);
                    icons.get(x).setTag(notIcon);
                    score = score + 3;
                    total = total + score;
                    Tong.setText(String.valueOf(total));
                    if(chosedIcon == iconmau) {
                        score_mau = score_mau - score;
                        mau.setText(String.valueOf(score_mau));
                    }
                    if(chosedIcon == iconatk) {
                        score_atk = score_atk - score;
                        atk.setText(String.valueOf(score_atk));
                    }
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
                    total = total + score;
                    Tong.setText(String.valueOf(total));
                    if(chosedIcon == iconmau) {
                        score_mau = score_mau - score;
                        mau.setText(String.valueOf(score_mau));
                    }
                    if(chosedIcon == iconatk) {
                        score_atk = score_atk - score;
                        atk.setText(String.valueOf(score_atk));
                    }
                    Column();
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
                    total = total + score;
                    Tong.setText(String.valueOf(total));
                    if(chosedIcon == iconmau) {
                        score_mau = score_mau - score;
                        mau.setText(String.valueOf(score_mau));
                    }
                    if(chosedIcon == iconatk) {
                        score_atk = score_atk - score;
                        atk.setText(String.valueOf(score_atk));
                    }
                    Column();
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
                    total = total + score;
                    Tong.setText(String.valueOf(total));
                    if(chosedIcon == iconmau) {
                        score_mau = score_mau - score;
                        mau.setText(String.valueOf(score_mau));
                    }
                    if(chosedIcon == iconatk) {
                        score_atk = score_atk - score;
                        atk.setText(String.valueOf(score_atk));
                    }
                    Column();
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
                    Column();
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
        Column();
        moveDownIcon();
    }

    private void Column(){
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
                if(chosedIcon == iconmau && score_mau >= 0) {
                    score_mau = score_mau - score;
                    mau.setText(String.valueOf(score_mau));
                }
                if(chosedIcon == iconatk && score_atk >= 0) {
                    score_atk = score_atk - score;
                    atk.setText(String.valueOf(score_atk));
                }
                total = total + score;
                Tong.setText(String.valueOf(total));
            }
        }
    }

    private void checkMucTieu(){
        if(score_mau <= 0){
            mau.setAlpha(0);
            muctieu1.setImageAlpha(255);
        }
        if(score_atk <= 0){
            atk.setAlpha(0);
            muctieu2.setImageAlpha(255);
        }
        if(score_atk <= 0 && score_mau <= 0){
            showHoanThanh();
            countDownTimer.cancel();
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
            try {
                checkRowForSix();
                checkRowForFive();
                checkRowForFour();
                checkRowForThree();
                checkColumn();
                moveDownIcon();
                checkMucTieu();
            }
            finally {
                mHandler.postDelayed(repeatChecher,interval);
            }

        }
    };
    void startRepeat(){
        repeatChecher.run();
    }

    private  void iconInterchange(){
        int background = (int) icons.get(iconToBeReplaced).getTag();
        int background1 = (int) icons.get(iconToBeDragged).getTag();
        icons.get(iconToBeDragged).setImageResource(background);
        icons.get(iconToBeReplaced).setImageResource(background1);
        icons.get(iconToBeDragged).setTag(background);
        icons.get(iconToBeReplaced).setTag(background1);
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
}