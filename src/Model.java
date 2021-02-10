
import java.util.LinkedList;
import java.util.Collection;
import java.awt.Color;
import java.util.HashSet;

public class Model implements Observable<Model> {

	//Instansvariabler:
	private LinkedList<Car> carList = new LinkedList<Car>();
	private int highScore = 0; //�ndra till n�gon slags tid
	private boolean pressedUp = false;
	private boolean pressedDown = false;
	private boolean pressedRight = false;
	private boolean pressedLeft = false;
	private static int TOPSPEED = 20;
	private static int CARSIZE = 10;
	private int carNumber = 1;
	private Track currentTrack;
	
	private final Collection<Observer<Model>> observers;
	
	public Model()
	{
		this.observers = new HashSet<>();
		modelInit(carNumber);
	}
	
	private void modelInit(int carNumber)
	{
		for(int i = 0; i < carNumber; i++)
		{
			carList.add(new Car(1, 0, 0, TOPSPEED, CARSIZE));
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
		
		notifyAll();
		
	}
	
	//h�mtacolorfr�nconfig()
	
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
	
	@Override
	public void addObserver(Observer<Model> o){
		this.observers.add(o);
	}

	@Override
    public void removeObserver(Observer<Model> o){
		this.observers.remove(o);
	}
	
	public void updateObservers(){
		for(Observer<Model> o : this.observers) {
            o.update(this);
		}
	}

}
