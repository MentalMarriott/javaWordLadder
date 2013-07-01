package testModel;

import static org.junit.Assert.*;

import model.CreateLadder;

import org.junit.Test;

public class LadderCreateTest {

	@Test
	public void test() 
	{
			CreateLadder createLadder = new CreateLadder("Bang", 5);
			createLadder.createWeights();
	}

}
