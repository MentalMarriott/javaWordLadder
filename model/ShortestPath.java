package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

import javax.swing.JPopupMenu.Separator;
import javax.swing.plaf.SliderUI;

public class ShortestPath
{
	private HashMap<String, String> wordParent = new HashMap<String, String>();
	private ArrayList<String> allWords = new ArrayList<String>();
	private ArrayList<String> queue = new ArrayList<String>();
	
	private String parent, startWord, endWord;
	
	/**
	 * This is the constructor and will read in the correct file
	 * based upon the length of the word.
	 * @param startWord
	 */
	public ShortestPath(String startWord, String endWord)
	{
		this.endWord = endWord;
		this.startWord = startWord;
		
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
		int lengthOfEndWord = this.endWord.length();
		
		if(checkLength(lengthOfEndWord, lengthOfStartWord))
		{
			String fileName = "dict" + lengthOfStartWord + ".txt";
			FileIn fileIn = new FileIn(fileName);
			allWords = fileIn.readFile();
		}
	}
	
	
	/**
	 * Verifies start and end word are same length.
	 * @param lengthOfEndWord
	 * @param lengthOfStartWord
	 */
	public boolean checkLength(int lengthOfEndWord, int lengthOfStartWord)
	{
		boolean matchLength = true;
		
		if(lengthOfEndWord != lengthOfStartWord)
		{
			System.out.println("Error: Length of start word does not match length of end word, please enter new words");
			matchLength = false;
		}
		
		return matchLength;
	}
	
	/**
	 * This takes the word at the start of the queue and letter by letter
	 * replaces with each letter of the alphabet. It then checks it against the 
	 * dictionary of words the same length to see if it is a valid word.
	 * If it is then it will set current word to be its parent and add it to the 
	 * hashmap of wordParent. 
	 */
	public void calculateShortestPath()
	{
		this.parent = null;
		this.wordParent.put(this.startWord, this.parent);
		char alphabetLetter;
		int i;
		
		while(!(this.queue.isEmpty()))
		{
			char[] splitWord = this.queue.get(0).toCharArray();
			
			for(i = 0; i < splitWord.length; i++)
			{
				String testWord;
				
				for(alphabetLetter = 'a'; alphabetLetter <= 'z'; alphabetLetter++)
				{
					char[] splitWordEdit = this.queue.get(0).toCharArray();
					splitWordEdit[i] = alphabetLetter;
					testWord = new String(splitWordEdit);
					//System.out.print(testWord + "\n");
					checkWordExists(testWord);
				}
			}
			this.queue.remove(0);
		}
		
	}
	
	/**
	 * Checks to see if valid word and if it is then it will add it to the end
	 * of the queue and set the wordparent and remove from all words to avoid
	 * repeat of the word.
	 * @param testWord
	 */
	public void checkWordExists(String testWord)
	{
		if(this.allWords.contains(testWord))
		{
			this.queue.add(testWord);
			this.wordParent.put(testWord, this.queue.get(0));
			this.allWords.remove(testWord);
			
			if(testWord.equals(this.endWord))
			{
				System.out.println("----------------------------End word found -----------------------------");
				printLadder();
				System.out.println("moosecake2");
			}
		}
	}
	
	/**
	 * This is meant to print out all the words added to the 
	 * arraylist final ladder in reverse order to look better.
	 */
	public void printLadder()
	{
		String nextWord = this.endWord;
		
		ArrayList<String> finalLadder = new ArrayList<String>();
		
		//finalLadder.add(this.endWord);
		System.out.println(this.endWord);
		
		while(!(nextWord.equalsIgnoreCase(null)))
		{
			//if(!(this.wordParent.get(nextWord).equals(null)))
			{
				System.out.println(this.wordParent.get(nextWord));
	//			finalLadder.add(this.wordParent.get(nextWord));
				nextWord = this.wordParent.get(nextWord);
			}
		}
		System.out.println("moosecake");
	}
	

	/////////////////////////////////////////// Inside tests /////////////////////////////////////////////
	/**
	 * Prints all words read in from specific file
	 */
	public void printAllWords()
	{
		for(String word : this.allWords)
		{
			System.out.println(word);
		}
	}
}
