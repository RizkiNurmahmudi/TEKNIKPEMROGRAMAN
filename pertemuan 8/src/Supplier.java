public class Supplier {
    private final String id;
    private final String name;
    private final String contactEmail;
    
    public Supplier(String id, String name, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getContactEmail() { return contactEmail; }
    
    @Override
    public String toString() {
        return name + " (" + contactEmail + ")";
    }
}