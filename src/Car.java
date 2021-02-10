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
    private int carSize;
	
	//Konstruktor
	public Car(int color, int xOffset, int yOffset, int topspeed, int carSize)
	{
		this.color = color;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.topspeed = topspeed;
		this.carSize = carSize;
		angle = 0;
		this.positionX = 500;
        this.positionY=500;
	}
	
	public void move() 	// ndra position varje frame baserat p angle och speed
	{
		positionX += (Math.cos(this.angle) * speed);
        positionY += (Math.sin(this.angle) * speed);
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
		if(speed < topspeed)
        {
            speed+=0.1;    
        }
	}
	public void decelerate()
	{
		 if(speed >= 0)
	        {
	            speed-=0.1;
	        }
	}
	public void turnRight()  //in radians
	{
		angle = angle   +   Math.PI    /   128.0;
	}
	public void turnLeft()
	{
		angle = angle   +   -Math.PI    /   128.0;
	}
	public void turnDirection() {
		angle= angle + Math.PI ;
		
	}
	//getters
	public double getPositionX()
	{
		return positionX;
	}
	public double getPositionY()
	{
		return positionY;
	}
	public double getAngle()
	{
		return angle;
	}
	public double getRadianAngle()
    {
        return Math.toRadians(angle);
    }
	public int getColor()
	{
		return color;
	}
	public int getCarSize()
	{
		return carSize;
	}
	

}