package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicineFormController {

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

    }

    @FXML
    void OnBilling(MouseEvent event) {

    }

    @FXML
    void OnDashbord(MouseEvent event) {

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
