import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {
    public static <T extends Product<?>> void applyBulkDiscount(Inventory<T> inventory, double percentage) {
        System.out.println("\nApplying bulk discount of " + percentage + "% to inventory");
        inventory.getAllProducts().forEach(product -> {
            if (product instanceof Discountable discountableProduct) {
                discountableProduct.applyDiscount(percentage);
            }
        });
    }

    public static <T extends Product<?> & Expirable> void removeExpiredProducts(Inventory<T> inventory) {
        List<T> expiredProducts = inventory.getAllProducts().stream()
                .filter(Expirable::isExpired)
                .collect(Collectors.toList());

        if (expiredProducts.isEmpty()) {
            System.out.println("\nNo expired products found");
            return;
        }

        System.out.println("\nRemoving " + expiredProducts.size() + " expired products:");
        expiredProducts.forEach(product -> {
            inventory.removeProduct(product.getId());
            System.out.println("Removed expired product: " + product.getName() + 
                             ", Expired on: " + product.getAdditionalInfo());
        });
    }

    public static <T extends Product<?> & Expirable> void applyDiscountToExpiringProducts(Inventory<T> inventory, int daysThreshold) {
        System.out.println("\nApplying discounts to products expiring within " + daysThreshold + " days:");
        inventory.getAllProducts().stream()
                .filter(p -> !p.isExpired() && p.daysUntilExpiry() <= daysThreshold)
                .forEach(p -> {
                    double discount = Math.min(50, 70 - (p.daysUntilExpiry() * 0.5));
                    ((Discountable)p).applyDiscount(discount);
                });
    }

    public static <T extends Product<?>> void generateReport(Inventory<T> inventory, ReportGenerator<T> reportGenerator) {
        reportGenerator.generate(inventory);
    }

    public static <T extends Product<?>> Map<String, Long> getProductCountByCategory(Inventory<T> inventory) {
        return inventory.getAllProducts().stream()
                .collect(Collectors.groupingBy(
                    p -> p.getClass().getSimpleName(),
                    Collectors.counting()
                ));
    }
}