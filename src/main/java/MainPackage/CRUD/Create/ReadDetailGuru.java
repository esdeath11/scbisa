package MainPackage.CRUD.Create;

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
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadDetailGuru extends ControllerBrain {
    @FXML
    private Text nip_text;

    @FXML
    private Text nama_text;

    @FXML
    private Text jabatan_text;

    @FXML
    private Text gelar_text;

    @FXML
    private Text mapel_text;
    @FXML
    private Text alamat;

    @FXML
    private void initialize(){
        nip_text.setText(valueID);
        nama_text.setText(nama_Guru);
        jabatan_text.setText(jabatan_Guru);
        gelar_text.setText(Gelar_guru);
        alamat.setText(Alamat_G);
        mapel_text.setText(mapel_G);
    }

    @FXML
    void Submit(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
