import  java.awt.event.*;   

public class MouseInput implements MouseListener{
    private Controller c;
    public MouseInput(Controller c)
    {
        this.c=c;
    }
    public void mouseClicked(MouseEvent e)
    {   
    	int x = e.getX();		//x och y koordinater av klick med muspekare
        int y = e.getY();
        
        if(c.getModel().getState()==STATE.MENU)		//Funkar endast om man har meny uppe.
        {
            

            //Kollar om klick on playBtn
            if(x >= c.getModel().getMenu().getPlayBtn().getX() && x <= c.getModel().getMenu().getPlayBtn().getX()+c.getModel().getMenu().getPlayBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getPlayBtn().getY() && y <= c.getModel().getMenu().getPlayBtn().getY()+c.getModel().getMenu().getPlayBtn().getHeight())
                {
                    c.getModel().selectMap();		//Kanske implementera select map knappar och ny state?
                   // c.getModel().getSelectedCar();
                   
                }
            }
          
          
          	 
            if(x >= c.getModel().getMenu().getConfigBtn().getX() && x <= c.getModel().getMenu().getConfigBtn().getX()+c.getModel().getMenu().getConfigBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getConfigBtn().getY() && y <= c.getModel().getMenu().getConfigBtn().getY()+c.getModel().getMenu().getConfigBtn().getHeight())
                {
                    c.getModel().selectCarConfig();
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
        	
        if(c.getModel().getState()==STATE.CARCONFIG) {
        	
        	if(x >= c.getModel().getMenu().getredCarBtn().getX() && x <= c.getModel().getMenu().getredCarBtn().getX()+c.getModel().getMenu().getredCarBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getredCarBtn().getY() && y <= c.getModel().getMenu().getredCarBtn().getY()+c.getModel().getMenu().getredCarBtn().getHeight())
                {
                	c.getModel().selectRedCar();
                }
            }
        	if(x >= c.getModel().getMenu().getblueCarBtn().getX() && x <= c.getModel().getMenu().getblueCarBtn().getX()+c.getModel().getMenu().getblueCarBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getblueCarBtn().getY() && y <= c.getModel().getMenu().getblueCarBtn().getY()+c.getModel().getMenu().getblueCarBtn().getHeight())
                {
                	 //work in progress
                }
            }
        	if(x >= c.getModel().getMenu().getgreenCarBtn().getX() && x <= c.getModel().getMenu().getgreenCarBtn().getX()+c.getModel().getMenu().getgreenCarBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getgreenCarBtn().getY() && y <= c.getModel().getMenu().getgreenCarBtn().getY()+c.getModel().getMenu().getgreenCarBtn().getHeight())
                {
                	 //work in progress
                }
            }
        	
        	if(x >= c.getModel().getMenu().getReturnBtn().getX() && x <= c.getModel().getMenu().getReturnBtn().getX()+c.getModel().getMenu().getReturnBtn().getWidth())
            {
                if(y >= c.getModel().getMenu().getReturnBtn().getY() && y <= c.getModel().getMenu().getReturnBtn().getY()+c.getModel().getMenu().getReturnBtn().getHeight())
                {
                    //work in progress
                }
            }
        }	
      
           
       
        
    }
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
} 