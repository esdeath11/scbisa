package MainPackage.Table;

import MainPackage.Helper.DBConnection;
import MainPackage.Interface.InDataGuru;
import MainPackage.MainController.ControllerBrain;
import MainPackage.Models.GuruModel;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataGuru extends ControllerBrain implements InDataGuru {

    @FXML
    private TableView<GuruModel> table;

    @FXML
    private TableColumn<GuruModel, String> id_guru;

    @FXML
    private TableColumn<GuruModel, String> nama_guru;

    @FXML
    private TableColumn<GuruModel, String> jabatan_guru;

    @FXML
    private TableColumn<GuruModel, String> gelar_guru;

    @FXML
    private TableColumn<GuruModel, String> mapel_guru;

    @FXML
    private TableColumn<GuruModel, String> alamat_guru;

    ObservableList<GuruModel> oblist = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        setOblist();
        showData();
        edit();
    }

    private void setOblist(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs =con.createStatement().executeQuery("SELECT * FROM guru");

            while (rs.next()){
                oblist.add(new GuruModel(rs.getString("id"),rs.getString("nama"),
                        rs.getString("jabatan"),rs.getString("gelar"),rs.getString("id_Mapel"),
                        rs.getString("Alamat")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void submitEdit(ActionEvent event) throws IOException {
        GuruModel guruModel = table.getSelectionModel().getSelectedItem();
        int a = Integer.parseInt(mapel_G);
        String query = "UPDATE `guru` SET `nama` = '"+guruModel.getNama()+"', " +
                "`jabatan` = '"+guruModel.getJabatan()+"', `gelar` = '"+guruModel.getGelar()+"', " +
                "`id_Mapel` = '"+guruModel.getMapel()+"', `Alamat` = '"+guruModel.getAlamat()+"' WHERE `guru`.`id` = "+valueID+";";
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        showData();
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
        id_guru.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama_guru.setCellValueFactory(new PropertyValueFactory<>("nama"));
        jabatan_guru.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        gelar_guru.setCellValueFactory(new PropertyValueFactory<>("gelar"));
        mapel_guru.setCellValueFactory(new PropertyValueFactory<>("mapel"));
        alamat_guru.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        table.setItems(oblist);
    }

    public void create(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/ResModel/createguru.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }



    public void checkItem(MouseEvent event){

        GuruModel guruModel = table.getSelectionModel().getSelectedItem();
        if (guruModel == null){
            System.out.println("no data");
        }else {
            valueID = guruModel.getId();
            nama_Guru = guruModel.getNama();
            jabatan_Guru = guruModel.getJabatan();
            Gelar_guru = guruModel.getGelar();
            mapel_G = guruModel.getMapel();
            Alamat_G = guruModel.getAlamat();
            System.out.println(guruModel.getNama());
        }
    }

    public void edit() {
        id_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        id_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        });

        nama_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        nama_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNama(e.getNewValue());
        });

        jabatan_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        jabatan_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setJabatan(e.getNewValue());
        });

        gelar_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        gelar_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGelar(e.getNewValue());
        });
        mapel_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel(e.getNewValue());
        });
        alamat_guru.setCellFactory(TextFieldTableCell.forTableColumn());
        alamat_guru.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAlamat(e.getNewValue());
        });

        table.setEditable(true);
    }

    public void delete(ActionEvent event) throws IOException {
        String query = "DELETE FROM `guru` WHERE `guru`.`id` = "+valueID;
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
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

    @FXML
    void detail(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/ResModel/detail_pegawai.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
