import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        boolean isInside;
        if ((1 - xCoord) * (1 - xCoord) + (1 - yCoord) * (1 - yCoord) < 1) {
            isInside = true;
        } else {
            isInside = false;
        }
        return isInside;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        int numInside = 0;
        int i = 0;

        Random rnd = new Random1L();

        while (i < n) {
            double xCoord = 2 * rnd.nextDouble();
            double yCoord = 2 * rnd.nextDouble();
            if (pointIsInCircle(xCoord, yCoord)) {
                numInside++;
            }
            i++;
        }
        return numInside;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        // Open input and output streams
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        // Ask user for number of points to generate
        output.print("Number of points: "); // Prompt user for input
        int n = input.nextInteger(); // Read the input number of points

        // Declare counters and initialize them
        int ptsInSubinterval = 0;

        // Generate points and count how many fall inside the circle
        ptsInSubinterval = numberOfPointsInCircle(n);

        // Estimate percentage of points generated in [0.0,2.0) interval that
        // fall inside the circle
        final double oneHundred = 100.0;
        double estimate = (oneHundred * ptsInSubinterval) / n;
        output.println("Estimate of percentage: " + estimate + "%");

        // Estimate of pi
        final int twentyFive = 25;
        double piEstimate = (estimate / twentyFive);
        output.println("Estimate of pi: " + piEstimate);

        // Close input and output streams
        input.close();
        output.close();
    }
}
