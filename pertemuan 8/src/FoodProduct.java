import java.time.LocalDate;

public class FoodProduct extends Product<LocalDate> implements Expirable, Discountable {
    private final boolean isOrganic;
    private final String storageInstructions;
    private String supplier;

    public FoodProduct(String id, String name, double price, LocalDate expiryDate, 
                      boolean isOrganic, String storageInstructions, String supplier) {
        super(id, name, price, expiryDate);
        this.isOrganic = isOrganic;
        this.storageInstructions = storageInstructions;
        this.supplier = supplier;
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Food Product - " + super.toString() + 
                         ", Expiry Date: " + getAdditionalInfo() + 
                         ", Organic: " + (isOrganic ? "Yes" : "No") +
                         ", Storage: " + storageInstructions +
                         ", Supplier: " + supplier);
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(getAdditionalInfo());
    }

    @Override
    public LocalDate getExpiryDate() {
        return getAdditionalInfo();
    }

    @Override
    public void applyDiscount(double percentage) {
        if (isExpired()) {
            System.out.println("Cannot apply discount to expired product: " + getName());
            return;
        }

        if (percentage > 0 && percentage <= getMaxDiscountPercentage()) {
            double newPrice = getPrice() * (1 - percentage/100);
            setPrice(newPrice);
            System.out.println("Applied " + percentage + "% discount to " + getName());
        } else {
            System.out.println("Invalid discount percentage for food product. Max allowed: " + getMaxDiscountPercentage() + "%");
        }
    }

    @Override
    public double getMaxDiscountPercentage() {
        long daysUntilExpiry = daysUntilExpiry();
        if (daysUntilExpiry <= 0) return 0;
        return Math.min(70, 30 + (daysUntilExpiry * 0.5));
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}