package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Christopher Marriott
 *
 * This will take a start word and a ladder size and genereate a
 * word ladderof appropriate size. If it can not create a ladder
 * without repeating words then it will print an appropriate message.
 */
public class CreateLadder 
{
	private HashMap<String, WordWeight> wordWeight = new HashMap<String, WordWeight>();
	private ArrayList<String> allWords = new ArrayList<String>();
	private ArrayList<String> queue = new ArrayList<String>();
	
	private String startWord;
	private int ladderSize;

	/**
	 * Sets the startword and ladder size
	 * @param startWord
	 * @param ladderSize
	 */
	public CreateLadder(String startWord, int ladderSize)
	{
		this.startWord = startWord;
		this.ladderSize = ladderSize;
		
		fileToRead();
		this.queue.add(startWord);
		this.allWords.remove(startWord);
	}
	
	
	/**
	 * Calculates length of start word and selects the file to read in
	 * containing words of equal length.
	 * @param startWord
	 */
	public void fileToRead()
	{
		int lengthOfStartWord = this.startWord.length();
		
		if(!(this.allWords.contains(startWord)))
		{
			String fileName = "dict" + lengthOfStartWord + ".txt";
			FileIn fileIn = new FileIn(fileName);
			allWords = fileIn.readFile();
		}
	
	}
	
}
