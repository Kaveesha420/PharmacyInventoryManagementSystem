package Repositry;

import model.entity.Supplier;

import java.util.List;

public interface SupplierRepository {
    void addSuplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String id);
    List<Supplier> getAllSupplier();
}
