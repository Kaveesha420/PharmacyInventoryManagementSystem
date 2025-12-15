package Service;

import javafx.collections.ObservableList;
import model.entity.Medicine;

public interface MedicineService {

        void addMedicine(Medicine medicine);
        void updateMedicine(Medicine medicine);
        void deleteMedicine(String id);
        Medicine searchMedicine(String id);
        ObservableList<Medicine> getAllMedicines();
        ObservableList<Medicine> getExpiringMedicines();
        String generateNewId();
}
