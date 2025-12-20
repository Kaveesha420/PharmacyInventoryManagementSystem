package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.MedicineService;
import Service.MedicineServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private final MedicineService service = new MedicineServiceImpl();

    @FXML private Label lblExpireSoon;
    @FXML private Label lblTodaySales;
    @FXML private Label lblTotalInventory;
    @FXML private Label lbllowStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardData();
    }

    private void loadDashboardData() {
        double totalValue = service.getTotalInventoryValue();
        lblTotalInventory.setText(String.format("%.2f", totalValue));

        int expiringCount = service.getExpiringMedicineCount();
        lblExpireSoon.setText(String.valueOf(expiringCount));

        int lowStockCount = service.getLowStockMedicineCount();
        lbllowStock.setText(String.valueOf(lowStockCount));
        lblTodaySales.setText(" 0.00");
    }

    @FXML
    void OnAlerts(MouseEvent event) throws IOException {
        navigate(event, "/View/AletsForm.fxml", "Alerts");
    }

    @FXML
    void OnMedicine(MouseEvent event) throws IOException {
        navigate(event, "/View/MedicineForm.fxml", "Medicine Management");
    }

    @FXML
    void OnSuppliers(MouseEvent event) throws IOException {
        navigate(event, "/View/SupplierForm.fxml", "Supplier Management");
    }

    @FXML
    void OnDashbord(MouseEvent event) {
        loadDashboardData();
    }

    @FXML
    void OnBilling(MouseEvent event) {
        // Billing
    }
    private void navigate(MouseEvent event, String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }
}