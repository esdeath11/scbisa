package MainPackage.Models;

import java.math.BigInteger;

public class GuruModel {
    String id,nama,jabatan,gelar,mapel,alamat;

    public GuruModel(String id, String nama, String jabatan, String gelar, String mapel, String alamat) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.gelar = gelar;
        this.mapel = mapel;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
