@FunctionalInterface
public interface ReportGenerator<T extends Product<?>> {
    void generate(Inventory<T> inventory);
    
    default void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("-----------------------------");
    }
    
    default void printFooter() {
        System.out.println("-----------------------------");
        System.out.println("End of Report");
    }
}