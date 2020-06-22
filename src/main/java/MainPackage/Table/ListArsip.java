package MainPackage.Table;

import MainPackage.Helper.DBConnection;
import MainPackage.MainController.ControllerBrain;
import MainPackage.Models.ArsipModel;
import MainPackage.Models.GuruModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ListArsip extends ControllerBrain {

    @FXML
    private TableView<ArsipModel> table;

    @FXML
    private TableColumn<ArsipModel, String> id;

    @FXML
    private TableColumn<ArsipModel, String> judul;

    @FXML
    private TableColumn<ArsipModel, String> penulis;

    @FXML
    private TableColumn<ArsipModel, String> kategori;

    @FXML
    private TableColumn<ArsipModel, Date> tanggal;

    @FXML
    private Text id_arsip_verifikasi;

    ObservableList<ArsipModel> oblist = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        getData();
        showData();

    }


    private void getData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM arsip");
            while (rs.next()){
                oblist.add(new ArsipModel(rs.getString(1),rs.getString(3),rs.getString(2),
                        rs.getString(5),rs.getDate(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showData(){

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        penulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        table.setItems(oblist);


    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        String query = "DELETE FROM `arsip` WHERE `arsip`.`id` = "+arsip_ID;
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsipKepsek.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void getfile(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("File Sudah di download!!");
        alert.show();
    }

    @FXML
    void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void no(ActionEvent event) throws IOException {
        getVerifikasi();
        if (statusVer != 0){
            String query = "UPDATE `arsip` SET `verifikasi` = '0' WHERE `arsip`.`id` ="+arsip_ID+";";
            executeQuery(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Arsip telah ditolak!!");
            alert.show();
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsipKepsek.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Arsip sudah ditolak!!");
            alert.show();
        }

    }

    int statusVer;

    @FXML
    void yes(ActionEvent event) throws IOException {

        if(jabatan_b.equals("Kepala Sekolah")){
            getVerifikasi();
            if(statusVer != 1){
                String query = "UPDATE `arsip` SET `verifikasi` = '1' WHERE `arsip`.`id` ="+arsip_ID+";";
                executeQuery(query);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Arsip telah diverifikasi!!");
                alert.show();
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsipKepsek.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Arsip sudah diverifikasi!!");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Anda bukan kepala sekolah!!");
            alert.show();
        }

    }


    private void getVerifikasi(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT verifikasi FROM arsip where id = "+arsip_ID);
            if (rs.next()){
                statusVer = rs.getInt("verifikasi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getID(MouseEvent event) {
        ArsipModel arsipModel = table.getSelectionModel().getSelectedItem();
        if (arsipModel == null){
            System.out.println("null");
        }
        else {
            arsip_ID = arsipModel.getId();
            id_arsip_verifikasi.setText("ID ARSIP "+arsip_ID);
        }
    }

    private void executeQuery(String query){
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
}
