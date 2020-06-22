package MainPackage;

import MainPackage.Helper.DBConnection;
import MainPackage.MainController.ControllerBrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard extends ControllerBrain {

    @FXML
    private Text id;

    @FXML
    private Text nama_login;

    @FXML
    private Text Jabatan_login;


    @FXML
    private void initialize(){
        id.setText("ID LOGIN  : "+username_b);
        getData();
        Jabatan_login.setText("JABATAN  : "+jabatan_b);
        nama_login.setText("NAMA      : "+name_b);

    }
    
    public void dataGuru(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void dataSiswa(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void arsip(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/ResModel/createarsip.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void lihatArsip(ActionEvent event) throws IOException {
        if(jabatan_b.equals("Guru")){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsip.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
        else if(jabatan_b.equals("Kepala Sekolah") || jabatan_b.equals("TU")){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsipKepsek.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }

    }

    public void logout(ActionEvent event) throws IOException {
        username_b = "";
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    private void getData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM guru where id = "+ username_b);
            if(rs.next()){
                name_b = rs.getString("nama");
                jabatan_b = rs.getString("jabatan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void verifikasi(ActionEvent event) {

    }
}
