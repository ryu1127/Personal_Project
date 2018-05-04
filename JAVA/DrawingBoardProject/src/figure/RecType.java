package figure;

import java.awt.Graphics2D;
import java.util.ArrayList;

import figure.PenType.PenTypeTracePoint;

public abstract class RecType extends Figure{
	protected int width,height;
	protected int startX,startY;
	protected int X,Y;
	
	
	public RecType(int startX, int startY,int width,int height)
	{
		super();
		X=this.startX=startX;
		Y=this.startY=startY;
		this.width=width;
		this.height=height;
	}
	
	@Override
	public void setFigureSize(int width, int height) {
		// TODO Auto-generated method stub
		this.width=width;
		this.height=height;
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return width*height;
	}

	@Override
	public abstract void drawFigure(Graphics2D g);

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub


		int wantWidth = currentX-startX;
		int wantHeight = currentY-startY;
		
		setFigureSize(Math.abs(wantWidth),Math.abs(wantHeight));
		
		if(wantWidth<0 && wantHeight <0)
		{
				X=startX+wantWidth;
				Y=startY+wantHeight;
		}
		else if(wantWidth<0 && wantHeight >=0)
		{
			
				X=startX+wantWidth;
		}
		else if(wantWidth>=0 && wantHeight <0)
		{
				Y=startY+wantHeight;
		
		}
		else if(wantWidth>=0 && wantHeight >=0)
		{
			X=startX; Y=startY;
		}
	}
	
	
	public void moveTo(int curX,int curY)
	{
		X+=curX;
		Y+=curY;
	}
	
	public abstract Figure clone();
}
