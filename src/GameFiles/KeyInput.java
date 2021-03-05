package GameFiles;

import  java.awt.event.*;   

public class KeyInput implements KeyListener
{
    private Model m;

    /**
     * Needs an Model-instans to be able to manipulate diffrent flags for keyinput.
     * @param m Model.instans to be able to manipulate flags.
     */
    public KeyInput(Model m)
    {
        this.m = m;
    }

    /**
     * Reacts to non-numpad arrows (up,down,right and left) and escape key.
     * arrows is used to controll the Car in game while escape can be used to return to menu or in some situations colse the application.
     * all manipulation works by setting diffrent flags in an Model-instans, this allows holding multiple keys at the same time.
     */
    public void keyPressed(KeyEvent e)
    {   
    	int k = e.getKeyCode(); 
        if(m.getState()==STATE.GAME)		//Activate specific keyinput only if state is game
        {
            if(k == KeyEvent.VK_UP)
            {
                m.setPressedUp();   
            }
            else if(k == KeyEvent.VK_LEFT)
            {
                m.setPressedLeft(); 
            }
            else if(k == KeyEvent.VK_RIGHT)
            {
                m.setPressedRight();    
            }
            else if(k == KeyEvent.VK_DOWN)
            {
                m.setPressedDown(); 
            }
        }
        if(k == KeyEvent.VK_ESCAPE)					//Allways activated
        {
            if(c.getModel().getState()==STATE.MENU)		//Quit Application
            {

                if(m.getState()==STATE.MENU)		//Quit Application
                {
                    System.exit(0);
                }
                
                //Open menu
                if(m.getState()==STATE.GAME || m.getState()==STATE.MAP_SELECTION ||
                		m.getState()==STATE.CARCONFIG || m.getState()==STATE.HIGHSCORE ||
                		m.getState()==STATE.GAMEFINISHED)		
                {
                    m.stateMenu();
                }
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
        if(m.getState()==STATE.GAME)
        {
            int k = e.getKeyCode(); 
            if(k == KeyEvent.VK_UP)
            {
                m.setReleasedUp();  
            }
            else if(k == KeyEvent.VK_DOWN)
            {
                m.setReleasedDown(); 
            }
            else if(k == KeyEvent.VK_LEFT)
            {
                m.setReleasedLeft(); 
            }
            else if(k == KeyEvent.VK_RIGHT)
            {
                m.setReleasedRight();   
            }
        }
    }
}

