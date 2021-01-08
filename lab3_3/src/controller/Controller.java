package controller;

import model.text_unit.text.part.Text;
import model.text_unit.text.part.Word;
import parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import model.exception.ControllerException;
import model.exception.FileException;
import model.exception.InvalidParsingException;
import model.exception.SwapFirstAndLastWordsException;
/**
 * word text splitter parser
 *
 * @author Grishkin Andrei
 * @version 1.1
 */
public class Controller {

	private static final Logger logger = LogManager.getLogger();
	
    private String textString;

    public String getTextString() {
        return textString;
    }
    
    /**
     * load text
     * @param path path to file
     * @throws FileException if no file
     * @return text string
     *
     */
    public String loadText(String path) throws FileException {

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            textString = new String(encoded, Charset.forName("windows-1251"));
        } catch (IOException e) {
        	throw new FileException("invalidArgs", e);
        }

        return textString;
    }
    
    /**
     * load locale
     * 
     * @param loc locale to program
     * @return ResourceBundle
     */
    public ResourceBundle loadlocale(String loc) {
    	Locale locale = new Locale(loc);

    	String BUNDLE_PATH = "localization/locales/locale";
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
        return bundle;
    }
    
    /**
     * parse text string to text object
     * @return text object
     *
     */
    public Text parseTextStringToText() throws InvalidParsingException {

        TextParser textParser = new TextParser();
        Text parsedText;
        try {
            parsedText = textParser.splitText(textString);
        } catch (Exception e) {
            throw e;
        }

        return parsedText;
    }

    /**
     * swap last and first words and text
     * @param text text object
     * @return swapped text object
     *
     */
    public Text swapFirstAndLastWordInText(Text text) throws ControllerException {

        Text swapedWordsText;

        try {
            swapedWordsText = text.swapFirstAndLastWords();
        } catch (SwapFirstAndLastWordsException e) {
            throw new ControllerException("as");
        }

        return swapedWordsText;
    }

    /**
     * sort words with started vowel by consonant in text
     * @param text text object
     * @return sorted words
     *
     */
    public List<Word> sortWords(Text text) {
        ArrayList<Word> words = text.getAllTextWords();

        List<Word> sortedWords = words.stream()
                .sorted((w1, w2) -> {
                    String w1OnlyConsonants = w1.getText().toLowerCase();
                    String w2OnlyConsonants = w2.getText().toLowerCase();
                    return w1OnlyConsonants.compareTo(w2OnlyConsonants);
                })
                .collect(Collectors.toList());

        return sortedWords;
    }

    /**
     * print sorted words
     * 
     * @param sorted sorted array of words
     * @param bundle ResourceBundle
     */
    public void printWords(List<Word> sorted, ResourceBundle bundle) {
    	if (sorted.isEmpty()) {
    		System.out.println(bundle.getString("SortWordsNotFound"));
        } else {
        	String a = "1";
            for (Word word :
                    sorted) {
            	if(word.toString().toLowerCase().charAt(0)!=a.toLowerCase().charAt(0)) {
            		System.out.println("\t"+word.toString().toLowerCase());
            		a=word.toString();
            	}
            	else {
            		System.out.println(word.toString().toLowerCase());
            	}
            }
        }
    }
}
