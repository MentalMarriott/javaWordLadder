package fileRead;

import java.io.*;
import java.util.ArrayList;
import userInterface.*;

/**
 * Upon construction will read in a file of same length words same as user input.
 * @author marriott
 *
 */
public class File 
{
   String list, read, currentWord;
   String words[];
   
   //stores all words
   ArrayList<String> allWords = new ArrayList<String>();
   int i =0;
   
	/**
	 * Reads in file and will store each word in a String arraylist this will then be retrived by the decoding part(word link)
	 * @param i 
	 * @throws IOException 
	 */
	public File(int i) 
	{
		  try
		    {
		    	//opens the file
		    	FileInputStream fstream = new FileInputStream("dict" + i + ".dat");
		    	// Get the object of DataInputStream
		    	DataInputStream in = new DataInputStream(fstream);
		    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	            
		    	//counts length of each word and add it as key and word as value to hashtable
		    	 while ((read = br.readLine()) != null ) 
		    	 {
		    		 if (read.isEmpty())
		   		 	 {
		    			 System.out.println();
		   		 	 }else
		   		 	 {
		   		 		 //removes whitespace  
		    		     words = read.split("\\s+");	 
		                 
		    		     //removes all punctuation of word
		    		      currentWord = words[0];//.replaceAll("[^A-Za-z]", "");
		    		     currentWord = currentWord.toLowerCase();
		    		     
		    		     //adds current word read in
		    		      //allWords.add(currentWord);
		    		      allWords.add(currentWord);
		    	         i++;	   
		   		 	 }
		         }
		    	 //closes the input stream
		    	 in.close();
		    	}catch(Exception e){//Catch exception if any
		    	  System.err.println("Error: " + e.getMessage() +"\n");	
		    	  UserInterface inter = new UserInterface();
		    	  inter.optionSelect();
		    	}	
		  }


	/**
	 * Returns array of all words
	 * @return
	 */
	public ArrayList<String> getAllWords()
	{
		return allWords;
	}
}
