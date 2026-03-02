import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change generateSeries (including its Javadoc comments) so that it also
 * computes and outputs the length of the series.
 *
 * @author Rachel Thoi
 *
 */
public final class Hailstone2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone2 series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        // Initialize counters and length
        int counter = n; // Starting number of the series
        int length = 1; // Length of the series
        // (initialized to include the starting number)

        // Print the starting number
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
            // Increment the length of the series
            length++;
        }

        // Print the length of the series
        out.println();
        out.println("The length of the series is: " + length);
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
