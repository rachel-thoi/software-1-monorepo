import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask the user for a positive integer and then compute and output the
 * corresponding Hailstone series.
 *
 * @author Rachel Thoi
 *
 */
public final class Hailstone1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone1 series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        // The initial number in the series
        int counter = n;
        // Print the first number of the series
        out.print("" + counter);

        // Continue generating the series until it reaches 1
        while (counter != 1) {
            // Apply Hailstone sequence rules: even numbers are halved,
            // odd numbers are tripled and increased by 1
            if (counter % 2 == 0) {
                counter = counter / 2;
            } else {
                final int three = 3;
                counter = three * counter + 1;
            }
            // Output the next number in the series
            out.print(", " + counter);
        }
        // The loop exits when counter equals 1,
        // hence no need for incrementing counter here
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

        // Prompt the user for a starting number for the Hailstone series
        out.print("Enter a variable for the Hailstone series: ");
        int n = in.nextInteger();

        // Generate and display the Hailstone series for the provided number
        generateSeries(n, out);

        // Close input and output streams
        in.close();
        out.close();
    }

}
