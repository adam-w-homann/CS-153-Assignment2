package wci.frontend.java.tokens;

import wci.frontend.Source;
import wci.frontend.java.JavaToken;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>PascalStringToken</h1>
 *
 * <p> Pascal string tokens.</p>
 *
 * <p>Copyright (c) 2017 by Nosa Edogun, Ann Le, Adam Homann, Garrick Kwan</p>
 * <p>For CS 153 Assignment 2</p>
 */
public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\"');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }

            if ((currentChar != '\"') && (currentChar != EOF) && (currentChar != '\\')) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();  // consume character
            }

            if (currentChar == '\\') {
            		textBuffer.append(currentChar);
            		currentChar = nextChar();
            		textBuffer.append(currentChar);
            		switch (currentChar) {
        			case 't': 
        				valueBuffer.append("\t");
        				break;
        			case 'b': 
        				valueBuffer.append("\b");
        				break;
        			case 'n': 
        				valueBuffer.append("\n");
        				break;
        			case 'r': 
        				valueBuffer.append("\r");
        				break;
        			case 'f': 
        				valueBuffer.append("\f");
        				break;
        			case '\'': 
        				valueBuffer.append("\'");
        				break;
        			case '\"': 
        				valueBuffer.append("\"");
        				break;
        			case '\\': 
        				valueBuffer.append("\\");
        				break;
        			}
            		currentChar = nextChar();
            }
            
        } while ((currentChar != '\"') && (currentChar != EOF));

        if (currentChar == '\"') {
            nextChar();  // consume final quote
            textBuffer.append('\"');

            type = STRING;
            value = valueBuffer.toString();
//            System.out.println("Ok here is the currentChar()");
//            System.out.println(currentChar);
//            System.out.println("Ok here is the peekChar()");
//            System.out.println(peekChar());
        }
        else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        text = textBuffer.toString();
    }
}