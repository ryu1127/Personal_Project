package information;

import java.util.Vector;

import figure.Figure;

public class FigureBuffer {
	
	
	private Vector<Figure> buffer = new Vector<Figure>();
	private static FigureBuffer instance;
	
	public static FigureBuffer getInstance()
	{
		if(instance==null) instance= new FigureBuffer();
		return instance;
	}
	
	
	public boolean isEmpty()
	{
		return buffer.isEmpty();
	}
	
	public void replaceBuffer(Vector<Figure> newBuffer)
	{
		buffer.clear();
		for(Figure cur : newBuffer)
		{
			buffer.add(cur.clone());
		}
		
	}
	
	public Vector<Figure> gerBuffer()
	{
		return (Vector<Figure>)buffer.clone();
	}


	public void replaceBuffer(Figure currentFigure) {
		// TODO Auto-generated method stub
		
		buffer.clear();
		buffer.add((Figure)currentFigure.clone());
		
	}

	public void clearBuffer()
	{
		buffer.clear();
	}
	
	
}
