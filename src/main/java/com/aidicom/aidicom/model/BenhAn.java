package com.aidicom.aidicom.model;

public class BenhAn {
    private static long idCounter = 1; // Tự động tăng ID
    private long id;

    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;

    private String mach;
    private String nhietDo;
    private String huyetAp;
    private String nhipTho;
    private String chieuCao;
    private String canNang;

    // Trường bổ sung
    private String maBenhNhan;
    private String lyDoKham;
    private String chanDoanSoBo;
    private String ngayKham;
    private String ghiChu;
    private String bacSiPhuTrach;

    public BenhAn() {
        this.id = idCounter++;
    }

    public BenhAn(String hoTen, String gioiTinh, String ngaySinh, String mach, String nhietDo,
                  String huyetAp, String nhipTho, String chieuCao, String canNang,
                  String maBenhNhan, String lyDoKham, String chanDoanSoBo, String ngayKham,
                  String ghiChu, String bacSiPhuTrach) {
        this.id = idCounter++;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.mach = mach;
        this.nhietDo = nhietDo;
        this.huyetAp = huyetAp;
        this.nhipTho = nhipTho;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.maBenhNhan = maBenhNhan;
        this.lyDoKham = lyDoKham;
        this.chanDoanSoBo = chanDoanSoBo;
        this.ngayKham = ngayKham;
        this.ghiChu = ghiChu;
        this.bacSiPhuTrach = bacSiPhuTrach;
    }

    public long getId() {
        return id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMach() {
        return mach;
    }

    public void setMach(String mach) {
        this.mach = mach;
    }

    public String getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(String nhietDo) {
        this.nhietDo = nhietDo;
    }

    public String getHuyetAp() {
        return huyetAp;
    }

    public void setHuyetAp(String huyetAp) {
        this.huyetAp = huyetAp;
    }

    public String getNhipTho() {
        return nhipTho;
    }

    public void setNhipTho(String nhipTho) {
        this.nhipTho = nhipTho;
    }

    public String getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(String chieuCao) {
        this.chieuCao = chieuCao;
    }

    public String getCanNang() {
        return canNang;
    }

    public void setCanNang(String canNang) {
        this.canNang = canNang;
    }

    public String getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(String maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getChanDoanSoBo() {
        return chanDoanSoBo;
    }

    public void setChanDoanSoBo(String chanDoanSoBo) {
        this.chanDoanSoBo = chanDoanSoBo;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getBacSiPhuTrach() {
        return bacSiPhuTrach;
    }

    public void setBacSiPhuTrach(String bacSiPhuTrach) {
        this.bacSiPhuTrach = bacSiPhuTrach;
    }
}
