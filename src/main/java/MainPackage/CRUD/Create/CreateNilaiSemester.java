package MainPackage.CRUD.Create;

import MainPackage.Helper.DBConnection;
import MainPackage.MainController.ControllerBrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class CreateNilaiSemester extends ControllerBrain {
    @FXML
    private TextField mapel1_new;

    @FXML
    private TextField mapel2_new;

    @FXML
    private TextField mapel3_new;

    @FXML
    private TextField mapel4_new;

    @FXML
    private TextField mapel5_new;

    @FXML
    private TextField mapel6_new;

    @FXML
    private Text id_nilai;

    @FXML
    private Text nama_nilai;

    @FXML
    private TextField semester_new;

    @FXML
    private void initialize(){
        id_nilai.setText(siswa_id);
        nama_nilai.setText(siswa_nama);
    }

    @FXML
    void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Detail/detailsiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        String query = "INSERT INTO `detail_nilai` (`id`,`id_siswa`, `nama_siswa`, `semester`, `mapel1`, `mapel2`, `mapel3`, `mapel4`, `mapel5`, `mapel6`) " +
                "VALUES (NULL,'"+siswa_id+"', '"+siswa_nama+"','"+semester_new.getText()+"', '"+mapel1_new.getText()+"', '"+mapel2_new.getText()+"'," +
                " '"+mapel3_new.getText()+"', '"+mapel4_new.getText()+"', '"+mapel5_new.getText()+"', '"+mapel6_new.getText()+"');";
        executeQuery(query);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Detail/detailsiswa.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

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

}
