import java.util.ArrayList;
import java.util.List;

public class ElectronicProduct extends Product<String> implements Discountable {
    private int warrantyPeriod; // in months
    private final String energyRating;
    private final List<Review> reviews;

    public ElectronicProduct(String id, String name, double price, String brand, 
                           int warrantyPeriod, String energyRating) {
        super(id, name, price, brand);
        this.warrantyPeriod = warrantyPeriod;
        this.energyRating = energyRating;
        this.reviews = new ArrayList<>();
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Electronic Product - " + super.toString() + 
                         ", Brand: " + getAdditionalInfo() + 
                         ", Warranty: " + warrantyPeriod + " months" +
                         ", Energy Rating: " + energyRating);
        displayReviews();
    }

    @Override
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= getMaxDiscountPercentage()) {
            double newPrice = getPrice() * (1 - percentage/100);
            setPrice(newPrice);
            System.out.println("Applied " + percentage + "% discount to " + getName());
        } else {
            System.out.println("Invalid discount percentage for electronic product. Max allowed: " + getMaxDiscountPercentage() + "%");
        }
    }

    @Override
    public double getMaxDiscountPercentage() {
        return warrantyPeriod > 12 ? 50 : 30;
    }

    public void extendWarranty(int additionalMonths) {
        this.warrantyPeriod += additionalMonths;
        System.out.println("Warranty extended for " + additionalMonths + " months");
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    private void displayReviews() {
        if (!reviews.isEmpty()) {
            System.out.println("  Reviews:");
            reviews.forEach(r -> System.out.println("    " + r));
        }
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }
}