package GameFiles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.*;


public class View extends JPanel implements Observer<Model> {

    private Model m;
    private LinkedList<Car> carList;

    /**
     * Constructor for View, needs an instans of the Model-class to function.
     * @param m Instans of Model-class
     * @see Model
     */
    public View(Model m) {
        this.m = m;
        this.carList = m.getCarList();
    }
    /**
     * extends the paintComponent() method in JComponent. Uses diffrent STATE to determain what should be drawn to the JPanel.
     * @param g recast this Graphics to Graphics2D to be able to handle the diffrent grafic elements of the application.
     * @see STATE
     * @see JComponent
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.gray);
        if(m.getState()==STATE.MENU)					//Rita upp OM meny
        {
        	drawMenu(m.getMenu(),g2d,m.getBorderX(),m.getBorderY());
        	drawMenuText(g2d,m.getMenu(),m.getBorderX(),m.getBorderY());
        }
        if(m.getState()==STATE.MAP_SELECTION)
        {
        	drawMapSelect(m.getMenu(),g2d,m.getBorderX(),m.getBorderY(),m.getLindholmen());
        	drawMenuText(g2d,m.getMenu(),m.getBorderX(),m.getBorderY());
        }
  
        if(m.getState()==STATE.CARCONFIG)				//Rita OM game
        {
            drawConfig(m.getMenu(), g2d,m.getBorderX(),m.getBorderY());
            drawMenuText(g2d,m.getMenu(),m.getBorderX(),m.getBorderY());
        }
      
        if(m.getState()==STATE.GAME)				//Rita OM game
        {
            drawGame(g2d,m.getTrack().getMap());
            
        }
        if(m.getState()==STATE.HIGHSCORE) {
        	drawHighscore(m.getMenu(), g2d,m.getBorderX(),m.getBorderY());
        	drawMenuText(g2d,m.getMenu(),m.getBorderX(),m.getBorderY());
        }
     
        if(m.getState()==STATE.GAMEFINISHED){//m.getGameFinished()== true) {
        	drawFinished(m.getMenu(),g2d,m.getBorderX(),m.getBorderY());
        	
        } 
    }
    /**
     * This method is called when the application enters the Config part of the  user menu. The method gathers information for all the buttons and pictures
     * that needs to be drawn from the menu instans and draws it to the Graphics2D.
     * @param menu  menu-class instans that the method gathers all information for the graphics
     * @param g2d   The Graphics2D instans that all component is drawn to
     * @param x     X cordinate used to draw out the GIF background of the meny
     * @param y     Y cordinate used to draw out the GIF background of the meny
     * @see Graphics2D
     * @see menu
     */
  public void drawConfig(Menu menu, Graphics2D g2d, int x,int y) {
    	m.getMenu().getImg().paintIcon(this, g2d, 0, 0);
    	
    	//BUTTONS
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getredCarBtn());					
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getredCarBtn());
        
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getblueCarBtn());					
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getblueCarBtn());
       
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getgreenCarBtn());					
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getgreenCarBtn());
        
        
        
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getConfigReturnBtn());				
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getConfigReturnBtn());
        //Slutet av buttons
        
        g2d.drawImage(menu.resize(menu.getRedCar(),350,240),(int)menu.getredCarBtn().getX()+10,(int)menu.getredCarBtn().getY()+10,null);
        g2d.drawImage(menu.resize(menu.getGreenCar(),350,240),(int)menu.getgreenCarBtn().getX()+10,(int)menu.getgreenCarBtn().getY()+10,null);
        g2d.drawImage(menu.resize(menu.getBlueCar(),350,240),(int)menu.getblueCarBtn().getX()+10,(int)menu.getblueCarBtn().getY()+10,null);
        
        //text knappar carconfig
        g2d.setFont(new Font("arial",Font.BOLD,50));											
        g2d.drawString("RED",
                (int)menu.getredCarBtn().getX()+(int)menu.getredCarBtn().getWidth()/2-50,
                (int)menu.getredCarBtn().getY()+(int)menu.getredCarBtn().getHeight()-20);
        g2d.drawString("BLUE",
                (int)menu.getblueCarBtn().getX()+(int)menu.getblueCarBtn().getWidth()/2-70,	    
                (int)menu.getblueCarBtn().getY()+(int)menu.getblueCarBtn().getHeight()-20);
        g2d.drawString("GREEN",
                (int)menu.getgreenCarBtn().getX()+(int)menu.getgreenCarBtn().getWidth()/2-90,	    
                (int)menu.getgreenCarBtn().getY()+(int)menu.getgreenCarBtn().getHeight()-20);
        g2d.drawString("RETURN",																	
                (int)menu.getConfigReturnBtn().getX()+(int)menu.getConfigReturnBtn().getWidth()/2-100,
                (int)menu.getConfigReturnBtn().getY()+(int)menu.getConfigReturnBtn().getHeight()/2+20);
        //slut pa text
   }
   /**
    * Draws the Map select part of the menu where the user inputs the map they whant to play. This method only gathers information from the menu-class and Track-class instans
    * and draws it to the Graphics2D.
    * @param menu  menu-class instans that the method gathers all information for the graphics
    * @param g2d   The Graphics2D instans that all component is drawn to
    * @param x     X cordinate used to draw out the GIF background of the meny
    * @param y     Y cordinate used to draw out the GIF background of the meny
    * @param l     Instant of the Track-class to acess the picture of the map
    * @see Graphics2D
    * @see menu
    * @see Track 
    */
    public void drawMapSelect(Menu menu, Graphics2D g2d, int x, int y, Track l)
    {
    	m.getMenu().getImg().paintIcon(this, g2d, 0, 0);
    	
    	
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getReturnBtn());				
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getReturnBtn());
    	
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getMapBtn());
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getMapBtn());
        
        
        g2d.drawImage(l.getIcon(250, 200),(int)menu.getMapBtn().getX()+25,(int)menu.getMapBtn().getY()+25,null);
        
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,30));
        g2d.drawString("LindholmenDerby", (int)menu.getMapBtn().getX()+25, (int)menu.getMapBtn().getY()+(int)menu.getMapBtn().getHeight()-25);
        
        
        
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,50));
        g2d.drawString("RETURN",																	
                (int)menu.getReturnBtn().getX()+(int)menu.getReturnBtn().getWidth()/2-100,
                (int)menu.getReturnBtn().getY()+(int)menu.getReturnBtn().getHeight()/2+20);	
        
        
    }
    /**
     * draws out all the elements that makes up the game. Draws the track, players car, "AI" car and the timer that shows the player the time it takes to drive the track.
     * All (except for drawing the track) this is done through support methods.
     * @param g2d   The Graphics2D instans that all component is drawn to
     * @param map   An bufferimage that is the background an the visible track that the player drivers around
     * @see Graphics2D 
     */
    public void drawGame(Graphics2D g2d, BufferedImage map)
    {
    	 if(m.getMapSelected())						//checks so the image is loaded in with out exeptions
         {
             g2d.drawImage(map, 0,0,this);

         }

         //For loop based on carlist length here if more then 1 Car.
         drawCar(g2d, carList.get(0));
         drawCar(g2d,  carList.get(1)); //draws the AI
         drawTime(g2d);
         
    }
    
    
    /**
     * Draws diffrent strings to the diffrent menu windows to convey info about the application. X and Y is used to determin for one point where all the strings
     * should be drawn. X and Y should be the Width and Height of the frame respectively.
     * @param g2d   The Graphics2D instans that all component is drawn to
     * @param menu  Used to get the colour of the text
     * @param x     X cordinate used to determin where all the text should be rendered
     * @param y     Y cordinate used to determin where all the text should be rendered
     */
    public void drawMenuText(Graphics2D g2d, Menu menu,int x,int y)
    {
    	 //TEXT AV MENU
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,150));
        g2d.drawString("CARGAME",20,150);					//Game title
        
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,20));
        g2d.drawString("Team: Ingen Aning", x-200, y-35);										//Gruppnamn
        g2d.drawString("Devs: David J, Erik L, Jacob W, Kevin P, Victoria B", x-500, y-10);		//Developers
        g2d.drawString(m.getBuild(), 10, y-10);											//build
    }
    
    /**
    * This method is called when the application enters the main-Menu part of the  user menu. The method gathers information for all the buttons and pictures
    * that needs to be drawn from the menu instans and draws it to the Graphics2D. Also draws the overlay text on the buttons provided by the menu instans.
    * @param menu  menu-class instans that the method gathers all information for the graphics
    * @param g2d   The Graphics2D instans that all component is drawn to
    * @param x     X cordinate used to draw out the GIF background of the meny
    * @param y     Y cordinate used to draw out the GIF background of the meny
    * @see Graphics2D
    * @see menu
    */
    public void drawMenu(Menu menu, Graphics2D g2d,int x,int y)
    {
    	setBackground(Color.black);
        
		m.getMenu().getImg().paintIcon(this, g2d, 0, 0);
        
        
       //BUTTONS
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getPlayBtn());					//playBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getPlayBtn());

        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getConfigBtn());					//playBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getConfigBtn());
       
        
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getQuitBtn());					//quitBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getQuitBtn());

        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getHighscoreBtn());					
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getHighscoreBtn());
        //Slutet av buttons
        
        
        //TEXT AV MENU
        g2d.setFont(new Font("arial",Font.BOLD,50));											//Playbtn text
        g2d.drawString("PLAY",
                (int)menu.getPlayBtn().getX()+(int)menu.getPlayBtn().getWidth()/2-60,
                (int)menu.getPlayBtn().getY()+(int)menu.getPlayBtn().getHeight()/2+20);

        g2d.drawString("CONFIG",
                (int)menu.getConfigBtn().getX()+(int)menu.getConfigBtn().getWidth()/2-100,	    //Configbtn text
                (int)menu.getConfigBtn().getY()+(int)menu.getConfigBtn().getHeight()/2+20);

        g2d.drawString("QUIT",																	//Quitbtn text
                (int)menu.getQuitBtn().getX()+(int)menu.getQuitBtn().getWidth()/2-60,
                (int)menu.getQuitBtn().getY()+(int)menu.getQuitBtn().getHeight()/2+20);
        
        g2d.setFont(new Font("arial",Font.BOLD,40));
        g2d.drawString("HIGHSCORES", menu.getHighscoreBtn().x + 15, menu.getHighscoreBtn().y + 65);
        //Slutet av text

        
    }

  public void drawHighscore(Menu menu, Graphics2D g2d,int x,int y) { //målar ut 10 strängar
	  
		m.getMenu().getImg().paintIcon(this, g2d, 0, 0);
	  
	  //BUTTON
	  g2d.setColor(menu.getBtnClr());
      g2d.fill(menu.getReturnBtn());				
      g2d.setColor(menu.getBtnOutClr());
      g2d.draw(menu.getReturnBtn());
	  
      //TEXT
      g2d.setColor(menu.getTitleClr());
      g2d.setFont(new Font("arial",Font.BOLD,50));
      g2d.drawString("RETURN",																	
              (int)menu.getReturnBtn().getX()+(int)menu.getReturnBtn().getWidth()/2-100,
              (int)menu.getReturnBtn().getY()+(int)menu.getReturnBtn().getHeight()/2+20);	
      
      
      g2d.drawString("HIGHSCORE LIST", x/2-200, 300);
      
      
      
	  for(int i=0; i< 10; i++) {
		  
		  g2d.setFont(new Font("arial",Font.BOLD,30));
    	g2d.drawString((i+1)+":		 "+formatScoreString(m.getFileManager().getHighscoreForPosition(i+1)), m.getBorderX()/2-100, m.getBorderY()/2-120 + 40*i);
    	//g2d.setFont(getFont());
    	}
    }

    /**
     * this is runs the repaint() method of Component-class to update the grafics.
     * @see Component
     */
    public void updateView() {
        repaint();
    }
    
    /**
     * Draws the Checkpoint layer of the track, this is to establish where the checkpoints are on the track. Gathering this data (pixel cordinates of the checkpoints)
     * is done in the getCheckpointsPixel-class. 
     * @param g2d           The Graphics2D instans that all component is drawn to
     * @param checkpoints   BufferedImage of the checkpoint layer of the track.
     * @see getCheckpointPixel
     */
    public void drawCheckpoints(Graphics2D g2d, BufferedImage checkpoints) {
    	
            g2d.drawImage(checkpoints, 0,0,this);					//Ritar banan
       
    }


    public void drawFinished(Menu menu, Graphics2D g2d,int x,int y) {
    	
    	m.getMenu().getFinishedImg().paintIcon(this, g2d, 0, 0);
    	g2d.setFont(new Font("arial",Font.BOLD,100));
    	g2d.drawString("GAME FINISHED", x/2-400, 150);
    	
    	g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getPlayAgainBtn());					//playBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getPlayAgainBtn());
        
        g2d.setColor(menu.getBtnClr());
        g2d.fill(menu.getEndGameBtn());					//playBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getEndGameBtn());
        
        g2d.setFont(new Font("arial",Font.BOLD,50));											//Playbtn text
        g2d.drawString("PLAY AGAIN",
                (int)menu.getPlayAgainBtn().getX()+(int)menu.getPlayAgainBtn().getWidth()/2-152,
                (int)menu.getPlayAgainBtn().getY()+(int)menu.getPlayAgainBtn().getHeight()/2+20);
        
        
        g2d.drawString("END GAME",																	//Quitbtn text
                (int)menu.getQuitBtn().getX()+(int)menu.getQuitBtn().getWidth()/2-135,
                (int)menu.getQuitBtn().getY()+(int)menu.getQuitBtn().getHeight()/2+20);
    }
    


    /**
     * Draws a car based on the Car-class, rescales the chosen car color png and rotates the image to desired angle found in the Car-class. Rescaling is done throw the Image method getScaledInstance().
     * rotateing the image is done throw the Graphics2D method rotate().
     *  
     * @param g2d   The Graphics2D instans that all component is drawn to
     * @param car   Car-class instance to get information on choosen png, cordiantes and desired size
     * @see Image
     * @see Graphics2D
     * @see Car
     */
    private void drawCar(Graphics2D g2d, Car car) {
       
        //rescales Car img to prefferd size
        Image resultingImage = car.getCarIMG().getScaledInstance(car.getWidth(), car.getHeight(), Image.SCALE_SMOOTH);
        //Before changing our Graphic2D we need to make a backup of everything else
        AffineTransform backup2 = g2d.getTransform(); 

       g2d.rotate(car.getAngle() + (3*Math.PI)/2, car.getPositionX(), car.getPositionY()); //rotate the car image
        
        g2d.drawImage(resultingImage, car.getPositionX() - (car.getWidth() / 2) , car.getPositionY() - (car.getHeight() / 2) , null);
        g2d.setTransform(backup2);
        
    }
    
    
   
    /**
     * This method is used to convert time of centiseconds i form of a string to a more presenteble string in the format min:sec:centisec.
     * @param scoreString   highscore placement represented as milisecounds as a String 
     * @return              A string that represent a Highscore time in the form min:sec:hundredths
     */
    public String formatScoreString(String scoreString)
    {
        int highscore = Integer.parseInt(scoreString);
        int minutes = highscore / 6000;
        int seconds = (highscore % 6000)/100;
        int hundredths = highscore % 100;
        String formatScoreString = minutes + ":" + seconds + ":" + hundredths;
        return formatScoreString;
    }
    
    
    
    /**
     * Draws the current time the player has aswell as draws out the current highest highscore on screen. 
     * @param g2d   The Graphics2D instans that all component is drawn to
     */
    private void drawTime(Graphics2D g2d){
    	g2d.setColor(Color.black);
    	String gameTimer = "" + m.getGameTimer();
        g2d.setFont(new Font("arial",Font.BOLD,20));
        g2d.drawString(formatScoreString(gameTimer), 20, 150);
        g2d.drawString(formatScoreString(m.getCurrentHighscore()), 20, 200);
    }


    /**
     * this method is part of the obeserveble pattern that is used between the Model-class and View-class for more info see the Observer interface.
     * Because View is an observer type class, the Model-class calls this method to notify View of an change. When View is notifyed repaint() is called,
     * This causes all grafics to be updated.
     * @see Observable
     * @see Observer
     */
    @Override
    public void update(Model observable) {
        repaint();
    }
}