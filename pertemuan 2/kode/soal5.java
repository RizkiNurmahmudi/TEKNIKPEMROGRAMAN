import java.util.Scanner;

public class soal5 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan string pertama: ");
            String str1 = scanner.nextLine();
            
            System.out.print("Masukkan string kedua: ");
            String str2 = scanner.nextLine();
            
            // Menampilkan jumlah total karakter dari dua string
            int totalLength = str1.length() + str2.length();
            System.out.println("Panjang total kedua string: " + totalLength);
            
            // Membandingkan dua string secara leksikografis
            String comparisonResult = (str1.compareTo(str2) > 0) ? "yes" : "no";
            System.out.println(comparisonResult);
            
            // Mengubah huruf pertama dari kedua string menjadi huruf besar
            String capitalizedStr1 = str1.substring(0, 1).toUpperCase() + str1.substring(1);
            String capitalizedStr2 = str2.substring(0, 1).toUpperCase() + str2.substring(1);
            
            System.out.println(capitalizedStr1 + " " + capitalizedStr2);
        }
    }
}
