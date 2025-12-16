package Repositry;

import model.entity.Medicine;
import java.util.List;

public interface AlertRepository {
    List<Medicine> getExpiringMedicines();
}