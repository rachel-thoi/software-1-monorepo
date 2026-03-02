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
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
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

        //variables to hold exponents
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double d = 0.0;
        double closest = pow(w, expValues[0]) * pow(x, expValues[0])
                * pow(y, expValues[0]) * pow(z, expValues[0]);

        //algorithm for finding best group of exponents
        //to produce the closest value
        int i = 0;
        while (i < expValues.length) {
            int j = 0;
            while (j < expValues.length) {
                int k = 0;
                while (k < expValues.length) {
                    int l = 0;
                    while (l < expValues.length) {
                        // Calculate the result of the de Jager formula
                        //with the collected numbers and defined exponents
                        double result = Math.pow(w, expValues[i])
                                * Math.pow(x, expValues[j]) * Math.pow(y, expValues[k])
                                * Math.pow(z, expValues[l]);
                        if (abs(mu - result) < abs(mu - closest)) {
                            a = expValues[i];
                            b = expValues[j];
                            c = expValues[k];
                            d = expValues[l];
                            closest = result;
                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

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
