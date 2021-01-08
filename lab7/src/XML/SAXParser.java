package XML;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controller.Controller;
import exception.ParserException;
import model.Appliances;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.BakeFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.ConditionerFactory;
import model.factory.HairdryerFactory;
import model.factory.DishwasherFactory;
import model.factory.VacuumCleanerFactory;
import model.factory.VacuumCleanerFactory;

/**
 * SAX parser class
 * @author Andrei Grishkin
 * @version 1.0
 */
public class SAXParser implements AppliancesParser{
	
	/**
     * Parse XML file to appliances list using SAXP parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
	@Override
	public ArrayList<Appliances> parse(String fileName) throws Exception {
		Controller.logger.info("Starting SAX parsing");
        ArrayList<Appliances> appliances;

        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(inputFile, handler);
            appliances = handler.getAppliances();
        } catch (SAXException e) {
            throw new ParserException("Configuration SAX parser error", e);
        }

        Controller.logger.info("Finish SAX parsing");
        return appliances;
	}
	
	/**
     * Tags handler
     */
    private class Handler extends DefaultHandler {

        Factory df = new DishwasherFactory();
        Factory ff = new FridgeFactory();
        Factory irf= new BakeFactory();
        Factory pf = new HairdryerFactory();
        Factory sf = new ConditionerFactory();
        Factory vcf = new VacuumCleanerFactory();

        private ArrayList<Appliances> appliances = new ArrayList<>();

        private boolean parsed = true;
        private String currentElement;

        private String name;
        private double powerWt;
        private boolean socket;
        private int operatingMode;
        private double capacityL;
        private int numberOfDrills;
        private double diskRotationSpeed;
        private double volumeSuction;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
            if (qName.equals("name") ||
                    qName.equals("powerWt") ||
                    qName.equals("socket") ||
                    qName.equals("operatingMode") ||
                    qName.equals("capacityL") ||
                    qName.equals("numberOfDrills") ||
                    qName.equals("diskRotationSpeed") ||
                    qName.equals("volumeSuction")) {
                parsed = false;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "drill":
                	appliances.add(df.createAppliances(name, powerWt, socket));
                    break;
                case "fridge":
                	appliances.add(ff.createAppliances(name, powerWt, socket));
                    break;
                case "iron":
                	appliances.add(irf.createAppliances(name, powerWt, socket));
                    break;
                case "perfarator":
                	appliances.add(pf.createAppliances(name, powerWt, socket));
                    break;
                case "screwdriver":
                	appliances.add(sf.createAppliances(name, powerWt, socket));
                    break;
                case "vacuumcleaner":
                	appliances.add(vcf.createAppliances(name, powerWt, socket));
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length);
            if (!parsed) {
                switch (currentElement) {
                    case "name":
                        name = value;
                        break;
                    case "powerWt":
                        powerWt = Double.parseDouble(value);
                        break;
                    case "socket":
                    	socket = Boolean.parseBoolean(value);
                        break;
                    case "operatingMode":
                    	operatingMode = Integer.parseInt(value);
                        break;
                    case "capacityL":
                        capacityL = Double.parseDouble(value);
                        break;
                    case "numberOfDrills":
                        numberOfDrills = Integer.parseInt(value);
                        break;
                    case "diskRotationSpeed":
                    	diskRotationSpeed = Double.parseDouble(value);
                        break;
                    case "volumeSuction":
                    	volumeSuction = Double.parseDouble(value);
                        break;
                }

                parsed = true;
            }


        }


        public ArrayList<Appliances> getAppliances() {
            return appliances;
        }
    }
}
