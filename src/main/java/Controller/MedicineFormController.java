package Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import model.entity.Medicine;
import Service.MedicineService;
import Service.MedicineServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicineFormController implements Initializable {

    private final MedicineService service = new MedicineServiceImpl();
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
    private TableColumn<?, ?> colId; // ID Column eka FXML eke thibuna nisa add kala

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Medicine> tblMedicine;

    @FXML
    private JFXTextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colGenaricName.setCellValueFactory(new PropertyValueFactory<>("genaricsName"));
        colDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colCurrentStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        loadTableData();
    }

    private void loadTableData() {
        ObservableList<Medicine> medicineList = service.getAllMedicines();
        tblMedicine.setItems(medicineList);
    }

    @FXML
    void OnAddNewDrug(ActionEvent event) {
        try {
            Stage stage = (Stage) tblMedicine.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AddDrugMedicine.fxml"))));
            stage.setTitle("Add New Drug");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnSearchDrug(KeyEvent event) {
       //n
    }

    @FXML
    void OnDashbord(MouseEvent event) throws IOException {
        navigate("/View/DashboardForm.fxml", "Dashboard");
    }

    @FXML
    void OnAlerts(MouseEvent event) throws IOException {
        navigate("/View/AletsForm.fxml", "Alerts");
    }

    @FXML
    void OnSuppliers(MouseEvent event) throws IOException {
        navigate("/View/SupplierForm.fxml", "Suppliers");
    }

    @FXML
    void OnMedicine(MouseEvent event) {
        //
    }

    @FXML
    void OnBilling(MouseEvent event) {
        //a
    }

    private void navigate(String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) tblMedicine.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }
}
