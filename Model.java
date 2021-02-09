import java.util.LinkedList;
import java.util.Observable;
import java.awt.Color;

public class Model extends Observable {

	//Instansvariabler:
	private LinkedList<Car> carList = new LinkedList<Car>();
	private int highScore = 0; //ändra till någon slags tid
	private boolean pressedUp = false;
	private boolean pressedDown = false;
	private boolean pressedRight = false;
	private boolean pressedLeft = false;
	private static int TOPSPEED = 20;
	private int carNumber = 1;
	private Track currentTrack;
	
	public Model()
	{
		modelInit(carNumber);
	}
	
	private void modelInit(int carNumber)
	{
		for(int i = 0; i < carNumber; i++)
		{
			carList.add(new Car(1, 0, 0, TOPSPEED));
		}
	}
	
	private void setStartPositions(Track track)
	{
		for(int i = 0; i < carList.size(); i++)
		{
			carList.get(i).setPosition(track);
			carList.get(i).setAngle(track);
		}
	}
	
	public void updateModel()
	{
		moveCar();
		
		//notifyObservers()
	}
	
	//hämtacolorfrånconfig()
	
	private void moveCar()
	{
		for(int i = 0; i < carList.size(); i++)
		{
			if(pressedUp == true)
			{
				carList.get(i).accelerate();
			}
			if(pressedDown == true)
			{
				carList.get(i).decelerate();
			}
			if(pressedRight == true)
			{
				carList.get(i).turnRight();
			}
			if(pressedLeft == true)
			{
				carList.get(i).turnLeft();
			}
			
		}
	}
	
	//getters
	public LinkedList<Car> getCarList()
	{
		return carList;
	}
	public Track getTrack()
	{
		return currentTrack;
	}
	
	//setters
	public void setUp()
	{
		pressedUp = !pressedUp;
	}
	public void setDown()
	{
		pressedDown = !pressedDown;
	}
	public void setRight()
	{
		pressedRight = !pressedRight;
	}
	public void setLeft()
	{
		pressedLeft = !pressedLeft;
	}
	
	
	//Observable-metoder
		//Controller kör model.addObserver(View)  ??

}
