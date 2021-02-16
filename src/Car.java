import java.awt.Color;

public class Car {
    
    //instansvariabler
    private double positionX; 
      private double positionY;
      private double speed;
      private double angle;
      private int color;
      private int xOffset;
      private int yOffset;
      private double topspeed;
      private int height;
	  private int width;
    
    //Konstruktor
    public Car(int color, int xOffset, int yOffset, int topspeed, int height, int width)
    {
        this.color = color;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.topspeed = topspeed;
        this.height = height;
		this.width = width;

        angle = 0;
    
        positionX = 200;
        positionY = 200;
    }
    
    public void move()  // �ndra position varje frame baserat p� angle och speed
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
	public int getWidth(){
		return this.width;
	}
}