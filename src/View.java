import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.*;
import javax.swing.*;

public class View extends JPanel implements Observer<Model> {

	private Model m;
    private BufferedImage redCar;
    private LinkedList<Car> carList;
    private getPixel bild;
	
	public View(Model m) {
		 this.m = m;
	     this.carList = m.getCarList();
	     this.bild = new getPixel();
	    
	     
		try {
			redCar = ImageIO.read(new File("REDCAR.png"));
			
		    
		}
		catch(IOException e){
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.WHITE);
		setBackground(Color.gray);
        if(m.getState()==STATE.MENU)					//Rita upp OM meny
        {
            Graphics2D g2d = (Graphics2D) g;
            setBackground(Color.black);

            g2d.setColor(Color.white);
            g2d.setFont(new Font("arial",Font.BOLD,150));
            g2d.drawString("CARGAME",20,200);					//Game title

            g2d.setColor(Color.red);
            g2d.fill(m.getMenu().getPlayBtn());					//playBtn med outline
            g2d.setColor(Color.white);
            g2d.draw(m.getMenu().getPlayBtn());
            g.setFont(new Font("arial",Font.BOLD,50));			//Play text
            g2d.drawString("PLAY",
                (int)m.getMenu().getPlayBtn().getX()+(int)m.getMenu().getPlayBtn().getWidth()/2-60,
                (int)m.getMenu().getPlayBtn().getY()+(int)m.getMenu().getPlayBtn().getHeight()/2+20);

            g2d.setColor(Color.red);
            g2d.fill(m.getMenu().getQuitBtn());					//quitBtn med outline
            g2d.setColor(Color.white);
            g2d.draw(m.getMenu().getQuitBtn());
            g2d.drawString("QUIT",								//Quit text
                (int)m.getMenu().getQuitBtn().getX()+(int)m.getMenu().getQuitBtn().getWidth()/2-60,
                (int)m.getMenu().getQuitBtn().getY()+(int)m.getMenu().getQuitBtn().getHeight()/2+20);

        }
        if(m.getState()==STATE.GAME)				//Rita OM game
        {
            if(m.getSelected())						//Rita OM map selected == true. Typ en dubbelkoll. Mest ifall bilden inte laddas in och ger Exceptions
            {
                g.drawImage(m.getTrack().getMap(), 0,0,this);					//Ritar banan

                //Debug code. visar sargen.
                Iterator<Point> it = m.getTrack().getHitbox().iterator();
                g.setColor(Color.red);
                while(it.hasNext())
                {
                    Point p = it.next();
                    g.drawLine(p.x,p.y,p.x,p.y);
                }
                //End of debug code.
            }
            Graphics2D g2d = (Graphics2D) g;
			drawCar(g2d, carList.get(0));
		
        
        }
       
    }	
	
	
	
	public void updateView() {
		repaint();
	}
	
	
	
	public void drawCar(Graphics2D g2d, Car car) {
		Image resultingImage = redCar.getScaledInstance(car.getHeight(), car.getWidth(), Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(car.getHeight(), car.getWidth(), BufferedImage.TYPE_INT_ARGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	   
	  
	    
	     double w = 45;  //Test values of green rectangle under png
	     double h = 50;
	     Point2D p1 = new Point2D(h/2,-w/2).rotate(car.getAngle());
	     Point2D p2 = new Point2D(h/2,w/2).rotate(car.getAngle());
	     Point2D p3 = new Point2D(-h/2,w/2).rotate(car.getAngle());
	     Point2D p4 = new Point2D(-h/2,-w/2).rotate(car.getAngle());
	     double x = car.getPositionX();
	     double y = car.getPositionY();
	     double[]   dx  = {x+p1.getIntX(),x+p2.getIntX(),x+p3.getIntX(),x+p4.getIntX()};  
	     double[]   dy  = {y+p1.getIntY(),y+p2.getIntY(),y+p3.getIntY(),y+p4.getIntY()};
	     int xPts[]= new int[4];
	     int yPts[]= new int[4];
	     for(int i=0;i<4;i++)
	     {
	          xPts[i]=(int)(dx[i]+.5);
	          yPts[i]=(int)(dy[i]+.5); 
	     }
	     g2d.fillPolygon(xPts,yPts,4);
	      //End of debug code
	      //End of debug code    
	    
        

     //Make a backup so that we can reset our graphics object after using it.
     AffineTransform backup = g2d.getTransform();


     AffineTransform a = AffineTransform.getRotateInstance(car.getAngle(), car.getPositionX(), car.getPositionY());
     
     //Set our Graphics2D object to the transform
     
     
    
    g2d.rotate(car.getAngle() + (3*Math.PI)/2, car.getPositionX(), car.getPositionY());
    
     //Reset our graphics object so we can draw with it again.
    
    g2d.drawImage(outputImage, car.getPositionX() - 25, car.getPositionY()-25 , null);
    g2d.setTransform(a); 
    g2d.setTransform(backup);
     
     
		
	}
	/*public void drawCar(Graphics g, Car car) {
		g.setColor(Color.green); 
        double w = car.getWidth();  //Test values of green rectangle under png
        double h = car.getHeight();
        Point2D p1 = new Point2D(h/2,-w/2).rotate(car.getAngle());
        Point2D p2 = new Point2D(h/2,w/2).rotate(car.getAngle());
        Point2D p3 = new Point2D(-h/2,w/2).rotate(car.getAngle());
        Point2D p4 = new Point2D(-h/2,-w/2).rotate(car.getAngle());
        double x = car.getPositionX();
        double y = car.getPositionY();
        double[]   dx  = {x+p1.getIntX(),x+p2.getIntX(),x+p3.getIntX(),x+p4.getIntX()};  
        double[]   dy  = {y+p1.getIntY(),y+p2.getIntY(),y+p3.getIntY(),y+p4.getIntY()};
        int xPts[]= new int[4];
        int yPts[]= new int[4];
        for(int i=0;i<4;i++)
        {
             xPts[i]=(int)(dx[i]+.5);
             yPts[i]=(int)(dy[i]+.5); 
        }	
        g.fillPolygon(xPts,yPts,4);
	}*/
	@Override
    public void update(Model observable) {
        repaint();
    }
}
