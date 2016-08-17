package pl.karol202.clickerbot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements ActionListener
{
	private JButton buttonBot;
	private JPanel root;
	
	private boolean enabled;
	
	public MainForm()
	{
		super("ClickerBot");
		setContentPane(root);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 200);
		setResizable(false);
		setVisible(true);
		
		buttonBot.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(enabled)
		{
			enabled = false;
			buttonBot.setText("Włącz bota");
		}
		else
		{
			enabled = true;
			buttonBot.setText("Wyłącz bota");
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(MainForm::new);
	}
}
