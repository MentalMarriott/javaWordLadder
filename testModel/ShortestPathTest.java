package testModel;

import static org.junit.Assert.*;

import model.ShortestPath;

import org.junit.Test;

public class ShortestPathTest 
{

	@Test
	public void test()
	{
		ShortestPath shrtPath = new ShortestPath("basil", "flack");
		shrtPath.calculateShortestPath();
		//System.out.println(shrtPath.getWordParent().get("bob"));
		
	}

}
