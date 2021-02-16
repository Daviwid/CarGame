import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.*;
import javax.swing.*;


public class View extends JPanel implements Observer<Model> {

    private Model m;
    private BufferedImage redCar;
    private LinkedList<Car> carList;
    
    public View(Model m) {
        this.m = m;
        this.carList = m.getCarList();
        
        try {
            redCar = ImageIO.read(new File("REDCAR.png"));  
        }
        catch(IOException e){
            
        }
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        setBackground(Color.WHITE);
        
        Graphics2D g2d = (Graphics2D)g;
        drawCar(g2d, carList.get(0));
        System.out.println(carList.get(0).getAngle());	//debug code
        
        //Debug code "yellow spinning circles"
        g.setColor(Color.yellow);
        for (int    i=0;    i<5;   i++)
        {   
            int xx   =   15  *   i;  
            int yy   =   0;
            int xx1  =   (int)Rotate2D.getX(xx,yy,carList.get(0).getAngle());  
            int yy1  =   (int)Rotate2D.getY(xx,yy,carList.get(0).getAngle());
            g.fillOval(350-5+xx1,300-5+yy1,10,10);    
        }   
        //End of debug code
    }
    
    
    public void updateView() {
        repaint();
    }
    
    
    public void drawCar(Graphics2D g2d, Car car) {
        
     Image resultingImage = redCar.getScaledInstance(car.getHeight(), car.getWidth(), Image.SCALE_DEFAULT);
     BufferedImage outputImage = new BufferedImage(car.getHeight(), car.getWidth(), BufferedImage.TYPE_INT_ARGB);
     outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
     
       /* //Debug code "Green rectangle"
        g2d.setColor(Color.green); 
        double w = 20;  //Test values of green rectangle under png
        double h = 40;
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
        */ //End of debug code


     //Make a backup so that we can reset our graphics object after using it.
     AffineTransform backup = g2d.getTransform();


     AffineTransform a = AffineTransform.getRotateInstance(car.getAngle()-(3.14/2), car.getPositionX(), car.getPositionY());

     //Set our Graphics2D object to the transform
     g2d.setTransform(a);

     g2d.drawImage(outputImage, car.getPositionX()-outputImage.getWidth()/2, car.getPositionY()-outputImage.getHeight()/2 , null);

     //Reset our graphics object so we can draw with it again.
     g2d.setTransform(backup);
        
        
    }
    @Override
    public void update(Model observable) {
        repaint();
    }
}
