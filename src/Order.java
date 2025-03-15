import java.util.List;

public record Order(String orderId, String userId, List<Product> products, double totalPrice, double discount) {}