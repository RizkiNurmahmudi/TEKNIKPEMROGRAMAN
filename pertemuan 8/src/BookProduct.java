public class BookProduct extends Product<String> implements Discountable {
    private final String author;
    private final String isbn;
    private final int pageCount;
    
    public BookProduct(String id, String name, double price, 
                      String author, String isbn, int pageCount) {
        super(id, name, price, author);
        this.author = author;
        this.isbn = isbn;
        this.pageCount = pageCount;
    }
    
    @Override
    public void displayProductInfo() {
        System.out.println("Book Product - " + super.toString() + 
                         ", Author: " + author + 
                         ", ISBN: " + isbn + 
                         ", Pages: " + pageCount);
    }
    
    @Override
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= getMaxDiscountPercentage()) {
            double newPrice = getPrice() * (1 - percentage/100);
            setPrice(newPrice);
            System.out.println("Applied " + percentage + "% discount to " + getName());
        } else {
            System.out.println("Invalid discount percentage for book. Max allowed: " + getMaxDiscountPercentage() + "%");
        }
    }
    
    @Override
    public double getMaxDiscountPercentage() {
        return 25;
    }
}