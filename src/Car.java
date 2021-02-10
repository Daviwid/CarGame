package src;

import java.awt.Color;

public class Car {
	
	//instansvariabler
	private double positionX; //g�r om till points
	private double positionY;
	private int speed;
	private int angle;
	private int color;
	private int xOffset;
	private int yOffset;
	private int topspeed;
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
	}
	
	private void move()
	{
		
		return;
	}
	
	//getters&setters
	public void setPosition(Track track) //g�r om till points
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
	public int getPositionX()
	{
		return (int)positionX;
	}
	public int getPositionY()
	{
		return (int)positionY;
	}
	public int getAngle()
	{
		return angle;
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