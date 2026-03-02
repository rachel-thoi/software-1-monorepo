import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Rachel Thoi
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        // Iterate over all children of the XMLTree
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            XMLTree child = xml.child(i);
            if (child.label().equals(tag)) {
                return i; // Return the index of the first child matching the tag
            }
        }
        return -1; // Return -1 if the tag is not found
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        // Open I/O streams
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Input the source URL.
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        // Read XML input and initialize XMLTree. If input is not legal XML,
        // this statement will fail
        XMLTree xml = new XMLTree1(url);

        // Extract <channel> element
        XMLTree channel = xml.child(0);

        // Output title, link, and description of the RSS feed.
        int titleIndex = getChildElement(channel, "title");
        int linkIndex = getChildElement(channel, "link");
        int descriptionIndex = getChildElement(channel, "description");

        if (titleIndex != -1) {
            XMLTree titleElement = channel.child(titleIndex);
            if (titleElement.numberOfChildren() > 0) {
                String title = titleElement.child(0).label();
                out.println("Title: " + title);
            } else {
                out.println("Title not available");
            }
        }

        if (linkIndex != -1) {
            XMLTree linkElement = channel.child(linkIndex);
            if (linkElement.numberOfChildren() > 0) {
                String link = linkElement.child(0).label();
                out.println("Link: " + link);
            } else {
                out.println("Link not available");
            }
        }

        if (descriptionIndex != -1) {
            XMLTree descriptionElement = channel.child(descriptionIndex);
            if (descriptionElement.numberOfChildren() > 0) {
                String description = descriptionElement.child(0).label();
                out.println("Description: " + description);
            } else {
                out.println("Description not available");
            }
        }

        // Close I/O streams.
        in.close();
        out.close();
    }
}
