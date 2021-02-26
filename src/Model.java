import java.util.LinkedList;



import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Model implements Observable<Model> {
    //Instansvariabler:
    private LinkedList<Car> carList = new LinkedList<Car>();
    private int borderX;
    private int borderY;
    private double saveHitspotX;
    private double saveHitspotY;
    private boolean mapSelected = false;
    private boolean pressedUp = false;
    private boolean pressedDown = false;
    private boolean pressedRight = false;
    private boolean pressedLeft = false;
    private static int TOPSPEED = 10;
    private static int height = 30;
    private static int width = 20;
    private int carNumber = 1;
    private Track currentTrack;
    private Menu menu;
    private STATE state;
    private String build = "Build v. 1.0.0.0";

	private int gameTimer;
    private ArrayList<Point> positionList = new ArrayList<Point>();
    private ArrayList<Double> angleList = new ArrayList<Double>();
    
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
    
    public void menuInit()			//Skapar meny och state = menu
    {
        menu = new Menu(borderX, borderY);
        state = STATE.MENU;
    }
    
    public Menu getMenu()		//Returnerar menu. Mest till att rita upp
    {
        return menu;
    }
    
    public boolean getSelected()		//Boolean till om man valt en bana varav bild laddats in. Genererar annars fel.
    {
        return mapSelected;
    }
    
    public void updateModel()
    {
        if(state==STATE.GAME)	//Kolla endast om man spelar. Genererar annars exceptions
        {
            
			checkBorder();
            checkHitboxes();
            moveCar();
			
            savePosition(carList.get(0).getPositionX(), carList.get(0).getPositionY());  //for loop if we have more then 1 player
            saveAngle(carList.get(0).getAngle());                                        // same here...
        }
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
    	
    }
    
    
    /* source: https://stackoverflow.com/questions/17136084/checking-if-a-point-is-inside-a-rotated-rectangle/17146376*/
    public boolean overlapsWith(double px, double py){ //px, py �r kordinater till track
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
    	saveHitspotX= px;
    	saveHitspotY= py;
        return true; 
    	} 
        
    

    
    public void checkHitboxes() {  //
    Iterator<Point> it = currentTrack.getHitbox().iterator();
        while(it.hasNext()) {
        boolean temp= false;
        Point p = it.next();
    	if( overlapsWith(p.x, p.y) ) { 
    		//carList.get(0).collisionSpeed();
    		//Toolkit.getDefaultToolkit().beep();
    		carList.get(0).turnDirection(); 

    		
    		
    		 temp= true; 
    		 System.out.println(carList.get(0).getSpeed());
    	}
    
    	/*if(temp==true && (
    			(carList.get(0).getPositionX() >= saveHitspotX + 10) || //car slows down after collision for a few seconds, men bugg
    			(carList.get(0).getPositionX() <= saveHitspotX - 10) || 
    			(carList.get(0).getPositionY() >= saveHitspotY + 10) || 
    			(carList.get(0).getPositionY() <= saveHitspotY - 10))) 
    	{
    	carList.get(0).toNormalSpeed();
    	}*/
        }
    }
        
    public void selectMap()				//Annorlunda om man har fler banor. Byter state till game och skapar track som ska scale med screen
    {
        currentTrack = new LindholmenDerby(borderX,borderY);
        resetGameTime();
        state = STATE.GAME;
        this.mapSelected=true;
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
