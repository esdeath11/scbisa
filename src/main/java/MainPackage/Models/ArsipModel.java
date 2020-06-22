package MainPackage.Models;

import java.sql.Date;

public class ArsipModel {
    String id,judul,penulis,kategori;
    Date tanggal;

    public ArsipModel(String id, String judul, String penulis, String kategori, Date tanggal) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
