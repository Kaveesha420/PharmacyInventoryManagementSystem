package Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Medicine;
import Repositry.AlertRepository;
import Repositry.AlertRepositoryImpl;

import java.util.List;

public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository = new AlertRepositoryImpl();

    @Override
    public ObservableList<Medicine> getExpiringMedicinesList() {
        List<Medicine> expiringList = alertRepository.getExpiringMedicines();
        return FXCollections.observableArrayList(expiringList);
    }
}