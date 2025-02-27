import java.util.Scanner;

public class RestaurantMain {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Restaurant menu = new Restaurant();

            menu.tambahMenuMakanan("Bala-Bala", 1000, 20);
            menu.tambahMenuMakanan("Gehu", 1000, 20);
            menu.tambahMenuMakanan("Tahu", 1000, 10);
            menu.tambahMenuMakanan("Molen", 1000, 20);

            while (true) {
                System.out.println("\nMenu Restoran:");
                menu.tampilMenuMakanan();

                System.out.print("\nMasukkan nama makanan yang ingin dipesan (atau ketik 'exit' untuk keluar): ");
                String nama = scanner.nextLine();
                if (nama.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Masukkan jumlah pesanan: ");
                int jumlah = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline

                menu.pesanMakanan(nama, jumlah);
            }

            System.out.println("Terima kasih telah memesan di restoran kami!");
        }
    }
}