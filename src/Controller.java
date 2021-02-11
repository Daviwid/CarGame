import  java.awt.*; 
import  java.awt.event.*;   
import  javax.swing.*; 
import  java.util.List;
import  java.util.Iterator;
    
public class Controller extends JFrame implements KeyListener, ActionListener
{
    private Model m;
    private View v;
    private Dimension screenSize;
    private Timer t;    //RITAR, FLYTTAR och kollar regler
    
    public Controller()
    {
        m = new Model();    
        v = new View(m);
        m.addObserver(v);
        
        createFrame(v);					//Skapar frame
        t = new Timer(10,this);      	//
        startGame();					//Startar timer
        
        setScreenY();
        setScreenX();
    }
    
    public void createFrame(View v)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(50,25);
    	screenSize = Toolkit.getDefaultToolkit().getScreenSize();		//Hmtar skrmstorlek
    	v.setPreferredSize(new Dimension((int)screenSize.getWidth()-100,(int)screenSize.getHeight()-100));	//anpassar frame efter skrm
    	
    	add(v);
        pack();
        v.addKeyListener(this); 
        v.setFocusable(true);       //Annars funkar inte tangenttryck
        
     
        setVisible(true);  ///gammalt
         
    }
    
    
    
    public void startGame()
    {
    	t.start();
    }
   
    
    public void keyPressed(KeyEvent e)
    {   
        int k = e.getKeyCode(); 
       
        if(k == KeyEvent.VK_UP)
        {
        	m.setPressedUp();	//Frsta bilen i listan flaggar: Kr framt
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setPressedLeft(); //Frsta bilen i listan flaggar: Svnger vnster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setPressedRight();	//Frsta bilen i listan flaggar: Svnger hger
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setPressedDown();	//Frsta bilen i listan flaggar: Kr bakt
        }
        else if(k == KeyEvent.VK_ESCAPE)
        {
        	System.exit(0);//pausar spelet / avsluta spelet?
        }
        
        
        
        
    } 

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e)
    {
        int k = e.getKeyCode(); 
        if(k == KeyEvent.VK_UP)
        {
        	m.setReleasedUp();	//Sluta accelerera framt
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setReleasedDown(); //Sluta accelerera bakt
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setReleasedLeft(); //Sluta svnga vnster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setReleasedRight();	//Sluta svnga hger
        }
    }

    public void actionPerformed(ActionEvent e)
    {   
    	m.updateModel();
        //v.updateView();
    }

    //public double getScreenY() { return v.getHeight();}
  // public double getScreenX() {return v.getWidth();}
   public void setScreenY() { m.setBorderY(v.getHeight());  }
   public void setScreenX() { m.setBorderX(v.getWidth()); }