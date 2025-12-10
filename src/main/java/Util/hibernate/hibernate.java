package Util.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernate {
        private static SessionFactory sessionFactory;
        static {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static Session getSession() {
            return sessionFactory.openSession();
        }
}
