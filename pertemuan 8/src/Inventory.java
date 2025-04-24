import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory<T extends Product<?>> {
    private final List<T> products;
    private final String inventoryName;
    private final List<Supplier> suppliers;

    public Inventory(String inventoryName) {
        this.inventoryName = inventoryName;
        this.products = new ArrayList<>();
        this.suppliers = new ArrayList<>();
    }

    // Add product to inventory
    public void addProduct(T product) {
        products.add(product);
        System.out.println("Added product: " + product.getName());
    }

    // Add supplier to inventory
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
        System.out.println("Added supplier: " + supplier.getName());
    }

    // Search products by name or ID
    public List<T> searchProducts(String searchTerm) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                           p.getId().equalsIgnoreCase(searchTerm))
                .collect(Collectors.toList());
    }

    // Display inventory statistics
    public void displayInventoryStats() {
        System.out.println("\n=== Inventory Statistics ===");
        System.out.println("Name: " + inventoryName);
        System.out.println("Total Products: " + products.size());
        System.out.println("Total Suppliers: " + suppliers.size());
        System.out.printf("Total Value: $%.2f\n", calculateTotalValue());
        
        if (!products.isEmpty()) {
            System.out.println("\nPrice Statistics:");
            System.out.printf("Average Price: $%.2f\n", 
                products.stream().mapToDouble(Product::getPrice).average().orElse(0));
            System.out.printf("Highest Price: $%.2f\n", 
                products.stream().mapToDouble(Product::getPrice).max().orElse(0));
            System.out.printf("Lowest Price: $%.2f\n", 
                products.stream().mapToDouble(Product::getPrice).min().orElse(0));
        }
    }

    // Other existing methods
    public boolean removeProduct(String productId) {
        return products.removeIf(p -> p.getId().equals(productId));
    }

    public T getProduct(String productId) {
        return products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public List<T> getAllProducts() {
        return new ArrayList<>(products);
    }

    public double calculateTotalValue() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void displayAllProducts() {
        System.out.println("\n=== Products in " + inventoryName + " ===");
        products.forEach(Product::displayProductInfo);
    }
}