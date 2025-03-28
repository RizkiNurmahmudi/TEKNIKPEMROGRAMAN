package service;

import exception.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import model.*;

public class LibraryService {
    private final LibraryDatabase database;
    
    public LibraryService() {
        database = LibraryDatabase.getInstance();
    }
    
    public void addBookFromInput(Scanner scanner) {
        System.out.println("\n=== Tambah Buku Baru ===");
        System.out.print("ID Buku: ");
        String id = scanner.nextLine();
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        System.out.print("Penulis: ");
        String author = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        
        Book book = new Book(id, title, author, year);
        database.addBook(book);
        System.out.println("Buku berhasil ditambahkan: " + title);
    }
    
    public void searchBookFromInput(Scanner scanner) throws BookNotFoundException {
        System.out.println("\n=== Cari Buku ===");
        System.out.print("Masukkan kata kunci (judul/penulis): ");
        String keyword = scanner.nextLine();
        
        List<Book> results = database.searchBooks(keyword);
        if (results.isEmpty()) {
            throw new BookNotFoundException("Tidak ditemukan buku dengan kata kunci '" + keyword + "'");
        }
        
        System.out.println("\nHasil Pencarian (" + results.size() + " buku ditemukan):");
        results.forEach(System.out::println);
    }
    
    public void addMemberFromInput(Scanner scanner) {
        System.out.println("\n=== Tambah Anggota Baru ===");
        System.out.print("ID Anggota: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        LibraryMember member = new LibraryMember(id, name, email);
        database.addMember(member);
        System.out.println("Anggota berhasil ditambahkan: " + name);
    }
    
    public void borrowBookFromInput(Scanner scanner) throws BookNotFoundException, LoanException {
        System.out.println("\n=== Peminjaman Buku ===");
        System.out.print("ID Anggota: ");
        String memberId = scanner.nextLine();
        System.out.print("ID Buku: ");
        String bookId = scanner.nextLine();
        
        LibraryMember member = database.findMemberById(memberId);
        if (member == null) {
            throw new LoanException("Anggota dengan ID " + memberId + " tidak ditemukan");
        }
        
        Book book = database.findBookById(bookId);
        if (!book.isAvailable()) {
            throw new LoanException("Buku '" + book.getTitle() + "' sedang dipinjam");
        }
        
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plus(14, ChronoUnit.DAYS);
        String loanId = "LOAN-" + System.currentTimeMillis();
        
        Loan loan = new Loan(loanId, book, member, today, dueDate);
        database.addLoan(loan);
        
        System.out.println("Peminjaman berhasil!");
        System.out.println("Judul Buku: " + book.getTitle());
        System.out.println("Peminjam: " + member.getName());
        System.out.println("ID Pinjaman: " + loanId);
        System.out.println("Jatuh Tempo: " + dueDate.format(DateTimeFormatter.ISO_DATE));
    }
    
    public void returnBookFromInput(Scanner scanner) throws BookNotFoundException, LoanException {
        System.out.println("\n=== Pengembalian Buku ===");
        System.out.print("ID Buku: ");
        String bookId = scanner.nextLine();
        
        // Cek keberadaan buku dan dapatkan objeknya
        Book book = database.findBookById(bookId);
        
        // Cari pinjaman aktif untuk buku ini
        Loan loan = database.findActiveLoanByBookId(bookId);
        if (loan == null) {
            throw new LoanException("Buku '" + book.getTitle() + "' tidak sedang dipinjam");
        }
        
        LocalDate returnDate = LocalDate.now();
        loan.returnBook(returnDate);
        
        System.out.println("\nPengembalian berhasil!");
        System.out.println("Judul Buku: " + book.getTitle());
        System.out.println("Nama Peminjam: " + loan.getMember().getName());
        System.out.println("Tanggal Kembali: " + returnDate.format(DateTimeFormatter.ISO_DATE));
        
        if (loan.getFine() > 0) {
            System.out.printf("Denda yang harus dibayar: Rp%,.2f%n", loan.getFine());
            System.out.println("Keterlambatan: " + 
                (returnDate.toEpochDay() - loan.getDueDate().toEpochDay()) + " hari");
        } else {
            System.out.println("Tidak ada denda (Tepat waktu)");
        }
    }
}