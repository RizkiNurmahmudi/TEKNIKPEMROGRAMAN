// InventoryReport.java
@FunctionalInterface
public interface InventoryReport<T extends Product<?>> {
    void generateReport(Inventory<T> inventory);

    default void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("-----------------------------");
    }
}