import java.util.LinkedList;
import java.util.Collection;
import java.awt.Color;
import java.util.HashSet;

public class Model implements Observable<Model> {
    //Instansvariabler:
    private LinkedList<Car> carList = new LinkedList<Car>();
    private int highScore = 0; //ndra till ngon slags tid
    private int borderX;
    private int borderY;
    
    private boolean pressedUp = false;
    private boolean pressedDown = false;
    private boolean pressedRight = false;
    private boolean pressedLeft = false;
    private static int TOPSPEED = 10;
    private static int height = 50;
    private static int width = 50;
    private int carNumber = 1;
    private Track currentTrack;
    
    private final Collection<Observer<Model>> observers;
   // private Controller controller;
    public Model()
    {
        this.observers = new HashSet<>();
        modelInit(carNumber);
    }
    
    private void modelInit(int carNumber)
    {
        for(int i = 0; i < carNumber; i++)
        {
            carList.add(new Car(1, 0, 0, TOPSPEED, height, width));
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
    	checkBorder();
        moveCar();
        
        updateObservers();
       
    }
    
    //hamtacolorfranconfig()
    
    private void moveCar()
    {
        for(int i = 0; i < carList.size(); i++)
        {
            if(pressedUp)
            {
                carList.get(i).accelerate();
            }
            if(pressedDown)
            {
                carList.get(i).decelerate();
            }
            if(pressedRight)
            {
                carList.get(i).turnRight();
            }
            if(pressedLeft)
            {
                carList.get(i).turnLeft();
            }
            
        }
        carList.get(0).move();
    }
    
    public void checkBorder() {
    	
    	if(carList.get(0).getPositionX() >= borderX) { 
    		carList.get(0).turnDirection(); 
    		}
    	if(carList.get(0).getPositionX() <= 0) {
    		carList.get(0).turnDirection();
    		} 
    	if(carList.get(0).getPositionY() >= borderY) {
    		carList.get(0).turnDirection();
    		} 
    	if(carList.get(0).getPositionY() <= 0) {
    		carList.get(0).turnDirection();
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
    public void setPressedUp()
    {
        pressedUp = true;
    }
    public void setPressedDown()
    {
        pressedDown = true;
    }
    public void setPressedRight()
    {
        pressedRight = true;
    }
    public void setPressedLeft()
    {
        pressedLeft = true;
    }
    public void setReleasedUp()
    {
        pressedUp = false;
    }
    public void setReleasedDown()
    {
        pressedDown = false;
    }
    public void setReleasedRight()
    {
        pressedRight = false;
    }
    public void setReleasedLeft()
    {
        pressedLeft = false;
    }
    public void setBorderX(double x) {
    	borderX = (int)x;
    }
    public void setBorderY(double y) { 
    	borderY = (int)y;
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
