// Polymorphism.java
import java.util.List;

public class Polymorphism {
    public static void tampilkanDaftarKasus(List<KasusKorupsi> daftarKasus) {
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
}