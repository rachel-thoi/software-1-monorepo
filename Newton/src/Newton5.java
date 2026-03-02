import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change it so, in addition to asking for x, it asks the user for a root k (an
 * integer greater than or equal to 2), and reports the k-th root of x as
 * computed by Newton iteration.
 *
 * @author Rachel Thoi
 *
 */
public final class Newton5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton5() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            the number from which the k-th root is to be extracted.
     * @param k
     *            the degree of the root to be extracted.
     * @param epsilon
     *            the precision required for the calculation
     * @return the estimated k-th root of x, calculated to within the specified
     *         precision
     */
    private static double kthRoot(double x, int k, double epsilon) {
        if (x == 0) {
            return 0; // the k-th root of 0 is 0
        }
        double r = x; // Initial guess

        // Iterate until the relative error is less than epsilon
        while ((r * r - x) * (r * r - x) / (x * x) > epsilon) {
            r = ((k - 1) * r + x / Math.pow(r, k - 1)) / k;
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
        boolean continuedLoop = true;

        // Prompt the user for epsilon value
        out.print("Enter an " + "epsilon" + " value: ");
        double epsilon = in.nextDouble();

        // Prompt the user for input value until user ends program
        while (continuedLoop) {
            out.print("Enter a positive number x (or a negative number to quit): ");
            double x = in.nextDouble();

            // If result is positive, continue
            if (x >= 0) {
                out.print("Enter an integer k (k >= 2) for the k-th root: ");
                int k = in.nextInteger();

                double result = kthRoot(x, k, epsilon);
                out.println("The " + k + "-th root of " + x + " is " + result + ".");

            } else {
                continuedLoop = false; // Update the loop to stop
            }
        }

        // Close input and output streams
        in.close();
        out.close();
    }
}
