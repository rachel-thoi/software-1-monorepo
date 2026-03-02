import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask the user for an amount (number of cents) to make change for and then use
 * the greedy solution to compute and output the numbers of coins of each kind
 * required to make that amount of change.
 *
 * @author Rachel Thoi
 *
 */
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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

        // constant
        final int oneDollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;
        final int penny = 1;

        out.print("Please input the change amount: "); // Prompt user for input
        int amount = in.nextInteger(); // Read the input amount

        // Computation
        int totalDollar = amount / oneDollar;
        amount = amount % oneDollar;

        int totalHalfDollar = amount / halfDollar;
        amount = amount % halfDollar;

        int totalQuarter = amount / quarter;
        amount = amount % quarter;

        int totalDime = amount / dime;
        amount = amount % dime;

        int totalNickel = amount / nickel;
        amount = amount % nickel;

        int totalPenny = amount / penny;

        // Output the result
        out.println("Dollar: " + totalDollar);
        out.println("Half dollar: " + totalHalfDollar);
        out.println("Quarter: " + totalQuarter);
        out.println("Dime: " + totalDime);
        out.println("Nickel: " + totalNickel);
        out.println("Penny: " + totalPenny);

        // Close input and output streams
        in.close();
        out.close();
    }

}
