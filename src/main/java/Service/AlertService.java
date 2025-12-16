package Service;

import javafx.collections.ObservableList;
import model.entity.Medicine;

public interface AlertService {
    ObservableList<Medicine> getExpiringMedicinesList();
}