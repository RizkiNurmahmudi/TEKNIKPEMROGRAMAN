// KasusKorupsi.java
public abstract class KasusKorupsi {
    protected String pelaku;
    protected String namaKasus;
    protected double jumlahKorupsi;
    protected String lokasi;

    // Constructor
    public KasusKorupsi(String pelaku, String namaKasus, double jumlahKorupsi, String lokasi) {
        this.pelaku = pelaku;
        this.namaKasus = namaKasus;
        this.jumlahKorupsi = jumlahKorupsi;
        this.lokasi = lokasi;
    }

    // Metode abstrak untuk analisis dampak
    public abstract void analisisDampak();

    // Metode abstrak untuk menampilkan informasi kasus
    public abstract void tampilkanInfo();

    // Metode abstrak untuk solusi
    public abstract void solusi();
}