package GameFiles;

import  java.awt.event.*;   

/**
 * The MouseInput class handels all mouse inputs from a user when they interact with the application.
 * @version 2.1.3.0
 * @since 2021-03-05
 */
public class MouseInput implements MouseListener{
    private Model m;

    /**
     * Needs an Model-instans to be able to get the menu-class information aswell as Model acts like a holder for diffrent button information.
     * Model is also used to see what state the game is in which detarmain how this MouseListener reacts to input.
     * @param m Model.instans to be able to manipulate flags.
     */
    public MouseInput(Model m)
    {
        this.m = m;
    }

    /**
     * Takes in the information on where the user has clicked and determin if this is a place of a button or not.
     */
    public void mouseClicked(MouseEvent e)
    {   
        int x = e.getX();		//The x,y coordinates of the mouseclick
        int y = e.getY();
        
        //Check the current STATE to see what buttons to be "activated" and possible to click on
        if(m.getState()==STATE.MENU)		
        {           
        	menuButtons(m, m.getMenu(),x,y);
        }
        if(m.getState()==STATE.CARCONFIG) {
        	carConfigButtons(m, m.getMenu(),x,y);
        }  
        if(m.getState()==STATE.MAP_SELECTION)	
        {           
        	selectMapButtons(m, m.getMenu(),x,y);
        }
        if(m.getState()==STATE.GAMEFINISHED) {
        	
        	playagainOptionButtons(m, m.getMenu(),x,y);
        }
        if(m.getState()==STATE.HIGHSCORE)		
        {           
        	selectHighscoreButtons(m, m.getMenu(),x,y);
        }
        
        
    }

    /**
     * Checks if X and Y cordinates is with in any button if so change STATE. This method do this for the MENU-STATE.
     * @param model Model-instans used for changing STATE if user has clicked on a button
     * @param m     Menu-instans to get information on where the button is
     * @param x     X-Cordinate where user clicked
     * @param y     Y-cordinate where user clicked
     * @see STATE
     */
    private void menuButtons(Model model, Menu m,int x,int y)
    {
    	//Check on playBtn
        if(x >= m.getPlayBtn().getX() && x <= m.getPlayBtn().getX()+m.getPlayBtn().getWidth())
        {
            if(y >= m.getPlayBtn().getY() && y <= m.getPlayBtn().getY()+m.getPlayBtn().getHeight())
            {
            	model.stateMap();
            }
        }
      	//Check on ConfigBtn 
        if(x >= m.getConfigBtn().getX() && x <= m.getConfigBtn().getX()+m.getConfigBtn().getWidth())
        {
            if(y >= m.getConfigBtn().getY() && y <= m.getConfigBtn().getY()+m.getConfigBtn().getHeight())
            {
                model.stateConfig();
            }
        }
        //Check on quitBtn
        if(x >= m.getQuitBtn().getX() && x <= m.getQuitBtn().getX()+m.getQuitBtn().getWidth())
        {
            if(y >= m.getQuitBtn().getY() && y <= m.getQuitBtn().getY()+m.getQuitBtn().getHeight())
            {
                System.exit(0);
            }
        }
        //Check on HighscoreBtn
        if(x >= m.getHighscoreBtn().getX() && x <= m.getHighscoreBtn().getX()+m.getHighscoreBtn().getWidth())
        {
            if(y >= m.getHighscoreBtn().getY() && y <= m.getHighscoreBtn().getY()+m.getHighscoreBtn().getHeight())
            {
                model.stateHighscore();
            }
        }  
    }
    
    /**
     * Checks if X and Y cordinates is with in any button if so change STATE. This method do this for the CARCONFIG-STATE.
     * @param model Model-instans used for changing STATE if user has clicked on a button
     * @param m     Menu-instans to get information on where the button is
     * @param x     X-Cordinate where user clicked
     * @param y     Y-cordinate where user clicked
     * @see STATE
     */
    private void carConfigButtons(Model model, Menu m, int x, int y)
    {
    	//RedcarBtn
    	if(x >= m.getredCarBtn().getX() && x <= m.getredCarBtn().getX()+m.getredCarBtn().getWidth())
        {
            if(y >= m.getredCarBtn().getY() && y <= m.getredCarBtn().getY()+m.getredCarBtn().getHeight())
            {
            	model.setCarColor(0);
            	model.stateMenu();
            }
        }
    	//GreencarBtn
    	if(x >= m.getgreenCarBtn().getX() && x <= m.getgreenCarBtn().getX()+m.getgreenCarBtn().getWidth())
        {
            if(y >= m.getgreenCarBtn().getY() && y <= m.getgreenCarBtn().getY()+m.getgreenCarBtn().getHeight())
            {
            	model.setCarColor(1);
            	model.stateMenu();
            }
        }
    	//BluecarBtn
    	if(x >= m.getblueCarBtn().getX() && x <= m.getblueCarBtn().getX()+m.getblueCarBtn().getWidth())
        {
            if(y >= m.getblueCarBtn().getY() && y <= m.getblueCarBtn().getY()+m.getblueCarBtn().getHeight())
            {
            	model.setCarColor(2);
            	model.stateMenu();
            }
        }
    	//ReturnBtn
    	if(x >= m.getConfigReturnBtn().getX() && x <= m.getConfigReturnBtn().getX()+m.getConfigReturnBtn().getWidth())
        {
            if(y >= m.getConfigReturnBtn().getY() && y <= m.getConfigReturnBtn().getY()+m.getConfigReturnBtn().getHeight())
            {
            	model.stateMenu();
            }
        }  
    }
    
    /**
     * Checks if X and Y cordinates is with in any button if so change STATE. This method do this for the MAP_SELECT-STATE.
     * @param model Model-instans used for changing STATE if user has clicked on a button
     * @param m     Menu-instans to get information on where the button is
     * @param x     X-Cordinate where user clicked
     * @param y     Y-cordinate where user clicked
     * @see STATE
     */
    private void selectMapButtons(Model model, Menu m, int x, int y)
    {
        //Check on returnBtn
    	if(x >= m.getReturnBtn().getX() && x <= m.getReturnBtn().getX()+m.getReturnBtn().getWidth())
        {
            if(y >= m.getReturnBtn().getY() && y <= m.getReturnBtn().getY()+m.getReturnBtn().getHeight())
            {
            	model.stateMenu();
            }
        }  
        //Check on map lindholmen
        if(x >= m.getMapBtn().getX() && x <= m.getMapBtn().getX()+m.getMapBtn().getWidth())
        {
            if(y >= m.getMapBtn().getY() && y <= m.getMapBtn().getY()+m.getMapBtn().getHeight())
            {
                model.selectMap(model.getLindholmen());
                model.stateGame();
            }
        }
    }

    /**
     * Checks if X and Y cordinates is with in any button if so change STATE. This method do this for the GAMEFINISHED-STATE.
     * @param model Model-instans used for changing STATE if user has clicked on a button
     * @param m     Menu-instans to get information on where the button is
     * @param x     X-Cordinate where user clicked
     * @param y     Y-cordinate where user clicked
     * @see STATE
     */
    private void playagainOptionButtons(Model model, Menu m, int x, int y) 
    {
    	//Check PlayAgainBtn
    	if(x >= m.getPlayAgainBtn().getX() && x <= m.getPlayAgainBtn().getX()+m.getPlayAgainBtn().getWidth())
        {
            if(y >= m.getPlayAgainBtn().getY() && y <= m.getPlayAgainBtn().getY()+m.getPlayAgainBtn().getHeight())
            {
            	model.stateMap();
            }
        }
    	//Check EndGameBtn
    	if(x >= m.getEndGameBtn().getX() && x <= m.getEndGameBtn().getX()+m.getEndGameBtn().getWidth())
        {
            if(y >= m.getEndGameBtn().getY() && y <= m.getEndGameBtn().getY()+m.getEndGameBtn().getHeight())
            {
            	model.stateMenu();
            }
        }
    }
    
    /**
     * Checks if X and Y cordinates is with in any button if so change STATE. This method do this for the HIGHSCORE-STATE.
     * @param model Model-instans used for changing STATE if user has clicked on a button
     * @param m     Menu-instans to get information on where the button is
     * @param x     X-Cordinate where user clicked
     * @param y     Y-cordinate where user clicked
     * @see STATE
     */
    private void selectHighscoreButtons(Model model, Menu m, int x, int y)
    {
      //Check returnBtn
    	if(x >= m.getReturnBtn().getX() && x <= m.getReturnBtn().getX()+m.getReturnBtn().getWidth())
        {
            if(y >= m.getReturnBtn().getY() && y <= m.getReturnBtn().getY()+m.getReturnBtn().getHeight())
            {
            	model.stateMenu();
            }
        }
    }
    
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
} 

