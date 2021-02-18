import java.awt.*;
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
        
        setBackground(Color.gray);
        if(m.getState()==STATE.MENU)					//Rita upp OM meny
        {
            Graphics2D g2d = (Graphics2D) g;
            setBackground(Color.black);
            
            g2d.setColor(Color.white);
            g2d.setFont(new Font("arial",Font.BOLD,150));
            g2d.drawString("CARGAME",20,200);					//Game title
            
            g2d.setColor(Color.red);
            g2d.fill(m.getMenu().getPlayBtn());					//playBtn med outline
            g2d.setColor(Color.white);
            g2d.draw(m.getMenu().getPlayBtn());
            g.setFont(new Font("arial",Font.BOLD,50));			//Play text
            g2d.drawString("PLAY",
                (int)m.getMenu().getPlayBtn().getX()+(int)m.getMenu().getPlayBtn().getWidth()/2-60,
                (int)m.getMenu().getPlayBtn().getY()+(int)m.getMenu().getPlayBtn().getHeight()/2+20);
            
            g2d.setColor(Color.red);
            g2d.fill(m.getMenu().getQuitBtn());					//quitBtn med outline
            g2d.setColor(Color.white);
            g2d.draw(m.getMenu().getQuitBtn());
            g2d.drawString("QUIT",								//Quit text
                (int)m.getMenu().getQuitBtn().getX()+(int)m.getMenu().getQuitBtn().getWidth()/2-60,
                (int)m.getMenu().getQuitBtn().getY()+(int)m.getMenu().getQuitBtn().getHeight()/2+20);
            
        }
        
        
        if(m.getState()==STATE.GAME)				//Rita OM game
        {
            if(m.getSelected())						//Rita OM map selected == true. Typ en dubbelkoll. Mest ifall bilden inte laddas in och ger Exceptions
            {
                g.drawImage(m.getTrack().getMap(), 0,0,this);					//Ritar banan
               
                //Debug code. visar sargen.
                Iterator<Point> it = m.getTrack().getHitbox().iterator();
                g.setColor(Color.red);
                while(it.hasNext())
                {
                    Point p = it.next();
                    g.drawLine(p.x,p.y,p.x,p.y);
                }
                //End of debug code.
            }
        
            drawCar(g, carList.get(0));
        
        
            //Debug code "yellow spinning circles"
            g.setColor(Color.yellow);
            for (int    i=0;    i<5;   i++)
            {   
                int xx   =   15  *   i;  
                int yy   =   0;
                int xx1  =   (int)Rotate2D.getX(xx,yy,carList.get(0).getAngle());  
                int yy1  =   (int)Rotate2D.getY(xx,yy,carList.get(0).getAngle());
                g.fillOval(350-5+xx1,300-5+yy1,10,10);    
            }   
            //End of debug code   
        }
    }
    

    
    public void updateView() {
        repaint();
    }
    
    
    public void drawCar(Graphics g, Car car) {
        
        g.setColor(Color.green); 
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
        g.fillPolygon(xPts,yPts,4);
    }
    
    
    @Override
    public void update(Model observable) {
        repaint();
    }
}
