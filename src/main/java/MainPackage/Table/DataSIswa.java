package MainPackage.Table;

import MainPackage.Helper.DBConnection;
import MainPackage.Interface.InDataSiswa;
import MainPackage.MainController.ControllerBrain;
import MainPackage.Models.GuruModel;
import MainPackage.Models.SiswaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSIswa extends ControllerBrain implements InDataSiswa {

    @FXML
    private TableView<SiswaModel> table;

    @FXML
    private TableColumn<SiswaModel, String> id_siswa;

    @FXML
    private TableColumn<SiswaModel, String> nama_siswa;

    @FXML
    private TableColumn<SiswaModel, String> kelas;

    @FXML
    private TableColumn<SiswaModel, String> tp_lahir;

    @FXML
    private TableColumn<SiswaModel, String> tg_lahir;

    @FXML
    private TableColumn<SiswaModel, String> alamat_siswa;

    ObservableList<SiswaModel> oblist = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        getData();
        showData();
        edit();
    }

    private void getData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM siswa");
            while (rs.next()){
                oblist.add(new SiswaModel(rs.getString("id"),rs.getString("nama"),rs.getString("kelas_id"),
                        rs.getString("tp_lahir"),rs.getString("alamat"),
                        rs.getString("tl_lahir")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void checkData(){
        SiswaModel siswaModel = table.getSelectionModel().getSelectedItem();
        if (siswaModel == null){
            System.out.println("no data");
        }else {
            siswa_id = siswaModel.getId();
            siswa_nama = siswaModel.getNama();
            kelas_s = siswaModel.getKelas();
            tp_lahir_s = siswaModel.getTp_lahir();
            tl_lahir_s = siswaModel.getAlamat();
            alamat_siswa_s = siswaModel.getAlamat();

            System.out.println(siswaModel.getNama());
        }
    }

    public void create(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/ResModel/createsiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void edit() {
        id_siswa.setCellFactory(TextFieldTableCell.forTableColumn());
        id_siswa.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });

        nama_siswa.setCellFactory(TextFieldTableCell.forTableColumn());
        nama_siswa.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNama(e.getNewValue());
        });

        kelas.setCellFactory(TextFieldTableCell.forTableColumn());
        kelas.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setKelas(e.getNewValue());
        });

        tp_lahir.setCellFactory(TextFieldTableCell.forTableColumn());
        tp_lahir.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTp_lahir(e.getNewValue());
        });
        tg_lahir.setCellFactory(TextFieldTableCell.forTableColumn());
        tg_lahir.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTgl_lahir(e.getNewValue());
        });
        alamat_siswa.setCellFactory(TextFieldTableCell.forTableColumn());
        alamat_siswa.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAlamat(e.getNewValue());
        });

        table.setEditable(true);
    }

    public void update(ActionEvent event) throws IOException {
        SiswaModel siswaModel = table.getSelectionModel().getSelectedItem();
        String query = "UPDATE `siswa` SET `id` = '"+siswaModel.getId()+"', `nama` = '"+siswaModel.getNama()+"'," +
                " `kelas_id` = '"+siswaModel.getKelas()+"', `tp_lahir` = '"+siswaModel.getTp_lahir()+"'," +
                " `tl_lahir` = '"+siswaModel.getTgl_lahir()+"', `alamat` = '"+siswaModel.getAlamat()+"' WHERE `siswa`.`id` = "+siswa_id+";";
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        showData();
    }

    public void deleteData(ActionEvent event) throws IOException {
        String query = "DELETE FROM `siswa` WHERE `guru`.`id` = "+siswa_id;
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        showData();
    }

    public void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void executeQuery(String query) {
        try {
            Connection con = DBConnection.getConnection();
            Statement st;
            System.out.println(query);
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("gagal");
        }
    }

    @Override
    public void showData() {
        id_siswa.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama_siswa.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        tp_lahir.setCellValueFactory(new PropertyValueFactory<>("tp_lahir"));
        tg_lahir.setCellValueFactory(new PropertyValueFactory<>("tgl_lahir"));
        alamat_siswa.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        table.setItems(oblist);
    }

    public void Detail(ActionEvent event) throws IOException {
        if(siswa_id ==null){
            System.out.println("ID SISWA YANG DIPILIH = "+siswa_id);
        }
        else {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Detail/detailsiswa.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }

    }
}
