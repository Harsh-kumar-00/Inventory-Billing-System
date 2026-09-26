package service;
import model.BillItem;
import java.util.List;
import model.Bill;

public class BillingService {
    public double calculateSubtotal(List<BillItem> items) {
        double subtotal = 0;
        for(BillItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    public double calculateDiscount(double subtotal) {
        if(subtotal < 1000) {
            return 0;
        }
        else if(subtotal < 5000) {
            return subtotal * 0.05;
        }
        else {
            return subtotal * 0.10;
        }
    }

    public double calculateTax(double subtotal, double discount) {
        double taxableAmount = subtotal - discount;
        return taxableAmount * 0.18;
    }

    public double calculateGrandTotal(double subtotal, double discount, double tax) {
        return subtotal - discount + tax;
    }

    public Bill createBill(List<BillItem> items) {
        double subtotal = calculateSubtotal(items);

        double discount = calculateDiscount(subtotal);

        double tax = calculateTax(subtotal, discount);

        double grandTotal = calculateGrandTotal(subtotal, discount, tax);

        return new Bill(subtotal, discount, tax, grandTotal);
    }
}
