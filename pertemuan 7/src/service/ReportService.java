package service;

import java.util.List;
import model.*;

public class ReportService {
    private final LibraryDatabase database;
    
    public ReportService() {
        database = LibraryDatabase.getInstance();
    }
    
    public void displayAllBooks() {
        System.out.println("\n=== Daftar Semua Buku ===");
        List<Book> books = database.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang terdaftar");
        } else {
            books.forEach(System.out::println);
        }
    }
    
    public void generateReports() {
        System.out.println("\n=== Laporan Perpustakaan ===");
        
        List<Book> books = database.getAllBooks();
        long availableBooks = books.stream().filter(Book::isAvailable).count();
        System.out.println("\nStatistik Buku:");
        System.out.println("Total Buku: " + books.size());
        System.out.println("Buku Tersedia: " + availableBooks);
        System.out.println("Buku Dipinjam: " + (books.size() - availableBooks));
        
        List<Loan> allLoans = database.getAllLoans();
        long activeLoans = allLoans.stream().filter(loan -> loan.getReturnDate() == null).count();
        System.out.println("\nPinjaman Aktif: " + activeLoans);
        
        double totalFines = allLoans.stream().mapToDouble(Loan::getFine).sum();
        System.out.printf("\nTotal Denda yang Terkumpul: Rp%,.2f%n", totalFines);
    }
}