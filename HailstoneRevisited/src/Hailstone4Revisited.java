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
public final class Hailstone4Revisited {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4Revisited() {
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
        int length = 1;
        int longest = length;

        NaturalNumber max = new NaturalNumber2(n);
        NaturalNumber maxStart = new NaturalNumber2(n);
        NaturalNumber index = new NaturalNumber2(n);
        NaturalNumber localMax = new NaturalNumber2(n);
        NaturalNumber longestStart = new NaturalNumber2(n);

        while (index.compareTo(one) >= 0) {
            length = 1;
            n.copyFrom(index);
            localMax.copyFrom(index);

            //Loops till it completes the Hailstone series
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
                if (n.compareTo(max) >= 0) {
                    max.copyFrom(n);
                    maxStart.copyFrom(index);
                }
                if (n.compareTo(localMax) > 0) {
                    localMax.copyFrom(n);
                }
                length++;
            }
            out.println(n);

            if (length >= longest) {
                longest = length;
                longestStart.copyFrom(index);
            }
            // Prints the length and max num of the series
            out.println("Length: " + length);
            out.println("Max number: " + localMax + "\n");

            index.subtract(one);
        }
        out.println("Highest number starting point: " + maxStart);
        out.println("Highest number: " + max + "\n");
        out.println("Longest series starting point: " + longestStart);
        out.println("Longest series: " + longest);
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
