package MainPackage.CRUD.Create;

import MainPackage.Helper.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class CreateSiswa {

    @FXML
    private TextField id_n;

    @FXML
    private TextField nama_n;

    @FXML
    private TextField kelas_n;

    @FXML
    private TextField tempat_n;

    @FXML
    private TextArea alamat_n;

    @FXML
    private DatePicker tanggal_n;

    @FXML
    private Text status;

    public void submit(ActionEvent event) throws IOException {
        if(id_n.getText() == null || nama_n.getText() == null || kelas_n.getText() == null || tempat_n.getText() == null || tanggal_n.getValue() == null ||
                alamat_n.getText() == null){
            status.setText("Status : form tidak lengkap");
        }
        else {
            String query = "INSERT INTO `siswa` (`id`, `nama`, `kelas_id`, `tp_lahir`, `tl_lahir`, `alamat`, `password`) " +
                    "VALUES ('"+id_n.getText()+"', '"+nama_n.getText()+"', '"+kelas_n.getText()+"', " +
                    "'"+tempat_n.getText()+"', '"+tanggal_n.getValue()+"', '"+alamat_n.getText()+"', '123');";
            executeQuery(query);
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }

    }

    public void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/datasiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
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
