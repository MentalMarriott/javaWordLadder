package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FindPath extends JFrame
{

	public FindPath()
	{
		JPanel mainPanel = new JPanel();
		JButton findPath = new JButton("Find Path!");
		
		this.setTitle("Find Path Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(mainPanel)	;
	}
	
}
