package dataProcess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


import userInterface.UserInterface;

import fileRead.*;

/**
 * This is where the algorithms for shortest path(discovery) and generation are. Each one will be
 * called depending upon what the user selects.
 * Could possibly slplit up discovery and generation into two subclasses for easier understanding.
 * @author marriott
 *
 */
public class WordLink 
{
	HashMap<String,  WordCost> wordsFromWord;

	ArrayList<String> tmp1= new ArrayList<String>();
	ArrayList<String> generated = new ArrayList<String>();
	ArrayList<String> wordTree = new ArrayList<String>();
	ArrayList<String> allWords = new ArrayList<String>();
	ArrayList<String> tmp4 = new ArrayList<String>();
	LinkedList<String> wordQueue = new LinkedList<String>();
	LinkedList<String> parentsList = new LinkedList<String>();
		
	String srcWord, targetWord, compareWords, word, parent, wordParent;
	UserInterface userInter = new UserInterface();
	int numDiffLetters, tmpLoc;
	int location =0;

	/**
	 * Runs all the decoding and validations, this will be called by the key of the
	 * word entered. Upon construction will add all words from file to allWords for comparrison
	 * @param i 
	 * @throws FileNotFoundException 
	 */
	public WordLink(int i) 
	{	
		addWords(i);
	}
	
	/**
	 * Takes start word and length and will check each word one letter off and decrease length,
	 * if length reaches 0 then completes ladder. If tmp1 is empty before length = 0 then it will check 
	 * previous word again further on.
	 */
	public void generation(String srcWord, int length)
	{
		generated.add(srcWord);
		System.out.println(srcWord);
		//processQueue(srcWord);
		checkOneLetterOff(srcWord);
		
		if(length != 0)
		{
			if(tmp4.size() > location)
			{
				if(!(tmp4.get(location).isEmpty()) && location <tmp4.size()-1)
				{
					location++;
				}
			}else{
				System.out.println("Can generate: " + generated.size() + " Links");
				System.out.println(generated);
				userInter.optionSelect();
			}
			String tempWrd = tmp4.get(location);
			length--;
			tmp4.clear();
		//	System.out.println(tempWrd);
			
			generation(tempWrd, length);
		}
			
		System.out.println("Can generate: " + generated.size() + " Links");
		System.out.println(generated);
		userInter.optionSelect();
	}

	/**
	 * Validates to see if srcword and target wrd do not mat ch if they do then ladder complete.
	 * Aslo checks to see if words are in list of words.
	 * Then initialises que with srcword(start word) and creates a que with words 1 letter off
	 * this will loop untill finds target word or there are no more words.
	 */
	public void discovery(String srcWrd, String targetWrd)
	{
		checkSrcVsDest(srcWrd, targetWrd);
		checkWrd(srcWrd);
		checkWrd(targetWrd);
		wordsFromWord = new HashMap<String, WordCost>();
		this.srcWord = srcWrd;
		this.targetWord = targetWrd;
		tmp1.add(srcWrd);
		addToQueue(tmp1);
	}
	
	/**
	 * Will add all the generated words to the end of the queue for checking.
	 * @param words
	 */
	public void addToQueue(ArrayList<String> words)
	{
		for(String wordToAdd: words)
		{
		wordQueue.add(wordToAdd);
		}
		if(wordQueue.isEmpty())
		{
			System.out.println("Can not generate ladder");
			userInter.optionSelect();
		}
		processQueue(wordQueue.poll());
	}	
	
	/**
	 * This will check to see if target word is in temporary array. If it is then it
	 * will traverse the parents till get to srcword
	 * @param arrayCheck
	 * @param queueHead
	 */
	public void checkForTarget(ArrayList<String> arrayCheck, String queueHead)
	{
		if(arrayCheck.contains(this.targetWord))
		{
			generated.add(this.targetWord);
			//generated.add(this.srcWord);
			wordParent = this.targetWord;
			//give target word a parent so can travese back up
			WordCost wordCost = new WordCost();
			wordCost.setParent(queueHead);
			wordsFromWord.put(this.targetWord, wordCost);
			while(!(wordParent.equals(this.srcWord)))
			{
				wordParent = wordsFromWord.get(wordParent).getParent();
				generated.add(wordParent);	
			}
			Collections.reverse(generated);
			System.out.println(generated);
			wordsFromWord.clear();
			userInter.optionSelect();
		}
	}
	
	/**
	 * Sets parent as null if parent is null as this will be the first word. If parent is not null then it will
	 * set parent to equal word it was generated from
	 * @param queueHead
	 * @param wordCost
	 */
	public void setParentAndCurrentWrd(String queueHead, WordCost wordCost)
	{
		if(parentsList.isEmpty())
		{
			wordCost.setCurrentWord(queueHead);
			wordCost.setParent(queueHead);
			this.parent = queueHead;
			parentsList.add(queueHead);
		}else{
			wordCost.setCurrentWord(queueHead);
			wordCost.setParent(this.parent);
			parentsList.add(queueHead);
		}
	}
	
	/**
	 * Checks first string in queue, gets all the words one letter different from allWords list.
	 * Assigns a parent and a current word to its object. Adds generated words to end of cue.
	 * @param words
	 */
	public void processQueue(String queueHead)
	{    
			//temp array to store results
			tmp4.clear();
			WordCost wordCost = new WordCost();
			validateQueueHead(queueHead);
			//goes through array of all words and checks against word from queue
			checkOneLetterOff(queueHead);
			
			tmp4.add(".");
			if(tmp4.get(0).endsWith("."))
			{
				if(wordQueue.isEmpty())
				{
					System.out.println("Can not generate ladder");
					userInter.optionSelect();	
				}else{
					if(wordQueue.size()>1)
					processQueue(wordQueue.poll());	
				}
			}
			
			setParentAndCurrentWrd(queueHead, wordCost);
			wordCost.setWord(tmp4);
			
			//setParentAndCurrentWrd(queueHead, wordCost);
			wordsFromWord.put(queueHead, wordCost);

			checkForTarget(tmp4, queueHead);
			addToQueue(tmp4);
	}
	
	/**
	 * Checks if head of queue is empty. If it is then return to the memory explaining can not generate ladder
	 * @param queueHead
	 */
	public void validateQueueHead(String queueHead)
	{
		if(queueHead.isEmpty())
		{
			System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOO, Ladder can not be made");
			userInter.optionSelect();
		}
		//looks at word after . removes full stop from wordQueue and removes first from parentlist
		if(queueHead =="." && parentsList.size()>=1 && wordQueue.size()!=0)
		{
			parentsList.remove(0);
			this.parent = parentsList.peek();
	//		System.out.println(wordQueue.peek());
			processQueue(wordQueue.poll());
		}else{
			if(queueHead.isEmpty())
			{
				System.out.println("Can not make ladder");
				userInter.optionSelect();
			}
		}
	}
	
	/**
	 * Compares head of cue to allWords and determines if word is one letter off.
	 * If it is then adds it to tmp4. Also then removes the word from allwords.
	 * @param queueHead
	 */
	public void checkOneLetterOff(String queueHead)
	{
		for(int i =0; i<allWords.size(); i++)
		{
			String currentWrd = allWords.get(i);
			
			//compares length and if greater than one skips the wordaddWords();
			if(currentWrd.length() == queueHead.length() )
			{
			//looks at length of word and compares chars
				numDiffLetters = 0;
				for(int k =0; k<currentWrd.length(); k++)
				{
					//checks if characters match
					char x = queueHead.charAt(k);
					char y = currentWrd.charAt(k);
					//System.out.print(x + " " + y + "\n");
					if(x != y && numDiffLetters <2)
					{
						numDiffLetters++;
						//System.out.println(numDiffLetters);
					}
				}
				//adds all words one letter diff to queueHead to temp array and removes them from all words to avoid going back
				if(numDiffLetters == 1)
				{
					
					tmp4.add(currentWrd);
					if( allWords.contains(currentWrd))
					{
						allWords.remove(currentWrd);
					}
				}
			}
		}
	}

	/*public void removeInvalidLength(String srcWord)
	{
		for(int i =0; i< allWords.size(); i++)
		{
			if(allWords.get(i).length() != srcWord.length())
			{
			allWords.remove(i);
			}
		}
	}*/
	
	/**
	 * This will check to see if src word is in list of words
	 */
	public void checkWrd(String Word)
	{
		if(!(allWords.contains(Word)))
		{
			System.out.println("Word not in list, returning to main menu");
			userInter.optionSelect();
		}else{
			allWords.remove(word);
		}
	}
		
	/**
	 * checks to see if number of links requested exceeds the total number of words in array.
	 * @param numberOfLinks
	 */
	public void chkSize(int numberOfLinks)
	{
		int allWordsSize = allWords.size();
		if(numberOfLinks > allWordsSize)
		{
			System.out.println("Number too big");
			userInter.optionSelect();
		}
	}
	
	/**
	 * Checks to see if src word == dest word, checks both length and whether they are the same word
	 * @return
	 */
	public void checkSrcVsDest(String srcWrd, String targetWrd)
	{
		if(srcWrd == targetWrd)
		{
			System.out.println(srcWrd + " - " + targetWrd);
			System.out.println("Word Ladder done");
			userInter.optionSelect();
		}
	}
	
	/**
	 * Sets all the words that are read in to be modified
	 * @throws FileNotFoundException 
	 */
	public void addWords(int i) 
	{
		File myFile = new File(i);
		allWords.addAll(myFile.getAllWords());
		System.out.println(allWords);
	}
	
}
