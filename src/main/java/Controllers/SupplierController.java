package Controllers;

import Service.SupplierService;
import Service.SupplierServiceImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.Supplier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    private final SupplierService supplierService = new SupplierServiceImpl();
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
    private TableView<Supplier> tblSupplier;

    @FXML
    void OnAddNewSupplier(ActionEvent event) {
        try {
            Stage stages = (Stage) tblSupplier.getScene().getWindow();
            stages.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AddNewSupplier.fxml"))));
            stages.setTitle("Add New Supplier");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnAlerts(MouseEvent event) throws IOException {
       navigate("/View/AletsForm.fxml" , "Alerts");
    }

    @FXML
    void OnBilling(MouseEvent event) throws IOException {
        navigate("/View/BillingForm.fxml","Billing Form");
    }

    @FXML
    void OnDashbord(MouseEvent event) throws IOException {
       navigate("/View/DashboardForm.fxml","Dashbord");
    }

    @FXML
    void OnMedicine(MouseEvent event) throws IOException {
        navigate("/View/MedicineForm.fxml","Medicine");
    }

    @FXML
    void OnSuppliers(MouseEvent event) {
        //
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadTableData();
    }
    public void loadTableData(){
        ObservableList<Supplier> suppliers = supplierService.getAllSupplier();
         tblSupplier.setItems(suppliers);
    }

    private void navigate(String fxmlPath , String title) throws IOException {
        Stage stage = (Stage) tblSupplier.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
            stage.setTitle(title);
            stage.show();
    }
}
