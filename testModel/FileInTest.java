package testModel;

import static org.junit.Assert.*;

import model.FileIn;

import org.junit.Test;

public class FileInTest 
{

	@Test
	public void testFileIn()
	{
		FileIn fileIn = new FileIn("dict5.txt");
		fileIn.readFile();
		fileIn.printArray();
	}

}
