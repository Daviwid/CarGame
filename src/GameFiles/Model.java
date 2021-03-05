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
    
    private int laps = 0;
    private static int maxlaps = 3;
    private int carNumber = 1;
    private Track currentTrack,lindholmen;
    private String currentHighscore;
    private int gameTimer;
    private Menu menu;
    private STATE state;
    private int carColor;
    private String build = "Build v. 2.1.3.0";
    
    private String[] highscoreList = new String[10];
  
    private ArrayList<Point> positionList = new ArrayList<Point>();
    private ArrayList<Double> angleList = new ArrayList<Double>();

    private Iterator<Point> ai_point;
    private Iterator<Double> ai_angle;

    private ArrayList<Point> aip = new ArrayList<Point>();
    private ArrayList<Double> aia = new ArrayList<Double>();
	
    private final Collection<Observer<Model>> observers;
    
    
    private FileManager fileManager;

    private MainSoundEffect mainSound;

    /**
     * Constructor that initiates the obeserver pattern, it also sets the base color of the car aswell as initate the player Car instans.
     */
    public Model()
    {
        this.observers = new HashSet<>();
        fileManager = new FileManager();
        carColor = fileManager.configGetCarColor();
        try {
        	mainSound = new MainSoundEffect();  //call on new audio thread to play
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    

    /**
     * creates an instans of a Car-class and adds it to a arraylist that holds all the instanses. The parameter carNumber is where in the arraylist the Car-instans is 
     * added to, so if it is called agian with the same value of carNumber the old instant will be overwriten. The method also adds the "AI Car" to the position after
     * the Car-instans is added (carNumber + 1). If u add several cars in a row the last index of the arraylist will always be the "AI Car".
     * 
     * @param carNumber The index for where the the car will be added to the arraylist of car instanses.
     * @see Car 
     */
    public void carsInit(int carNumber)
    {
    	ai_point = aip.iterator();
    	ai_angle = aia.iterator();
        for(int i = 0; i < carNumber; i++)
        {
        	carList.add(new Car(carColor, 0, 0, TOPSPEED, height, width));
        }
        carList.add(new Car(3, 0, 0, TOPSPEED, height, width));
    }
    
    public void makeHighscoreList()
    {
    	for(int i=0;i<10;i++)
    	{
    		highscoreList[i] = fileManager.getHighscoreForPosition(i+1);
    	}
    }
    
    public String[] getHighscoreList()
    {
    	return highscoreList;
    }

    public void resetCarFlags()  //reset all car keys flags
    {
    	pressedUp=false;
    	pressedDown=false;
    	pressedLeft=false;
    	pressedRight=false;
    }
    
    public void resetCheckBox() {  ///reset checkpoint flags
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
        if(state==STATE.GAME)	//only checks if you're in Game mode(playing)
        {
        	
            checkBorder();
            if(checkpoint4 && laps==maxlaps) {  //if car drives past checkpoint 4 and has past maxlaps, call on statefinished(changes state to FINISHED)
            	stateFinished();
                new Client(gameTimer,positionList,angleList);
            }
           if(point1==false) {  //for each checkpoint, checks if the car has drove past it
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
           checkHitboxes();  //checks if the car has collided with track rim

           
        	moveCar();
        	moveAI();
            savePosition(carList.get(0).getPositionX(), carList.get(0).getPositionY());  //saves the position of current car for AI
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
    
    public void checkBorder() {  //method checks if the car is outside the screen border, in that case changes the direction of the car for a bounce effect
    	if(carList.get(0).getPositionX() >= borderX || carList.get(0).getPositionX() <= 0 || carList.get(0).getPositionY() >= borderY || carList.get(0).getPositionY() <= 0) { 
    		carList.get(0).turnDirection(); 
    	}
    	
    }
  
    /**
     * Method takes the cordinates of a point p from hitbox, and use the car current position, Calculates the sum of areas
    △APD, △DPC, △CPB, △PBA which are the triangle area from point A to P to D etc. 
    If the sum is greater than the area of the rectangle, then point P(x,y) is outside the area of the rectangle.
    else it is in or on the rectangle
     * @param px x cordinate to checkpoints or hitbox
     * @param py y cordinate to checkpoints or hitbox
    * @return returns true if car is on a hitbox or checkpoint cordinate, otherwise false
    */
    public boolean overlapsWith(double px, double py){ 
    	double width= (double) carList.get(0).getWidth()/10;
    	double height= (double)carList.get(0).getHeight()/10;
    	double carx= carList.get(0).getPositionX(); //car position
    	double cary= carList.get(0).getPositionY();
    	
    	double rectarea = (2*height)*(2*width); //the area of the car
      //creates  4 triangles
    	double APD = Math.abs((px * (cary - height) - (carx - width) * py) + ((carx + width) * py - px * (cary + height)) + ((carx - width) * (cary + height) - (carx + width) * (cary - height)))/2;
    	double DPC = Math.abs((px * (cary + height) - (carx + width) * py) + ((carx - width) * py - px * (cary + height)) + ((carx + width) * (cary + height) - (carx - width) * (cary + height)))/2;
    	double CPB = Math.abs((px * (cary + height) - (carx - width) * py) + ((carx + width) * py - px * (cary - height)) + ((carx - width) * (cary - height) - (carx + width) * (cary + height)))/2;  
    	double PBA = Math.abs(((carx + width) * py - px * (cary - height)) + ((carx - width) * (cary - height) - (carx + width) * (cary - height)) + (px * (cary - height) - (carx - width) * py))/2;
    	double sum = APD + DPC + CPB + PBA; // total area sum of all 4 triangles
    	
    	if(sum > rectarea ) return false;  
        return true; 
    	} 
    /**
     * checks if objects position overlaps with one of the tracks
     */
    public void checkHitboxes() {  
    Iterator<Point> it = currentTrack.getHitbox().iterator();
        while(it.hasNext())
        {   
	        Point p = it.next();
	    	if( overlapsWith(p.x, p.y) )
	    	{ 
	    		Toolkit.getDefaultToolkit().beep();
	    		if(checkpoint1){
	    			carList.get(0).setCheckpointPosition(checkpoint1x, checkpoint1y);
	    			carList.get(0).collisionSpeed();
	    		}
	    		else if(checkpoint2)
	    		{
	    			carList.get(0).setCheckpointPosition(checkpoint2x , checkpoint2y);
	    			carList.get(0).collisionSpeed();
	    		}
	    		
	    		else if(checkpoint3)
	    		{
	    			carList.get(0).setCheckpointPosition(checkpoint3x , checkpoint3y);
	    			carList.get(0).collisionSpeed();
	    		}
	    		else  //if the car hasn't drove past a checkpoint yet
	    		{
	    			carList.get(0).setStartPosition(currentTrack); 
	    			carList.get(0).setStartAngle(currentTrack);
	    		}
	    		
	    	}
    	
    	}
    }
    
    /**
     * checks if the car has drove past checkpoint n and notifies updateModel through checkpoint variables for each checkpoint 
     * */
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
            		laps++;
            	}
        	}
        	}
        }
    
    private void resetGame(){
        resetCheckBox();  //mainly for playagainbutton, reset all flags so the game is resetted for next game
        resetCarFlags();
        resetGameTimer();
    }
    
    public void selectMap(Track t)			
    {
        currentTrack = t;
        aip = fileManager.getHighscorePositionList();
        aia = fileManager.getHighscoreAngleList();
        carList.clear();
        laps=0;
        carsInit(carNumber);
        currentHighscore = fileManager.getHighscoreForPosition(1);
        carList.get(0).setStartPosition(currentTrack);
        carList.get(0).setStartAngle(currentTrack);
        state = STATE.GAME;
        this.mapSelected=true;
        resetGame();
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
    	fileManager.recieveStringFromServer(fileManager.sendScoreToServer(1, positionList, angleList));
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
    public Menu getMenu()		//Returns Menu,mostly for drawing
    {
        return menu;
    }
    public Track getLindholmen()
    {
    	return lindholmen;
    }
    public int getLaps()
    {
    	return laps;
    }
    public int getMaxLaps()
    {
    	return maxlaps;
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
    public void stateHighscore()
    {
    		makeHighscoreList();
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
