import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Change it so that it checks that the inputs provided by the user are valid,
 * that is, the input for epsilon is a positive real number and the input for x
 * is a real number. Note that you cannot assume the user will provide a number;
 * the user can type pretty much anything. So your method should read the input
 * as a String (use SimpleReader.nextLine()), then make sure that the input is a
 * real number (use FormatChecker.canParseDouble()), and finally convert the
 * string to a double (use Double.parseDouble()).
 *
 * @author Rachel Thoi
 *
 */
public final class Newton6 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton6() {
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
        // Declare your variables
        double r = x;
        final double epsilonSquare = 0.00000001;

        // Compute estimate of square root using Newton's iteration
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
        boolean continuedLoop = true;

        // Prompt the user for epsilon value and validate input
        out.print("Enter a positive epsilon value: ");
        String epsilonInput = in.nextLine();
        while (!FormatChecker.canParseDouble(epsilonInput)
                || Double.parseDouble(epsilonInput) <= 0) {
            out.print(
                    "Invalid input. Please enter a positive real number for epsilons: ");
            epsilonInput = in.nextLine();
        }
        double epsilon = Double.parseDouble(epsilonInput);

        // Prompt the user for input value until user ends program
        while (continuedLoop) {
            out.print("Enter a positive number x (or a negative number to quit): ");
            String xInput = in.nextLine();
            while (!FormatChecker.canParseDouble(xInput)) {
                out.println("Invalid input. Please enter a real number.");
            }
            double x = Double.parseDouble(xInput);

            // If x is negative, stop the loop
            if (x < 0) {
                continuedLoop = false;
            }

            out.print("Enter an integer k (k >= 2) for the k-th root: ");
            int k = in.nextInteger();

            double result = kthRoot(x, k, epsilon);
            out.println("The " + k + "-th root of " + x + " is " + result + ".");
        }

        // Close input and output streams
        in.close();
        out.close();
    }

}
