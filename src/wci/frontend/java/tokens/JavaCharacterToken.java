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
 * <p>Copyright (c) 2017 by Nosa Edogun, Ann Le, Adam Homann</p>
 * <p>For CS 153 Assignment 2</p>
 */
public class JavaCharacterToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCharacterToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        boolean noLongerACharacter = false;
        textBuffer.append('\'');
        valueBuffer.append('\'');
        
        // empty single quotes we consume the second/final quote and give error
        // more than one character within quotes, don't consume char and guess that it is start of next token 
        if(currentChar == '\'') {
        		noLongerACharacter = true;
        		nextChar();  // consume this final quote and send error since it is blank in single quotes
        }
        if(Character.isLetter(currentChar)) {
        		textBuffer.append(currentChar);
        		valueBuffer.append(currentChar);
        		currentChar = nextChar();
        		if(currentChar == '\'') {
        			textBuffer.append(currentChar);
        			valueBuffer.append(currentChar);
        			nextChar();  // consume final single quote
        		}
        		else {
    				noLongerACharacter = true;
        		}
        }
        
        //escaped characters e.g. /t, /n, and /"
        else if(currentChar == '\\') {
        		textBuffer.append(currentChar);
        		currentChar = nextChar();  // consume the backslash
        		if(currentChar == 't' || currentChar == 'b' || currentChar == 'n' 
        				|| currentChar == 'r' || currentChar == 'f' || currentChar == '\'' 
        				|| currentChar == '\"' || currentChar == '\\') {
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
        			if(currentChar == '\'') {
            			textBuffer.append(currentChar);
            			valueBuffer.append(currentChar);
            			nextChar();  // consume final single quote
            		}
        			else {
        				noLongerACharacter = true;
                	}
        		}
        		
        		else {
				noLongerACharacter = true;
        		}
        }
        
        else {
			noLongerACharacter = true;
        }
        if(noLongerACharacter) {
			type = ERROR;
			if(currentChar != EOF) {
				value = INVALID_CHARACTER;
			}
			else {
	            value = UNEXPECTED_EOF;
			}
        }
        else {
			type = CHAR;
			value = valueBuffer.toString();
	        text = textBuffer.toString();
        }
    }
}
