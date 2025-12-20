package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.MedicineService;
import Service.MedicineServiceImpl;
import Service.OrderService;
import Service.OrderServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private final MedicineService medicineService = new MedicineServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @FXML private Label lblExpireSoon;
    @FXML private Label lblTodaySales;
    @FXML private Label lblTotalInventory;
    @FXML private Label lbllowStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardData();
    }

    private void loadDashboardData() {
        double totalValue = medicineService.getTotalInventoryValue();
        lblTotalInventory.setText(String.format("%.2f", totalValue));

        int expiringCount = medicineService.getExpiringMedicineCount();
        lblExpireSoon.setText(String.valueOf(expiringCount));

        int lowStockCount = medicineService.getLowStockMedicineCount();
        lbllowStock.setText(String.valueOf(lowStockCount));

        double todaySales = orderService.getTodaySales();
        lblTodaySales.setText(String.format("%.2f", todaySales));
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
    void OnBilling(MouseEvent event) throws IOException {
        navigate(event,"/View/BillingForm.fxml","Billing Form");
    }

    private void navigate(MouseEvent event, String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }
}