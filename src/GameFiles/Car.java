package GameFiles;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Car {
      private double positionX; 
      private double positionY;
      private double speed;
      private double angle;
      private int color;
      private int xOffset;		//In case of 2 players
      private int yOffset;
      private double topspeed;
      private int height;
	  private int width;
	  private BufferedImage currentCarIMG,redCar,blueCar,greenCar;

    //Konstruktor
    public Car(int color, int xOffset, int yOffset, int topspeed, int height, int width)
    {

    	this.speed=0;

        this.color = color;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.topspeed = topspeed;
        this.height = height;
		this.width = width;

        try {
            	redCar = ImageIO.read(getClass().getResource("/Resources/REDCAR.png"));
            	greenCar = ImageIO.read(getClass().getResource("/Resources/GREENCAR.png"));
            	blueCar =ImageIO.read(getClass().getResource("/Resources/BLUECAR.png"));
        }
	    catch(IOException e){}
	    setCarIMG(color);
    }
        
    public void move() 
    {
        positionX += (Math.cos(angle) * speed); 
        positionY += (Math.sin(angle) * speed);  
    }

    //getters&setters
    public void setStartPosition(Track track) 
    {
        positionX = track.getStartPositions().x + xOffset;
        positionY = track.getStartPositions().y + yOffset;
    }
    public void setCheckpointPosition(int x,int y) 
    {
        positionX = x + xOffset;
        positionY = y + yOffset;
    }
    public void setStartAngle(Track track)
    {
        angle = track.getStartAngle();
    }
    
    public void setPositionAI(int x, int y) 
    {
        positionX = x;
        positionY = y;
    }
    public void setAngleAI(double a) 
    {
        angle = a;
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
	  speed=0;
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
	public void setCarIMG(int c)
	{
		if(c==0) {currentCarIMG=redCar;}
		if(c==1) {currentCarIMG=greenCar;}
		if(c==2) {currentCarIMG=blueCar;}
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

	public BufferedImage getCarIMG() { return this.currentCarIMG; }
	public BufferedImage getRedCar() { return this.redCar; }
	public BufferedImage getGreenCar() { return this.greenCar; }
	public BufferedImage getBlueCar() { return this.blueCar; }
}