package pl.karol202.clickerbot;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm extends JFrame implements ActionListener, NativeKeyListener
{
	private JButton buttonBot;
	private JPanel root;
	
	private BotRunner runner;
	
	private MainForm()
	{
		super("ClickerBot");
		setContentPane(root);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(500, 200);
		setResizable(false);
		setVisible(true);
		
		buttonBot.addActionListener(this);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
				super.windowClosed(e);
				try
				{
					GlobalScreen.unregisterNativeHook();
				}
				catch(NativeHookException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		try
		{
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(this);
		}
		catch(NativeHookException e)
		{
			throw new RuntimeException("Could not register native key listener.", e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(runner != null && runner.isRunning()) stop();
		else start();
	}
	
	private void start()
	{
		buttonBot.setText("Wyłącz bota");
		runner = new BotRunner(1);
		new Thread(runner).start();
	}
	
	private void stop()
	{
		buttonBot.setText("Włącz bota");
		runner.stop();
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		if(e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) stop();
	}
	
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) { }
	
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) { }
	
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
