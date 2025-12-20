package Service;

import Util.HibernateUtil;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.entity.Medicine;
import model.entity.Order;
import model.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class OrderServiceImpl implements OrderService{

    public boolean placeOrder(ObservableList<CartItem> cartItems, double grandTotal) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString().substring(0, 8)); // Random Order ID
            order.setGrandTotal(grandTotal);

            List<OrderDetail> detailsList = new ArrayList<>();

            for (CartItem cartItem : cartItems) {
                Medicine medicine = cartItem.getMedicineEntity();
                int buyingQty = cartItem.getBuyQuantity();

                if (medicine.getCurrentStock() < buyingQty) {
                    throw new RuntimeException("Not enough stock for: " + medicine.getBrandName());
                }

                medicine.setCurrentStock(medicine.getCurrentStock() - buyingQty);
                session.merge(medicine);

                OrderDetail detail = new OrderDetail();
                detail.setOrder(order);
                detail.setMedicine(medicine);
                detail.setQuantity(buyingQty);
                detail.setSubTotal(cartItem.getTotal());

                detailsList.add(detail);
            }
            order.setOrderDetails(detailsList);

            session.persist(order);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public double getTodaySales() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDate localDate = LocalDate.now();
            Date today = Date.valueOf(localDate);

            String hql = "SELECT SUM(o.grandTotal) FROM Order o WHERE o.orderDate = :today";

            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("today", today);

            Double sum = query.uniqueResult();

            return sum != null ? sum : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}