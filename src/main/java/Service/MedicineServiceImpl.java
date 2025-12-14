package Service;

import Repositry.MedicineRepositry;
import Repositry.MedicineRepositryImpl;

public class MedicineServiceImpl implements MedicineService{
        MedicineRepositry medicineRepositry = new MedicineRepositryImpl();
}
