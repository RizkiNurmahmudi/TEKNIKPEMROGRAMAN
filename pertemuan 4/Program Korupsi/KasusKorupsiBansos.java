// Inheritance.java
class KasusKorupsiBansos extends KasusKorupsi implements Hukuman {

    // Constructor
    public KasusKorupsiBansos(String pelaku, String namaKasus, double jumlahKorupsi, String lokasi) {
        super(pelaku, namaKasus, jumlahKorupsi, lokasi);
    }

    @Override
    public void analisisDampak() {
        System.out.println("Dampak korupsi bansos:");
        System.out.println("- Masyarakat miskin tidak menerima bantuan yang seharusnya.");
        System.out.println("- Meningkatkan ketidakpercayaan masyarakat terhadap pemerintah.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\n--- Informasi Kasus Korupsi Bansos ---");
        System.out.println("Pelaku: " + pelaku);
        System.out.println("Nama Kasus: " + namaKasus);
        System.out.println("Jumlah Korupsi: Rp " + jumlahKorupsi);
        System.out.println("Lokasi: " + lokasi);
    }

    @Override
    public void hitungHukuman() {
        System.out.println("\n--- Hukuman ---");
        System.out.println("- Penjara minimal 10 tahun dan maksimal 30 tahun.");
        System.out.println("- Denda: Rp " + (jumlahKorupsi * 5) + " (5x lipat dari jumlah korupsi).");
        System.out.println("- Pencabutan hak politik selama 10 tahun.");
        System.out.println("- Penyitaan aset yang diduga berasal dari hasil korupsi.");
        System.out.println("- Wajib mengembalikan kerugian negara sebesar Rp " + (jumlahKorupsi * 2) + ".");
    }

    @Override
    public void solusi() {
        System.out.println("\n--- Solusi ---");
        System.out.println("- Transparansi dalam penyaluran bansos.");
        System.out.println("- Pengawasan ketat oleh lembaga independen.");
        System.out.println("- Sanksi tegas bagi pelaku korupsi.");
    }
}

class KasusKorupsiProyekPembangunan extends KasusKorupsi implements Hukuman {

    // Constructor
    public KasusKorupsiProyekPembangunan(String pelaku, String namaKasus, double jumlahKorupsi, String lokasi) {
        super(pelaku, namaKasus, jumlahKorupsi, lokasi);
    }

    @Override
    public void analisisDampak() {
        System.out.println("Dampak korupsi proyek pembangunan:");
        System.out.println("- Infrastruktur yang dibangun tidak memenuhi standar kualitas.");
        System.out.println("- Kerugian negara akibat pembangunan yang tidak optimal.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\n--- Informasi Kasus Korupsi Proyek Pembangunan ---");
        System.out.println("Pelaku: " + pelaku);
        System.out.println("Nama Kasus: " + namaKasus);
        System.out.println("Jumlah Korupsi: Rp " + jumlahKorupsi);
        System.out.println("Lokasi: " + lokasi);
    }

    @Override
    public void hitungHukuman() {
        System.out.println("\n--- Hukuman ---");
        System.out.println("- Penjara minimal 15 tahun dan maksimal 40 tahun.");
        System.out.println("- Denda: Rp " + (jumlahKorupsi * 7) + " (7x lipat dari jumlah korupsi).");
        System.out.println("- Larangan menduduki jabatan publik selama 20 tahun.");
        System.out.println("- Penyitaan aset yang diduga berasal dari hasil korupsi.");
        System.out.println("- Wajib mengembalikan kerugian negara sebesar Rp " + (jumlahKorupsi * 3) + ".");
    }

    @Override
    public void solusi() {
        System.out.println("\n--- Solusi ---");
        System.out.println("- Audit proyek pembangunan secara berkala.");
        System.out.println("- Pelibatan masyarakat dalam pengawasan proyek.");
        System.out.println("- Sanksi tegas bagi kontraktor dan pejabat yang terlibat.");
    }
}

class KasusKorupsiAPBD extends KasusKorupsi implements Hukuman {

    // Constructor
    public KasusKorupsiAPBD(String pelaku, String namaKasus, double jumlahKorupsi, String lokasi) {
        super(pelaku, namaKasus, jumlahKorupsi, lokasi);
    }

    @Override
    public void analisisDampak() {
        System.out.println("Dampak korupsi APBD:");
        System.out.println("- Anggaran daerah tidak digunakan untuk kepentingan publik.");
        System.out.println("- Pembangunan daerah terhambat.");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\n--- Informasi Kasus Korupsi APBD ---");
        System.out.println("Pelaku: " + pelaku);
        System.out.println("Nama Kasus: " + namaKasus);
        System.out.println("Jumlah Korupsi: Rp " + jumlahKorupsi);
        System.out.println("Lokasi: " + lokasi);
    }

    @Override
    public void hitungHukuman() {
        System.out.println("\n--- Hukuman ---");
        System.out.println("- Penjara minimal 12 tahun dan maksimal 25 tahun.");
        System.out.println("- Denda: Rp " + (jumlahKorupsi * 4) + " (4x lipat dari jumlah korupsi).");
        System.out.println("- Larangan menduduki jabatan publik selama 15 tahun.");
        System.out.println("- Penyitaan aset yang diduga berasal dari hasil korupsi.");
        System.out.println("- Wajib mengembalikan kerugian negara sebesar Rp " + (jumlahKorupsi * 2.5) + ".");
    }

    @Override
    public void solusi() {
        System.out.println("\n--- Solusi ---");
        System.out.println("- Transparansi dalam pengelolaan APBD.");
        System.out.println("- Pelibatan masyarakat dalam pengawasan anggaran.");
        System.out.println("- Sanksi tegas bagi pejabat yang terlibat.");
    }
}