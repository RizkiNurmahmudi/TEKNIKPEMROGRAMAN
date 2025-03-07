// Main.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<KasusKorupsi> daftarKasus = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Tambah Kasus Korupsi");
                System.out.println("2. Lihat Daftar Kasus");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu (1/2/3): ");
                int menu = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline

                switch (menu) {
                    case 1 -> {
                        System.out.println("\n--- Tambah Kasus Korupsi ---");
                        System.out.println("Pilih jenis kasus korupsi:");
                        System.out.println("1. Bansos");
                        System.out.println("2. Proyek Pembangunan");
                        System.out.println("3. APBD");
                        System.out.print("Masukkan pilihan (1/2/3): ");
                        int pilihan = scanner.nextInt();
                        scanner.nextLine(); // Membersihkan newline

                        System.out.print("Masukkan nama pelaku: ");
                        String pelaku = scanner.nextLine();

                        System.out.print("Masukkan nama kasus: ");
                        String namaKasus = scanner.nextLine();

                        double jumlahKorupsi;
                        while (true) {
                            System.out.print("Masukkan jumlah korupsi (dalam Rupiah): ");
                            if (scanner.hasNextDouble()) {
                                jumlahKorupsi = scanner.nextDouble();
                                scanner.nextLine(); // Membersihkan newline
                                if (jumlahKorupsi > 0) {
                                    break;
                                } else {
                                    System.out.println("Jumlah korupsi harus positif!");
                                }
                            } else {
                                System.out.println("Input tidak valid! Masukkan angka.");
                                scanner.next(); // Membersihkan input yang tidak valid
                            }
                        }

                        System.out.print("Masukkan lokasi kasus: ");
                        String lokasi = scanner.nextLine();

                        KasusKorupsi kasus = switch (pilihan) {
                            case 1 -> new KasusKorupsiBansos(pelaku, namaKasus, jumlahKorupsi, lokasi);
                            case 2 -> new KasusKorupsiProyekPembangunan(pelaku, namaKasus, jumlahKorupsi, lokasi);
                            case 3 -> new KasusKorupsiAPBD(pelaku, namaKasus, jumlahKorupsi, lokasi);
                            default -> {
                                System.out.println("Pilihan tidak valid!");
                                yield null;
                            }
                        };

                        if (kasus != null) {
                            daftarKasus.add(kasus);
                            System.out.println("Kasus berhasil ditambahkan!");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Kasus Korupsi ---");
                        if (daftarKasus.isEmpty()) {
                            System.out.println("Belum ada kasus yang ditambahkan.");
                        } else {
                            for (int i = 0; i < daftarKasus.size(); i++) {
                                System.out.println("\nKasus #" + (i + 1));
                                daftarKasus.get(i).tampilkanInfo(); // Polymorphism
                                daftarKasus.get(i).analisisDampak(); // Polymorphism
                                if (daftarKasus.get(i) instanceof Hukuman hukuman) { // Polymorphism
                                    hukuman.hitungHukuman();
                                }
                                daftarKasus.get(i).solusi(); // Polymorphism
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Terima kasih! Program selesai.");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid!");
                }
            }
        }
    }
}