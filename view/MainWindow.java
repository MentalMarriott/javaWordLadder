package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow implements ActionListener 
{
	//declare and instantiate globals
	private JFrame mainFrame = new JFrame("Word Ladder Generator");
	private JPanel masterPanel, choicePanel, cardPanel, panelOne, panelTwo, resultsPanel;
	private JTextArea results = new JTextArea();
	private JTextField inputs;
	
	public static void main(String[] args)
	{
		new MainWindow();
	}

	
	
	//jbutton array
	private JButton[] ourButtons = new JButton[]
			{
				new JButton("Create ladder between two words"),
				new JButton("Create ladder of user set length"),
				new JButton("Calculate Path"),
				new JButton("Create Ladder"),
			};
	
	/**
	 * Creates the main window where you can select what operation
	 * you wish to carry out
	 */
	public MainWindow()
	{
		//master container
		masterPanel = (JPanel)mainFrame.getContentPane();
		masterPanel.setLayout(new BorderLayout());
		
		//choice panel
		choicePanel = new JPanel();
		choicePanel.setLayout(new FlowLayout());
		choicePanel.add(ourButtons[0]);
		choicePanel.add(ourButtons[1]);
		
		//jcombo box
		ourButtons[0].addActionListener(this);
		ourButtons[1].addActionListener(this);
		
		//master card panel
		cardPanel = new JPanel();
		cardPanel.setLayout(new CardLayout());
		
		//panel one
		panelOne = new JPanel();
		panelOne.add(inputs = new JTextField("Word One"));
		panelOne.add(inputs = new JTextField("Word Two"));
		inputs.addActionListener(this);
		panelOne.add(ourButtons[2]);
		ourButtons[2].addActionListener(this);
	
		//panel two
		panelTwo = new JPanel();
		panelTwo.add(inputs = new JTextField("Word One"));
		panelTwo.add(inputs = new JTextField("Ladder Size"));
		panelTwo.add(ourButtons[3]);
		ourButtons[3].addActionListener(this);
		
		//results panel
		resultsPanel = new JPanel();
		resultsPanel.add(results);
		results.setText("Results displayed here");
		results.setSize(400, 200);
		results.setColumns(40);
		results.setRows(19);
		results.setEditable(false);
		
		//add two panels to master card panel
		cardPanel.add(panelOne, ourButtons[0].getText());
		cardPanel.add(panelTwo, ourButtons[1].getText());
		
		//add combobox panel and master card panel to master container
		masterPanel.add(cardPanel, BorderLayout.NORTH);
		masterPanel.add(choicePanel, BorderLayout.CENTER);
				masterPanel.add(resultsPanel, BorderLayout.SOUTH);
		
		mainFrame.setSize(600, 400);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.pack();
		mainFrame.validate();
	}

	/**
	 * Clears results text area for results to be added into it.
	 * It also takes the result of a calculation in the form of and array 
	 * list and appends results to be displayed. 
	 * @param result
	 */
	public void setResultsPanel(ArrayList<String> result)
	{
		this.results.setText("");
		
		for(String word : result)
		{
			this.results.append(word);
			if(word != result.get(result.size()-1))
			{
				this.results.append("|");
				this.results.append("V");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
		cardLayout.show(cardPanel, (String)e.getActionCommand());
		
		if(e.getSource() == ourButtons[2]);
		{
			System.out.println("Calculated ladder 1");
		}
		
		if(e.getSource() == ourButtons[3])
		{
			System.out.println("Calculated ladder 2");
		}
	}
	
}
