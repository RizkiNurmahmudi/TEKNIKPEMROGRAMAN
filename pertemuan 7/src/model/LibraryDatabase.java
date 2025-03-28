package model;

import exception.BookNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryDatabase {
    private static LibraryDatabase instance;
    private final List<Book> books;
    private final List<LibraryMember> members;
    private final List<Loan> loans;
    
    private LibraryDatabase() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();
    }
    
    public static synchronized LibraryDatabase getInstance() {
        if (instance == null) {
            instance = new LibraryDatabase();
        }
        return instance;
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    public Book findBookById(String id) throws BookNotFoundException {
        Optional<Book> foundBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
                
        return foundBook.orElseThrow(() -> new BookNotFoundException("Buku dengan ID " + id + " tidak ditemukan"));
    }
    
    public List<Book> searchBooks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerKeyword) || 
                                book.getAuthor().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    public void addMember(LibraryMember member) {
        members.add(member);
    }
    
    public LibraryMember findMemberById(String id) {
        return members.stream()
                .filter(member -> member.getMemberId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.getBook().setAvailable(false);
        loan.getMember().addLoan(loan);
    }
    
    public Loan findActiveLoanByBookId(String bookId) {
        return loans.stream()
                .filter(loan -> loan.getBook().getId().equals(bookId) && loan.getReturnDate() == null)
                .findFirst()
                .orElse(null);
    }
    
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }
}