package Repositry;

import Util.HibernateUtil;
import model.entity.Medicine;
import org.hibernate.Session;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AlertRepositoryImpl implements AlertRepository {

    @Override
    public List<Medicine> getExpiringMedicines() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDate futureDate = LocalDate.now().plusDays(30);
            Date targetDate = Date.valueOf(futureDate);
            String hql = "FROM Medicine WHERE expireDate <= :target";

            return session.createQuery(hql, Medicine.class)
                    .setParameter("target", targetDate)
                    .list();
        }
    }
}