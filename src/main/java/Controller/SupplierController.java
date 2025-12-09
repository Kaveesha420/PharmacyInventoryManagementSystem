package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierController {

    Stage stage = new Stage();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    void OnAddNewSupplier(ActionEvent event) {

    }

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
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.show();
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
