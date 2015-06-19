package dataProcess;

import java.util.ArrayList;

/**
 * This sets and gets the parent of a word, current word and list
 * of all words one letter off
 * @author marriott
 *
 */
public class WordCost 
{
	private String parent, currentWord;
	private ArrayList<String> word;
	
	public WordCost()
	{
		
	}

	/**
	 * Gets parent word
	 * @return
	 */
	public String getParent() 
	{
		return parent;
	}

	/**
	 * Sets parent word
	 * @param parent
	 */
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	/**
	 * Gets an array of all words one letter different to current word
	 * @return
	 */
	public ArrayList<String> getWord() 
	{
		return word;
	}

	/**
	 * Sets an array of words one letter different to current word
	 * @param word
	 */
	public void setWord(ArrayList<String> word) 
	{
		this.word = word;
	}

	/**
	 * Sets current word
	 * @return
	 */
	public String getCurrentWord() 
	{
		return currentWord;
	}

	/**
	 * Gets current word
	 * @param currentWord
	 */
	public void setCurrentWord(String currentWord)
	{
		this.currentWord = currentWord;
	}
}
