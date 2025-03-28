package model;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final int year;
    private boolean isAvailable;
    
    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }
    
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    @Override
    public String toString() {
        return String.format("ID: %s, Judul: %s, Penulis: %s, Tahun: %d, Status: %s",
                id, title, author, year, isAvailable ? "Tersedia" : "Dipinjam");
    }
}