package GameFiles;

import  java.awt.event.*;   

public class KeyInput implements KeyListener
{
    private Controller c;
    public KeyInput(Controller c)
    {
        this.c=c;
    }

    /**
     * Reacts to non-numpad arrows (up,down,right and left) and escape key.
     * arrows is used to controll the Car in game while escape can be used to return to menu or in some situations colse the application.
     * all manipulation works by setting diffrent flags in an Model-instans, this allows holding multiple keys at the same time.
     */
    public void keyPressed(KeyEvent e)
    {   
    	int k = e.getKeyCode(); 
        if(c.getModel().getState()==STATE.GAME)		//Activate specific keyinput only if state is game
        {
            if(k == KeyEvent.VK_UP)
            {
                c.getModel().setPressedUp();   
            }
            else if(k == KeyEvent.VK_LEFT)
            {
                c.getModel().setPressedLeft(); 
            }
            else if(k == KeyEvent.VK_RIGHT)
            {
                c.getModel().setPressedRight();    
            }
            else if(k == KeyEvent.VK_DOWN)
            {
                c.getModel().setPressedDown(); 
            }
        }
            if(k == KeyEvent.VK_ESCAPE)					//Allways activated
            {
                if(c.getModel().getState()==STATE.MENU)		//Quit Application
                {
                    System.exit(0);
                }
                
                //Open menu
                if(c.getModel().getState()==STATE.GAME || c.getModel().getState()==STATE.MAP_SELECTION ||
                		c.getModel().getState()==STATE.CARCONFIG || c.getModel().getState()==STATE.HIGHSCORE ||
                		c.getModel().getState()==STATE.GAMEFINISHED)		
                {
                    c.getModel().stateMenu();
                }

            }
    } 

    public void keyTyped(KeyEvent e){}

    /**
     * Reacts to non-numpad arrows (up,down,right and left).
     * arrows is used to controll the Car in game.
     * all manipulation works by setting diffrent flags in an Model-instans, this allows holding multiple keys at the same time.
     */
    public void keyReleased(KeyEvent e)
    {
        if(c.getModel().getState()==STATE.GAME)
        {
            int k = e.getKeyCode(); 
            if(k == KeyEvent.VK_UP)
            {
                c.getModel().setReleasedUp();  
            }
            else if(k == KeyEvent.VK_DOWN)
            {
                c.getModel().setReleasedDown(); 
            }
            else if(k == KeyEvent.VK_LEFT)
            {
                c.getModel().setReleasedLeft(); 
            }
            else if(k == KeyEvent.VK_RIGHT)
            {
                c.getModel().setReleasedRight();   
            }
        }
    }
}

