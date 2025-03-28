package model;

import java.time.LocalDate;

public class Loan {
    private final String loanId;
    private final Book book;
    private final LibraryMember member;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    
    public Loan(String loanId, Book book, LibraryMember member, LocalDate loanDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.fine = 0.0;
    }
    
    public String getLoanId() { return loanId; }
    public Book getBook() { return book; }
    public LibraryMember getMember() { return member; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
    
    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        if (returnDate.isAfter(dueDate)) {
            long daysLate = returnDate.toEpochDay() - dueDate.toEpochDay();
            fine = daysLate * 5000;
        }
        book.setAvailable(true);
    }
    
    @Override
    public String toString() {
        return String.format("ID Pinjaman: %s, Buku: %s, Anggota: %s, Tanggal Pinjam: %s, Jatuh Tempo: %s, Dikembalikan: %s, Denda: Rp%,.2f",
                loanId, book.getTitle(), member.getName(), loanDate, dueDate, 
                returnDate != null ? returnDate.toString() : "Belum", fine);
    }
}