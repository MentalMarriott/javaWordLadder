package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow implements ActionListener  
{

	private JPanel cardPanel;
	
	private JPanel pathPanel = new JPanel();
	private JPanel definedSizeLadder = new JPanel();
	
	private JButton findPath = new JButton("Find Path between two words");
	private JButton createLadder = new JButton("Create a word ladder of defined size");
	
	public JPanel createCardHolderPanel()
	{
		cardPanel = new JPanel(new CardLayout());
		//cardPanel.setBorder(BorderFactory.createTitledBorder("Card holder panel"));
		
		cardPanel.add(findPath, "Find path between two words");
		findPath.setBackground(new Color(18));
		cardPanel.add(createLadder, "Create ladder of user defined size");
				
		return cardPanel;
	}
	
	public JPanel setMainPanel()
	{
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
		
		mainPanel.add(findPath);
		//findPath.setBounds(100, 100, 100, 100);
		//findPath.setSize(50, 50);
		findPath.addActionListener(this);
		
		mainPanel.add(createLadder);
		//createLadder.setBounds(200, 200, 100, 100);
		createLadder.addActionListener(this);
		
		return mainPanel;
	}
	
	
	public void setFindPathPanel()
	{

	}
	
	
	public void setCreateLadderPanel()
	{
		
	}
	
	
	public JPanel createContentPane() 
	{
        JPanel mainPanel = new JPanel(new BorderLayout()); 
        
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main Content Pane"));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.add(setMainPanel(), BorderLayout.WEST);
        mainPanel.add(createCardHolderPanel(), BorderLayout.CENTER);
        
        return mainPanel;
    }

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This is the test main method to check gui is working
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Message here");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow mainWin = new MainWindow();
		frame.add(mainWin.createContentPane());
		frame.pack();
		frame.setVisible(true);
		//mainWin.setVisible(true);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////





	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
