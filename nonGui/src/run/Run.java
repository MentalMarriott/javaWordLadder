package run;

import java.io.FileNotFoundException;
import userInterface.UserInterface;

/**
 * This class run the main method which will display the option to find shortest path 
 * between two words or a list of words with start word and number of different words
 * to generate. These are known as word ladders where only one letter can change 
 * between words.
 * @author Christopher Marriott
 *
 */
public class Run
{

	/**
	 * This is the main method which starts the program and brings up the user option menu
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		UserInterface gui = new UserInterface();
		gui.optionSelect();
	}

}
