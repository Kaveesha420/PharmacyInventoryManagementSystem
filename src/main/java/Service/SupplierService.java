package Service;

import javafx.collections.ObservableList;
import model.entity.Supplier;

public interface SupplierService {
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String id);
    ObservableList<Supplier> getAllSupplier();
    String genarateNewId();
}
