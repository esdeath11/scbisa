package MainPackage.CRUD.Create;

import MainPackage.Helper.DBConnection;
import MainPackage.MainController.ControllerBrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class CreateArsip extends ControllerBrain {
    @FXML
    private TextField judul;

    @FXML
    private RadioButton laporan;

    @FXML
    private RadioButton proposal;

    @FXML
    private Text name_file;

    @FXML
    void kembali(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    boolean check = false;
    String kategori = "";
    String filepath = "";

    @FXML
    void pilih(ActionEvent event) {




        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file","*.pdf"));
        File f = fc.showOpenDialog(null);
        if(f != null){
            name_file.setText(f.getAbsolutePath());
            check = true;
            filepath = f.getAbsolutePath();
        }
        else {
            check = false;
            name_file.setText("No File Selected");
        }


    }

    private void checkArsipType() {
        if (proposal.isSelected()) {
            kategori = "Proposal";
            System.out.println("proposal");
        }
        else if (laporan.isSelected()) {
            kategori = "Laporan";
            System.out.println("Laporan");
        }
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        checkArsipType();
            if (check == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("silahkan pilih kategori atau file belum dipilih");
                alert.show();
            }else {
                String query = "INSERT INTO `arsip` (`id`, `nama_penulis`, `kategori`, `path`) " +
                        "VALUES (NULL, '"+name_b+"', '"+kategori+"', '"+filepath+"');";
                executeQuery(query);
                if (jabatan_b.equals("Kepala Sekolah")){
                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsipKepsek.fxml"));
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();
                }
                else if(jabatan_b.equals("Guru")){
                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Table/ListArsip.fxml"));
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();
                }
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
