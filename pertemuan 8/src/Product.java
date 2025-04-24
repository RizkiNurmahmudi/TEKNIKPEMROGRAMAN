import java.util.Comparator;

public abstract class Product<T> {
    private final String id;
    private final String name;
    private double price;
    private final T additionalInfo;
    private int stockQuantity;

    public Product(String id, String name, double price, T additionalInfo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.additionalInfo = additionalInfo;
        this.stockQuantity = 0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public T getAdditionalInfo() { return additionalInfo; }
    public int getStockQuantity() { return stockQuantity; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int quantity) { this.stockQuantity = quantity; }

    public abstract void displayProductInfo();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + String.format("%.2f", price) + 
               ", Stock: " + stockQuantity;
    }

    public static Comparator<Product<?>> getPriceComparator() {
        return Comparator.comparingDouble(Product::getPrice);
    }

    public static Comparator<Product<?>> getNameComparator() {
        return Comparator.comparing(Product::getName);
    }
}