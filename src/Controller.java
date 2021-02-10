package car_game;

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
        
        createFrame(v);					//Skapar frame
        t = new Timer(10,this);      	//
        startGame();					//Startar timer
    }
    
    public void createFrame(View v)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(50,25);
    	screenSize = Toolkit.getDefaultToolkit().getScreenSize();		//Hämtar skärmstorlek
    	v.setPreferredSize(new Dimension((int)screenSize.getWidth()-100,(int)screenSize.getHeight()-100));	//anpassar frame efter skärm
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
        	m.setUp();	//Första bilen i listan flaggar: Kör framåt
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setLeft(); //Första bilen i listan flaggar: Svänger vänster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setRight();	//Första bilen i listan flaggar: Svänger höger
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setDown();	//Första bilen i listan flaggar: Kör bakåt
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
        	m.setUp();	//Sluta accelerera framåt
        }
        else if(k == KeyEvent.VK_DOWN)
        {
        	m.setDown(); //Sluta accelerera bakåt
        }
        else if(k == KeyEvent.VK_LEFT)
        {
        	m.setLeft(); //Sluta svänga vänster
        }
        else if(k == KeyEvent.VK_RIGHT)
        {
        	m.setRight();	//Sluta svänga höger
        }
    }

    public void actionPerformed(ActionEvent e)
    {   
    	m.updateModel();
        v.repaint();
    }




}
