package wci.frontend.java;

import wci.frontend.*;

/**
 * <h1>PascalToken</h1>
 *
 * <p>Base class for Pascal token classes.</p>
 *
 * <p>Copyright (c) 2017 by Nosa Edogun, Ann Le, Adam Homann, Garrick Kwan</p>
 * <p>For CS 153 Assignment 2</p>
 */
public class JavaToken extends Token
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    protected JavaToken(Source source)
        throws Exception
    {
        super(source);
    }
}
