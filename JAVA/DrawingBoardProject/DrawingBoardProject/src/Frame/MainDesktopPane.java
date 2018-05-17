package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import SubFrame.DrawInternalFrame;
import SubFrame.RightDownInternalFrame;
import SubFrame.RightUpInternalFrame;
import information.Information;

public class MainDesktopPane extends JDesktopPane{
	
//	private DrawInternalFrame drawInternalFrame;
	private HashMap<String, DrawInternalFrame> drawInternalFrameSet;
	private RightUpInternalFrame rightUpInternalFrame;
	private RightDownInternalFrame rightDownInternalFrame;
	private ImageIcon backgrountIcon;

	private static MainDesktopPane instance;
	
	
	public static MainDesktopPane getInstance()
	{
		if(instance==null) instance = new MainDesktopPane();
		return instance;
	}
	
	
	
	public MainDesktopPane()
	{
		Information.setCurrentMainDesktopPane(this);
		this.changeCursor();
		drawInternalFrameSet= new HashMap<String, DrawInternalFrame>();

		Color background = new Color(160,198,235);
		this.setVisible(true);
		this.setBackground(background);
		this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		backgrountIcon = new ImageIcon("resource/ui_1_01.png");
		

		rightUpInternalFrame = new RightUpInternalFrame();
		rightDownInternalFrame = RightDownInternalFrame.getInstance();
		
		
		

//		this.add(drawInternalFrame);
		this.add(rightUpInternalFrame);
		this.add(rightDownInternalFrame);

	}
	
	public static void setBack(Color color){
		instance.setBackground(color);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int width = getWidth();
		
		
		int height = getHeight();
		g.drawImage(backgrountIcon.getImage(), width/2-400, height/2-350, null);
		rightUpInternalFrame.setLocation(width-300, 0);
		rightDownInternalFrame.setLocation(width-300,300);
	}
	

	
	public void addDrawFrameToSet(String name, DrawInternalFrame newFrame)
	{
		drawInternalFrameSet.put(name, newFrame);	
	}
	
	public DrawInternalFrame getDrawFrame(String key)
	{
		System.out.println("ÆÐ½º : "+ key);
		return drawInternalFrameSet.get(key);
	}
	
	public void changeCursor()
	{	  
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image;

		
		switch(Information.getCurrentMode())
		{
		case Information.MODE_POLYGON : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_REC : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_TRIANGLE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_CIRCLE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_LINE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_ERAGE : image = toolkit.getImage("resource/mouse/Erager.png"); break;
		case Information.MODE_TEXT : image = toolkit.getImage("resource/mouse/textCursor.png"); break;
		case Information.MODE_MOVE : image = toolkit.getImage("resource/mouse/hand.png"); break;
		case Information.MODE_PEN : image = toolkit.getImage("resource/mouse/pen.png"); break;
		case Information.MODE_PAINT: image = toolkit.getImage("resource/mouse/Brush.png"); break;
		case Information.MODE_RESIZE :image = toolkit.getImage("resource/mouse/resize.png"); break;
		default : image = toolkit.getImage("resource/mouse/normal.png"); break;
		}
		
		Cursor c = toolkit.createCustomCursor(image , new Point(15,15), "img");
		this.setCursor (c);
	}
	
	@Override
		public synchronized void addKeyListener(KeyListener l) {
			// TODO Auto-generated method stub
			super.addKeyListener(l);
		}
}
