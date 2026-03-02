import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * tests of overlap.
     */
    @Test
    public void testOverlapLexingburg() {
        String str1 = "Lexing";
        String str2 = "ingburg";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(3, overlap);
    }

    @Test
    public void testOverlapVietnam() {
        String str1 = "Viet";
        String str2 = "etnam";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(2, overlap);
    }

    @Test
    public void testOverlapUnitedStates() {
        String str1 = "United";
        String str2 = "itedStates";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(4, overlap);
    }

    /*
     * tests of addToSetAvoidingSubstrings.
     */
    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("hola");
        strSet.add("hello");
        strSet.add("jour");
        String str = "bonjour";
        Set<String> expect = new Set1L<>();
        expect.add("hola");
        expect.add("hello");
        expect.add("bonjour");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("pizza");
        strSet.add("taco");
        strSet.add("burger");
        String str = "hamburger";
        Set<String> expect = new Set1L<>();
        expect.add("pizza");
        expect.add("taco");
        expect.add("hamburger");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> strSet = new Set1L<>();
        strSet.add("beer");
        strSet.add("vodka");
        strSet.add("alcohols");
        String str = "alcohol";
        Set<String> expect = new Set1L<>();
        expect.add("beer");
        expect.add("vodka");
        expect.add("alcohols");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    /*
     * tests of printWithLineSeparators.
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("testoutput1.txt");
        SimpleReader in = new SimpleReader1L("testoutput1.txt");
        String text = "GO~BUCKS~BEAT~MICHIGAN";
        String expect = "GO" + "\n" + "BUCKS" + "\n" + "BEAT" + "\n" + "MICHIGAN";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        String test4 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3 + "\n" + test4);
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("testoutput2.txt");
        SimpleReader in = new SimpleReader1L("testoutput2.txt");
        String text = "MICHIGAN~WILL~LOSE";
        String expect = "MICHIGAN" + "\n" + "WILL" + "\n" + "LOSE";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L("testoutput3.txt");
        SimpleReader in = new SimpleReader1L("testoutput3.txt");
        String text = "OSU~WILL~SLAYYY";
        String expect = "OSU" + "\n" + "WILL" + "\n" + "SLAYYY";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    /*
     * tests of assemble
     */
    @Test
    public void testAssemble1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");
        strSet.add("cde");
        strSet.add("efg");
        Set<String> expect = new Set1L<>();
        expect.add("abcdefg");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("hij");
        strSet.add("jkl");
        strSet.add("lmn");
        Set<String> expect = new Set1L<>();
        expect.add("hijklmn");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble3() {
        Set<String> strSet = new Set1L<>();
        strSet.add("opq");
        strSet.add("qrs");
        strSet.add("stu");
        Set<String> expect = new Set1L<>();
        expect.add("opqrstu");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

}
