package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;

/**
 * <h1>JavaTokenType</h1>
 *
 * <p>Java token types.</p>
 *
 * <p>Copyright (c) 2017 by Nosa Edogun, Ann Le, Adam Homann, Garrick Kwan</p>
 * <p>For CS 153 Assignment 2</p>
 */
public enum JavaTokenType implements TokenType
{
    // Reserved words.
    	ABSTRACT, ASSERT, BREAK, CASE, CATCH, CLASS, CONST, CONTINUE, DEFAULT, 
    	DO, ELSE, ENUM, EXTENDS, FALSE, FINAL, FINALLY, FOR, GOTO, IF,
    IMPLEMENTS, IMPORT, INSTANCEOF, INTERFACE, NATIVE, NEW, NULL, PACKAGE, 
    PRIVATE, PROTECTED, PUBLIC, RETURN, SHORT, STATIC, STRICTFP, SUPER,
    SWITCH, SYNCHRONIZED, THIS, THROW, THROWS, TRANSIENT, TRUE, TRY, VOID,
    VOLATILE, WHILE,

    // Special symbols, in no particular order
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), MODULUS("%"), INCREMENT("++"),
    DECREMENT("--"), EQUALS("="), BITWISE_AND("&"), BITWISE_OR("|"), BITWISE_XOR("^"),
    BITWISE_COMPLIMENT("~"), LEFT_SHIFT("<<"), RIGHT_SHIFT(">>"), ZERO_FILL_RIGHT_SHIFT(">>>"),
    DOT("."), COMMA(","), SEMICOLON(";"), COLON(":"), QUOTE("\""), CHAR_QUOTE("'"),
    EQUALS_EQUALS("=="), LOGICAL_NOT("!"), NOT_EQUALS("!="), LESS_THAN("<"), LESS_EQUALS("<="),
    GREATER_EQUALS(">="), GREATER_THAN(">"), AND("&&"), OR("||"), LEFT_PAREN("("), RIGHT_PAREN(")"),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"),
    ADD_ASSIGN("+="), SUBTRACT_ASSIGN("-="), MULTIPLY_ASSIGN("*="), DIVIDE_ASSIGN("/="),
    MODULOUS_ASSIGN("%="), LSHIFT_EQUALS("<<="), RSHIFT_EQUALS(">>="),
    BITWISE_AND_ASSIGN("&="), BITWISE_EXCLUSIVE_OR_ASSIGN("^="), BITWISE_INCLUSIVE_OR_ASSIGN("|="),
    TAB("\t"), NEWLINE(""), QUESTION("?"),
    
    IDENTIFIER, INTEGER, REAL, STRING, CHAR,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX  = WHILE.ordinal();

    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int LAST_SPECIAL_INDEX  = QUESTION.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Pascal reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Pascal special symbols.  Each special symbol's text
    // is the key to its Pascal token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
        new Hashtable<String, JavaTokenType>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}
