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
	private ArrayList<String> finalLadder = new ArrayList<String>();

	
	private boolean validParameters = true;
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
			
			checkWordsExist();
		}
	}
	
	/**
	 * Checks to see if start word and end word are in the list of all words
	 * @return boolean
	 */
	public void checkWordsExist()
	{
		if(!(this.allWords.contains(startWord)) || !(this.allWords.contains(endWord)))
		{
			System.out.println("Error: One of the words could not be found, please try again");
			this.validParameters = false;
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
			this.validParameters = false;
		}
	
		return matchLength;
	}
	
	/**
	 * Returns true only if both words lengths match and they
	 * can be found in the list of all words.
	 * @return validParameters
	 */
	public boolean isValidParameters()
	{
		return validParameters;
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
		this.parent = this.startWord;
		this.wordParent.put(this.startWord, this.parent);
		char alphabetLetter;
		int i;
		
		//label used for breaking loop
		outerLoop:
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
					checkWordExists(testWord);
					if(testWord.equals(this.endWord))
					{
						System.out.println("End word found!");
						break outerLoop;
					}
				}
			}
			this.queue.remove(0);
		}
		printLadder();
		System.out.println("Done!");
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
		}
	}
	
	/**
	 * This is meant to print out all the words added to the 
	 * arraylist final ladder in reverse order to look better.
	 */
	public void printLadder()
	{
		String nextWord = this.endWord;
		
		finalLadder.add(this.endWord);
	//	System.out.println(this.endWord);
		
		while(!(this.wordParent.get(nextWord).equals(nextWord)))
		{
				//System.out.println(this.wordParent.get(nextWord));
				finalLadder.add(this.wordParent.get(nextWord));
				nextWord = this.wordParent.get(nextWord);
		}
		printAllWords(finalLadder);
	}
	

	/**
	 * This is meant to print out all the words added to the 
	 * arraylist final ladder in reverse order to look better.
	 */
	public void printAllWords(ArrayList<String> finalLadder)
	{
		int i;
		System.out.println("------------------ arraylist ladder ---------------------");
		for(i = (finalLadder.size()-1); i >= 0; i--)
		{
			System.out.println(finalLadder.get(i));
		}
	}



	public ArrayList<String> getAllWords()
	{
		return this.finalLadder;
	}


}

