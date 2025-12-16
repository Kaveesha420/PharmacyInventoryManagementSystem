package Service;

import Repositry.MedicineRepositry;
import Repositry.MedicineRepositryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entity.Medicine;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MedicineServiceImpl implements MedicineService{

       private final MedicineRepositry medicineRepositry = new MedicineRepositryImpl();

    @Override
    public void addMedicine(Medicine medicine) {
        medicineRepositry.addMedicine(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        medicineRepositry.updateMedicine(medicine);
    }

    @Override
    public void deleteMedicine(String id) {
        medicineRepositry.deleteMedicine(id);
    }

    @Override
    public Medicine searchMedicine(String id) {
        return medicineRepositry.searchMedicine(id);
    }

    @Override
    public ObservableList<Medicine> getAllMedicines() {
        List<Medicine> list = medicineRepositry.getAllMedicine();
        return FXCollections.observableArrayList(list);
    }

    @Override
    public ObservableList<Medicine> getExpiringMedicines() {
        List<Medicine> allMedicines = medicineRepositry.getAllMedicine();

        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusDays(30);

        List<Medicine> expiringList = allMedicines.stream()
                .filter(medicine -> {

                    if (medicine.getExpireDate() == null) return false;
                    LocalDate expireDate = medicine.getExpireDate().toLocalDate();

                    return expireDate.isBefore(nextMonth);
                })
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(expiringList);
    }
    @Override
    public String generateNewId() {
        try {
            List<Medicine> allMedicines = medicineRepositry.getAllMedicine();

            if (allMedicines.isEmpty()) {
                return "M1";
            }

            // Java Streams පාවිච්චි කරලා අංකය විතරක් අරන් Max එක හොයනවා
            int maxId = allMedicines.stream()
                    .map(m -> Integer.parseInt(m.getId().replace("M", "")))
                    .max(Integer::compareTo)
                    .orElse(0);

            return "M" + (maxId + 1);

        } catch (Exception e) {
            e.printStackTrace();
            return "M1";
        }
    }
    @Override
    public ObservableList<Medicine> searchMedicines(String text) {
        List<Medicine> list = medicineRepositry.searchByNameOrBrand(text);
        return FXCollections.observableArrayList(list);
    }
}
