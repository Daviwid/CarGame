import  java.awt.event.*;   

public class MouseInput implements MouseListener{
    private Controller c;
    public MouseInput(Controller c)
    {
        this.c=c;
    }
    public void mouseClicked(MouseEvent e)
    {   
        if(c.getModel().getState()==STATE.MENU)		//Funkar endast om man har meny uppe.
        {
            int x = e.getX();		//x och y koordinater av klick med muspekare
            int y = e.getY();
            
            //Kollar om klick on playBtn
            if(x >= c.getModel().getMenu().getPlayBtn().getX() && x <= c.getModel().getMenu().getPlayBtn().getX()+c.getModel().getMenu().getPlayBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getPlayBtn().getY() && y <= c.getModel().getMenu().getPlayBtn().getY()+c.getModel().getMenu().getPlayBtn().getHeight())
                {
                    c.getModel().selectMap();		//Kanske implementera select map knappar och ny state?
                }
            }
            
            //Kollar om klick on quitBtn
            if(x >= c.getModel().getMenu().getQuitBtn().getX() && x <= c.getModel().getMenu().getQuitBtn().getX()+c.getModel().getMenu().getQuitBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getQuitBtn().getY() && y <= c.getModel().getMenu().getQuitBtn().getY()+c.getModel().getMenu().getQuitBtn().getHeight())
                {
                    System.exit(0);
                }
            }
            
        }
    }
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}