import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 *
 * @author Rachel Thoi
 */
public final class Glossary {
    /**
     * Default constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Reads and concatenates non-empty lines from the input until an empty line
     * or the end of the input stream is reached. Each line is concatenated with
     * a "|" separator between them.
     *
     * @param input
     *            the SimpleReader input stream from which to read lines
     * @return a concatenated string of all non-empty lines, separated by "|"
     */
    public static String getOneTerm(SimpleReader input) {
        assert input != null : "Violation of: input is not null";
        // Read the next line
        String word = input.nextLine();
        String result = "";
        // Loop until an empty line is encountered or the end of the input stream
        while (!word.equals("") && !input.atEOS()) {
            result += word;
            word = input.nextLine();
            String nextWord = word;
            if (!word.equals("")) {
                result += "|";
            }
            word = nextWord;
        }
        return result;
    }

    /**
     * Adds a term and its definition to the provided map. The term and
     * definition are extracted from the given line, which is expected to be in
     * the format "term|definition".
     *
     * @param line
     *            the input line containing the term and definition separated by
     *            "|"
     * @param map
     *            the map to which the term and definition are added
     * @requires line != null and line contains a "|"
     * @ensures map contains the term as the key and the definition as the value
     */
    public static void addDataToMap(String line, Map<String, String> map) {
        assert line != null : "Violation of: fileName is not null";
        // Extract the substring from the beginning
        String term = line.substring(0, line.indexOf("|"));
        // Extract the substring after the first occurrence
        String def = line.substring(line.indexOf("|") + 1, line.length());
        // Check if the map does not already contain the term as a key
        if (!map.hasKey(term)) {
            map.add(term, def);
        }
    }

    /**
     * Transfers all keys from the given map into a queue. The keys are enqueued
     * in no particular order. The original map remains unchanged after the
     * operation.
     *
     * @param map
     *            the map from which to extract keys
     * @return a queue containing all the keys from the map
     */
    public static Queue<String> keysInQueue(Map<String, String> map) {
        // Create a temporary map to hold the contents of the original map
        Map<String, String> tempMap = new Map1L<>();
        // Create a queue to store the keys of the map
        Queue<String> queue = new Queue1L<>();
        tempMap.transferFrom(map);
        // Loop until the temporary map is empty
        while (tempMap.size() != 0) {
            Map.Pair<String, String> pair = tempMap.removeAny();
            String key = pair.key();
            String value = pair.value();
            queue.enqueue(key);
            map.add(key, value);
        }
        return queue;
    }

    /**
     * A comparator class for comparing two strings in lexicographical order.
     */
    public static final class StringLT implements Comparator<String> {
        /**
         * Compares two strings lexicographically.
         *
         * @param s1
         *            the first string to be compared
         * @param s2
         *            the second string to be compared
         * @return -1 if s1 is lexicographically less than s2; 1 if s1 is
         *         lexicographically greater than s2; 0 if they are equal
         */
        @Override
        public int compare(String s1, String s2) {
            int result = 0;
            if (s1.compareTo(s2) < 0) {
                result = -1;
            } else if (s1.compareTo(s2) > 0) {
                result = 1;
            }
            return result;
        }
    }

    /**
     * Replaces terms in the map's values with HTML links if the terms are found
     * in the queue. Each term in the value of the map is checked and replaced
     * with an HTML link to the corresponding term page if it exists.
     *
     * @param map
     *            the map containing terms and their definitions
     * @param q
     *            the queue containing terms to be linked
     */
    public static void replaceTheTerm(Map<String, String> map, Queue<String> q) {
        // Define a string containing various separators
        final String separatorStr = " \t:;,.-";
        // Create a set to hold the separator characters
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);
        Map<String, String> tempMap = new Map1L<>();
        tempMap.transferFrom(map);

        while (tempMap.size() != 0) {
            StringBuffer result = new StringBuffer();
            Map.Pair<String, String> pair = tempMap.removeAny();

            // Extract the key and value from the removed entry
            String key = pair.key();
            String value = pair.value();

            // Initialize position for iterating through the value string
            int position = 0;
            String tokenTemp = "";

            // Loop through the value string
            while (position < value.length()) {
                String token = nextWordOrSeparator(value, position, separatorSet);
                tokenTemp = token;

                // Check if the token is not a separator
                if (!separatorSet.contains(token.charAt(0))) {
                    Queue<String> tempQ = q.newInstance();
                    tempQ.transferFrom(q);

                    // Loop until the temporary queue is empty
                    while (tempQ.length() != 0) {
                        String theKey = tempQ.dequeue();

                        // If the token matches the key, replace it with a hyperlink
                        if (token.equals(theKey)) {
                            tokenTemp = "<a href=\"" + theKey + ".html\">" + theKey
                                    + "</a>";
                        }
                        q.enqueue(theKey); // Enqueue theKey back into q
                    }
                }
                position = position + token.length();
                result.append(tokenTemp); // Use StringBuffer for concatenation
            }
            map.add(key, result.toString());
        }

    }

    /**
     *
     * @param str
     * @param strSet
     */
    public static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";
        strSet.clear();
        for (int i = 0; i < str.length(); i++) {
            if (!strSet.contains(str.charAt(i))) {
                strSet.add(str.charAt(i));
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index in {@code text}
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String result = "";
        int i = position;
        if (!separators.contains(text.charAt(position))) {
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                i++;
            }
            result = text.substring(position, i);
        } else {
            while (i < text.length() && separators.contains(text.charAt(i))) {
                i++;
            }
            result = text.substring(position, i);
        }
        return result;
    }

    /**
     * Outputs HTML files for each term in the map. For each term, an HTML file
     * is created in the specified folder, containing the term and its
     * definition. The term definitions are processed to include hyperlinks to
     * other terms where applicable.
     *
     * @param map
     *            the map containing terms and their definitions
     * @param output
     *            the SimpleWriter used for writing the HTML files
     * @param folderName
     *            the name of the folder where the HTML files will be saved
     */
    private static void outputHeader(Map<String, String> map, SimpleWriter output,
            String folderName) {
        Map<String, String> temp = new Map1L<>();
        Queue<String> keysQ = keysInQueue(map);
        replaceTheTerm(map, keysQ);
        temp.transferFrom(map);
        while (temp.size() != 0) {
            Map.Pair<String, String> pair = temp.removeAny();
            String key = pair.key();
            String value = pair.value();
            SimpleWriter out = new SimpleWriter1L(folderName + "/" + key + ".html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + key + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2><b><i><font color=\"red\">" + key + "</font></i></b></h2>");
            out.println("<blockquote>" + value + "</blockquote>");
            out.println("<hr />");
            out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    /**
     * Generates the HTML index page for the glossary. The index page lists all
     * the terms with links to their respective HTML pages.
     *
     * @param q
     *            the queue containing the terms to be included in the index
     * @param output
     *            the SimpleWriter used for writing the HTML index page
     */
    private static void processIndex(Queue<String> q, SimpleWriter output) {
        Queue<String> temp = new Queue1L<>();
        temp.transferFrom(q);
        output.println("<html>");
        output.println("<head>");
        output.println("<title>Glossary</title>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h1>Glossary</h1>");
        output.println("<hr />");
        output.println("<h3>Index</h3>");
        output.println("<ul>");
        while (temp.length() != 0) {
            String key = temp.dequeue();
            output.println("<li><a href=\"" + key + ".html\">" + key + "</a></li>");
            q.enqueue(key);
        }
        output.println("</ul>");
        output.println("</body>");
        output.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        Comparator<String> order = new StringLT();
        //Prompt the user
        out.print("Enter the name of the input file: ");
        String fileName = in.nextLine();
        SimpleReader fileIn = new SimpleReader1L(fileName);
        out.print("Enter the name of a folder that you want to "
                + "saved the outputs in it: ");
        String folderName = in.nextLine();
        // Change the format of the input file to a Map format
        Map<String, String> map = new Map1L<>();
        while (!fileIn.atEOS()) {
            String str = getOneTerm(fileIn);
            String term = str.substring(0, str.indexOf("|") + 1);
            String def = str.substring(str.indexOf("|") + 1, str.length());
            while (def.contains("|")) {
                def = def.substring(0, def.indexOf("|")) + " "
                        + def.substring(def.indexOf("|") + 1, def.length());
            }
            String result = term + def;
            addDataToMap(result, map);
        }
        // Create the index.html file, save it to the folder that user input
        // before, and sort the keys
        Queue<String> keysQ = keysInQueue(map);
        keysQ.sort(order);
        SimpleWriter outIndex = new SimpleWriter1L(folderName + "/index.html");
        processIndex(keysQ, outIndex);
        // Create the {@term}.html files and save them to the same folder as
        // index.html
        SimpleWriter output = new SimpleWriter1L();
        outputHeader(map, output, folderName);
        fileIn.close();
        out.close();
        outIndex.close();
        in.close();
    }
}
