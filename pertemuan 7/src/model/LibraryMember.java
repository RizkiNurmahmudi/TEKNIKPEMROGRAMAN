package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private final String memberId;
    private final String name;
    private final String email;
    private final List<Loan> loanHistory;
    
    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.loanHistory = new ArrayList<>();
    }
    
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Loan> getLoanHistory() { return loanHistory; }
    
    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }
    
    @Override
    public String toString() {
        return String.format("ID Anggota: %s, Nama: %s, Email: %s, Total Pinjaman: %d",
                memberId, name, email, loanHistory.size());
    }
}