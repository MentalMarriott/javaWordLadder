package testModel;

import static org.junit.Assert.*;

import model.FileIn;
import model.ShortestPath;

import org.junit.Test;

public class ShortestPathFileInTest {

	@Test
	public void test()
	{
		ShortestPath shrtPath = new ShortestPath("Baker", "Flake");
		shrtPath.printAllWords();
	}

}
