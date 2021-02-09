import java.awt.Color;

public class Car {
	
	//instansvariabler
	private double positionX; //gör om till points
	private double positionY;
	private int speed;
	private int angle;
	private int color;
	private int xOffset;
	private int yOffset;
	private int topspeed;
	
	//Konstruktor
	public Car(int color, int xOffset, int yOffset, int topspeed)
	{
		this.color = color;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.topspeed = topspeed;
		angle = 0;
	}
	
	private void move()
	{
		
		return;
	}
	
	//getters&setters
	public void setPosition(Track track) //gör om till points
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
			speed++;	
		}
	}
	public void decelerate()
	{
		if(speed >= 0)
		{
			speed--;
		}
	}
	public void turnRight()
	{
		angle++;
		if(angle >= 360)
		{
			angle = angle - 360;
		}
	}
	public void turnLeft()
	{
		angle--;
		if(angle <= 360)
		{
			angle = angle + 360;
		}
	}
	
	//getters
	public int[] getCarInfo()
	{
		int[] info = {(int)positionX, (int)positionY, angle, color};
		return info;
	}
	
	

}