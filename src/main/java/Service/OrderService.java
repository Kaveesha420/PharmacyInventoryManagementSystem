package Service;
import javafx.collections.ObservableList;
import model.dto.CartItem;

public interface OrderService {
        boolean placeOrder(ObservableList<CartItem> cartItems, double grandTotal);
        double getTodaySales();
}

