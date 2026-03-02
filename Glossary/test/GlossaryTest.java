import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
// import components.simpleReader.SimpleReader;
// import components.simpleReader.SimpleReader1L;

public class GlossaryTest {

//    @Test
//    public void testGetOneTerm() {
//        // Create a SimpleReader from a string
//        SimpleReader in = new SimpleReader1L("term1|term2");
//        String result = Glossary.getOneTerm(in);
//        in.close();
//        // Verify the result
//        assertEquals("term1|term2", result);
//    }

    @Test
    public void testAddDataToMap() {
        Map<String, String> map = new Map1L<>();
        Glossary.addDataToMap("term1|definition1", map);
        assertTrue(map.hasKey("term1"));
        assertEquals("definition1", map.value("term1"));
    }

    @Test
    public void testKeysInQueue() {
        Map<String, String> map = new Map1L<>();
        map.add("term1", "definition1");
        map.add("term2", "definition2");
        Queue<String> queue = Glossary.keysInQueue(map);
        assertTrue(queue.length() == 2);
        assertTrue(queue.front().equals("term1") || queue.front().equals("term2"));
    }

    @Test
    public void testReplaceTheTerm() {
        Map<String, String> map = new Map1L<>();
        map.add("term1", "This is a definition with term2.");
        Queue<String> queue = new Queue1L<>();
        queue.enqueue("term2");
        Glossary.replaceTheTerm(map, queue);
        String modifiedValue = map.value("term1");
        assertTrue(modifiedValue.contains("<a href=\"term2.html\">term2</a>"));
    }

    @Test
    public void testGenerateElements() {
        Set<Character> set = new Set1L<>();
        Glossary.generateElements(" ,.-", set);
        assertTrue(set.contains(' '));
        assertTrue(set.contains(','));
        assertTrue(set.contains('.'));
        assertTrue(set.contains('-'));
    }

    @Test
    public void testNextWordOrSeparator() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        String text = "Hello, world.";
        String word = Glossary.nextWordOrSeparator(text, 0, separators);
        assertEquals("Hello", word);
        final int five = 5;
        String separator = Glossary.nextWordOrSeparator(text, five, separators);
        assertEquals(", ", separator);
    }
}
