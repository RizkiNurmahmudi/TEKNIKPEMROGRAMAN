import java.math.BigInteger;
import java.util.Scanner;

public class NumberRangeChecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan jumlah bilangan: ");
            int jumlah = scanner.nextInt();
            BigInteger[] angka = new BigInteger[jumlah];
            
            for (int i = 0; i < jumlah; i++) {
                System.out.print("Masukkan angka :");
                angka[i] = scanner.nextBigInteger();
            }
            
            for (BigInteger num : angka) {
                cekRentang(num);
            }
        }
    }
    
    private static void cekRentang(BigInteger num) {
        if (num.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 ||
            num.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) {
            System.out.println(num + " cannot be fitted anywhere");
        } else {
            System.out.println(num + " can be printed in:");
            if (num.compareTo(BigInteger.valueOf(Byte.MAX_VALUE)) <= 0 &&
                num.compareTo(BigInteger.valueOf(Byte.MIN_VALUE)) >= 0) {
                System.out.println("* byte");
            }
            if (num.compareTo(BigInteger.valueOf(Short.MAX_VALUE)) <= 0 &&
                num.compareTo(BigInteger.valueOf(Short.MIN_VALUE)) >= 0) {
                System.out.println("* short");
            }
            if (num.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 &&
                num.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0) {
                System.out.println("* int");
            }
            System.out.println("* long");
        }
    }
}
