package GameFiles;

import  java.awt.event.*;   

public class MouseInput implements MouseListener{
    private Controller c;
    public MouseInput(Controller c)
    {
        this.c=c;
    }
    public void mouseClicked(MouseEvent e)
    {   
        int x = e.getX();		//The x,y coordinates of the mouseclick
        int y = e.getY();
        
        //Check the current STATE to see what buttons to be "activated" and possible to click on
        if(c.getModel().getState()==STATE.MENU)		
        {           
        	menuButtons(c.getModel(), c.getModel().getMenu(),x,y);
        }
        if(c.getModel().getState()==STATE.CARCONFIG) {
        	carConfigButtons(c.getModel(), c.getModel().getMenu(),x,y);
        }  
        if(c.getModel().getState()==STATE.MAP_SELECTION)	
        {           
        	selectMapButtons(c.getModel(), c.getModel().getMenu(),x,y);
        }
        if(c.getModel().getState()==STATE.GAMEFINISHED) {
        	
        	playagainOptionButtons(c.getModel(), c.getModel().getMenu(),x,y);
        }
        if(c.getModel().getState()==STATE.HIGHSCORE)		
        {           
        	selectHighscoreButtons(c.getModel(), c.getModel().getMenu(),x,y);
        }
        
        
    }
    public void menuButtons(Model model, Menu m,int x,int y)
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
    
    public void carConfigButtons(Model model, Menu m, int x, int y)
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
    
    public void selectMapButtons(Model model, Menu m, int x, int y)
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
    
    public void playagainOptionButtons(Model model, Menu m, int x, int y) 
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
    
    public void selectHighscoreButtons(Model model, Menu m, int x, int y)
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

