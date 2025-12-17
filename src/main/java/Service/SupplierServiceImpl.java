package Service;

import Repositry.SupplierRepository;
import Repositry.SupplierRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Supplier;

import java.util.List;

public class SupplierServiceImpl implements SupplierService{
    private final SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public void addSupplier(Supplier supplier){
        supplierRepository.addSuplier(supplier);
    }
    @Override
    public void updateSupplier(Supplier supplier){
        supplierRepository.updateSupplier(supplier);
    }
    @Override
    public void deleteSupplier(String id){
        supplierRepository.deleteSupplier(id);
    }
    @Override
    public ObservableList<Supplier> getAllSupplier(){
        List<Supplier> list = supplierRepository.getAllSupplier();
        return FXCollections.observableArrayList(list);
    }

}
