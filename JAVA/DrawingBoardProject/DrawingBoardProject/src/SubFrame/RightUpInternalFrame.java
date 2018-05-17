package SubFrame;

import java.awt.Color;

import javax.swing.JInternalFrame;

import SubPanel.ColorPanel;

public class RightUpInternalFrame extends JInternalFrame{
	
	public RightUpInternalFrame()
	{
		super("Color Choose",true,false,false,true);
		setSize(300,300);
		setBackground(Color.DARK_GRAY);
		
		
		ColorPanel colorPanel = new ColorPanel();
		this.add(colorPanel);
		
		setVisible(true);
	}

}
