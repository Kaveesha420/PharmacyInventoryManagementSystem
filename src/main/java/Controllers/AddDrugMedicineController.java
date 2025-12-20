package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.Medicine;
import Service.MedicineService;
import Service.MedicineServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddDrugMedicineController implements Initializable {

    private final MedicineService service = new MedicineServiceImpl();

    private String selectedMedicineId = null;

    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableView<Medicine> tblMedicine;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colBrandName;
    @FXML
    private TableColumn<?, ?> colGenaricName;
    @FXML
    private TableColumn<?, ?> colDosage;
    @FXML
    private TableColumn<?, ?> colExpireDate;
    @FXML
    private TableColumn<?, ?> colCurrentStock;
    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private JFXTextField txtBrandName;
    @FXML
    private JFXTextField txtGenaricName;
    @FXML
    private JFXTextField txtDosage;
    @FXML
    private JFXTextField txtExpireDate;
    @FXML
    private JFXTextField txtCurrentStock;
    @FXML
    private JFXTextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colGenaricName.setCellValueFactory(new PropertyValueFactory<>("genaricsName"));
        colDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colCurrentStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        tblMedicine.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedMedicineId = newValue.getId();

                txtBrandName.setText(newValue.getBrandName());
                txtGenaricName.setText(newValue.getGenaricsName());
                txtDosage.setText(newValue.getDosage());
                txtExpireDate.setText(String.valueOf(newValue.getExpireDate()));
                txtCurrentStock.setText(String.valueOf(newValue.getCurrentStock()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        });

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<Medicine> list = service.getAllMedicines();
        tblMedicine.setItems(list);
    }

    @FXML
    void OnAdd(ActionEvent event) {
        try {
            String id = service.generateNewId();

            String brand = txtBrandName.getText();
            String generic = txtGenaricName.getText();
            String dosage = txtDosage.getText();
            Date expireDate = Date.valueOf(txtExpireDate.getText());
            int stock = Integer.parseInt(txtCurrentStock.getText());
            double price = Double.parseDouble(txtUnitPrice.getText());

            Medicine medicine = new Medicine(id, brand, generic, dosage, expireDate, stock, price);
            service.addMedicine(medicine);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Drug Added! ID: " + id);

            loadTableData();
            OnClear(event);

        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Date Error", "Date format must be YYYY-MM-DD");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data Provided");
            e.printStackTrace();
        }
    }

    @FXML
    void OnUpdate(ActionEvent event) {
        if (selectedMedicineId == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a drug from the table first.");
            return;
        }

        try {
            String brand = txtBrandName.getText();
            String generic = txtGenaricName.getText();
            String dosage = txtDosage.getText();
            Date expireDate = Date.valueOf(txtExpireDate.getText());
            int stock = Integer.parseInt(txtCurrentStock.getText());
            double price = Double.parseDouble(txtUnitPrice.getText());

            Medicine medicine = new Medicine(selectedMedicineId, brand, generic, dosage, expireDate, stock, price);
            service.updateMedicine(medicine);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Drug Updated!");
            loadTableData();
            OnClear(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnDelete(ActionEvent event) {
        if (selectedMedicineId == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a drug to delete.");
            return;
        }

        service.deleteMedicine(selectedMedicineId);

        showAlert(Alert.AlertType.INFORMATION, "Deleted", "Drug Deleted Successfully!");
        loadTableData();
        OnClear(event);
    }

    @FXML
    void OnClear(ActionEvent event) {
        selectedMedicineId = null;
        txtBrandName.clear();
        txtGenaricName.clear();
        txtDosage.clear();
        txtExpireDate.clear();
        txtCurrentStock.clear();
        txtUnitPrice.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML void OnDashbord(MouseEvent event) throws IOException { navigate("/View/DashboardForm.fxml", "Dashboard"); }
    @FXML void OnMedicine(MouseEvent event) throws IOException { navigate("/View/MedicineForm.fxml", "Medicine"); }
    @FXML void OnAlerts(MouseEvent event) throws IOException { navigate("/View/AletsForm.fxml", "Alerts"); }
    @FXML void OnSuppliers(MouseEvent event) throws IOException { navigate("/View/SupplierForm.fxml", "Suppliers"); }
    @FXML void OnBilling(MouseEvent event) throws IOException {navigate("/View/BillingForm.fxml","Billing Form");}

    private void navigate(String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }
}