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
        if(c.getModel().getState()==STATE.GAME)		//Funkar endast om man ska spela.
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
            if(k == KeyEvent.VK_ESCAPE)					//Funkar alltid.
            {
                if(c.getModel().getState()==STATE.MENU)		//Quit Application
                {
                    System.exit(0);
                }
                if(c.getModel().getState()==STATE.GAME)		//Open menu
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