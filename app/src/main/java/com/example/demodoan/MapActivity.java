package com.example.demodoan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {
    RelativeLayout manhinh, map, khung;
    TextView chon, date, quocgia;
    ImageView thuyen, moc1, moc2, moc3, moc4, thuyenbien, ben, thuyen1;
    float xDown = 0, yDown = 0;
    private Handler mHandler;
    ObjectAnimator ngang, doc, xoay;
    int x, y;
    Boolean condition=false;
   // Animation zoomin;

    private SqliteHelper sqliteHelper;
    final  String DB_NAME="DB_Demo";
    final  String TABLE_NAME="TBL_SAVE_GAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //manhinh = findViewById(R.id.manhinhmap);
        map = findViewById(R.id.idmap);
        khung = findViewById(R.id.frame_cottruyen);
        moc1 = findViewById(R.id.imagemoc1);
        moc2 = findViewById(R.id.imagemoc2);
        moc3 = findViewById(R.id.imagemoc3);
        thuyen = findViewById(R.id.imageship);
        thuyenbien = findViewById(R.id.thuyenbien);
        thuyen1 = findViewById(R.id.imageship2);
        ben = findViewById(R.id.igvBen);
        date = findViewById(R.id.txtDate);
        quocgia = findViewById(R.id.txtQuocGia);

        thuyen1.setImageAlpha(0);
        map.setPivotX(920);
        map.setPivotY(470);
        map.animate().scaleXBy(3).scaleYBy(3);
        moc2.setImageAlpha(0);
        moc2.setEnabled(false);
        moc3.setImageAlpha(0);
        moc3.setEnabled(false);

        //registerForContextMenu(chon);
       // map.setImageBitmap(decodeSampledBitmapFromResource(getResources(),R.drawable.map,500,500));
        //zoomin = AnimationUtils.loadAnimation(this,R.anim.zoom_in);

        map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = event.getX();
                        movedY = event.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        map.setX(map.getX()+distanceX);
                        map.setY(map.getY()+distanceY);
                        break;
                }
                return true;
            }
        });

        sqliteHelper = new SqliteHelper(this,DB_NAME,1);
        if(sqliteHelper.getSaveGame(0,0,2)){
            thuyen.setImageAlpha(0);
            manhinh.setAlpha(0);
            sqliteHelper.updateSaveGame(0, 0,3);
            map.setPivotX(920);
            map.setPivotY(470);
            map.animate().scaleXBy(3).scaleYBy(3).setDuration(10000);
            khung.animate().alpha(1).setDuration(2000);
            moc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this,R.style.AlertDialogTheme);
                    builder.setTitle("Bạn có muốn tiếp tục?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapActivity.this, MissionMatch3Activity.class);
                            startActivity(intent);
                            finish();
                            dialog.dismiss();
                            khung.setAlpha(0);
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
            });
        }

        if(sqliteHelper.getSaveGame(0,0,3)){
            thuyen.setImageAlpha(0);
            manhinh.setAlpha(0);
            moc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this,R.style.AlertDialogTheme);
                    builder.setTitle("Bạn có muốn tiếp tục?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapActivity.this, MissionMatch3Activity.class);
                            startActivity(intent);
                            finish();
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
            });
        }

        if(sqliteHelper.getSaveGame(0,1,2)){
            date.setText("Ngày: 8 Tháng: 6 Năm: 1911");
            quocgia.setText("Singapore");
            moc2.setImageAlpha(255);
            moc2.setEnabled(true);
            moc1.setImageResource(R.drawable.mocvang);
            moc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DiChuyenDenMoc(moc2);
                }
            });
        }

        if(sqliteHelper.getSaveGame(0,2,0)){
            //thuyen.setX(moc2.getX());
            //thuyen.setY(moc2.getY());
            //doc = ObjectAnimator.ofFloat(thuyen,"y",moc2.getY());
            //doc.setDuration(10);
            //doc.start();
            thuyen.setImageAlpha(0);
            thuyen1.setImageAlpha(255);
            moc2.setImageAlpha(255);
            moc2.setEnabled(true);
            moc1.setImageResource(R.drawable.mocvang);
            moc3.setImageAlpha(255);
            moc2.setImageResource(R.drawable.mocvang);
            moc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VaoBenCang();
                }
            });
        }

        if(sqliteHelper.getSaveGame(0,2,1)){
            moc2.setImageAlpha(255);
            moc2.setEnabled(true);
            moc1.setImageResource(R.drawable.mocvang);
            moc3.setImageAlpha(255);
            moc3.setEnabled(true);
            moc2.setImageResource(R.drawable.mocvang);
            if(thuyen.getX() == moc2.getX() && thuyen.getY() == moc2.getY()){
                moc2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VaoBenCang();
                    }
                });
            }
            moc3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DiChuyenDenMoc(moc3);
                }
            });
        }
    }

    public void VaoBenCang(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this,R.style.AlertDialogTheme);
        builder.setTitle("Bạn có muốn vào bến cảng ?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MapActivity.this,BenCangActivity.class);
                startActivity(intent);
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

    public void DiChuyenDenMoc(ImageView m){
        if(!ThongBaoChoiGame(m)){
            ngang = ObjectAnimator.ofFloat(thuyenbien,"x",ben.getX());
            final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this,R.style.AlertDialogTheme);
            builder.setTitle("Bạn có muốn di chuyển?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ngang.setDuration(3000);
                    ngang.start();
                    condition=false;
                    starthandler(m);
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
    }

    public boolean ThongBaoChoiGame(ImageView m){
        x = (int) m.getX();
        y = (int) m.getY();
        if(x == thuyen.getX() && y == thuyen.getY()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this, R.style.AlertDialogTheme);
            builder.setTitle("Bạn có muốn tiếp tục?").setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MapActivity.this, MissionMatch3Activity.class);
                    startActivity(intent);
                    finish();
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
            return true;
        }
        return false;
    }

    public void starthandler(ImageView m)
    {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!condition)
                {
                 dichuyen(m);
                }
            }
        }, 100);
    }


    public void stophandler()
    {
        condition=true;
    }

    public void dichuyen(ImageView m){
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
                    stophandler();
                }
    }

    private void showTuyChonDialog(){
        //final String[] listItems = ("Cài Đặt","");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.tuy_chon, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.caidat:
                Toast.makeText(this,"Đang phát triển",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.hethong:
                return true;
            case R.id.cuahang:
                Toast.makeText(this,"Đang phát triển",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.skill:
                Toast.makeText(this,"Đang phát triển",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nhatky:
                Toast.makeText(this,"Đang phát triển",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

   /* private  static  int CalculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final  int haftWidth = width / 2;

            while ( (halfHeight / inSampleSize) >= reqHeight && (haftWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        return  inSampleSize;
    }

    public  static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final  BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId,options);
        options.inSampleSize = CalculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return  BitmapFactory.decodeResource(res,resId,options);
    }*/
}