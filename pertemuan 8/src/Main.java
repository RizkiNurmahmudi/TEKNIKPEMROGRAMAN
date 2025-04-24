import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Membuat inventaris elektronik
        Inventory<ElectronicProduct> electronicInventory = new Inventory<>("Toko Elektronik");
        electronicInventory.addSupplier(new Supplier("S1", "Tech Distributors Inc.", "contact@techdist.com"));
        
        // Menambahkan produk elektronik
        electronicInventory.addProduct(new ElectronicProduct("E001", "Smartphone", 799.99, "Samsung", 12, "A+"));
        electronicInventory.addProduct(new ElectronicProduct("E002", "Laptop", 1299.99, "Dell", 24, "A"));
        electronicInventory.addProduct(new ElectronicProduct("E003", "Headphones", 149.99, "Sony", 6, "B"));
        
        // Membuat inventaris makanan
        Inventory<FoodProduct> foodInventory = new Inventory<>("Toko Kelontong");
        foodInventory.addSupplier(new Supplier("F1", "Organic Foods Ltd.", "sales@organicfoods.com"));
        
        // Menambahkan produk makanan
        foodInventory.addProduct(new FoodProduct("F001", "Susu Organik", 3.99, LocalDate.now().plusDays(7), true, "Dinginkan", "F1"));
        foodInventory.addProduct(new FoodProduct("F002", "Roti Tawar", 2.49, LocalDate.now().minusDays(1), false, "Suhu ruangan", "F1"));
        FoodProduct eggs = new FoodProduct("F003", "Telur Free-range", 4.99, LocalDate.now().plusDays(14), true, "Dinginkan", "F1");
        foodInventory.addProduct(eggs);
        
        // Membuat inventaris pakaian
        Inventory<ClothingProduct> clothingInventory = new Inventory<>("Toko Pakaian");
        
        // Menambahkan produk pakaian
        clothingInventory.addProduct(new ClothingProduct("C001", "Kaos", 19.99, "Katun", "M", "Biru"));
        clothingInventory.addProduct(new ClothingProduct("C002", "Celana Jeans", 49.99, "Denim", "32", "Hitam"));
        
        // Menampilkan semua produk
        electronicInventory.displayAllProducts();
        foodInventory.displayAllProducts();
        clothingInventory.displayAllProducts();
        
        // Menerapkan diskon
        InventoryService.applyBulkDiscount(electronicInventory, 15);
        InventoryService.applyBulkDiscount(foodInventory, 20);
        
        // Menghapus produk kadaluarsa
        InventoryService.removeExpiredProducts(foodInventory);
        
        // Menerapkan diskon untuk produk yang hampir kadaluarsa
        InventoryService.applyDiscountToExpiringProducts(foodInventory, 10);
        
        // Menampilkan statistik
        electronicInventory.displayInventoryStats();
        foodInventory.displayInventoryStats();
        clothingInventory.displayInventoryStats();
        
        // Membuat laporan
        System.out.println("\nMembuat Laporan Kustom:");
        
        // Ringkasan produk elektronik
        InventoryService.generateReport(electronicInventory, inventory -> {
            System.out.println("\n=== Ringkasan Produk Elektronik ===");
            System.out.println("-----------------------------");
            System.out.println("Total Item: " + inventory.getAllProducts().size());
            System.out.println("Item Termahal: " + 
                inventory.getAllProducts().stream()
                    .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                    .map(Product::getName)
                    .orElse("Tidak Ada"));
        });

        // Analisis diskon produk makanan
        InventoryService.generateReport(foodInventory, inventory -> {
            System.out.println("\n=== Analisis Diskon Produk Makanan ===");
            System.out.println("-----------------------------");
            inventory.getAllProducts().forEach(p -> {
                System.out.println(p.getName() + ": Rp" + p.getPrice());
                if (p instanceof Expirable expirable) {
                    System.out.println("  Kadaluarsa: " + (expirable.isExpired() ? "Ya" : "Tidak"));
                    System.out.println("  Hari hingga kadaluarsa: " + expirable.daysUntilExpiry());
                }
            });
        });
        
        // Jumlah produk per kategori
        System.out.println("\n=== Jumlah Produk per Kategori ===");
        Map<String, Long> electronicCounts = InventoryService.getProductCountByCategory(electronicInventory);
        System.out.println("Produk Elektronik: " + electronicCounts);
        
        // Fungsi pencarian
        System.out.println("\nHasil Pencarian untuk 'smart':");
        electronicInventory.searchProducts("smart").forEach(p -> 
            System.out.println("Ditemukan: " + p.getName()));
    }
}