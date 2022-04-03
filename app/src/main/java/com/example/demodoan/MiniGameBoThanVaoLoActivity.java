package com.example.demodoan;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MiniGameBoThanVaoLoActivity extends AppCompatActivity {

    //Tạo biến
    ImageView imgCircle, imgCircle2, imgCoal;
    Animation animationTran;
    Button btnBoThan, btnDung;
    TextView txtThongBaoThanhCong, txtThongBaoThatBai, txtSoLuotChoi, txtThongBaoKetThuc, txtMucTieu, txtHieuUngTruMot,
            txtHieuUngTruMotMucTieu, txtThanhDen;
    MediaPlayer soundBackgroundFire, soundBoThan, soundThanhCong, soundThatBai, soundGameWin, soundGameOver;

    int SoLuotChoi = 10;
    int MucTieu = 8;
    //Tạo biến để lưu điểm mục tiêu
    float localCir;
    float localCir2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game_bo_than_vao_lo);
        //sqliteHelper = new SqliteHelper(this,DB_NAME,null,1);
        //int id = sqliteHelper.getIDNguoiChoi();
        //UPDATE CANH AND THOIGIANCHOI (TBL_SAVE_GAME) WHERE ID = IDSAVEGAME (TBL_NGUOI_CHOI)
        //sqliteHelper.updateSaveGame(id,5,0);
        //Gán các biến
        animationTran = AnimationUtils.loadAnimation(this, R.anim.translation);

        imgCircle = findViewById(R.id.circle);
        imgCircle2 = findViewById(R.id.circle2);
        imgCoal = findViewById(R.id.coal);
        btnBoThan = findViewById(R.id.btnBoThan);
        btnDung = findViewById(R.id.btnDung);
        txtThanhDen = findViewById(R.id.thanhden);
        txtThongBaoThanhCong= findViewById(R.id.txtThongBaoThanhCong);
        txtThongBaoThatBai = findViewById(R.id.txtThongBaoThatBai);
        txtSoLuotChoi = findViewById(R.id.SoLanLuotChoi);
        txtThongBaoKetThuc = findViewById(R.id.txtThongBaoKetThucGame);
        txtMucTieu = findViewById(R.id.txtMucTieu);
        txtHieuUngTruMot = findViewById(R.id.txtHieuUngTruMot);
        txtHieuUngTruMotMucTieu = findViewById(R.id.txtHieuUngTruMotMucTieu);

        soundBackgroundFire = MediaPlayer.create(this, R.raw.soundbackground_gamebothan);
        soundBoThan = MediaPlayer.create(this, R.raw.soundbothan_gamebothan);
        soundThanhCong = MediaPlayer.create(this, R.raw.soungthanhcong_gamebothan);
        soundThatBai = MediaPlayer.create(this, R.raw.soundthatbai_gamebothan);
        soundGameWin = MediaPlayer.create(this, R.raw.gamewin_gamebothan);
        soundGameOver = MediaPlayer.create(this, R.raw.soundgameover_gamevbothan);
        //Code

        txtSoLuotChoi.setText(String.valueOf(SoLuotChoi));
        soundBackgroundFire.start();
        soundBackgroundFire.setLooping(true);

        //Phương thức cho nút bỏ than
        btnBoThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đặt trạng thái cho các button
                btnDung.setEnabled(true);
                btnBoThan.setEnabled(false);

                soundBoThan.start();
                //Hiệu ứng trừ một lượt chơi
                txtHieuUngTruMot.animate().alpha(1).setDuration(1000);
                txtHieuUngTruMot.animate().translationY(0).translationYBy(-200).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        txtHieuUngTruMot.animate().alpha(0).setDuration(0);
                        txtHieuUngTruMot.animate().translationX(0).translationY(0).setDuration(0);
                    }
                });
                //Hoạt ảnh vòng tròn di chuyển
                imgCircle.animate().translationX(txtThanhDen.getWidth() - imgCircle.getWidth()).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        imgCircle.animate().translationX(0).setDuration(2000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                btnBoThan.setEnabled(true);
                                btnDung.setEnabled(false);
                            }
                        });
                    }
                });
                /*imgCircle.animate().translationX(0).translationXBy(750).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        imgCircle.animate().translationX(0).translationXBy(-750).setDuration(2000).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                btnBoThan.setEnabled(true);
                                btnDung.setEnabled(false);
                            }
                        });
                    }
                });*/

                //Hoạt ảnh đưa than vào lò
                imgCoal.animate().alpha(1).translationY(0).translationYBy(-600).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        imgCoal.animate().alpha(0).setDuration(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                imgCoal.animate().translationX(0).translationY(0).setDuration(0);
                            }
                        });
                    }
                });

                //Trừ lượt chơi
                if(SoLuotChoi >= 0) {
                    SoLuotChoi = SoLuotChoi - 1;
                    txtSoLuotChoi.setText(String.valueOf(SoLuotChoi));
                }
                if (SoLuotChoi == -1) {
                    soundGameOver.start();
                    txtSoLuotChoi.setText("0");
                    txtThongBaoKetThuc.setText("Thất Bại");
                    txtThongBaoKetThuc.setAlpha(1);

                    //Dừng hoạt động activity
                    imgCoal.animate().cancel();
                    imgCircle.animate().cancel();
                    imgCircle.animate().translationX(0).setDuration(0);
                    btnDung.setEnabled(false);
                    btnBoThan.setEnabled(false);
                }


            }
        });
        //Phương thức cho nút dừng bỏ than
        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnDung.setEnabled(false);

                imgCircle.animate().cancel(); // Dừng tại vị trí đó
                float localPre = imgCircle.getX();
                localCir = imgCircle2.getX();
                localCir2 = localCir + 25;
                //So sánh vị trí của cir có bằng vị trí của điểm không
                if(localPre >= localCir && localPre <= localCir2) {
                    //Hiệu ứng trừ 1
                    txtHieuUngTruMotMucTieu.animate().alpha(1).setDuration(1000);
                    txtHieuUngTruMotMucTieu.animate().translationY(0).translationYBy(-200).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtHieuUngTruMotMucTieu.animate().alpha(0).setDuration(0);
                            txtHieuUngTruMotMucTieu.animate().translationX(0).translationY(0).setDuration(0);
                        }
                    });

                    MucTieu = MucTieu-1;
                    soundThanhCong.start();
                    txtMucTieu.setText(String.valueOf(MucTieu));
                    txtThongBaoThanhCong.animate().alpha(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtThongBaoThanhCong.animate().alpha(0);
                        }
                    });
                }
                else {
                    soundThatBai.start();
                    txtThongBaoThatBai.animate().alpha(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtThongBaoThatBai.animate().alpha(0);
                        }
                    });
                }
                /*if(localPre >= 740.0 && localPre <= 760.0) {
                    //Hiệu ứng trừ 1
                    txtHieuUngTruMotMucTieu.animate().alpha(1).setDuration(1000);
                    txtHieuUngTruMotMucTieu.animate().translationY(0).translationYBy(-200).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtHieuUngTruMotMucTieu.animate().alpha(0).setDuration(0);
                            txtHieuUngTruMotMucTieu.animate().translationX(0).translationY(0).setDuration(0);
                        }
                    });

                    MucTieu = MucTieu-1;
                    soundThanhCong.start();
                    txtMucTieu.setText(String.valueOf(MucTieu));
                    txtThongBaoThanhCong.animate().alpha(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtThongBaoThanhCong.animate().alpha(0);
                        }
                    });
                }
                else {
                    soundThatBai.start();
                    txtThongBaoThatBai.animate().alpha(1).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            txtThongBaoThatBai.animate().alpha(0);
                        }
                    });
                }*/

                //Đưa vòng tròn về vị trí ban đầu
                imgCircle.animate().translationX(0).translationY(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        btnBoThan.setEnabled(true);
                    }
                });

                if(MucTieu == 0){
                    soundGameWin.start();
                    txtThongBaoKetThuc.setText("Thành Công");
                    txtThongBaoKetThuc.setAlpha(1);

                    //Dừng hoạt động activity
                    imgCoal.animate().cancel();
                    imgCircle.animate().cancel();
                    imgCircle.animate().translationX(0).translationY(0).setDuration(0);
                    btnDung.setEnabled(false);
                    btnBoThan.setEnabled(false);
                }

            }
        });
    }


    private void makeAnimation (View view,int anim)
    {
        Animation animation = AnimationUtils.loadAnimation(this, anim);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }

    private void stopAnimation (View view)
    {
        view.clearAnimation();
    }


}