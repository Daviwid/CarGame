import java.util.LinkedList;
import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

public class Model implements Observable<Model> {
    //Instansvariabler:
    private LinkedList<Car> carList = new LinkedList<Car>();
    private int borderX;
    private int borderY;
    private boolean mapSelected = false;
    private boolean pressedUp = false;
    private boolean pressedDown = false;
    private boolean pressedRight = false;
    private boolean pressedLeft = false;
    
    private boolean checkpoint1 = false;
	private boolean checkpoint2 = false;
	private boolean checkpoint3 = false;
	private boolean checkpoint4 = false;
	
	private int checkpoint1x;
	private int checkpoint1y;
	private int checkpoint2x;
	private int checkpoint2y;
	private int checkpoint3x;
	private int checkpoint3y;
	private int checkpoint4x;
	private int checkpoint4y;
	
    private static int TOPSPEED = 10;


    private static int height = 30;
    private static int width = 20;
    private int carNumber = 1;
    private Track currentTrack,lindholmen, currentCheckpoints;
    
    private Menu menu;
    private STATE state;
    private int carColor = 0;
    private String build = "Build v. 1.0.0.0";

	private int gameTimer;
    private ArrayList<Point> positionList = new ArrayList<Point>();
    private ArrayList<Double> angleList = new ArrayList<Double>();
    
    private final Collection<Observer<Model>> observers;
 // private Controller controller;
    public Model()
    {
		
        this.observers = new HashSet<>();
        carsInit(carNumber);
    }
    
    public void carsInit(int carNumber)
    {
        for(int i = 0; i < carNumber; i++)
        {
        	carList.add(new Car(carColor, 0, 0, TOPSPEED, height, width));  //REDCAR
        }
    }
    

    public void resetCarFlags()
    {
    	pressedUp=false;
    	pressedDown=false;
    	pressedLeft=false;
    	pressedRight=false;
    }
    
    public void resetCheckBox() {
    	checkpoint1 = false;
    	checkpoint2 = false;
    	 checkpoint3 = false;
    	checkpoint4 = false;
    }
    public void menuInit()			//Skapar meny och state = menu
    {
        menu = new Menu(borderX, borderY);
        state = STATE.MENU;
    }
    
    public void updateModel()
    {
        if(state==STATE.GAME)	//Kolla endast om man spelar. Genererar annars exceptions
        {
            checkBorder();
           checkHitboxes();
            checkCheckpoint1Hitboxes();
            checkCheckpoint2Hitboxes();
            checkCheckpoint3Hitboxes();
            checkCheckpoint4Hitboxes();

            moveCar();
			
            savePosition(carList.get(0).getPositionX(), carList.get(0).getPositionY());  //for loop if we have more then 1 player
            saveAngle(carList.get(0).getAngle());                                        // same here...
        }
        updateObservers();
    }
    
    
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
    	
    }
  
    /* source: https://stackoverflow.com/questions/17136084/checking-if-a-point-is-inside-a-rotated-rectangle/17146376*/



    public boolean overlapsWith(double px, double py){ //px, py är kordinater till track



    	double width= (double) carList.get(0).getWidth()/2;
    	double height= (double)carList.get(0).getHeight()/2;
    	double carx= carList.get(0).getPositionX();
    	double cary= carList.get(0).getPositionY();
    	double ax= carx - width;
    	double ay= cary - height;
    	double bx= carx + width;
    	double by= cary - height;
    	double cx= carx - width;  
    	double cy= cary + height;
    	double dx= carx + width;
    	double dy= cary + height;
    	double rectarea = (2*height)*(2*width);
    	double APD = Math.abs((px * ay - ax * py) + (dx * py - px * dy) + (ax * dy - dx * ay))/2;
    	double DPC = Math.abs((px * dy - dx * py) + (cx * py - px * cy) + (dx * cy - cx * dy))/2;
    	double CPB = Math.abs((px * cy - cx * py) + (bx * py - px * by) + (cx * by - bx * cy))/2;  
    	double PBA = Math.abs((bx * py - px * by) + (ax * by - bx * ay) + (px * ay - ax * py))/2;
    	double sum = APD + DPC + CPB + PBA;
    	
    	if(sum > rectarea ) return false;
        return true; 
    	} 
    
   public void checkHitboxes() {  //
    Iterator<Point> it = currentTrack.getHitbox().iterator();
        while(it.hasNext()) {
        
        Point p = it.next();
    	if( overlapsWith(p.x, p.y) ) { 
    		//carList.get(0).turnDirection(); 
    		if(checkpoint1==true && checkpoint2!=true && checkpoint3!=true && checkpoint4!=true) {carList.get(0).setCheckpointPosition(currentTrack, checkpoint1x , checkpoint1y);}
    		
    		if(checkpoint2==true && checkpoint1==true && checkpoint3!=true && checkpoint4!=true) {carList.get(0).setCheckpointPosition(currentTrack, checkpoint2x , checkpoint2y);}
    		
    		if(checkpoint3==true && checkpoint2==true && checkpoint1==true && checkpoint4!=true) {carList.get(0).setCheckpointPosition(currentTrack, checkpoint3x , checkpoint3y);}
    		
    		if(checkpoint4==true && checkpoint3==true && checkpoint2==true && checkpoint1==true) {carList.get(0).setCheckpointPosition(currentTrack, checkpoint4x , checkpoint4y);}
    	}
    	
    	}
    }
    public void checkCheckpoint1Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints1Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y) ) { 
        		checkpoint1= true;
        		checkpoint1x= p.x;
        		checkpoint1y= p.y;
        		//System.out.println("checkpoint1");
        	}
        	}
        }
    public void checkCheckpoint2Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints2Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y) ) { 
        		checkpoint2= true;
        		checkpoint2x= p.x;
        		checkpoint2y= p.y;
        		//System.out.println("checkpoint2");
        	}
        	}
        }
    public void checkCheckpoint3Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints3Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y) ) { 
        		checkpoint3= true;
        		checkpoint3x= p.x;
        		checkpoint3y= p.y;
        		//System.out.println("checkpoint3");
        	}
        	}
        }
    public void checkCheckpoint4Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints4Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y) ) { 
        		checkpoint4= true;
        		checkpoint4x= p.x;
        		checkpoint4y= p.y;
        		//System.out.println("checkpoint4");
        	}
        	}
        }
    
    
    
    public void selectMap(Track t)			
    {

        currentTrack = t;
        carsInit(carNumber);

        resetGameTime();


        state = STATE.GAME;
        this.mapSelected=true;
    }
    

    public void mapInit()
    {
    	lindholmen = new LindholmenDerby(borderX,borderY);
    }
        


    private void savePosition( int xPosition, int yPosition){
        positionList.add(new Point(xPosition, yPosition));
    }
    
    private void saveAngle(Double angle){
        angleList.add(angle);
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
    public Track getCheckpoints()
    {
       return currentCheckpoints;
    }
    public STATE getState()
    {
        return this.state;
    }
    public int getBorderX()
    {
    	return borderX;
    }
    public int getBorderY()
    {
    	return borderY;
    }
    public String getBuild()
    {
    	return build;
    }

    public int getCarnmbr()
    {
    	return carNumber;
    }
    public Menu getMenu()		//Returnerar menu. Mest till att rita upp
    {
        return menu;
    }
    public Track getLindholmen()
    {
    	return lindholmen;
    }
    public boolean getMapSelected()
    {
    	return mapSelected;
    }

    public int getGameTimer()
    {
        return gameTimer;

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
    public void stateGame()
    {
            state=STATE.GAME;
    }
    public void stateMenu()
    {
            state=STATE.MENU;
    }  

    public void stateMap()
    {
    		state=STATE.MAP_SELECTION;
    }
    public void stateConfig()
    {
            state=STATE.CARCONFIG;
    }
    public void setCarColor(int c)
    {
    	carColor=c;
    	carList.clear();
    }


    public void resetGameTime()
    {
        gameTimer = 0;
    }
    public void setGameTime()
    {
        gameTimer++;
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