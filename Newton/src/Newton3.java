import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change it so the main program prompts the user to input the value of ε
 * (rather than assuming it is 0.0001), just once as the program begins, and so
 * this value is also passed to sqrt.
 *
 * @author Rachel Thoi
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
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
        String answer = "y";

        // Prompt the user for epsilon value
        out.print("Enter an " + "epsilon" + " value: ");
        double epsilon = in.nextDouble();

        // Prompt the user for input value until user ends program
        while (answer.equals("y")) {
            out.print("Enter a positive number to calculate the square root: ");
            double input = in.nextDouble();
            out.println("The results: " + sqrt(input, epsilon));
            out.print("Would you like to input another value (y/n): ");
            answer = in.nextLine();
        }

        // Close input and output streams
        in.close();
        out.close();
    }

}
