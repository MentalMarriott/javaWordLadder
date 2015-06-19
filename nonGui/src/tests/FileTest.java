/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marriott
 *
 */
public class FileTest 
{

	 private BufferedReader in = null;

	@Before
    public void setup()
        throws IOException
    {
        in = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("all.txt")));
    }

    @After
    public void teardown()
        throws IOException
    {
        if (in != null)
        {
            in.close();
        }

        in = null;
    }


	/**
	 * Test method for {@link fileRead.File#getAllWords()}.
	 */
	@Test
	public final void testGetAllWords()
	{
		fail("Not yet implemented"); // TODO
	}

}
