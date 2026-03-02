import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Introduce a private static method called coinCountsToMakeChange that has two
 * formal parameters: the number of cents for which change is needed, and the
 * array of coin denominations to be used to make change; and that returns an
 * array of coin counts for the respective denominations.
 *
 * @author Rachel Thoi
 */
public final class CoinChange3 {

    /**
     * Private constructor to prevent instantiation.
     */
    private CoinChange3() {
    }

    /**
     * Computes the number of coins needed for each denomination to make the
     * given amount of change using a while loop.
     *
     * @param amount
     *            the total amount of change needed
     * @param denominations
     *            the array of coin denominations
     * @return an array representing the count of each coin type needed
     */
    private static int[] coinCountsToMakeChange(int amount, int[] denominations) {
        // Array to store the counts of each coin
        int[] counts = new int[denominations.length];
        int remainingAmount = amount; // Use a local variable to hold the amount
        // Declare and initialize to 0
        int index = 0;
        // Loop through each denomination to calculate coin counts
        while (index < denominations.length) {
            counts[index] = remainingAmount / denominations[index];
            remainingAmount = remainingAmount % denominations[index];
            index++;
        }
        return counts;
    }

    /**
     * Main method to run the coin denomination calculation.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Define the coin denominations
        final int[] denominations = { 100, 50, 25, 10, 5, 1 };

        out.print("Please input the change amount: ");
        int amount = in.nextInteger();

        // Calculate the number of coins for each denomination
        int[] coinCount = coinCountsToMakeChange(amount, denominations);

        // more constants
        final int zero = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;

        // Output the results
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
