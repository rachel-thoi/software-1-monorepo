import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change sqrt (including its Javadoc comments) so it also works when x = 0.
 * Note: if your code from Newton1 appears to work without any changes, but it
 * is such that it might execute a division by 0, then it is not correct.
 * Division by 0, in general, is undefined and you should not write code that
 * attempts to compute it.
 *
 * @author Rachel Thoi
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
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

        // Check if the estimate does computes to 0
        if (x != 0) {
            while ((r * r - x) * (r * r - x) / (x * x) > epsilonSquare) {
                r = (r + x / r) / 2;
            }
        } else {
            r = 0;
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
