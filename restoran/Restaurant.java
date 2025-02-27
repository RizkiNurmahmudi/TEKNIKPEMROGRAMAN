import java.util.ArrayList;

public class Restaurant {
    private final ArrayList<MenuItem> menu;

    public Restaurant() {
        menu = new ArrayList<>();
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {
        menu.add(new MenuItem(nama, harga, stok));
    }

    public void tampilMenuMakanan() {
        for (MenuItem item : menu) {
            if (!item.isOutOfStock()) {
                System.out.println(item.getNama() + " [" + item.getStok() + "]\tRp. " + item.getHarga());
            }
        }
    }

    public void pesanMakanan(String nama, int jumlah) {
        for (MenuItem item : menu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                if (item.getStok() >= jumlah) {
                    item.kurangiStok(jumlah);
                    System.out.println("Pesanan " + jumlah + " " + nama + " berhasil!");
                } else {
                    System.out.println("Stok " + nama + " tidak cukup!");
                }
                return;
            }
        }
        System.out.println("Menu tidak ditemukan!");
    }
}

class MenuItem {
    private final String nama;
    private final double harga;
    private int stok;

    public MenuItem(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
        } else {
            System.out.println("Stok tidak cukup!");
        }
    }

    public boolean isOutOfStock() {
        return stok == 0;
    }
}
