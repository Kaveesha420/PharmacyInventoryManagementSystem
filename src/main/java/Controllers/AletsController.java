package Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.Medicine;
import Service.AlertService;
import Service.AlertServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AletsController implements Initializable {


    private final AlertService alertService = new AlertServiceImpl();

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
    private TableView<Medicine> tblMedicine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colGenaricName.setCellValueFactory(new PropertyValueFactory<>("genaricsName"));
        colDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colCurrentStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        tblMedicine.setRowFactory(tv -> new TableRow<Medicine>() {
            @Override
            protected void updateItem(Medicine item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {
                    LocalDate today = LocalDate.now();
                    LocalDate expireDate = item.getExpireDate().toLocalDate();

                    String baseStyle = "-fx-font-weight: bold; -fx-text-background-color: black;";

                    if (expireDate.isBefore(today)) {
                        setStyle(baseStyle + "-fx-background-color: #ffcccc;");
                    } else if (expireDate.isBefore(today.plusDays(30))) {
                        setStyle(baseStyle + "-fx-background-color: #ffffcc;");
                    } else {
                        setStyle(baseStyle + "-fx-background-color: white;");
                    }
                }
            }
        });

        loadAlerts();
    }

    private void loadAlerts() {
        ObservableList<Medicine> expiringList = alertService.getExpiringMedicinesList();
        tblMedicine.setItems(expiringList);
    }

    @FXML
    void OnAlerts(MouseEvent event) {
        loadAlerts();
    }

    @FXML
    void OnBilling(MouseEvent event) {
        // Billing
    }

    @FXML
    void OnDashbord(MouseEvent event) throws IOException {
        navigate(event, "/View/DashboardForm.fxml", "Dashboard");
    }

    @FXML
    void OnMedicine(MouseEvent event) throws IOException {
        navigate(event, "/View/MedicineForm.fxml", "Medicine Management");
    }

    @FXML
    void OnSuppliers(MouseEvent event) throws IOException {
        navigate(event, "/View/SupplierForm.fxml", "Supplier Management");
    }

    private void navigate(MouseEvent event, String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }
}