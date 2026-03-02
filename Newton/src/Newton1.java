import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Repeatedly asks the user whether they wish to calculate a square root. Each
 * time, if the response is "y", then the program should proceed; if it is
 * anything else, then the program should quit. Whenever it proceeds, the
 * program should prompt the user for a number (a positive double, and your
 * program may simply assume the input is consistent with this requirement) and
 * then report the square root of that number to within a relative error of no
 * more than 0.01%. The computation must be done using Newton's iteration.
 *
 * @author Rachel Thoi
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        // Declare your variables
        double r = x;
        final double epsilonSquare = 0.00000001;

        // Compute estimate of square root using Newton's iteration
        // while (Math.abs((r * r) - x) / x > epsilonSquare) {
        while ((r * r - x) * (r * r - x) / (x * x) > epsilonSquare) {
            r = (r + x / r) / 2;
        }
        return r;
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
        String answer = "y";

        // Prompt the user for input value until user ends program
        while (answer.equals("y")) {
            out.print("Enter a positive number to calculate the square root: ");
            double input = in.nextDouble();
            out.println("The results: " + sqrt(input));
            out.print("Would you like to input another value (y/n): ");
            answer = in.nextLine();
        }

        // Close input and output streams
        in.close();
        out.close();
    }

}
