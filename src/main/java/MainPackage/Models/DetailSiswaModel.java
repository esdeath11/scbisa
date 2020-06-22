package MainPackage.Models;

public class DetailSiswaModel {
    String id,id_detail_siswa, nama_detail_siswa, semester,mapel1,mapel2,mapel3,mapel4,mapel5,mapel6;


    public DetailSiswaModel(String id,String id_detail_siswa, String nama_detail_siswa, String semester,String mapel1, String mapel2,
                            String mapel3, String mapel4, String mapel5, String mapel6) {
        this.id = id;
        this.id_detail_siswa = id_detail_siswa;
        this.nama_detail_siswa = nama_detail_siswa;
        this.semester = semester;
        this.mapel1 = mapel1;
        this.mapel2 = mapel2;
        this.mapel3 = mapel3;
        this.mapel4 = mapel4;
        this.mapel5 = mapel5;
        this.mapel6 = mapel6;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getId_detail_siswa() {
        return id_detail_siswa;
    }

    public void setId_detail_siswa(String id_detail_siswa) {
        this.id_detail_siswa = id_detail_siswa;
    }

    public String getNama_detail_siswa() {
        return nama_detail_siswa;
    }

    public void setNama_detail_siswa(String nama_detail_siswa) {
        this.nama_detail_siswa = nama_detail_siswa;
    }

    public String getMapel1() {
        return mapel1;
    }

    public void setMapel1(String mapel1) {
        this.mapel1 = mapel1;
    }

    public String getMapel2() {
        return mapel2;
    }

    public void setMapel2(String mapel2) {
        this.mapel2 = mapel2;
    }

    public String getMapel3() {
        return mapel3;
    }

    public void setMapel3(String mapel3) {
        this.mapel3 = mapel3;
    }

    public String getMapel4() {
        return mapel4;
    }

    public void setMapel4(String mapel4) {
        this.mapel4 = mapel4;
    }

    public String getMapel5() {
        return mapel5;
    }

    public void setMapel5(String mapel5) {
        this.mapel5 = mapel5;
    }

    public String getMapel6() {
        return mapel6;
    }

    public void setMapel6(String mapel6) {
        this.mapel6 = mapel6;
    }
}
