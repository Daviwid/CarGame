import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class View extends JPanel implements Observer<Model> {

    private Model m;
    private LinkedList<Car> carList;
    private BufferedImage redCar;
    
    public View(Model m) {
        this.m = m;
        this.carList = m.getCarList();
		try {
			redCar = ImageIO.read(new File("REDCAR.png"));
		}
		catch(IOException e){
			
		}
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.gray);
        if(m.getState()==STATE.MENU)					//Rita upp OM meny
        {
        	
        	drawMenu(m.getMenu(),g2d,m.getBorderX(),m.getBorderY());
        }
        
        
        if(m.getState()==STATE.GAME)				//Rita OM game
        {
            drawGame(g2d,m.getTrack().getMap());
        }
    }
    
    public void drawGame(Graphics2D g2d, BufferedImage map)
    {
    	 if(m.getSelected())						//Rita OM map selected == true. Typ en dubbelkoll. Mest ifall bilden inte laddas in och ger Exceptions
         {
             g2d.drawImage(map, 0,0,this);					//Ritar banan
            
             //Debug code. visar sargen.
             Iterator<Point> it = m.getTrack().getHitbox().iterator();
             g2d.setColor(Color.red);
             while(it.hasNext())
             {
                 Point p = it.next();
                 g2d.drawLine(p.x,p.y,p.x,p.y);
             }
             //End of debug code.
         }
     
         drawCar(g2d, carList.get(0));
     
     
         //Debug code "yellow spinning circles"
         g2d.setColor(Color.yellow);
         for (int    i=0;    i<5;   i++)
         {   
             int xx   =   15  *   i;  
             int yy   =   0;
             int xx1  =   (int)Rotate2D.getX(xx,yy,carList.get(0).getAngle());  
             int yy1  =   (int)Rotate2D.getY(xx,yy,carList.get(0).getAngle());
             g2d.fillOval(350-5+xx1,300-5+yy1,10,10);    
         }   
         //End of debug code   
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
        g2d.fill(menu.getQuitBtn());					//quitBtn med outline
        g2d.setColor(menu.getBtnOutClr());
        g2d.draw(menu.getQuitBtn());
        //Slutet av buttons
        
        
        //TEXT AV MENU
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,150));
        g2d.drawString("CARGAME",20,150);					//Game title
        
        g2d.setColor(menu.getTitleClr());
        g2d.setFont(new Font("arial",Font.BOLD,20));
        g2d.drawString("Team: Ingen Aning", x-200, y-35);										//Gruppnamn
        g2d.drawString("Devs: David J, Erik L, Jacob W, Kevin P, Victoria B", x-500, y-10);		//Developers
        g2d.drawString(m.getBuild(), 10, y-10);											//build
        
        g2d.setFont(new Font("arial",Font.BOLD,50));											//Playbtn text
        g2d.drawString("PLAY",
                (int)menu.getPlayBtn().getX()+(int)menu.getPlayBtn().getWidth()/2-60,
                (int)menu.getPlayBtn().getY()+(int)menu.getPlayBtn().getHeight()/2+20);
        g2d.drawString("QUIT",																	//Quitbtn text
                (int)menu.getQuitBtn().getX()+(int)menu.getQuitBtn().getWidth()/2-60,
                (int)menu.getQuitBtn().getY()+(int)menu.getQuitBtn().getHeight()/2+20);
        //Slutet av text
    }

    
    public void updateView() {
        repaint();
    }
    
    
    public void drawCar(Graphics2D g2d, Car car) {
        // getsubimage om vi ska ha 1 bild med alla olika bilar.

		Image resultingImage = redCar.getScaledInstance(car.getHeight(), car.getWidth(), Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(car.getHeight(), car.getWidth(), BufferedImage.TYPE_INT_ARGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    
	    //start debug
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

        AffineTransform backup = g2d.getTransform();
//        AffineTransform a = AffineTransform.getRotateInstance(car.getAngle(), car.getPositionX(), car.getPositionY());
        //Set our Graphics2D object to the transform
       g2d.rotate(car.getAngle() + (3*Math.PI)/2, car.getPositionX(), car.getPositionY());
        
        g2d.drawImage(outputImage, car.getPositionX() - 25, car.getPositionY()-25 , null);
//      g2d.setTransform(a); 
        g2d.setTransform(backup);
    }
    
    
    @Override
    public void update(Model observable) {
        repaint();
    }
}
