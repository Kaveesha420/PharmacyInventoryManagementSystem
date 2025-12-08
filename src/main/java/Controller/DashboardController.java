package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    Stage stage = new Stage();

    @FXML
    void OnAlerts(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AletsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Alets");
        stage.show();
    }

    @FXML
    void OnBilling(MouseEvent event) {

    }

    @FXML
    void OnDashbord(MouseEvent event) {

    }

    @FXML
    void OnMedicine(MouseEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/MedicineForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Medicine");
        stage.show();
    }

    @FXML
    void OnSuppliers(MouseEvent event) {

    }

}
