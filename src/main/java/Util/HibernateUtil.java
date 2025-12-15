package Util;

import model.entity.Medicine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {

            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Medicine.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}