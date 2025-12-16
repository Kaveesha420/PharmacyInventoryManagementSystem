package Repositry;

import model.entity.Medicine;

import java.util.List;

public interface MedicineRepositry {
    void addMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    void deleteMedicine(String id);
    Medicine searchMedicine(String id);
    List<Medicine> getAllMedicine();
    List<Medicine> searchByNameOrBrand(String text);
}
