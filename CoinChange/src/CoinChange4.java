import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Modify the program so that it uses a different (non-greedy) solution that
 * gives an optimal answer for any combination of coin denominations. Note that
 * you should not be too disturbed if you cannot find an algorithm for this
 * problem that is "fast" for all possible denominations of coins. On the other
 * hand, you should be very pleased if you can -- because it would solve an
 * important open theoretical problem in computer science and mathematics for
 * which a solution would earn you a $1M prize and unfathomable fame. (Well,
 * you'd be famous among theoreticians and would certainly be featured in an
 * article in the New York Times. It is beyond the scope of this course to
 * explain why the Clay Mathematics Institute rather than a vending-machine
 * maker should care this much about a seemingly simple problem like making
 * change.)
 *
 * @author Rachel Thoi
 */
public final class CoinChange4 {

    /**
     * Private constructor to prevent instantiation.
     */
    private CoinChange4() {
    }

    /**
     * Uses dynamic programming with while loops to find the minimum number of
     * coins that make up the given amount.
     *
     * @param amount
     *            the total amount of change needed
     * @param denominations
     *            the array of coin denominations
     * @return an array representing the minimum count of each coin type needed
     */
    private static int[] coinCountsToMakeChange(int amount, int[] denominations) {
        int n = denominations.length; // Number of different coin denominations
        int[] dp = new int[amount + 1]; // dp array that will store the minimum number
        // of coins for each amount
        int[][] coinUsed = new int[n][amount + 1]; // Matrix to store the usage
        // count of each denomination for each amount

        // Initialize the dp array, setting a high number for all indices
        int amountIndex = 1;
        while (amountIndex <= amount) {
            dp[amountIndex] = Integer.MAX_VALUE;
            amountIndex++;
        }

        // Main loop to process each coin denomination
        int i = 0;
        while (i < n) {
            int j = denominations[i];
            // Update dp array for all amounts that can be
            // reached with the current denomination
            while (j <= amount) {
                if (dp[j - denominations[i]] != Integer.MAX_VALUE
                        && dp[j] > dp[j - denominations[i]] + 1) {
                    dp[j] = dp[j - denominations[i]] + 1;
                    coinUsed[i][j] = coinUsed[i][j - denominations[i]] + 1;
                    int k = 0;
                    // Copy the coin usage from the previous
                    // amount after using the current coin
                    while (k < i) {
                        coinUsed[k][j] = coinUsed[k][j - denominations[i]];
                        k++;
                    }
                }
                j++;
            }
            i++;
        }

        // Building the result using the coinUsed matrix
        int[] counts = new int[denominations.length];
        int remainingAmount = amount;
        // Calculate the number of each type of coin used for the given amount
        i = n - 1;
        while (i >= 0 && remainingAmount > 0) {
            counts[i] = coinUsed[i][remainingAmount];
            remainingAmount -= counts[i] * denominations[i];
            i--;
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
