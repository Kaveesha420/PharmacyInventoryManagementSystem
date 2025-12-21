package Controllers;

import Service.SupplierService;
import Service.SupplierServiceImpl;
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
import model.entity.Supplier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewSupplierController implements Initializable {

    private final SupplierService supplierService = new SupplierServiceImpl();
    private String selectSupplierId = null;

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
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtContactPerson;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtSupId;

    @FXML
    void OnAdd(ActionEvent event) {
        try {
            String id = supplierService.genarateNewId();
            String CompanyName = txtCompanyName.getText();
            String ContactPerson = txtContactPerson.getText();
            String Phone = txtPhone.getText();
            String Email = txtEmail.getText();
            String Address = txtAddress.getText();

            Supplier supplier = new Supplier(id, CompanyName, ContactPerson, Phone, Email, Address);
            supplierService.addSupplier(supplier);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier Added! ID: " + id);

            loadTable();
            OnClear(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    void OnUpdate(ActionEvent event) {
        if(selectSupplierId == null){
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a supplier from the table first.");
            return;
        }
        try {
            String CompanyName = txtCompanyName.getText();
            String ContactPerson = txtContactPerson.getText();
            String Phone = txtPhone.getText();
            String Email = txtEmail.getText();
            String Address = txtAddress.getText();

            Supplier supplier = new Supplier(selectSupplierId, CompanyName, ContactPerson, Phone, Email, Address);
            supplierService.updateSupplier(supplier);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier Updated!");

            loadTable();
            OnClear(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OnDelete(ActionEvent event) {
        if (selectSupplierId == null ){
            showAlert(Alert.AlertType.WARNING,"Warning","Please select a Supplier to delete");
            return;
        }
        supplierService.deleteSupplier(selectSupplierId);
        showAlert(Alert.AlertType.INFORMATION, "Deleted", "Supplier Deleted Successfully!");
        loadTable();
        OnClear(event);
    }
    @FXML
    void OnClear(ActionEvent event) {
        txtSupId.clear();
        txtCompanyName.clear();
        txtContactPerson.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtAddress.clear();
    }

    @FXML
    void OnAlerts(MouseEvent event) throws IOException {
        navigate("/View/AletsForm.fxml","Alerts");
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
    void OnSuppliers(MouseEvent event) throws IOException {
        navigate("/View/SupplierForm.fxml","Supplier");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue )->{
           if (newValue != null){

               selectSupplierId = newValue.getId();
               txtCompanyName.setText(newValue.getCompanyName());
               txtContactPerson.setText(newValue.getContactPerson());
               txtPhone.setText(newValue.getPhone());
               txtEmail.setText(newValue.getEmail());
               txtAddress.setText(newValue.getAddress());
           }
        });

        loadTable();
    }
    public void loadTable(){
        ObservableList<Supplier> suppliers = supplierService.getAllSupplier();
        tblSupplier.setItems(suppliers);
    }
    private void navigate(String fxmlPath , String title) throws IOException {
        Stage stage = (Stage) tblSupplier.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.setTitle(title);
        stage.show();
    }

    public void OnLogout(ActionEvent actionEvent) {

        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"))));
            loginStage.setTitle("Login_Form");
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
