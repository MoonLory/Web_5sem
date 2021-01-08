package view;

import controller.Controller;
import model.text_unit.text.part.Text;
import model.text_unit.text.part.Word;
import model.exception.ControllerException;
import model.exception.FileException;
import model.exception.InvalidParsingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class which run controller's demonstrate method
 *
 * @author Grishkin Andrei
 * @version 1.1
 */
public class Main {
	
	private static final Logger logger = LogManager.getLogger();
	
    /**
     * main method
     * @param args argument lines
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
        	ResourceBundle bundle = controller.loadlocale("ru");
        	logger.info(bundle.getString("FileLoading"));
            String result = controller.loadText("C:\\Users\\Lory\\Desktop\\Mylabs\\web_java\\lab3_3\\input.txt");
            System.out.println();
            System.out.println(result);
            System.out.println();
            logger.info(bundle.getString("FileLoaded"));
            System.out.println();
            
            logger.info(bundle.getString("StartTextParsing"));
            Text text = controller.parseTextStringToText();
            logger.info(bundle.getString("FinishTextParsing"));
            System.out.println(bundle.getString("FinishTextParsing"));
            System.out.println(text.toString());
            System.out.println();
            
            logger.info(bundle.getString("StartWordsSwapping"));
            System.out.println(controller.swapFirstAndLastWordInText(text).toString());
            logger.info(bundle.getString("FinishWordsSwapping"));
            System.out.println();
            
            logger.info(bundle.getString("StartWordsSorting"));
            List<Word> sorted = controller.sortWords(text);
            controller.printWords(sorted, bundle);
            logger.info(bundle.getString("FinishWordsSorting"));
        } catch (FileException | InvalidParsingException | ControllerException | ArrayIndexOutOfBoundsException ex) {
        	logger.error(ex.getMessage(), ex);
            System.out.println();
            ex.printStackTrace();
        }
    }
}
