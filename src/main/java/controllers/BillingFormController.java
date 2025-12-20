package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class BillingFormController {

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colMedicineId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblGrandTotal;

    @FXML
    private JFXListView<?> listSearchResults;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private JFXTextField txtSearch1;

    @FXML
    void OnAlerts(MouseEvent event) {

    }

    @FXML
    void OnBilling(MouseEvent event) {

    }

    @FXML
    void OnCartQtyEditCommit(ActionEvent event) {

    }

    @FXML
    void OnCheckout(ActionEvent event) {

    }

    @FXML
    void OnDashbord(MouseEvent event) {

    }

    @FXML
    void OnMedicine(MouseEvent event) {

    }

    @FXML
    void OnRemoveFromCart(ActionEvent event) {

    }

    @FXML
    void OnSearchKeyReleased(KeyEvent event) {

    }

    @FXML
    void OnSearchResultClicked(MouseEvent event) {

    }

    @FXML
    void OnSuppliers(MouseEvent event) {

    }

}
