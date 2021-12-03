package src;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test {

    public static void main(String[] args) {

        String fileName = null;
        switch (args.length) { // check if filename passed in
        case 1:
            fileName = "src/xmlFiles/" + args[0]; // open file
            break;
        default:
            System.out.println("java Test <xmlfilename>"); // did not pass filename
        return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); // Create a saxParserFactory, that will allow use to create a parser

        // filling in what needs to be changed between the open and closed braces.
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DisplayXMLHandler handler = new DisplayXMLHandler();
            saxParser.parse(new File(fileName), handler); // This will parse the xml file given by fileName
            ArrayList<Displayable> displayables = handler.getDisplayables();
        // print everything
            for (Displayable displayable : displayables) {
                System.out.println(displayable);
            }


            System.out.println(handler.toString());


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}