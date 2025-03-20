// ****************************************************************
// ParseInts.java
//
// Reads a line of text and prints the integers in the line.
//
// ****************************************************************
import java.util.Scanner;

public class ParseInts {
    public static void main(String[] args) {
        int val, sum = 0;

        // Menggunakan try-with-resources untuk Scanner
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter a line of text");
            String line = scan.nextLine();

            // Scanner untuk memproses baris input
            try (Scanner scanLine = new Scanner(line)) {
                while (scanLine.hasNext()) {
                    try {
                        val = Integer.parseInt(scanLine.next());
                        sum += val;
                    } catch (NumberFormatException e) {
                        // Mengabaikan token yang bukan bilangan bulat
                    }
                }
            } // scanLine akan ditutup 

            System.out.println("The sum of the integers on this line is " + sum);
        } // scan akan ditutup 
    }
}