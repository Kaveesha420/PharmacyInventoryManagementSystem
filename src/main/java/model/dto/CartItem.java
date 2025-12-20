package model.dto;

import javafx.beans.property.*;
import model.entity.Medicine;

public class CartItem {
    private final Medicine medicine;

    private final IntegerProperty buyQuantity;
    private final DoubleProperty total;

    public CartItem(Medicine medicine, int buyQuantity) {
        this.medicine = medicine;
        this.buyQuantity = new SimpleIntegerProperty(buyQuantity);
        this.total = new SimpleDoubleProperty(calculateTotal());

        this.buyQuantity.addListener((obs, oldVal, newVal) -> {
            setTotal(calculateTotal());
        });
    }

    private double calculateTotal() {
        return medicine.getUnitPrice() * getBuyQuantity();
    }

    public String getMedicineId() { return medicine.getId(); }
    public String getBrandName() { return medicine.getBrandName(); }
    public double getUnitPrice() { return medicine.getUnitPrice(); }

    public IntegerProperty buyQuantityProperty() { return buyQuantity; }
    public int getBuyQuantity() { return buyQuantity.get(); }
    public void setBuyQuantity(int qty) { this.buyQuantity.set(qty); }

    public DoubleProperty totalProperty() { return total; }
    public double getTotal() { return total.get(); }
    public void setTotal(double total) { this.total.set(total); }

    public Medicine getMedicineEntity() { return medicine; }
}