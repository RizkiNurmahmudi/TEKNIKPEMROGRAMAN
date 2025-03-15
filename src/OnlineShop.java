import java.util.*;
import java.util.concurrent.*;

public class OnlineShop {
    private static final Map<String, Product> productMap = new ConcurrentHashMap<>();
    private static final Map<String, User> userMap = new ConcurrentHashMap<>();
    private static final Queue<Order> orderQueue = new ConcurrentLinkedQueue<>();
    private static final Map<String, ShoppingCart> shoppingCartMap = new ConcurrentHashMap<>();
    private static final ExecutorService orderProcessor = Executors.newFixedThreadPool(3);
    private static final Scanner scanner = new Scanner(System.in);

    // Method untuk menambahkan produk ke toko
    public static void addProduct(Product product) {
        productMap.put(product.id(), product);
    }

    // Method untuk mendaftarkan pengguna
    public static void registerUser(User user) {
        userMap.put(user.userId(), user);
    }

    // Method untuk login pengguna
    public static Optional<User> login(String username, String password) {
        return userMap.values().stream()
                .filter(user -> user.username().equals(username) && user.password().equals(password))
                .findFirst();
    }

    // Method untuk menambahkan produk ke keranjang belanja
    public static void addToCart(String userId, String productId) {
        Product product = productMap.get(productId);
        if (product != null) {
            shoppingCartMap.compute(userId, (key, cart) -> {
                List<Product> products = (cart == null) ? new ArrayList<>() : new ArrayList<>(cart.products());
                products.add(product);
                return new ShoppingCart(userId, List.copyOf(products)); // Immutable List
            });
            System.out.println("Product added to cart: " + product.name());
        } else {
            System.out.println("Product not found!");
        }
    }

    // Method untuk membuat pesanan dari keranjang belanja
    public static Optional<Order> createOrder(String userId, double discount) {
        ShoppingCart cart = shoppingCartMap.get(userId);
        if (cart == null || cart.products().isEmpty()) {
            return Optional.empty();
        }

        double totalPrice = cart.products().stream().mapToDouble(Product::price).sum();
        double discountedPrice = totalPrice * (1 - discount);

        Order order = new Order(UUID.randomUUID().toString(), userId, List.copyOf(cart.products()), discountedPrice, discount);
        orderQueue.add(order);
        shoppingCartMap.remove(userId); // Kosongkan keranjang setelah membuat pesanan
        return Optional.of(order);
    }

    // Method untuk memproses pesanan secara multithreading
    public static void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            orderProcessor.submit(() -> {
                System.out.println("Processing order: " + order.orderId());
                System.out.println("User ID: " + order.userId());
                System.out.println("Products: " + order.products());
                System.out.println("Total Price (before discount): " + order.totalPrice() / (1 - order.discount()));
                System.out.println("Discount: " + (order.discount() * 100) + "%");
                System.out.println("Total Price (after discount): " + order.totalPrice());
                System.out.println("Order processed successfully by thread: " + Thread.currentThread().getName() + "\n");
            });
        }
    }

    // Method untuk menampilkan menu utama
    public static void displayMainMenu() {
        System.out.println("=== Online Shop ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    // Method untuk menampilkan menu pengguna setelah login
    public static void displayUserMenu(String userId) {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> {
                    System.out.print("Enter product ID to add to cart: ");
                    String productId = scanner.nextLine();
                    addToCart(userId, productId);
                }
                case 3 -> viewCart(userId);
                case 4 -> {
                    System.out.print("Enter discount percentage (e.g., 10 for 10%): ");
                    double discount = scanner.nextDouble() / 100;
                    Optional<Order> orderOptional = createOrder(userId, discount);
                    orderOptional.ifPresentOrElse(
                            order -> System.out.println("Order created: " + order.orderId()),
                            () -> System.out.println("No products found for the order.")
                    );
                    processOrders();
                }
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    // Method untuk menampilkan semua produk
    public static void viewProducts() {
        System.out.println("\n=== Available Products ===");
        productMap.values().forEach(product ->
                System.out.println("ID: " + product.id() + ", Name: " + product.name() +
                        ", Price: $" + product.price() + ", Category: " + product.category())
        );
    }

    // Method untuk menampilkan keranjang belanja
    public static void viewCart(String userId) {
        ShoppingCart cart = shoppingCartMap.get(userId);
        if (cart == null || cart.products().isEmpty()) {
            System.out.println("Your cart is empty!");
        } else {
            System.out.println("\n=== Your Cart ===");
            cart.products().forEach(product ->
                    System.out.println("Name: " + product.name() + ", Price: $" + product.price())
            );
        }
    }

    public static void main(String[] args) {
        // Menambahkan beberapa produk ke toko
        addProduct(new Product("1", "Laptop", 1200.00, "Electronics"));
        addProduct(new Product("2", "Smartphone", 800.00, "Electronics"));
        addProduct(new Product("3", "Headphones", 150.00, "Electronics"));
        addProduct(new Product("4", "T-Shirt", 25.00, "Clothing"));
        addProduct(new Product("5", "Jeans", 50.00, "Clothing"));

        // Mendaftarkan pengguna
        registerUser(new User("1", "user1", "password1"));
        registerUser(new User("2", "user2", "password2"));

        // Menu utama
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    Optional<User> loggedInUser = login(username, password);
                    if (loggedInUser.isPresent()) {
                        System.out.println("Login successful!");
                        displayUserMenu(loggedInUser.get().userId());
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                }
                case 2 -> {
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    String newUserId = UUID.randomUUID().toString();
                    registerUser(new User(newUserId, newUsername, newPassword));
                    System.out.println("Registration successful!");
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    orderProcessor.shutdown();
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}