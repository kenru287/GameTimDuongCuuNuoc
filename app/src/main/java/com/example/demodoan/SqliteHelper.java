package com.example.demodoan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String stClass = "SqliteHelper";

    public SqliteHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
        //Tạo mới hoặc mở kết nối đến DB name
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Nên gọi các phương thức tạo bảng
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Thực hiện các thao tác chỉnh sửa cấu trúc bảng của DB
        Log.w(stClass, "Upgrading database from version "+ oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    //Tạo Bảng
    public boolean createTable(String stringCreateTable) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL(stringCreateTable);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    //Xóa bảng
    public boolean deleteTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("delete from " + tableName);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public Cursor getAllTableName(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);
        return res;
    }

    public boolean getSaveGame(int id,int nut, int canh){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NUT, CANH FROM TBL_SAVE_GAME WHERE ID = " + id,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getnut = res.getInt(0);
            int getcanh = res.getInt(1);
            if (nut == getnut && canh == getcanh) return true;
        }
        return false;
    }

    public int getCount(String tablename){
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT COUNT(*) FROM " + tablename,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            count = res.getInt(0);
            return count;
        }
        return -1;
    }

    public int getIDSG(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT ID FROM TBL_SAVE_GAME",null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int id = res.getInt(0);
            return id;
        }
        return -1;
    }

    public String getTenVatPham(int ma){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TBL_VAT_PHAM WHERE MAVATPHAM = " + ma,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            String ten = res.getString(res.getColumnIndex("TENVATPHAM"));
            return ten;
        }
        return null;
    }
    /*public int getcount(String tableName) {
        int count = 0;
        Cursor cursor = getAllTableName(tableName);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ cursor
                count++;
                cursor.moveToNext();
            }
        }
        return count;
    }*/

    /*public List<UserObject> getAllUserConvert(String tableName) {
        List<UserObject> userObjects = new ArrayList<UserObject>();
        Cursor cursor = getAllUser(tableName);
        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {//Cách lấy dữ liệu từ cursor
                String id = cursor.getString(cursor
                        .getColumnIndex("id"));
                String name = cursor.getString(cursor
                        .getColumnIndex("name"));

                userObjects.add(new UserObject(id, name));
                cursor.moveToNext();
            }
        }
        return userObjects;
    }*/
    //thêm bản ghi
    public boolean insertSaveGame(String tableName, int id, int nut, int canh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NUT", nut);
        contentValues.put("CANH", canh);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    public boolean insertSaveGame(String tableName, int id, int nut, int canh, String ngay, String thoigian, int tien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NUT", nut);
        contentValues.put("CANH", canh);
        contentValues.put("NGAY", ngay);
        contentValues.put("THOIGIANCHOI", thoigian);
        contentValues.put("TIEN", tien);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean insertCTSaveGame(String tableName, int id, int nut, int diem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NUT", nut);
        contentValues.put("DIEM", diem);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean insertNut(String tableName, int nut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUT", nut);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean insertVatPham(String tableName, int ma, String ten, boolean size, boolean sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAVATPHAM", ma);
        contentValues.put("TENVATPHAM", ten);
        contentValues.put("KICHCO", size);
        contentValues.put("SL", sl);

        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean insertTuiDo(String tableName, int mavatpham, int id, int sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAVATPHAM", mavatpham);
        contentValues.put("ID", id);
        contentValues.put("SOLUONG", sl);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean insertNguoiChoi(String tableName, int id, int nut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("IDSAVEGAME", id);
        contentValues.put("NUT", nut);
        try {
            db.insert(tableName, null, contentValues);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    //Cập nhật bản ghi
    public boolean updateSaveGame(int id, int nut, int canh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUT", nut);
        contentValues.put("CANH", canh);
        try {
            db.update("TBL_SAVE_GAME", contentValues, "ID = " + id, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateDiem(String tableName, int id, int nut, int diem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DIEM", diem);
        try {
            db.update(tableName, contentValues, "ID = " + id + "AND NUT = " + nut, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateTien(int id, int tien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIEN",tien);
        try {
            db.update("TBL_SAVE_GAME", contentValues, "ID = " + id, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateVatPham(String tableName, int ma, boolean sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SL", sl);
        try {
            db.update(tableName, contentValues, "MAVATPHAM = " + ma, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateTuiDo(String tableName, int mavatpham, int id, int sl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SOLUONG", sl);
        try {
            db.update(tableName, contentValues, "MAVATPHAM = " + mavatpham + "AND ID = " + id, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateNguoiChoi(String tableName, int id, int nut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDSAVEGAME", id);
        contentValues.put("NUT", nut);
        try {
            db.update(tableName, contentValues, "ID = 0", null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateIDSaveGame(String tableName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDSAVEGAME", id);
        try {
            db.update(tableName, contentValues, "ID = 0", null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateNutNguoiChoi(String tableName, int nut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUT", nut);
        try {
            db.update(tableName, contentValues, "ID = 0", null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean updateTienTrinh(String tableName, int id, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("THOIGIANCHOI", time);
        try {
            db.update(tableName, contentValues, "ID = " + id, null);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    //Lấy 1 trong bảng
    /*public String getTenVatPham(int ma){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TBL_VAT_PHAM WHERE MAVATPHAM = " + ma,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            String ten = res.getString(res.getColumnIndex("TENVATPHAM"));
            return ten;
        }
        return null;
    }*/

    public String getNgayChoi(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TBL_SAVE_GAME WHERE ID = " + id,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            String getngay = res.getString(res.getColumnIndex("NGAY"));
            return getngay;
        }
        return null;
    }

    public String getTime(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TBL_SAVE_GAME WHERE ID = " + id,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            String gettime = res.getString(res.getColumnIndex("THOIGIANCHOI"));
            return gettime;
        }
        return null;
    }

    public int getIDNguoiChoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT IDSAVEGAME FROM TBL_NGUOI_CHOI",null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getid = res.getInt(0);
            return getid;
        }
        return 0;
    }

    public int getNutNguoiChoi(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NUT FROM TBL_NGUOI_CHOI",null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getnut = res.getInt(0);
            return getnut;
        }
        return 0;
    }

    public int getNut(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NUT FROM TBL_SAVE_GAME WHERE ID = " + id,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getnut = res.getInt(0);
            return getnut;
        }
        return 0;
    }

    public int getDiem(int id, int nut){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT DIEM FROM TBL_CT_SAVE_GAME WHERE ID = " + id + "AND NUT = " + nut,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getdiem = res.getInt(0);
            return getdiem;
        }
        return 0;
    }

    public int getTien(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT TIEN FROM TBL_SAVE_GAME WHERE ID = " + id,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int gettien = res.getInt(0);
            return gettien;
        }
        return 0;
    }

    public int getTuiDo(int ma, int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT SL FROM TBL_TUI_DO WHERE ID = " + id + " AND MAVATPHAM = " + ma,null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            int getsl = res.getInt(0);
            return getsl;
        }
        return 0;
    }

    //Xóa bản ghi
    public Integer deleteSaveGame(String tableName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID = " + id, null);
    }
    //Xóa bản ghi với nhiều hơn 1 điều kiện
    public Integer deleteTuiDo(String tableName, int mavatpham, int manhanvat) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "MAVATPHAM = " + mavatpham + " AND " + "MANHANVAT = " + manhanvat, null);
    }
}
