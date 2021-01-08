package parser;

import model.text_unit.code.CodeBlock;
import model.text_unit.text.TextUnit;
import model.text_unit.text.part.Paragraph;
import model.text_unit.text.part.Sentence;
import model.text_unit.text.part.Text;
import model.exception.InvalidParsingException;
import java.util.ArrayList;

/**
 * paragraph text splitter parser
 *
 * @author Grishkin Andrei
 * @version 1.1
 */
public class TextParser {

    /**
     * next splitter
     */
    private ParagraphTextParser nextSplitter;


    public TextParser() {
        nextSplitter = new ParagraphTextParser();
    }

    /**
     * split text codeBlock and paragraph
     * @param textString text
     * @return return Text object
     */
    public Text splitText(String textString) throws InvalidParsingException {
        Text text = new Text();
        ArrayList<TextUnit> result;
        
        result = nextSplitter.split(trim(textString));

        text.setText(result);
        return text;
    }

    /**
     * replace tabs
     * @param text text
     * @return replaced text
     */
    protected String trim(String text){
        text = text.trim();
        text = text.replaceAll( "[\t ]+", " ");
        return text;
    }

}
