package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicineFormController {

    Stage stage = new Stage();

    @FXML
    private TableColumn<?, ?> colBrandName;

    @FXML
    private TableColumn<?, ?> colCurrentStock;

    @FXML
    private TableColumn<?, ?> colDosage;

    @FXML
    private TableColumn<?, ?> colExpireDate;

    @FXML
    private TableColumn<?, ?> colGenaricName;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<?> tblMedicine;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void OnAddNewDrug(ActionEvent event) {

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

    }

    @FXML
    void OnSearchDrug(KeyEvent event) {

    }

    @FXML
    void OnSuppliers(MouseEvent event) {

    }

}
