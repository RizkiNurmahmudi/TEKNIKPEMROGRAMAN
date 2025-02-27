import java.util.*;

// Kelas Film untuk menyimpan informasi film
class Film {
    private final String judul;
    private final String jadwal;
    private final double harga;
    
    public Film(String judul, String jadwal, double harga) {
        this.judul = judul;
        this.jadwal = jadwal;
        this.harga = harga;
    }
    
    public String getJudul() { return judul; }
    public String getJadwal() { return jadwal; }
    public double getHarga() { return harga; }
}

// Kelas Pelanggan untuk menyimpan data pelanggan
class Pelanggan {
    private final String nama;
    
    public Pelanggan(String nama) {
        this.nama = nama;
    }
    
    public String getNama() { return nama; }
}

// Kelas Tiket untuk menyimpan informasi tiket yang dipesan
class Tiket {
    private final Film film;
    private final int jumlah;
    
    public Tiket(Film film, int jumlah) {
        this.film = film;
        this.jumlah = jumlah;
    }
    
    public Film getFilm() { return film; }
    public int getJumlah() { return jumlah; }
    public double getTotalHarga() { return jumlah * film.getHarga(); }
}

// Kelas Pemesanan untuk menyimpan detail transaksi pemesanan tiket
class Pemesanan {
    private final Pelanggan pelanggan;
    private final Tiket tiket;
    
    public Pemesanan(Pelanggan pelanggan, Tiket tiket) {
        this.pelanggan = pelanggan;
        this.tiket = tiket;
    }
    
    public void tampilkanDetail() {
        System.out.println("\n=== Detail Pemesanan ===");
        System.out.println("Nama Pelanggan: " + pelanggan.getNama());
        System.out.println("Film: " + tiket.getFilm().getJudul());
        System.out.println("Jadwal: " + tiket.getFilm().getJadwal());
        System.out.println("Jumlah Tiket: " + tiket.getJumlah());
        System.out.println("Total Harga: Rp" + tiket.getTotalHarga());
    }
}

// Kelas Main untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Daftar film yang tersedia
        List<Film> daftarFilm = new ArrayList<>();
        daftarFilm.add(new Film("Avengers: Endgame", "14:00", 50000));
        daftarFilm.add(new Film("Interstellar", "16:00", 60000));
        daftarFilm.add(new Film("Inception", "18:00", 55000));
        
        // Menampilkan film yang tersedia
        System.out.println("Selamat datang di Sistem Pemesanan Tiket!");
        System.out.println("Daftar film yang tersedia:");
        for (int i = 0; i < daftarFilm.size(); i++) {
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul() + " (" + daftarFilm.get(i).getJadwal() + ") - Rp" + daftarFilm.get(i).getHarga());
        }

        // Input nama pelanggan
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.nextLine();
        Pelanggan pelanggan = new Pelanggan(nama);

        // Memilih film
        System.out.print("Pilih nomor film: ");
        int pilihanFilm = scanner.nextInt();
        if (pilihanFilm < 1 || pilihanFilm > daftarFilm.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Film filmDipilih = daftarFilm.get(pilihanFilm - 1);

        // Memasukkan jumlah tiket
        System.out.print("Masukkan jumlah tiket: ");
        int jumlahTiket = scanner.nextInt();
        
        // Membuat objek tiket dan pemesanan
        Tiket tiket = new Tiket(filmDipilih, jumlahTiket);
        Pemesanan pemesanan = new Pemesanan(pelanggan, tiket);

        // Menampilkan detail pemesanan
        pemesanan.tampilkanDetail();
        
        System.out.println("Terima kasih telah memesan tiket!");
    }
}
