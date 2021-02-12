


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
    }
    
    public void createFrame(View v)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(50,25);
    	screenSize = Toolkit.getDefaultToolkit().getScreenSize();		//H�mtar sk�rmstorlek
    	v.setPreferredSize(new Dimension((int)screenSize.getWidth()-100,(int)screenSize.getHeight()-100));	//anpassar frame efter sk�rm
        add(v);
        pack();
        v.addKeyListener(this); 
        v.setFocusable(true);       //Annars funkar inte tangenttryck
        setVisible(true);
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
        	m.setPressedUp();	//F�rsta bilen i listan flaggar: K�r fram�t
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setPressedLeft(); //F�rsta bilen i listan flaggar: Sv�nger v�nster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setPressedRight();//F�rsta bilen i listan flaggar: Sv�nger h�ger
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setPressedDown();	//F�rsta bilen i listan flaggar: K�r bak�t
        }
        else if(k == KeyEvent.VK_ESCAPE)
        {
        	//pausar spelet / avsluta spelet?
        }
        
        
        
        
    } 

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e)
    {
        int k = e.getKeyCode(); 
        if(k == KeyEvent.VK_UP)
        {
        	m.setReleasedUp();	//Sluta accelerera fram�t
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setReleasedDown(); //Sluta accelerera bak�t
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setReleasedLeft();//Sluta sv�nga v�nster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setReleasedRight(); //Sluta sv�nga h�ger
        }
    }

    public void actionPerformed(ActionEvent e)
    {   
    	m.updateModel();
        //v.updateView();
    }




}
