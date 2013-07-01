package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Christopher Marriott
 *
 * This will take a start word and a ladder size and generate a
 * word ladder of appropriate size. If it can not create a ladder
 * without repeating words then it will print an appropriate message.
 */
public class CreateLadder 
{
	private HashMap<String, WordWeight> wordWeight = new HashMap<String, WordWeight>();
	private ArrayList<String> allWords = new ArrayList<String>();
	private ArrayList<String> queue = new ArrayList<String>();
	private ArrayList<String> finalLadder = new ArrayList<String>();
	
	private String startWord, endWord;
	private int ladderSize, currentWeight;

	/**
	 * Sets the start word and ladder size
	 * @param startWord
	 * @param ladderSize
	 */
	public CreateLadder(String startWord, int ladderSize)
	{
		this.startWord = startWord;
		this.ladderSize = ladderSize;
		
		fileToRead();
		this.queue.add(startWord);
	}
	
	
	/**
	 * Calculates length of start word and selects the file to read in
	 * containing words of equal length.
	 * @param startWord
	 */
	public void fileToRead()
	{
		int lengthOfStartWord = this.startWord.length();
		
		if(lengthOfStartWord >=3 && lengthOfStartWord <=15)
		{
			this.allWords.remove(startWord);
			String fileName = "dict" + lengthOfStartWord + ".txt";
			FileIn fileIn = new FileIn(fileName);
			allWords = fileIn.readFile();
		}else{
			System.out.println("Error with word or length");
		}
	
	}

	
	public void createWeights()
	{
		WordWeight wordWeight = new WordWeight(this.startWord, 0);
		this.wordWeight.put(this.startWord, wordWeight);
		
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
					if(this.currentWeight == this.ladderSize)
					{
						System.out.println("End word found!");
						this.endWord = testWord;
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
	 * of the queue and set the word parent and remove from all words to avoid
	 * repeat of the word.
	 * @param testWord
	 */
	public void checkWordExists(String testWord)
	{
		if(this.allWords.contains(testWord))
		{
			String currentWord = this.queue.get(0);
			int wordsWeight = this.wordWeight.get(currentWord).getWeight()+1;
			
			WordWeight wordWeight = new WordWeight(currentWord, wordsWeight);
			this.queue.add(testWord);
			this.wordWeight.put(testWord, wordWeight);
			
			this.currentWeight = wordsWeight;
			
			if(wordsWeight >= 3)
			{
				for(int i = 0; i < this.wordWeight.size(); i++)
				{
					WordWeight parentObj = (WordWeight)this.wordWeight.values().toArray()[i];
					String wordToRemove = parentObj.getParent();
					int parentWeight = parentObj.getWeight();
					
					if(parentWeight == wordsWeight-2 && wordsWeight-2 <= 0)
					{
						this.allWords.remove(wordToRemove);
					}
				}
			}
		}
	}
	
	
	/**
	 * This adds the series of correct words to the final ladder array
	 * list.
	 */
	public void printLadder()
	{
		String nextWord = this.endWord;
		
		finalLadder.add(this.endWord);
	//	System.out.println(this.endWord);
		
		while(!(this.wordWeight.get(nextWord).equals(nextWord)))
		{
			nextWord = this.wordWeight.get(nextWord).getParent();
			finalLadder.add(nextWord);
		}
		printAllWords(finalLadder);
	}
	
	
	/**
	 * This is meant to print out all the words added to the 
	 * array list final ladder in reverse order to look better.
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
	
}
