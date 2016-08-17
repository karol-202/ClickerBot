package pl.karol202.clickerbot;

import java.awt.*;
import java.awt.event.InputEvent;

public class BotRunner implements Runnable
{
	private boolean running;
	private int delay;
	
	public BotRunner(int delay)
	{
		this.running = true;
		this.delay = delay;
	}
	
	@Override
	public void run()
	{
		try
		{
			Point point = MouseInfo.getPointerInfo().getLocation();
			int x = (int) point.getX();
			int y = (int) point.getY();
			Robot robot = new Robot();
			while(running)
			{
				robot.mouseMove(x, y);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.delay(delay);
			}
		}
		catch(AWTException e)
		{
			throw new RuntimeException("Robot error", e);
		}
	}
	
	public void stop()
	{
		running = false;
	}
	
	public boolean isRunning()
	{
		return running;
	}
}
