public class ClothingProduct extends Product<String> implements Discountable {
    private final String material;
    private String size;
    private final String color;
    
    public ClothingProduct(String id, String name, double price, 
                         String material, String size, String color) {
        super(id, name, price, material);
        this.material = material;
        this.size = size;
        this.color = color;
    }
    
    @Override
    public void displayProductInfo() {
        System.out.println("Clothing Product - " + super.toString() + 
                         ", Size: " + size + 
                         ", Color: " + color);
    }
    
    @Override
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= getMaxDiscountPercentage()) {
            double newPrice = getPrice() * (1 - percentage/100);
            setPrice(newPrice);
            System.out.println("Applied " + percentage + "% discount to " + getName());
        } else {
            System.out.println("Invalid discount percentage for clothing. Max allowed: " + getMaxDiscountPercentage() + "%");
        }
    }
    
    @Override
    public double getMaxDiscountPercentage() {
        return material.equalsIgnoreCase("cotton") ? 40 : 30;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
}