package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Christopher Marriott
 * This class will take a filename and read it line by line
 * and store each word in an array list of string top be manipulated
 * later.
 */
public class FileIn 
{
	private String fileName;
	private ArrayList<String> words= new ArrayList<String>(); 
	
	/**
	 * This sets the file name to be read by the readFile() 
	 * function.
	 * @param fileName
	 */
	public FileIn(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * This will read the this.fileName line by line and store in the arraylist 
	 * words. It then returns this array.
	 * @return words
	 */
	public ArrayList<String> readFile()
	{
		
		BufferedReader br = null;
		
		try
		{
			String line;
			
			br = new BufferedReader(new FileReader(this.fileName));
			
			while((line = br.readLine()) != null)
			{
				words.add(line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return words;
	}
	
	/**
	 * This prints the contents of the arraylist words to check
	 * if file is read in correctly.
	 */
	public void printArray()
	{
		for(String word : words)
		{
			System.out.println(word);
		}
	}
	
	
	
}
