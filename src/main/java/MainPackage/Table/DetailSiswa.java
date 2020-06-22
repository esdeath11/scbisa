package MainPackage.Table;

import MainPackage.Helper.DBConnection;
import MainPackage.Interface.InDetailSiswa;
import MainPackage.MainController.ControllerBrain;
import MainPackage.Models.DetailSiswaModel;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailSiswa extends ControllerBrain implements InDetailSiswa {
    @FXML
    private TableView<DetailSiswaModel> tabel;

    @FXML
    private TableColumn<DetailSiswaModel, String> semester;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel1;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel2;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel3;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel4;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel5;

    @FXML
    private TableColumn<DetailSiswaModel, String> mapel6;

    ObservableList<DetailSiswaModel> oblist = FXCollections.observableArrayList();

    @FXML
    private Text id_detail;

    @FXML
    private Text nama_detail;

    @FXML
    private void initialize(){
        getData();
        showData();
        getValueTable();
    }


    private void getData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `detail_nilai` WHERE `id_siswa` = "+siswa_id);
            while (rs.next()){
                oblist.add(new DetailSiswaModel(rs.getString("id"),rs.getString("id_siswa"),rs.getString("nama_siswa"),
                        rs.getString("semester"),rs.getString("mapel1"),rs.getString("mapel2"),
                        rs.getString("mapel3"),rs.getString("mapel4"),rs.getString("mapel5")
                        ,rs.getString("mapel6")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void createNilaiSemester(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/ResModel/tambahnilai.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        DetailSiswaModel ds = tabel.getSelectionModel().getSelectedItem();
        String query = "UPDATE `detail_nilai` SET `semester` = '"+ds.getSemester()+"', `mapel1` = '"+ds.getMapel1()+"', " +
                "`mapel2` = '"+ds.getMapel2()+"', `mapel3` = '"+ds.getMapel3()+"', `mapel4` = '"+ds.getMapel4()+"', " +
                "`mapel5` = '"+ds.getMapel5()+"', `mapel6` = '"+ds.getMapel6()+"' WHERE `detail_nilai`.`semester` = "+semester_s+";";
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Detail/detailsiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        showData();
    }


//    private void checkData(){

//
//    }

    private void getValueTable(){
        semester.setCellFactory(TextFieldTableCell.forTableColumn());
        semester.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSemester(e.getNewValue());
        });
        mapel1.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel1.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel1(e.getNewValue());
        });
        mapel2.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel2.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel2(e.getNewValue());
        });
        mapel3.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel3.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel3(e.getNewValue());
        });
        mapel4.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel4.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel4(e.getNewValue());
        });
        mapel5.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel5.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel5(e.getNewValue());
        });
        mapel6.setCellFactory(TextFieldTableCell.forTableColumn());
        mapel6.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapel6(e.getNewValue());
        });

        tabel.setEditable(true);

    }

    @Override
    public void showData() {
        id_detail.setText(siswa_id);
        nama_detail.setText(siswa_nama);
        semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        mapel1.setCellValueFactory(new  PropertyValueFactory<>("mapel1"));
        mapel2.setCellValueFactory(new  PropertyValueFactory<>("mapel2"));
        mapel3.setCellValueFactory(new  PropertyValueFactory<>("mapel3"));
        mapel4.setCellValueFactory(new  PropertyValueFactory<>("mapel4"));
        mapel5.setCellValueFactory(new  PropertyValueFactory<>("mapel5"));
        mapel6.setCellValueFactory(new  PropertyValueFactory<>("mapel6"));

        tabel.setItems(oblist);
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

    public void checkData(MouseEvent event) {
        DetailSiswaModel ds = tabel.getSelectionModel().getSelectedItem();
        if(ds == null){
            System.out.println("null");
        }
        else {
            semester_s = ds.getSemester();
        }
    }

    public void Delete(ActionEvent event) throws IOException {
        String query = "DELETE FROM `detail_nilai` WHERE `detail_nilai`.`semester` = "+semester_s;
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Detail/detailsiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
