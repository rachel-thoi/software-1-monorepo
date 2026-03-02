import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Rachel Thoi
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD.
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven.
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod.
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Test of isWitness.
     */

    @Test
    public void testIsWitnessToCompositeness_21_4() {
        NaturalNumber n = new NaturalNumber2(21);
        NaturalNumber p = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isWitnessToCompositeness(p, n);
        assertTrue(result);
    }

    @Test
    public void testIsWitnessToCompositeness_23_5() {
        NaturalNumber n = new NaturalNumber2(23);
        NaturalNumber p = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isWitnessToCompositeness(p, n);
        assertFalse(result);
    }

    /*
     * Test of isPrime1.
     */

    @Test
    public void testIsPrime1_45() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isPrime1(n);
        assertTrue(result);
    }

    @Test
    public void testIsPrime1_80() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isPrime1(n);
        assertTrue(result);
    }

    /*
     * Test of isPrime2.
     */

    @Test
    public void testIsPrime2_34893() {
        NaturalNumber n = new NaturalNumber2(34893);
        boolean result = CryptoUtilities.isPrime2(n);
        assertFalse(result);
    }

    @Test
    public void testIsPrime2_42423() {
        NaturalNumber n = new NaturalNumber2(42423);
        boolean result = CryptoUtilities.isPrime2(n);
        assertFalse(result);
    }

    /*
     * Test of GenerateNextLikelyPrime.
     */

    @Test
    public void testGenerateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2("4");
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber w = new NaturalNumber2("5");
        assertEquals(n, w);
    }

    @Test
    public void testGenerateNextLikelyPrime_6() {
        NaturalNumber n = new NaturalNumber2("6");
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber w = new NaturalNumber2("7");
        assertEquals(n, w);
    }
}
