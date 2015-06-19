package userInterface;

import java.util.Scanner;
import dataProcess.WordLink;


/**
 * This is where the user gets to select option of generate a ladder or find shortest path.
 * It also provides information about the program. Stores srcWord, targetWord, and length where needed.
 * @author marriott
 *
 */
public class UserInterface
{
	Scanner in = new Scanner(System.in);
	String sourceWord, targetWord;
	int steps;
	
	/**
	 * This will as the user to select option 1 for discovery and 
	 * 2 for generation. 3 for information on modes. 4 to quit.
	 */
	public void optionSelect()
	{
		System.out.println("WELCOME TO WORD LADDER MADNESS ");
		System.out.println();
		System.out.println("Enter option 1 for Discovery mode: ");
		System.out.println("Enter option 2 for Generation mode: ");
		System.out.println("Enter option 3 for information on modes: ");
		System.out.println("Enter option 4 to close program: ");
		System.out.println("Please enter a number");
		
		int choice = in.nextInt();

		switch (choice)
		{
		case 1:
			   discovery();
			   break;
		
		case 2:
			   generation();
			   break;
		
		case 3:
			   information();
			   break;
			   
		case 4:
			   System.exit(0);
			   
		default:
			   System.out.println("Invalid Choice!");
			   optionSelect();
		
		}
	}
	
	
	/**
	 * This displays the information about the two modes. To return to main menu user must
	 * enter 1.
	 */
	private void information() 
	{
		System.out.println("Generation Mode: ");
		System.out.println();
		System.out.println("This mode will ask for a start word and then a number,");
		System.out.println("it will then create a word ladder with the number of ");
		System.out.println("steps from the number given.");
		System.out.println();System.out.println();
		System.out.println("Discovery Mode: ");
		System.out.println();
		System.out.println("This will take two words, a start word and a finish word.");
		System.out.println("It will then discover the shortest word ladder between the two.");
		System.out.println();
		System.out.println();
		System.out.println("Press 1 to return to main menu.");
		
		int returnToMain = in.nextInt();
		if(returnToMain == 1)
		{
			optionSelect();
		}else{
			information();
		}
	}

	/**
	 * Saves two words given to navigate between
	 * and runs discovery function in Wordlink.
	 * Also adds all words to array when created.
	 */
	public void discovery()
	{
		System.out.println("Please enter Start word: ");
		setSourceWord(in.next());

		WordLink decode = new WordLink(getSourceWord().length());
		
		System.out.println("Please enter End word: ");
		setTargetWord(in.next());
	
		decode.discovery(getSourceWord(), getTargetWord());
	}
	
	/**
	 * Saves src word and number of steps required
	 * and runs the generation function in Wordlink.
	 * Creates list of same length words upon generation.
	 */
	public void generation()
	{
		System.out.println("Please enter Start word: ");
		setSourceWord(in.next());

		WordLink decode = new WordLink(getSourceWord().length());
	//	decode.checkWrd(getSourceWord());
		
		System.out.println("Please enter number of steps you wish to be displayed: ");
		setSteps(in.nextInt());

		//String temp = getSourceWord();
		decode.generation(getSourceWord(), getSteps());
	}
	
	
	/**
	 * returns steps
	 * @return
	 */
	public int getSteps()
	{
		return steps;
	}

	/**
	 * Sets steps
	 * @param steps
	 */
	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	/**
	 * gets source word
	 * @return
	 */
	public String getSourceWord()
	{
		return sourceWord;
	}
	
	/**
	 * Sets srcword
	 * @param sourceWord
	 */
	public void setSourceWord(String sourceWord) 
	{
		this.sourceWord = sourceWord;
	}
	
	/**
	 * gets target word
	 * @return
	 */
	public String getTargetWord()
	{
		return targetWord;
	}
	
	/**
	 * sets target word
	 * @param targetWord
	 */
	public void setTargetWord(String targetWord) 
	{
		this.targetWord = targetWord;
	}
	
}
