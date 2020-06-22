package MainPackage.CRUD.Create;

import MainPackage.Helper.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class CreateGuru {

    @FXML
    private TextField id;

    @FXML
    private TextField nama;

    @FXML
    private TextField jabatan;

    @FXML
    private TextField gelar;

    @FXML
    private TextArea alamat;

    @FXML
    private TextField mapel;

    @FXML
    private Text status;


    @FXML
    private void initialize(){

    }

    public void submit(ActionEvent event) throws IOException {
        if(id.getText().trim().isEmpty()||nama.getText().trim().isEmpty()||jabatan.getText().trim().isEmpty()||
                gelar.getText().trim().isEmpty() ||alamat.getText().trim().isEmpty()||mapel.getText().trim().isEmpty()){
            System.out.println("harus di isi");
            status.setText("Status : Harap melengkapi data");

        }else {
            System.out.println("bisa");
            String query = "INSERT INTO `guru` (`id`, `nama`, `jabatan`, `gelar`, `id_Mapel`, `Alamat`, `password`) " +
                    "VALUES ('"+id.getText()+"', '"+nama.getText()+"', '"+jabatan.getText()+"'," +
                    " '"+gelar.getText()+"', '"+mapel.getText()+"', '"+alamat.getText()+"', '123');";
            executeQuery(query);
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }


    }



    public void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/dataguru.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void checklist(ActionEvent event) {

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
