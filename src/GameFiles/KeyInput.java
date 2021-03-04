package GameFiles;

import  java.awt.event.*;   

public class KeyInput implements KeyListener
{
    private Controller c;
    public KeyInput(Controller c)
    {
        this.c=c;
    }
    public void keyPressed(KeyEvent e)
    {   
    	int k = e.getKeyCode(); 
        if(c.getModel().getState()==STATE.GAME)		//Activate specifik keyinput only if state is game
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

