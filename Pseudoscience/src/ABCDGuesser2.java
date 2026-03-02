import static java.lang.Math.abs;
import static java.lang.Math.pow;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Demonstrates the application of mathematical models to approximate real-world
 * constants using personalized data, also testing the limits of these
 * approximations.
 *
 * @author Rachel Thoi
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        // Prompt the user to enter a number
        //out.print("Enter a positive constant number: ");

        String input = in.nextLine(); // Read the initial user input as a string

        // Loop to check if the entered string is a valid double and positive
        while (!FormatChecker.canParseDouble(input) || Double.parseDouble(input) <= 0) {

            // Inform the user of invalid input and prompt for another attempt
            out.print("Invalid input. Please enter a positive constant number: ");

            input = in.nextLine(); // Read the next line of user input
        }

        // Once a valid and positive double is confirmed, convert the string to a double
        // and return it
        return Double.parseDouble(input); // Parse the string input to a double
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
        // Prompt the user with a specific request for a number not equal to 1.0
        // out.print("Enter a positive real number that is not 1.0: ");
        String input = in.nextLine(); // Read the initial user input as a string

        // Loop to check the entered string: it must be a valid double, must be positive,
        // and must not equal 1.0. The loop continues until all conditions are satisfied.
        while (!FormatChecker.canParseDouble(input) || Double.parseDouble(input) <= 0
                || Double.parseDouble(input) == 1.0) {

            // Provide feedback for invalid input and request new data
            out.print("Invalid input. "
                    + "Please enter a positive real number that is not 1.0: ");

            input = in.nextLine(); // Read next line of user input
        }

        // Convert the validated and appropriate string input to a double and return it
        return Double.parseDouble(input); // Parse the string input to a double
    }

    /**
     * Finds the closest exponent values for the given constants.
     *
     * @param w
     *            the constant w
     * @param x
     *            the constant x
     * @param y
     *            the constant y
     * @param z
     *            the constant z
     * @param mu
     *            the target value to approximate
     * @param expValues
     *            the array of possible exponents
     * @return an array containing the best exponents and the closest result
     */
    private static double[] findTheClosestExponent(double w, double x, double y, double z,
            double mu, double[] expValues) {
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;
        double closest = pow(w, expValues[0]) * pow(x, expValues[0])
                * pow(y, expValues[0]) * pow(z, expValues[0]);
        // nested for loop to find the exponents that approximate to the relative error
        for (int i = 0; i < expValues.length; i++) {
            for (int j = 0; j < expValues.length; j++) {
                for (int k = 0; k < expValues.length; k++) {
                    for (int l = 0; l < expValues.length; l++) {
                        double result = pow(w, expValues[i]) * pow(x, expValues[j])
                                * pow(y, expValues[k]) * pow(z, expValues[l]);
                        if (abs(mu - result) < abs(mu - closest)) {
                            a = expValues[i];
                            b = expValues[j];
                            c = expValues[k];
                            d = expValues[l];
                            closest = result;
                        }
                    }
                }
            }
        }
        return new double[] { a, b, c, d, closest };
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        // Initialize the input and output handlers
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Greet the user with a welcome message
        out.println("Welcome to the ABCD Guesser!");

        // The 17 numbers
        final double[] expValues = { -5, -4, -3, -2, -1, (-1.0 / 2.0), (-1.0 / 3.0),
                (-1.0 / 4.0), (1.0 / 4.0), (1.0 / 3.0), (1.0 / 2.0), 1, 2, 3, 4, 5 };

        // Collect four personal significant numbers from the user
        out.print("For the value u: ");
        double mu = getPositiveDouble(in, out);
        out.print("For the value w: ");
        double w = getPositiveDoubleNotOne(in, out);
        out.print("For the value x: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.print("For the value y: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.print("For the value z: ");
        double z = getPositiveDoubleNotOne(in, out);

        // Find the best exponents and the closest result
        double[] result = findTheClosestExponent(w, x, y, z, mu, expValues);

        // Define constants for the indices
        final int zero = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        // Extract exponents and closest value from the result
        double a = result[zero];
        double b = result[one];
        double c = result[two];
        double d = result[three];
        double closest = result[four];

        // Calculate the relative error in percentage
        final int oneHundred = 100;
        double error = (abs(mu - closest) / mu) * oneHundred;

        // Output the results to the user
        out.print("The values of the exponents are: ");
        out.println(a + " " + b + " " + c + " " + d);
        out.print("The approximated value is about: ");
        out.println(closest);
        out.print("The percent of error is: ");
        out.print(error, 2, false);
        out.print("%");

        // Close input and output streams
        in.close();
        out.close();
    }
}
