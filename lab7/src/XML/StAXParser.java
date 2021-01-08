package XML;

import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import controller.Controller;
import exception.ParserException;
import model.Appliances;
import model.factory.BakeFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.ConditionerFactory;
import model.factory.HairdryerFactory;
import model.factory.DishwasherFactory;
import model.factory.VacuumCleanerFactory;
import model.factory.VacuumCleanerFactory;

/**
 * StAX parser class
 * @author Andrei Grishkin
 * @version 1.0
 */
public class StAXParser implements AppliancesParser {
	
	/**
     * Parse XML file to appliances list using StAX parser
     * @param fileName name of the file that contains collective stored in XML format
     * @return parsed collective object
     * @throws Exception if some error occurred while parsing XML file
     * */
	@Override
	public ArrayList<Appliances> parse(String fileName) throws Exception {
		Controller.logger.info("Starting StAX parsing");
		ArrayList<Appliances> appliances = new ArrayList<>();
        Factory df = new DishwasherFactory();
        Factory ff = new FridgeFactory();
        Factory irf= new BakeFactory();
        Factory pf = new HairdryerFactory();
        Factory sf = new ConditionerFactory();
        Factory vcf = new VacuumCleanerFactory();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(fileName));
        } catch (XMLStreamException e) {
            throw new ParserException("Configuration StAX parser error", e);
        }
        String currentElement = "";
        boolean parsed;
        parsed = true;
        String name = "";
        double powerWt = 0.0;
        boolean socket = false;
        int operatingMode = 0;
        double capacityL = 0.0;
        int numberOfDrills = 0;
        double diskRotationSpeed = 0.0;
        double volumeSuction = 0.0;


        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParserException("Fail to get eventReader", e);
            }
            String qName = "";

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    qName = startElement.getName().getLocalPart();
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
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String value = event.asCharacters().getData();
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
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    qName = endElement.getName().getLocalPart();
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
                    break;
            }
        }

        Controller.logger.info("Finish StAX parsing");
        return appliances;
	}

}
