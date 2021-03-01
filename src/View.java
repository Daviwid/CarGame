import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;


public class View extends JPanel implements Observer<Model> {

    private Model m;
    private LinkedList<Car> carList;

    
    public View(Model m) {
        this.m = m;
        this.carList = m.getCarList();
    }
    
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
        	//drawCheckpoints(g2d, m.getTrack().getCheckpointsMap());
            drawGame(g2d,m.getTrack().getMap());
            //drawCheckpoints(g2d, m.getTrack().getCheckpointsMap());
            drawCar(g2d, carList.get(0));
            
        }
        if(m.getState()== STATE.CARCRASH) {
        	drawCrash(g2d);
        	
        } 
        if(m.getState()== STATE.GAMEFINISHED) {
        	drawFinished(g2d);
        	
        } 
    }
    
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
        
        g2d.drawImage(menu.resize(menu.getRedCar(),280,300),(int)menu.getredCarBtn().getX()+10,(int)menu.getredCarBtn().getY()+10,null);
        g2d.drawImage(menu.resize(menu.getGreenCar(),280,300),(int)menu.getgreenCarBtn().getX()+10,(int)menu.getgreenCarBtn().getY()+10,null);
        g2d.drawImage(menu.resize(menu.getBlueCar(),280,300),(int)menu.getblueCarBtn().getX()+10,(int)menu.getblueCarBtn().getY()+10,null);
        
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
    
    public void drawGame(Graphics2D g2d, BufferedImage map)
    {
    	 if(m.getMapSelected())						//Rita OM map selected == true. Typ en dubbelkoll. Mest ifall bilden inte laddas in och ger Exceptions
         {
             g2d.drawImage(map, 0,0,this);					//Ritar banan
            

            /* //Debug code. visar sargen.
             Iterator<Point> it = m.getTrack().getHitbox().iterator();
             g2d.setColor(Color.red);
             while(it.hasNext())
             {
                 Point p = it.next();
                 g2d.drawLine(p.x,p.y,p.x,p.y);
             }

             
             //End of debug code.
         }
    	 
         //drawCar(g2d, carList.get(0));

             */ //End of debug code.

         }

         drawCar(g2d, carList.get(0));

         drawTime(g2d);
         
    }
    

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
        //Slutet av text
    }

    
    public void updateView() {
        repaint();
    }
    
    public void drawCheckpoints(Graphics2D g2d, BufferedImage checkpoints) {
    	
            g2d.drawImage(checkpoints, 0,0,this);					//Ritar banan
           
            //Debug code. visar checkpoints
          /*  Iterator<Point> it = m.getTrack().getCheckpoints4Hitbox().iterator();
            g2d.setColor(Color.yellow);
            while(it.hasNext())
            {
                Point p = it.next();
                g2d.drawLine(p.x,p.y,p.x,p.y);
            }
            
            Iterator<Point> it2 = m.getTrack().getCheckpoints3Hitbox().iterator();
            g2d.setColor(Color.red);
            while(it2.hasNext())
            {
                Point p = it2.next();
                g2d.drawLine(p.x,p.y,p.x,p.y);
            }
            Iterator<Point> it3 = m.getTrack().getCheckpoints2Hitbox().iterator();
            g2d.setColor(Color.green);
            while(it3.hasNext())
            {
                Point p = it3.next();
                g2d.drawLine(p.x,p.y,p.x,p.y);
            }
            Iterator<Point> it4 = m.getTrack().getCheckpoints1Hitbox().iterator();
            g2d.setColor(Color.RED);
            while(it4.hasNext())
            {
                Point p = it4.next();
                g2d.drawLine(p.x,p.y,p.x,p.y);
            }*/
           //End of debug code.
       
    }
   
    public void drawCrash(Graphics2D g2d) {
    	m.getMenu().getCrashImg().paintIcon(this, g2d, 0, 0);
    }
    public void drawFinished(Graphics2D g2d) {
    	m.getMenu().getFinishedImg().paintIcon(this, g2d, 0, 0);
    	
    	g2d.drawString("GAME FINISHED", 500, 600);
    }
    



    private void drawCar(Graphics2D g2d, Car car) {

	    
        g2d.setColor(Color.green); 
        double w = car.getWidth();  //Test values of green rectangle under png
        double h = car.getHeight();
        Point2D p1 = new Point2D(h/2,-w/2).rotate(car.getAngle());
        Point2D p2 = new Point2D(h/2,w/2).rotate(car.getAngle());
        Point2D p3 = new Point2D(-h/2,w/2).rotate(car.getAngle());
        Point2D p4 = new Point2D(-h/2,-w/2).rotate(car.getAngle());
        double x = car.getPositionX();
        double y = car.getPositionY();
        double[]   dx  = {x+p1.getIntX(),x+p2.getIntX(),x+p3.getIntX(),x+p4.getIntX()};  
        double[]   dy  = {y+p1.getIntY(),y+p2.getIntY(),y+p3.getIntY(),y+p4.getIntY()};
        int xPts[]= new int[4];
        int yPts[]= new int[4];
        for(int i=0;i<4;i++)
        {
             xPts[i]=(int)(dx[i]+.5);
             yPts[i]=(int)(dy[i]+.5); 
        }
        g2d.fillPolygon(xPts,yPts,4);
        

        // end debug

        
        Image resultingImage = redCar.getScaledInstance(car.getWidth(), car.getHeight(), Image.SCALE_SMOOTH);
        

        AffineTransform backup = g2d.getTransform();

        //Set our Graphics2D object to the transform
       g2d.rotate(car.getAngle() + (3*Math.PI)/2, car.getPositionX(), car.getPositionY());
        
        g2d.drawImage(resultingImage, car.getPositionX() - (car.getWidth() / 2) , car.getPositionY() - (car.getHeight() / 2) , null);
//      g2d.setTransform(a);
        g2d.setTransform(backup); 
        
    }
    
    private void drawTime(Graphics2D g2d){
        
        int min = m.getGameTimer() / 60;
        int sec = m.getGameTimer() % 60;

        String s = (String.valueOf(min) + ":" + String.valueOf(sec));
        g2d.setFont(new Font("arial",Font.BOLD,20));
        g2d.drawString(s, 20, 150);
        
    }

    @Override
    public void update(Model observable) {
        repaint();
    }
}