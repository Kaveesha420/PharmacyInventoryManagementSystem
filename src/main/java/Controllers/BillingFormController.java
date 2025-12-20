package Controllers;

import Service.OrderService;
import Service.OrderServiceImpl;
import Service.MedicineService;
import Service.MedicineServiceImpl;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.dto.CartItem;
import model.entity.Medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BillingFormController implements Initializable {

    private final MedicineService medicineService = new MedicineServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ObservableList<CartItem> cartItemList = FXCollections.observableArrayList();

    @FXML private JFXTextField txtSearch;
    @FXML private JFXListView<String> listSearchResults;

    @FXML private TableView<CartItem> tblCart;
    @FXML private TableColumn<CartItem, String> colMedicineId;
    @FXML private TableColumn<CartItem, String> colBrand;
    @FXML private TableColumn<CartItem, Double> colUnitPrice;
    @FXML private TableColumn<CartItem, Integer> colQty;
    @FXML private TableColumn<CartItem, Double> colTotal;

    @FXML private Label lblGrandTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        colQty.setCellValueFactory(cellData -> cellData.getValue().buyQuantityProperty().asObject());

        colQty.setCellFactory(column -> new javafx.scene.control.TableCell<CartItem, Integer>() {

            private final JFXTextField textField = new JFXTextField();

            {
                textField.setAlignment(javafx.geometry.Pos.CENTER);

                textField.setOnAction(event -> {
                    commitEdit(parseQty(textField.getText()));
                    event.consume();
                });

                textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal) {
                        commitEdit(parseQty(textField.getText()));
                    }
                });
            }

            private Integer parseQty(String text) {
                try {
                    return text.isEmpty() ? 0 : Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    return getItem();
                }
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isEditing()) {
                        textField.setText(item.toString());
                        setText(null);
                        setGraphic(textField);
                    } else {
                        setText(item.toString());
                        setGraphic(null);
                    }
                }
            }

            @Override
            public void startEdit() {
                super.startEdit();
                if (!isEmpty()) {
                    textField.setText(getItem().toString());
                    setGraphic(textField);
                    setText(null);
                    textField.selectAll();
                    textField.requestFocus();
                }
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText(getItem().toString());
                setGraphic(null);
            }
        });

        tblCart.setEditable(true);
        tblCart.setItems(cartItemList);

        cartItemList.addListener((javafx.collections.ListChangeListener.Change<? extends CartItem> c) -> {
            calculateGrandTotal();
        });
    }

    @FXML
    void OnSearchKeyReleased(KeyEvent event) {
        String text = txtSearch.getText();
        if (text == null || text.isEmpty()) {
            listSearchResults.getItems().clear();
            return;
        }
        ObservableList<Medicine> searchResults = medicineService.searchMedicines(text);
        listSearchResults.getItems().setAll(
                searchResults.stream()
                        .map(m -> m.getId() + " - " + m.getBrandName() + " (" + m.getCurrentStock() + " left)")
                        .collect(Collectors.toList())
        );
    }

    @FXML
    void OnSearchResultClicked(MouseEvent event) {
        String selectedString = listSearchResults.getSelectionModel().getSelectedItem();
        if (selectedString == null) return;

        String medicineId = selectedString.split(" - ")[0];
        Medicine selectedMedicine = medicineService.searchMedicine(medicineId);

        if (selectedMedicine != null) {
            for (CartItem item : cartItemList) {
                if (item.getMedicineId().equals(medicineId)) {
                    showAlert(Alert.AlertType.WARNING, "Already Added", "This item is already in the cart.");
                    return;
                }
            }
            cartItemList.add(new CartItem(selectedMedicine, 1));
        }
    }

    @FXML
    void OnCartQtyEditCommit(TableColumn.CellEditEvent<CartItem, Integer> event) {
        CartItem item = event.getRowValue();
        int newQty = event.getNewValue();

        if (newQty > 0 && newQty <= item.getMedicineEntity().getCurrentStock()) {
            item.setBuyQuantity(newQty);
            calculateGrandTotal();
            tblCart.refresh();
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Quantity", "Quantity must be greater than 0 and less than stock.");
            tblCart.refresh();
        }
    }

    @FXML
    void OnRemoveFromCart(ActionEvent event) {
        CartItem selected = tblCart.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cartItemList.remove(selected);
        }
    }

    private void calculateGrandTotal() {
        double grandTotal = 0;
        for (CartItem item : cartItemList) {
            grandTotal += item.getTotal();
        }
        lblGrandTotal.setText(String.format("%.2f", grandTotal));
    }

    @FXML
    void OnCheckout(ActionEvent event) {
        if (cartItemList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Cart", "Please add items to cart first.");
            return;
        }
        double grandTotal = Double.parseDouble(lblGrandTotal.getText());
        boolean success = orderService.placeOrder(cartItemList, grandTotal);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Bill Placed Successfully!");
            cartItemList.clear();
            lblGrandTotal.setText("0.00");
            txtSearch.clear();
            listSearchResults.getItems().clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed", "Could not place order.");
        }
    }

    @FXML void OnDashbord(MouseEvent event) throws IOException { navigate(event, "/View/DashboardForm.fxml"); }
    @FXML void OnMedicine(MouseEvent event) throws IOException { navigate(event, "/View/MedicineForm.fxml"); }
    @FXML void OnSuppliers(MouseEvent event) throws IOException { navigate(event, "/View/SupplierForm.fxml"); }
    @FXML void OnAlerts(MouseEvent event) throws IOException { navigate(event, "/View/AletsForm.fxml"); }

    @FXML
    void OnBilling(MouseEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/BillingForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigate(MouseEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}