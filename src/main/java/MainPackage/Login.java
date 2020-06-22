package MainPackage;

import MainPackage.Helper.DBConnection;
import MainPackage.Helper.DBLogin;
import MainPackage.MainController.ControllerBrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends ControllerBrain {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize(){

    }



    @FXML
    void login(ActionEvent event) throws IOException {
        String uname = username.getText();
        username_b = uname;
        String pass = password.getText();

        if(DBLogin.CheckLoginUser(uname,pass)){
            checkJabatan();
            loginButton.getScene().getWindow().hide();
            Stage dashboard = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard.fxml"));
            Scene scene = new Scene(root);
            dashboard.setScene(scene);
            dashboard.show();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Error in login system");
            alert.show();
            username.setText("");
            password.setText("");
        }


    }

    private void checkJabatan(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT jabatan FROM guru where id = "+ username_b);
            if (rs.next()){
                jabatan_b = rs.getString("jabatan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
