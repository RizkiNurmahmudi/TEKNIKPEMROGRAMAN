import service.LibraryService;
import service.ReportService;
import exception.BookNotFoundException;
import exception.LoanException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        ReportService reportService = new ReportService();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Daftar Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Tambah Anggota");
            System.out.println("5. Pinjam Buku");
            System.out.println("6. Kembalikan Buku");
            System.out.println("7. Laporan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            try {
                switch (choice) {
                    case 1:
                        libraryService.addBookFromInput(scanner);
                        break;
                    case 2:
                        reportService.displayAllBooks();
                        break;
                    case 3:
                        libraryService.searchBookFromInput(scanner);
                        break;
                    case 4:
                        libraryService.addMemberFromInput(scanner);
                        break;
                    case 5:
                        libraryService.borrowBookFromInput(scanner);
                        break;
                    case 6:
                        libraryService.returnBookFromInput(scanner);
                        break;
                    case 7:
                        reportService.generateReports();
                        break;
                    case 0:
                        System.out.println("Keluar dari sistem...");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (BookNotFoundException | LoanException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}