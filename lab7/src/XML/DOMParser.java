package XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controller.Controller;
import exception.ParserException;
import model.Appliances;
import model.factory.Factory;
import model.factory.BakeFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.ConditionerFactory;
import model.factory.HairdryerFactory;
import model.factory.DishwasherFactory;
import model.factory.VacuumCleanerFactory;

/**
 * DOM parser class
 * @author Andrei Grishkin
 * @version 1.0
 */
public class DOMParser implements AppliancesParser {
	
	/**
     * Parse XML file to appliances list using DOM parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
	@Override
	public ArrayList<Appliances> parse(String fileName) throws Exception {
		Controller.logger.info("Starting DOM parsing");
		ArrayList<Appliances> appliances = new ArrayList<>();
		Factory df = new DishwasherFactory();
		Factory ff = new FridgeFactory();
		Factory irf= new BakeFactory();
		Factory pf = new HairdryerFactory();
		Factory sf = new ConditionerFactory();
		Factory vcf = new VacuumCleanerFactory();

        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
        } catch (IOException e) {
            throw new ParserException("Configuration DOM parser error", e);
        }
        doc.getDocumentElement().normalize();


        NodeList conditioner = doc.getElementsByTagName("conditioner");
        NodeList fridge = doc.getElementsByTagName("fridge");
        NodeList bake = doc.getElementsByTagName("bake");
        NodeList dishwasher = doc.getElementsByTagName("dishwasher");
        NodeList hairdryer = doc.getElementsByTagName("hairdryer");
        NodeList vacumcleaner = doc.getElementsByTagName("vacuumcleaner");


        for (int i = 0; i < conditioner.getLength(); ++i) {
            Node node = conditioner.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(df.createAppliances(name, powerWt, socket));
            }
        }

        for (int i = 0; i < fridge.getLength(); ++i) {
            Node node = fridge.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(ff.createAppliances(name, powerWt, socket));
            }
        }

        for (int i = 0; i < bake.getLength(); ++i) {
            Node node = bake.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(irf.createAppliances(name, powerWt, socket));
            }
        }

        for (int i = 0; i < dishwasher.getLength(); ++i) {
            Node node = dishwasher.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(pf.createAppliances(name, powerWt, socket));
            }
        }

        for (int i = 0; i < hairdryer.getLength(); ++i) {
            Node node = hairdryer.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(sf.createAppliances(name, powerWt, socket));
            }
        }
        
        for (int i = 0; i < vacumcleaner.getLength(); ++i) {
            Node node = vacumcleaner.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                double powerWt = Double.parseDouble(element.getElementsByTagName("powerWt").item(0).getTextContent());
                boolean socket = Boolean.parseBoolean(element.getElementsByTagName("socket").item(0).getTextContent());
                appliances.add(vcf.createAppliances(name, powerWt, socket));
            }
        }

        Controller.logger.info("Finish DOM parsing");
        return appliances;
	}

}
