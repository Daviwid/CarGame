import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;

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
    private getPixel bild = new getPixel();
    private final Collection<Observer<Model>> observers;
    //private ArrayList<Point2D> tracklist = new ArrayList<Point2D>();
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
    	checkHitboxes();
        moveCar();
        System.out.println(carList.get(0).getAngle());
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
    	
    	if(carList.get(0).getPositionX() >= borderX || carList.get(0).getPositionX() <= 0 || carList.get(0).getPositionY() >= borderY || carList.get(0).getPositionY() <= 0) { 
    		carList.get(0).turnDirection(); 
    		
    		}
    	/*if(carList.get(0).getPositionX() <= 0) {
    		carList.get(0).turnDirection();
    		
    		} 
    	if(carList.get(0).getPositionY() >= borderY) {
    		carList.get(0).turnDirection();
    		
    		} 
    	if(carList.get(0).getPositionY() <= 0) {
    		carList.get(0).turnDirection();
    		
    	} 	*/
    }
   
    
 /* source: https://stackoverflow.com/questions/17136084/checking-if-a-point-is-inside-a-rotated-rectangle/17146376*/
public boolean overlapsWith(int px, int py){ //px, py är kordinater till track
	int ax= carList.get(0).getPositionX() - carList.get(0).getWidth()/2;
	int ay= carList.get(0).getPositionY() - carList.get(0).getHeight()/2;
	int bx= carList.get(0).getPositionX() + carList.get(0).getWidth()/2;
	int by= carList.get(0).getPositionY() - carList.get(0).getHeight()/2;
	int cx= carList.get(0).getPositionX() - carList.get(0).getWidth()/2;  //bugg? elr koden som är fel?
	int cy= carList.get(0).getPositionY() + carList.get(0).getHeight()/2;
	int dx= carList.get(0).getPositionX() + carList.get(0).getWidth()/2;
	int dy= carList.get(0).getPositionY() + carList.get(0).getHeight()/2;
	
	int rectarea = (carList.get(0).getHeight())*(carList.get(0).getWidth());
					
	int APD = Math.abs((px * ay - ax * py) + (dx * py - px * dy) + (ax * dy - dx * ay))/2;
	int DPC = Math.abs((px * dy - dx * py) + (cx * py - px * cy) + (dx * cy - cx * dy))/2;
	int CPB = Math.abs((px * cy - cx * py) + (bx * py - px * by) + (cx * by - bx * cy))/2;  
	int PBA = Math.abs((bx * py - px * by) + (ax * by - bx * ay) + (px * ay - ax * py))/2;
	int sum = APD + DPC + CPB + PBA;
	
	if(sum > rectarea) return false;
	else		 
    return true; 
    }     

    public void checkHitboxes() {  //
    Iterator<Point> it = bild.getList().iterator();
        while(it.hasNext()) {
        Point p = it.next();
    	if(overlapsWith(p.x, p.y) == true) { 
    		//System.out.println("krock");
    		carList.get(0).setNewCordinates();
    		//carList.get(0).turnDirection(); 
    	//	for(int i=0; i<= 5000; i++) {carList.get(0).collisionSpeed();}  //car slows down after collision for a few seconds
    	//carList.get(0).toNormalSpeed();
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
    public void setBorderX(int x) {
    	borderX = x;
    }
    public void setBorderY(int y) { 
    	borderY = y;
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
