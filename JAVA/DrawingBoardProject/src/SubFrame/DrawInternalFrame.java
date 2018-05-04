package SubFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import SubPanel.DrawPanel;
import figure.Figure;
import information.FigureBuffer;
import information.Information;
import Frame.MainDesktopPane;

public class DrawInternalFrame extends JInternalFrame {
	
	private DrawPanel draw;
	private String mName;
	
	
	public DrawInternalFrame(String name)
	{
		super(name,true,true,true,true);

		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				changeCursor();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addKeyListener(new KeyAdapter() {
			   public void keyPressed(KeyEvent evt) {
				   System.out.println("키보드 콜백 ");
				   if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {

					   //Copy Current Figure
		                if(Information.getCurrentFigure()!=null)
		                {
		                	FigureBuffer.getInstance().replaceBuffer(Information.getCurrentFigure());
		                	System.out.println("객체 카피!");
		                	
		                }
		                else
		            	  {
		  					JOptionPane.showMessageDialog(null,"Error : Cant' find Figure","ERROR",JOptionPane.ERROR_MESSAGE);
		            	  }

		            

		            } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {

		            	//Paste Bufferd FigureSet
		            	  if(!FigureBuffer.getInstance().isEmpty())
			                {
		            		  	if(Information.getCurrentJPanel()!=null)
		            		  	{
		            		  		Information.getCurrentJPanel().addVector((Vector<Figure>)FigureBuffer.getInstance().gerBuffer().clone());
		            		  		FigureBuffer.getInstance().clearBuffer();
		            		  		System.out.println("객체 붙여넣기!");
		            		  	}
			                	
			                }
		            	  else
		            	  {
		  					JOptionPane.showMessageDialog(null,"Error : Cant' find BufferSet","ERROR",JOptionPane.ERROR_MESSAGE);
		            	  }
		            	

		            } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_A) {
		            	//Copy AllFigure
		                FigureBuffer.getInstance().replaceBuffer(Information.getCurrentJPanel().getVector());
		                JOptionPane.showMessageDialog(null,"COPY ALL PANEL FIGURE","COPY",JOptionPane.PLAIN_MESSAGE);
	                	
		            }
		           if(evt.getKeyCode() == KeyEvent.VK_UP){
		        	   System.out.print("ddd");
		        	   Information.getCurrentJPanel().setBackground(Information.getCurrentColor());
		           }
		           if(evt.getKeyCode() == KeyEvent.VK_DOWN){
		        	   Information.getCurrentMainDesktopPane().setBackground(Information.getCurrentColor());
		           }
			   }
			
		});
		
		this.setFocusable(true);
		this.mName=name;
		setSize(500,500);
		setBackground(Color.WHITE);
		
		draw = new DrawPanel();
		add(draw);
		setVisible(true);
	}
	
	
	public void  replacePanel(DrawPanel panel)
	{	
		draw.changeVector(panel.getVector());
		repaint();
	}
	
	public void changeCursor()
	{	  
		Image image;

		
		switch(Information.getCurrentMode())
		{
		case Information.MODE_POLYGON : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_REC : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_TRIANGLE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_CIRCLE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_LINE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
		case Information.MODE_ERAGE : image = Information.getToolkit().getImage("resource/mouse/Erager.png"); break;
		case Information.MODE_TEXT : image = Information.getToolkit().getImage("resource/mouse/textCursor.png"); break;
		case Information.MODE_MOVE : image = Information.getToolkit().getImage("resource/mouse/hand.png"); break;
		case Information.MODE_PEN : image = Information.getToolkit().getImage("resource/mouse/pen.png"); break;
		case Information.MODE_PAINT: image = Information.getToolkit().getImage("resource/mouse/Brush.png"); break;
		case Information.MODE_RESIZE :image = Information.getToolkit().getImage("resource/mouse/resize.png"); break;
		default : image = Information.getToolkit().getImage("resource/mouse/normal.png"); break;
		}
		Cursor c = Information.getToolkit().createCustomCursor(image , new Point(15,15), "img");
		this.setCursor (c);
	}

}
