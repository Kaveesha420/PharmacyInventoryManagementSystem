package Repositry;

import Util.HibernateUtil;
import model.entity.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MedicineRepositryImpl implements MedicineRepositry {

    @Override
    public void addMedicine(Medicine medicine) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(medicine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(medicine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicine(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Medicine medicine = session.find(Medicine.class, id);
            if (medicine != null) {
                session.remove(medicine);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Medicine searchMedicine(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Medicine.class, id);
        }
    }
    @Override
    public List<Medicine> getAllMedicine() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Medicine", Medicine.class).list();
        }
    }
}