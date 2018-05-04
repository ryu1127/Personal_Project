package figure;

import java.awt.Graphics2D;
import java.util.Arrays;

public class Polygon extends Figure{
   
   private int[] xPoints ={0,0,0,0,0};
   private int[] yPoints ={0,0,0,0,0};
   
   public Polygon(int startx, int starty)
   {
      super();
      this.xPoints[0]=this.xPoints[1]=this.xPoints[2]=this.xPoints[3]=this.xPoints[4]=startx;
      this.yPoints[0]=this.yPoints[2]=this.yPoints[2]=this.yPoints[3]=this.yPoints[4]=starty;
   }
   
   @Override
   public void setFigureSize(int xPoint, int yPoint) {
      // TODO Auto-generated method stub
      xPoints[4]=xPoint;
      yPoints[4]=yPoint;
      
      xPoints[2]=xPoint - (xPoint - xPoints[3])*2;
      yPoints[2]=yPoint;
      
      xPoints[1]=xPoint;
      yPoints[1]=yPoint / 3;
      
      xPoints[0] = xPoint - (xPoint - xPoints[3])*2;
      yPoints[0]=yPoint / 3;   
      
   }
   
   @Override
   public void drawFigure(Graphics2D g) {
      g.setColor(figureColor);
      g.drawPolygon(xPoints, yPoints, 5);
      g.fillPolygon(xPoints, yPoints, 5);
   }

   @Override
   public String toString() {
      //return "Polygon [width=" + width + ", height=" + height + "]";
      return "Polygon [xPoints=" + Arrays.toString(xPoints) + ", yPoints=" + Arrays.toString(yPoints) + "]";
   }      //string for layer

   @Override
    public Figure clone() { 
         Polygon cir = new Polygon(0,0);   //cloning the circle
         cir.xPoints=xPoints.clone();
         cir.yPoints=yPoints.clone();
         
         return cir;
       }

   public void moveTo(int curX,int curY)
   {
      xPoints[0]+=curX;
      xPoints[1]+=curX;
      xPoints[2]+=curX;
      xPoints[3]+=curX;
      xPoints[4]+=curX;
      
      yPoints[0]+=curY;
      yPoints[1]+=curY;
      yPoints[2]+=curY;
      yPoints[3]+=curY;
      yPoints[4]+=curY;
   }

   @Override
   public int getSize() {
      // TODO Auto-generated method stub
      int width = Math.abs(xPoints[4]-xPoints[3]);
      int height = Math.abs(yPoints[0]-yPoints[3]);
      
      return width*height;
   }

   @Override
   public void calcFigure(int currentX, int currentY) {
      // TODO Auto-generated method stub
      setFigureSize(currentX,currentY);
   }
   
}