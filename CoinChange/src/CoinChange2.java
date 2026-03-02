import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Modify the program so that it solves the same problem, with the same greedy
 * solution, but uses arrays (one for the coin denominations and one for the
 * coin counts) and one loop with a simple body to replace the longer
 * straight-line code in CoinChange1.java. Note that this change also
 * generalizes the solution so it can be easily modified, by changing a single
 * line of code, to work with other coin denominations.
 *
 * @author Rachel Thoi
 *
 */
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // constants
        final int oneDollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;
        final int penny = 1;

        // Prompt user for input
        out.print("Please input the change amount: ");
        int amount = in.nextInteger(); // Read the input amount
        // Array of coin denominations
        int[] denomination = { oneDollar, halfDollar, quarter, dime, nickel, penny };
        // Array to store the counts of each coin
        int[] coinCount = new int[denomination.length];
        // Declare and initialize to 0
        int i = 0;
        // Loop through each denomination to calculate coin counts
        while (i < denomination.length) {
            coinCount[i] = amount / denomination[i];
            amount = amount % denomination[i];
            i++;
        }
        // more constants
        final int zero = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;

        out.println("Dollar: " + coinCount[zero]);
        out.println("Half dollar: " + coinCount[one]);
        out.println("Quarter: " + coinCount[two]);
        out.println("Dime: " + coinCount[three]);
        out.println("Nickel: " + coinCount[four]);
        out.println("Penny: " + coinCount[five]);

        // Close input and output streams
        in.close();
        out.close();
    }

}
