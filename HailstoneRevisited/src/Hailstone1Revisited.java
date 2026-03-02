import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Rachel Thoi
 *
 */
public final class Hailstone1Revisited {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1Revisited() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        final NaturalNumber one = new NaturalNumber2(1);
        final NaturalNumber two = new NaturalNumber2(2);
        final NaturalNumber three = new NaturalNumber2(3);

        // Loop until it completes the Hailstone series
        while (!n.equals(one)) {
            out.print(n + ", ");
            NaturalNumber copy = new NaturalNumber2(n);

            // Calculates the next num depending on if num is even or odd
            if (copy.divide(two).isZero()) {
                n.divide(two);
            } else {
                n.multiply(three);
                n.add(one);
            }
        }
        out.println(n);
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

        // Ask for a input and assigns it to userNum
        out.print("Input a positive integer: ");
        String userInput = in.nextLine();
        NaturalNumber userNum = new NaturalNumber2(userInput);

        // Generate and display the Hailstone series for the provided number
        generateSeries(userNum, out);

        // Close input and output streams
        in.close();
        out.close();
    }

}
