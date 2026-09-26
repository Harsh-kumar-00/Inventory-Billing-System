package model;

public class Bill {

    private double subtotal;
    private double discount;
    private double tax;
    private double grandTotal;

    public Bill(double subtotal, double discount, double tax, double grandTotal) {
        this.subtotal = subtotal;
        this.discount = discount;
        this.tax = tax;
        this.grandTotal = grandTotal;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getGrandTotal() {
        return grandTotal;
    }
}