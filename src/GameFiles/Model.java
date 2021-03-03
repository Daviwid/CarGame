package GameFiles;


import java.util.LinkedList;
import GameFiles.Controller.GameTimer;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

public class Model implements Observable<Model>{
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
	
    private boolean gameFinished=false;
	private boolean carCrash=false;

	private boolean point1=false;
	private boolean point2=false;
	private boolean point3=false;
	private boolean point4=false;
	
	private int checkpoint1x;
	private int checkpoint1y;
	private int checkpoint2x;
	private int checkpoint2y;
	private int checkpoint3x;
	private int checkpoint3y;
	
    private static int TOPSPEED = 10;
    private static int height = 50;
    private static int width = 30;
    private int carNumber = 1;
    private Track currentTrack,lindholmen;
    private String currentHighscore;
    private int gameTimer;
    private Menu menu;
    private STATE state;
    private int carColor;
    private String build = "Build v. 1.0.0.0";
   

    private ArrayList<Point> positionList = new ArrayList<Point>();
    private ArrayList<Double> angleList = new ArrayList<Double>();

	private Iterator<Point> ai_point;
	private Iterator<Double> ai_angle;

	private ArrayList<Point> aip = new ArrayList<Point>();
	private ArrayList<Double> aia = new ArrayList<Double>();
	
    private final Collection<Observer<Model>> observers;
    
    
    private FileManager fileManager;

    private MainSoundEffect mainSound;
    private SoundEffectCarCollision s;
    // private Controller controller;
    public Model()
    {
        this.observers = new HashSet<>();
        fileManager = new FileManager();
        carColor = fileManager.configGetCarColor();
    }
    
    public void carsInit(int carNumber)
    {
    	ai_point = aip.iterator();
    	ai_angle = aia.iterator();
        for(int i = 0; i < carNumber; i++)
        {
        	carList.add(new Car(carColor, 0, 0, TOPSPEED, height, width));
        }
        carList.add(new Car(1, 0, 0, TOPSPEED, height, width));
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
            if(checkpoint4) {

         	   mainSound.closeAudio();
            	gameFinished();
            	mainSound.stopAudio();
                new Client(gameTimer,positionList,angleList);
            }
           if(point1==false) {
            checkCheckpoint1Hitboxes();
           }
           if(point2==false) {
            checkCheckpoint2Hitboxes();
           }
           if(point3==false) {
            checkCheckpoint3Hitboxes();
           }
           if(point4==false) {
            checkCheckpoint4Hitboxes();
           }
           checkHitboxes();

           
        	moveCar();
        	moveAI();
            savePosition(carList.get(0).getPositionX(), carList.get(0).getPositionY());  //for loop if we have more then 1 player
            saveAngle(carList.get(0).getAngle());                                           // same here...
        }

        updateObservers();
    }
    public void moveAI()
    {
    	if(ai_angle.hasNext())
    	{
    		Point p = ai_point.next();
    		carList.get(1).setPositionAI(p.x,p.y);
    		carList.get(1).setAngleAI(ai_angle.next());
    	}
    }
    
    private void moveCar()
    {
        if(pressedUp)
        {
            carList.get(0).accelerate();
        }
        if(pressedDown)
        {
            carList.get(0).decelerate();
        }
        if(pressedRight)
        {
            carList.get(0).turnRight();
        }
        if(pressedLeft)
        {
            carList.get(0).turnLeft();
        }
        carList.get(0).move();
    }
    
    public void checkBorder() {
    	if(carList.get(0).getPositionX() >= borderX || carList.get(0).getPositionX() <= 0 || carList.get(0).getPositionY() >= borderY || carList.get(0).getPositionY() <= 0) { 
    		carList.get(0).turnDirection(); 
    	}
    	
    }
  
    /* source: https://stackoverflow.com/questions/17136084/checking-if-a-point-is-inside-a-rotated-rectangle/17146376*/
    public boolean overlapsWith(double px, double py){ //px, py kordinater till checkpoints
    	/*double width= (double) carList.get(0).getWidth()/2;
    	double height= (double)carList.get(0).getHeight()/2;*/
    	double width= (double) carList.get(0).getWidth()/10;
    	double height= (double)carList.get(0).getHeight()/10;
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
    
    public void gameFinished() {
    	state= STATE.GAMEFINISHED;
    }
   public void checkHitboxes() {  //checks if objects position overlaps with one of the tracks
    Iterator<Point> it = currentTrack.getHitbox().iterator();
    int count=0;
        while(it.hasNext())
        {   
	        Point p = it.next();
	    	if( overlapsWith(p.x, p.y) )
	    	{ 
	    		if(count==0)
	    		{ 
	    			carList.get(0).turnDirection(); count++;
	    		}
	    		//mainSound.closeAudio();       
	    		try {
	    			//s = new SoundEffectCarCollision();
	    		} catch (Exception e1) {
	    			e1.printStackTrace();
	    		}
	    		Toolkit.getDefaultToolkit().beep();
	    		if(checkpoint1){
	    			carList.get(0).setCheckpointPosition(checkpoint1x, checkpoint1y);
	    			carList.get(0).collisionSpeed();
	    			carList.get(0).turnDirection();
	    		}
	    		else if(checkpoint2)
	    		{
	    			carList.get(0).setCheckpointPosition(checkpoint2x , checkpoint2y);
	    			carList.get(0).collisionSpeed();
	    			carList.get(0).turnDirection();
	    		}
	    		
	    		else if(checkpoint3)
	    		{
	    			carList.get(0).setCheckpointPosition(checkpoint3x , checkpoint3y);
	    			carList.get(0).collisionSpeed();
	    			carList.get(0).turnDirection();
	    		}
	    		else
	    		{
	    			carList.get(0).setStartPosition(currentTrack);
	    			carList.get(0).setStartAngle(currentTrack);
	    		}
	    		
	    	}
    	
    	}
    }
    public void checkCheckpoint1Hitboxes() {  
    	
        Iterator<Point> it = currentTrack.getCheckpoints1Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            
           if( overlapsWith(p.x, p.y)) { 
            	checkpoint1= true;
            	checkpoint1x= p.x;
            	checkpoint1y= p.y;
            	
        	}
        	}
        }
    public void checkCheckpoint2Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints2Hitbox().iterator();
            while(it.hasNext()) {
	            Point p = it.next();
	            if( overlapsWith(p.x, p.y) )
	            { 
	            	if(checkpoint1)
	            	{
	            		checkpoint1=false;
		        		checkpoint2= true;
		        		checkpoint2x= p.x;
		        		checkpoint2y= p.y;
	            	}
	            }
        	}
        }
    public void checkCheckpoint3Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints3Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y))
            { 
            	if(checkpoint2)
            	{
            		checkpoint2=false;
	        		checkpoint3= true;
	        		checkpoint3x= p.x;
	        		checkpoint3y= p.y;
            	}
        	}
        	}
        }
    public void checkCheckpoint4Hitboxes() {  
        Iterator<Point> it = currentTrack.getCheckpoints4Hitbox().iterator();
            while(it.hasNext()) {
            Point p = it.next();
            if( overlapsWith(p.x, p.y) ) { 
            	if(checkpoint3)
            	{
            		checkpoint3=false;
            		checkpoint4= true;
            	}
        	}
        	}
        }
    
    
    
    public void selectMap(Track t)			
    {
        currentTrack = t;
        aip = fileManager.getHighscorePositionList();
        aia = fileManager.getHighscoreAngleList();
        carList.clear();
        carsInit(carNumber);
        currentHighscore = fileManager.getHighscoreForPosition(1);
        carList.get(0).setStartPosition(currentTrack);
        carList.get(0).setStartAngle(currentTrack);
        state = STATE.GAME;
        this.mapSelected=true;
        resetCheckBox();  //mainly for playagainbutton
        resetCarFlags();
        resetGameTimer();
        try {
        	mainSound = new MainSoundEffect();  
		} catch (Exception e) {
			
			e.printStackTrace();
		}
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
    
    public void saveAI()
    {
    	fileManager.recieveHighscoreStringFromServer(fileManager.sendScoreToServer(1, positionList, angleList));
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
    public String getCurrentHighscore()
    {
    	return currentHighscore;
    }
    public FileManager getFileManager(){
        return fileManager;
    }
    public STATE getState()
    {
        return this.state;
    }
    public boolean getCarCrash() {
    	return carCrash;
    }
    public boolean getGameFinished() {
    	return gameFinished;
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
    public int getGameTimer(){
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
    public void setCarColor(int c)
    {
    	fileManager.configSetCarColor(c);
    	carColor=c;
    	carList.clear();
    }
    public void stateGame()
    {
            state=STATE.GAME;
    }
    public void stateMenu()
    {
            mainSound.stopAudio(); 
            mainSound.closeAudio();
            state=STATE.MENU;
            mainSound.stopAudio();
            mainSound.closeAudio();
    }  
    public void stateMap()
    {
    		state=STATE.MAP_SELECTION;
    }
    public void stateConfig()
    {
            state=STATE.CARCONFIG;
            
    }
    public void stateHighscore()
    {
            state=STATE.HIGHSCORE;
    }
    public void stateFinished()
    {
            state=STATE.GAMEFINISHED;
    }
    public void resetGameTimer(){
        gameTimer = 0;
    }
    public void setGameTimer()
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
