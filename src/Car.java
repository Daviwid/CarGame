import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Car {
      private double positionX; 
      private double positionY;
      private double speed;
      private double tempspeed;
      private double angle;
      private int color;
      private int xOffset;
      private int yOffset;
      private double topspeed;
      private int height;
	    private int width;
	    private BufferedImage redCar;
	    private BufferedImage blueCar;
	    private BufferedImage greenCar;
    //Konstruktor
    public Car(int color, int xOffset, int yOffset, int topspeed, int height, int width, int col)
    {
=======
	  
    //Konstruktor
    public Car(int color, int xOffset, int yOffset, int topspeed, int height, int width)
    {
    	this.speed = 0;

        this.color = color;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.topspeed = topspeed;
        this.height = height;
		this.width = width;

        angle = 0;

        positionX = 500;
        positionY = 500;
        if(col==0) {
            try {
    		 blueCar = ImageIO.read(new File("BLUECAR.png"));
    		}
    		catch(IOException e){
    			
    		} 
        if(col==1) {
        try {
		           redCar = ImageIO.read(new File("REDCAR.png"));
        }
          
		catch(IOException e){
			
		}

    public void move() 
    {
        positionX += (Math.cos(angle) * speed); 
        positionY += (Math.sin(angle) * speed);  
    }

    //getters&setters
    public void setPosition(Track track) 
    {
        positionX = track.getStartPositionX() + xOffset;
        positionY = track.getStartPositionY() + yOffset;
    }
    public void setAngle(Track track)
    {
        angle = track.getStartAngle();
    }
        
    
    public void collisionSpeed() {
  	  tempspeed = speed;
  	  speed = 0.5;
    }
    public void toNormalSpeed() {
  	  speed = tempspeed;
    }
    public void accelerate()
    {
        if(speed <= topspeed)
        {
            speed+=0.1; //decimal for smooth acceleration
        }
    }
    public void decelerate()
    {
        if(speed >= 0)
        {
            speed-=0.1;
        }
    }
  public void turnDirection() {
	  angle = (angle) + Math.PI ;
		if(angle>(2*Math.PI))             //keep interval 0-6
              {       
                  angle-=(2*Math.PI);
              }
              if(angle<0)                      //keep interval 0-6
              {
                  angle+=(2*Math.PI);
              }
	}
  
  public void collisionSpeed() {
	  tempspeed = speed;
	  speed = 0.5;
  }
  public void toNormalSpeed() {
	  speed = tempspeed;
  }
	public void turnRight()
	{
		angle = angle   +   Math.PI    /   128.0; //increase radian angle
		if(angle>(2*Math.PI))                     //keep interval 0-6
    {       
       angle-=(2*Math.PI);
    }
	}
	public void turnLeft()
	{
		  angle = angle   +   -Math.PI    /   128.0;    //decrease radian angle
      if(angle<0)                                   //keep interval 0-6
      {
          angle+=(2*Math.PI);
      }
	}
  
	public void setSpeed(double s)
	{
		this.speed=s;
	}
	
	//getters
	public int getPositionX()
	{
		return (int)positionX;
	}
	public int getPositionY()
	{
		return (int)positionY;
	}
	public double getAngle()
	{
		return angle;
	}
	public int getColor()
	{
		return color;
	}
	public int getHeight(){
		return this.height;
	}
	public int getWidth()
  {
		return this.width;
  }
	public double getSpeed()
	{
		return this.speed;
	}
	public BufferedImage getRedCar()
	{
		return redCar;
	}
	public BufferedImage getRedCar() { return this.redCar; }
	public BufferedImage getBlueCar() { return this.blueCar; }
	public BufferedImage getGreenCar() { return this.greenCar; }
}