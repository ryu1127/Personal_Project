package SubPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Frame.MainFrame;
import SubFrame.RightDownInternalFrame;
import figure.Polygon;
import figure.Circle;
import figure.Eraser;
import figure.Figure;
import figure.Line;
import figure.Pen;
import figure.Rectangle;
import figure.Text;
import figure.Triangle;
import information.Information;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, Serializable{
	
	static int LinePotX=0;
	static int LinePotY=0;
	private String filepath = null;
	private Vector<Figure> figureSet = new Vector<Figure>();
	private Stack<Vector<Figure>> figureStack = new Stack<Vector<Figure>>();
	private Stack<Vector<Figure>> figureCancelStack = new Stack<Vector<Figure>>();
	
	private int dragStartX,dragStartY;
	
	public DrawPanel()
	{
		Information.setCurrentpanel(this);
		this.setBackground(new Color(255,255,255));
		setVisible(true);
		this.setFocusable(true);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	
	}
	
	@Override
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
	
		for(Figure current:figureSet)
		{
			current.drawFigure(g2);
	
		}
			
		
	}
	
	
	public void setFilepath(String filepath)
	{
		this.filepath=filepath;
	}
	
	
	public String getFilepath()
	{
		return filepath;
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
		
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if( Information.getCurrentFigure()!=null)
			{
				int moveX=e.getX()-dragStartX;
				int moveY=e.getY()-dragStartY;
				Information.getCurrentFigure().moveTo(moveX, moveY);
				dragStartX=e.getX();
				dragStartY=e.getY();
				repaint();
			}
			
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
		}
		
		
		else if(Information.getCurrentMode()==Information.MODE_RESIZE)
		{
			if(Information.getCurrentFigure()!=null)
			{
				
				Information.getCurrentFigure().calcFigure(e.getX(), e.getY());
				repaint();
			}
		}
		else
		{
			Figure current = figureSet.lastElement();
			//figureSet.remove(figureSet.lastElement());
			drawCurrentFigureFunc(e, current);
		}
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Information.setCurrentpanel(this);
		RightDownInternalFrame.getInstance().setListItems(figureSet);
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if(Information.getCurrentFigure()!=null)
			{	
				dragStartX=e.getX();
				dragStartY=e.getY();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//changeCursor();
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("Out of Frame ");
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Information.setCurrentpanel(this);
	
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if(Information.getCurrentFigure()!=null)
			{	
				dragStartX=e.getX();
				dragStartY=e.getY();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		else if(Information.getCurrentMode()==Information.MODE_RESIZE)
		{
			
		}
		else
		{
			drawFigureFunc(e);

		}
		
		

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		RightDownInternalFrame.getInstance().setListItems(figureSet);
		
		figureStack.push((Vector<Figure>)figureSet.clone());	

		figureCancelStack.clear();
		
	}
	/*if(Information.getCurrentMode()==Information.MODE_LINEPOT)
		{	
				Line line = new Line(LinePotX,LinePotY,e.getX(),e.getY());
				Graphics2D g = null ;
				line.drawFigure(g);
		}*/
	
	
	private void drawFigureFunc(MouseEvent e)
	{
		int mode=Information.getCurrentMode();
		switch(mode)
		{
		case Information.MODE_POLYGON: 
			Polygon pol = new Polygon(e.getX(),e.getY());
			figureSet.addElement(pol);
			break;
		case Information.MODE_PEN: 
			Pen pens = new Pen(e.getX(),e.getY());
			figureSet.addElement(pens);
			break;
		
		case Information.MODE_DRAW_REC: 
			Rectangle rec = new Rectangle(e.getX(),e.getY(),0,0);
			figureSet.addElement(rec);
			break;
		case Information.MODE_DRAW_CIRCLE:
			Circle circle = new Circle(e.getX(),e.getY(),0,0);
			figureSet.addElement(circle);
			break;
		case Information.MODE_DRAW_LINE:
			Line line = new Line(e.getX(),e.getY(),e.getX(),e.getY());
			figureSet.addElement(line);
			break;
		case Information.MODE_DRAW_TRIANGLE:
			Triangle triangle = new Triangle(e.getX(),e.getY());
			figureSet.addElement(triangle);
			break;
		case Information.MODE_ERAGE:
			Eraser eraser = new Eraser(e.getX(),e.getY());
			figureSet.addElement(eraser);
			break;
		case Information.MODE_TEXT:
			String textData=null;
			if(textData==null || textData.equals("")) textData=JOptionPane.showInputDialog(null,"텍스트를 입력해 주세요",JOptionPane.OK_OPTION);
			if(textData==null || textData.equals("")) return;
			
			Text text = new Text(e.getX(),e.getY(),textData);
			figureSet.addElement(text);
			RightDownInternalFrame.getInstance().setListItems(figureSet);
			break;
		default : return;
		}
		repaint();
	}
	

	public void drawCurrentFigureFunc(MouseEvent e, Figure temp)
	{
		temp.calcFigure(e.getX(), e.getY());
		//figureSet.addElement(temp);
		repaint();
	}


	public void changeVector(Vector<Figure> vector)
	{
		figureSet=vector;
	}
	public Vector<Figure> getVector()
	{
		return figureSet;
	}
	
	public void addVector(Vector<Figure> addData)
	{
		figureSet.addAll(addData);
		RightDownInternalFrame.getInstance().setListItems(figureSet);
		
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		repaint();
	}
			
	public void addVector(Figure addData)
	{
		figureSet.add((Figure) addData.clone());
		
		RightDownInternalFrame.getInstance().setListItems(figureSet);
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		repaint();
	}
	
	public void  claearFigure()
	{
		figureSet.clear();
		figureStack.clear();
		figureCancelStack.clear();
		RightDownInternalFrame.getInstance().setListItems(figureSet);
		repaint();
	}
	
	public void deleteFigure(int idx)
	{
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		figureSet.remove(idx);
		RightDownInternalFrame.getInstance().setListItems(figureSet);
		
		
		
		repaint();
	}
	
	public void popStackTrace()
	{
		if(figureStack.isEmpty())
		{
			
			figureCancelStack.push((Vector<Figure>)figureSet.clone());	
			figureSet.clear();
			repaint();
			RightDownInternalFrame.getInstance().setListItems(figureSet);
			JOptionPane.showMessageDialog(null,"Error : Cant' find More Action","ERROR",JOptionPane.ERROR_MESSAGE);

			return;
		}
		else
		{
			
			figureCancelStack.push((Vector<Figure>)figureSet.clone());		

			
		
			if(figureSet.equals(figureStack.peek())) figureStack.pop();
			if(!figureStack.empty())
			{
				figureSet=(Vector<Figure>)figureStack.peek().clone();
				figureStack.pop();
			}
			else
			{
				figureSet.clear();
			}
			
			
			RightDownInternalFrame.getInstance().setListItems(figureSet);
			repaint();
		}
		
	}
	public void popStackCaneStack()
	{
		if(figureCancelStack.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Error : Cant' find More Action","ERROR",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{	
			
			figureStack.push((Vector<Figure>)figureSet.clone());
			figureSet=(Vector<Figure>)figureCancelStack.pop().clone();

			
			
			
			RightDownInternalFrame.getInstance().setListItems(figureSet);
			repaint();
		}
	
		
	}

	
	
	
}
