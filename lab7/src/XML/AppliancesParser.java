package XML;

import java.util.ArrayList;

import model.Appliances;

/**
 * Interface, provide parse function
 * @author Andrei Grishkin
 * @version 1.0
 */
public interface AppliancesParser {
	 ArrayList<Appliances> parse(String fileName) throws Exception;
}
