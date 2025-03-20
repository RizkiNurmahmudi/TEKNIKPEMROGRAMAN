// *******************************
// MathUtils.java
//
// Provides static mathematical utility functions.
//
// *******************************
public class MathUtils {
    public static int factorial(int n) throws IllegalArgumentException {
        // Memeriksa apakah input valid
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers: " + n);
        }
        if (n > 16) {
            throw new IllegalArgumentException("Factorial is too large for int type (n > 16): " + n);
        }

        // Menghitung faktorial
        int fac = 1;
        for (int i = n; i > 0; i--) {
            fac *= i;
        }
        return fac;
    }
}