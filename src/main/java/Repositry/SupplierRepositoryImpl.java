package Repositry;

import Util.HibernateUtil;
import model.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository{

    @Override
    public void addSuplier(Supplier supplier) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(supplier);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(supplier);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSupplier(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Supplier supplier = session.find(Supplier.class , id);
            if (supplier != null){
                session.remove(supplier);
            }
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return List.of();
    }
}
