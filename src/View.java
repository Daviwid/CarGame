
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
	private	BufferedImage redCar;
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
		
	}
	
	
	public void updateView() {
		repaint();
	}
	
	
	public void drawCar(Graphics2D g2d, Car car) {
		
		Image resultingImage = redCar.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        g2d.setColor(Color.red);
        g2d.drawLine(500,0,500,1000);
        g2d.drawLine(0,500,1000,500);

		//Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();


        AffineTransform a = AffineTransform.getRotateInstance(car.getAngle()+(-3.14/2), car.getPositionX(), car.getPositionY());

        //Set our Graphics2D object to the transform
        g2d.setTransform(a);




        g2d.drawImage(outputImage, (int)car.getPositionX()-outputImage.getWidth()/2, (int)car.getPositionY()-outputImage.getHeight()/2 , null);

        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);
	    
		
	}

	@Override
    public void update(Model observable) {
        repaint();
		//System.out.println("notify");
    }
}
