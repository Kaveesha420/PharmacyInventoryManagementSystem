package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginFormController {

    public CheckBox CboxRememberme;
    public PasswordField txtPassword;
    public TextField txtUsername;
    Stage stage = new Stage();

    @FXML
    public void OnLogin(ActionEvent event) {
        String name = txtUsername.getText();
        String password = txtPassword.getText();

        if ("admin".equals(name) && "1234".equals(password)){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Dashboard");
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid User");
            alert.setContentText("Please Input Valid UserName and Password");
            alert.showAndWait();
        }
    }
}