package MainPackage.Models;

import java.sql.Date;

public class SiswaModel {
    String id,nama,kelas,tp_lahir,alamat,tgl_lahir;


    public SiswaModel(String id, String nama, String kelas, String tp_lahir, String alamat, String tgl_lahir) {
        this.id = id;
        this.nama = nama;
        this.kelas = kelas;
        this.tp_lahir = tp_lahir;
        this.alamat = alamat;
        this.tgl_lahir = tgl_lahir;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
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

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }


    public String getTp_lahir() {
        return tp_lahir;
    }

    public void setTp_lahir(String tp_lahir) {
        this.tp_lahir = tp_lahir;
    }


    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
