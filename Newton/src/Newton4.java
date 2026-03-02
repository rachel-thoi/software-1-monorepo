import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change it so the main program does not ask the user whether they wish to
 * calculate a square root, but instead simply asks for a new value of x each
 * time and interprets a negative value as an indication that it's time to quit.
 *
 * @author Rachel Thoi
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param e
     *            epsilon for determining relative error
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {
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
        boolean result = true;

        // Prompt the user for epsilon value
        out.print("Enter an " + "epsilon" + " value: ");
        double epsilon = in.nextDouble();

        // Prompt the user for input value until user ends program
        while (result) {
            out.print("Enter a positive number to calculate the square root: ");
            double input = in.nextDouble();

            // If result is positive, continue
            if (input >= 0) {
                double response = sqrt(input, epsilon);
                out.println("The results: " + response);
                result = true;

                // Else, end the program
            } else {
                result = false;
            }
        }

        // Close input and output streams
        in.close();
        out.close();
    }

}
