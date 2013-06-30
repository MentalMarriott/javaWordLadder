package model;

/**
 * @author Christopher Marriott
 *
 * This is the word object for creating a word ladder of user 
 * defined length. It will store the current word parent and weight
 */
public class WordWeight 
{
	private int weight;
	private String parent;
	
	/**
	 * Sets weight and parent
	 * @param parent
	 * @param weight
	 */
	public WordWeight(String parent, int weight)
	{
		this.weight = weight;
		this.parent = parent;
	}

    /**
     * Returns the weight of the parent word.
     * @return weight
     */
	public int getWeight() 
	{
		return weight;
	}

    /**
     * This returns the parent of the word associated with this
     * word object.
     */
	public String getParent() 
	{
		return parent;
	}
	
	
	
}
