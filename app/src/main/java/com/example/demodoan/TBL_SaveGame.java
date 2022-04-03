package com.example.demodoan;

public class TBL_SaveGame {
    private int id;
    private int nut;
    private int canh;
    private String ngay, thoigian;
    public TBL_SaveGame(int id, int nut, int canh){
        this.id = id;
        this.nut = nut;
        this.canh = canh;
    }
    public TBL_SaveGame(int id, int nut, int canh, String ngay, String thoigian){
        this.id = id;
        this.nut = nut;
        this.canh = canh;
        this.ngay = ngay;
        this.thoigian = thoigian;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public int getCanh() {
        return canh;
    }

    public void setCanh(int canh) {
        this.canh = canh;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThoiGian() {
        return thoigian;
    }

    public void setThoiGian(String thoigian) {
        this.thoigian = thoigian;
    }
}
