import java.util.Scanner;

// Kelas Produk merepresentasikan sebuah produk di toko
class Produk {
    private String nama; // Nama produk
    private double harga; // Harga produk dalam USD

    // Konstruktor untuk inisialisasi nama dan harga produk
    public Produk(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Getter untuk mendapatkan nama produk
    public String getNama() {
        return nama;
    }

    // Setter untuk mengubah nama produk
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk mendapatkan harga produk
    public double getHarga() {
        return harga;
    }

    // Setter untuk mengubah harga produk
    public void setHarga(double harga) {
        this.harga = harga;
    }
}

// Kelas Kasir untuk menangani transaksi pembelian
class Kasir {
    private String namaKasir; // Nama kasir yang bertugas

    // Konstruktor untuk inisialisasi nama kasir
    public Kasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }

    // Getter untuk mendapatkan nama kasir
    public String getNamaKasir() {
        return namaKasir;
    }

    // Setter untuk mengubah nama kasir
    public void setNamaKasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }

    // Metode untuk memproses pembayaran
    public void prosesPembayaran(Produk[] produkDibeli, int jumlahProduk, double uangDiberikan) {
        System.out.println("\n===============================");
        System.out.println("         STRUK PEMBAYARAN       ");
        System.out.println("===============================");
        System.out.println("Kasir : " + namaKasir);
        double totalHarga = 0;
        for (int i = 0; i < jumlahProduk; i++) {
            System.out.println("Produk : " + produkDibeli[i].getNama() + " - $" + produkDibeli[i].getHarga());
            totalHarga += produkDibeli[i].getHarga();
        }
        System.out.println("Total Harga : $" + totalHarga);
        System.out.println("Bayar  : $" + uangDiberikan);
        
        if (uangDiberikan >= totalHarga) { // Cek apakah uang yang diberikan cukup
            double kembalian = uangDiberikan - totalHarga; // Hitung kembalian
            System.out.println("Kembalian : $" + kembalian);
            System.out.println("===============================");
            System.out.println("     TERIMA KASIH SUDAH BELANJA ");
            System.out.println("===============================");
        } else {
            System.out.println("Uang yang diberikan kurang! Pembayaran gagal.");
            System.out.println("===============================");
        }
    }
}

// Kelas Main untuk menjalankan program utama
public class Main {
    public static void main(String[] args) {
        // Menggunakan try-with-resources agar Scanner otomatis ditutup setelah digunakan,
        // sehingga tidak perlu menutupnya secara manual.
        try (Scanner scanner = new Scanner(System.in)) {
            // Daftar produk yang tersedia di toko
            Produk[] daftarProduk = {
                new Produk("Laptop", 500.00),
                new Produk("Mouse", 10.00),
                new Produk("Keyboard", 20.00),
                new Produk("Monitor", 150.00)
            };

            // Menampilkan daftar produk ke layar
            System.out.println("\n===============================");
            System.out.println("      SELAMAT DATANG DI TOKO    ");
            System.out.println("===============================");
            System.out.println("Daftar Produk yang tersedia:");
            for (int i = 0; i < daftarProduk.length; i++) {
                System.out.println((i + 1) + ". " + daftarProduk[i].getNama() + " - $" + daftarProduk[i].getHarga());
            }
            System.out.println("===============================");

            // Meminta pengguna memilih satu atau lebih produk
            Produk[] produkDibeli = new Produk[daftarProduk.length];
            int jumlahProduk = 0;
            while (true) {
                System.out.print("Pilih produk (1-" + daftarProduk.length + "), atau 0 untuk selesai: ");
                int pilihan = scanner.nextInt();
                if (pilihan == 0) {
                    break;
                }
                if (pilihan < 1 || pilihan > daftarProduk.length) { // Validasi input
                    System.out.println("Pilihan tidak valid!");
                    continue;
                }
                produkDibeli[jumlahProduk++] = daftarProduk[pilihan - 1];
            }

            if (jumlahProduk == 0) {
                System.out.println("Anda tidak memilih produk apapun. Program selesai.");
                return;
            }

            // Meminta input jumlah uang yang diberikan oleh pengguna
            System.out.print("Masukkan jumlah uang untuk membayar: ");
            double uangDiberikan = scanner.nextDouble();

            // Membuat objek kasir dan memproses pembayaran
            Kasir kasir = new Kasir("Andi");
            kasir.prosesPembayaran(produkDibeli, jumlahProduk, uangDiberikan);
        }
    }
}
