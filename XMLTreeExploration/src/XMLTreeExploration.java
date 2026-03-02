import java.util.Iterator;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Initializes an XMLTree from a URL, outputs its textual representation, and
 * displays it in a new window.
 *
 * @author Rachel Thoi
 *
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires * [the label of the root of xt is a tag] and [xt has at least
     *           one child] and out.is_open
     *
     * @ensures * out.content = #out.content * [the label of the middle child of
     *          xt, whether the root of the middle child is a tag or text, and
     *          if it is a tag, the number of children of the middle child]
     *
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        // Check if the XMLTree node has children
        if (xt.numberOfChildren() > 0) {

            // Calculate the index of the middle child by
            // dividing the number of children by 2
            int middleIndex = xt.numberOfChildren() / 2;

            // Access the middle child using the calculated index
            XMLTree middleChild = xt.child(middleIndex);

            // Print the label of the middle child
            out.println("Middle child label: " + middleChild.label());

            // Check if the middle child is a tag
            if (middleChild.isTag()) {

                // If it is a tag, print that information
                out.println("The middle child is a tag.");

                // Print the number of children that this middle child node has
                out.println("Number of children of the middle child: "
                        + middleChild.numberOfChildren());

            } else {
                // If the middle child is not a tag, it must be text
                out.println("The middle child is text.");
            }
        }
    }

    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures * out.content = #out.content * [the name and value of each
     *          attribute of the root of xt]
     *
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {
        // Check if the root node is a tag.
        if (xt.isTag()) {

            // Retrieve an Iterable over the names of all attributes at the root
            Iterable<String> attributeNames = xt.attributeNames();

            // Obtain an iterator from the Iterable to go through each attribute name
            Iterator<String> it = attributeNames.iterator();

            // A flag to check if any attributes are found during iteration
            boolean found = false;

            // Iterate over each attribute name provided by the iterator
            while (it.hasNext()) {

                // Set found to true indicating that at least one attribute is present
                found = true;

                // Retrieve the next attribute name
                String attributeName = it.next();
                // Retrieve the corresponding attribute
                // value for the current attribute name
                String attributeValue = xt.attributeValue(attributeName);

                // Print the attribute name
                out.println(attributeName + " = " + attributeValue);
            }

            // If no attributes were found after completing the iteration
            if (!found) {
                out.println("No attributes found.");
            }
        } else {
            // If the root of the XMLTree is not a tag
            out.println("Root is not a tag.");
        }
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

        // Load XML data from a URL
        XMLTree xml = new XMLTree1("http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                + "extras/instructions/xmltree-model/columbus-weather.xml");

        // Check if the XML data is loaded successfully
        if (xml != null) {
            out.println("XML data loaded successfully.");

            // Check if the root node is a tag and print the type and label
            if (xml.isTag()) {
                out.println("The root of the XMLTree is a tag.");
                out.println("The label of the root is: " + xml.label());
            } else {
                out.println("The root of the XMLTree is text.");
                out.println("The text of the root is: " + xml.label());
            }

            // Initialize the results XMLTree
            XMLTree results = xml.child(0); // Assuming 'results' is the first child
            // Initialize the channel XMLTree
            XMLTree channel = results.child(0); // Assuming 'channel' is the first child
            out.println("Number of children in the channel node: "
                    + channel.numberOfChildren());

            // Initialize the title XMLTree
            XMLTree title = channel.child(1); // Assuming 'title' is the second child
            // Initialize the titleText XMLTree
            // Assuming the text node is the first child in 'title'
            XMLTree titleText = title.child(0);

            out.println("Label of the titleText: " + titleText.label());

            // Direct single-statement access for the title text label
            out.println("Direct label access: "
                    + xml.child(0).child(0).child(1).child(0).label());

            // Initialize the 'astronomy' XMLTree using a while loop
            XMLTree astronomy = null;
            int childIndex = 0;
            while (astronomy == null && childIndex < channel.numberOfChildren()) {
                XMLTree currentChild = channel.child(childIndex);
                if (currentChild.label().equals("yweather:astronomy")) {
                    astronomy = currentChild;
                }
                childIndex++;
            }

            if (astronomy != null) {
                // Check for 'sunset' and 'midday' attributes
                boolean hasSunset = astronomy.hasAttribute("sunset");
                boolean hasMidday = astronomy.hasAttribute("midday");
                out.println("Has 'sunset' attribute: " + hasSunset);
                out.println("Has 'midday' attribute: " + hasMidday);

                // Output the values of 'sunrise' and 'sunset' if they exist
                if (astronomy.hasAttribute("sunrise")) {
                    out.println("Sunrise time: " + astronomy.attributeValue("sunrise"));
                }
                if (hasSunset) {
                    out.println("Sunset time: " + astronomy.attributeValue("sunset"));
                }
            } else {
                out.println("The 'astronomy' node was not found.");
            }

            // Call the printMiddleNode method for the channel XMLTree
            printMiddleNode(channel, out);

            // Print the attributes of the root node
            printRootAttributes(xml, out);

            // Display the XMLTree in a new window for a visual representation
            xml.display();

            // Print the XML data again for double-checking
            // (can be commented out after verification)
            out.println(xml.toString());

            // Close input and output streams
            in.close();
            out.close();
        }
    }
}
