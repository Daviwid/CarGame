package GameFiles;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Car class is responsible of handling the movement and which visual to be used for the car
 * @version 2.0.3.0
 * @since 2021-03-05
 */
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
	  private BufferedImage currentCarIMG,redCar,blueCar,greenCar,aiCar;

    /**
     * Constructor 
     * 
     * @param color indicate which color the car will have
     * @param xoffset start x offset for the car 
     * @param yoffset start y offset for the car 
     * @param topspeed topspeed of the car 
     * @param height the height/length of the car
     * @param width the width of the car
     */
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
            	aiCar = ImageIO.read(getClass().getResource("/Resources/AICAR.png"));
        }
	    catch(IOException e){}
	    setCarIMG(color);
    }
    /**
    * method calculates the next x and y position of the car with regard to the cars current angle and speed
    */  
    public void move() 
    {
        positionX += (Math.cos(angle) * speed); 
        positionY += (Math.sin(angle) * speed);  
    }

    //getters&setters
    /**
     * set the current track starting position for the car
     * @param track current track
     */
    public void setStartPosition(Track track) 
    {
        positionX = track.getStartPositions().x + xOffset;
        positionY = track.getStartPositions().y + yOffset;
    }
    
    /**
     * set the position where the car last passed a checkpoint
     * @param x
     * @param y
     */
    public void setCheckpointPosition(int x,int y) 
    {
        positionX = x + xOffset;
        positionY = y + yOffset;
    }
    
    /**
     * set the starting angle for the car
     * @param track current track
     */
    public void setStartAngle(Track track)
    {
        angle = track.getStartAngle();
    }
    /**
     * set the position for the AI car
     * @param x  x cordinate
     * @param y  y cordinate
     */
    public void setPositionAI(int x, int y) 
    {
        positionX = x;
        positionY = y;
    }
    /**
     * set the angle for the AI car
     * @param a angle 
     */
    public void setAngleAI(double a) 
    {
        angle = a;
    }
    /**
     * method accelerate currentspeed if speed is below topspeed
     */
    public void accelerate()
    {
        if(speed <= topspeed)
        {
            speed+=0.1; //decimal for smooth acceleration
        }
    }
    /**
     * method decelerate currentspeed if car is moving
     */
    public void decelerate()
    {
        if(speed >= 0)
        {
            speed-=0.1;
        }
    }

    /**
    * set the current car angle to be +-180 deg taking regard to if the current angle <0 or >360 deg
    */
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
    /**
    * set the current speed to 0 whenever there is a collision
    */
  public void collisionSpeed() {
	  speed=0;
  }
    /**
     * set the angle to be -90 degrees depending of the cars current angle
    */
	public void turnRight()
	{
		angle = angle   +   Math.PI    /   128.0; //increase radian angle
		if(angle>(2*Math.PI))                     //keep interval 0-6
    {       
       angle-=(2*Math.PI);
    }
	}
    /**
    * set the angle to be +90 degrees depending of the cars current angle
    */
	public void turnLeft()
	{
		  angle = angle   +   -Math.PI    /   128.0;    //decrease radian angle
      if(angle<0)                                   //keep interval 0-6
      {
          angle+=(2*Math.PI);
      }
	}
    /**
     * set the speed according to s
     * @param s speed value
     */
	public void setSpeed(double s)
	{
		this.speed=s;
	}
    /**
     * set which BufferedImage the car should use as visual 
     * @param c
     */
	public void setCarIMG(int c)
	{
		if(c==0) {currentCarIMG=redCar;}
		if(c==1) {currentCarIMG=greenCar;}
		if(c==2) {currentCarIMG=blueCar;}
		if(c==3) {currentCarIMG=aiCar;}
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
//getters which return buffered image of the chosen car
	public BufferedImage getCarIMG() { return this.currentCarIMG; }
	public BufferedImage getRedCar() { return this.redCar; }
	public BufferedImage getGreenCar() { return this.greenCar; }
	public BufferedImage getBlueCar() { return this.blueCar; }
}