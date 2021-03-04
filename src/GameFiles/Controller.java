package GameFiles;

import  java.awt.*; 
import  java.awt.event.*;   
import  javax.swing.*; 

    
public class Controller extends JFrame implements ActionListener
{
    private Model m;
    private View v;
    private Dimension screenSize;
    private Timer t;
    private GameTimer g;

    public class GameTimer implements ActionListener{   //inner class only for race timer
        private Timer t;    

        public GameTimer(Model m){
            t = new Timer(10, this);
            t.start();
        }
        public void actionPerformed(ActionEvent e){
            m.setGameTimer();
        }
    }
    
    public Controller()
    {
        
        m = new Model();  
        v = new View(m);
        m.addObserver(v);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(0,0);
        screenSize = new Dimension(1600,900);									//adjust windowsize
        screenSize.setSize(screenSize.getWidth(), screenSize.getHeight());
        v.setPreferredSize(screenSize);
        add(v);
        pack();
        addKeyListener(new KeyInput(this));										//Connect the keylistener
        addMouseListener(new MouseInput(this));									//Connect the mouselistener
        setFocusable(true);      
        setVisible(true);                                 
        
        setScreenY();
        setScreenX();
        
        m.menuInit();				//Initiate the menu
        m.mapInit();				//Initiate the maps
        
        t = new Timer(10,this);		//Timer for GUI
        g = new GameTimer(m);		//Playtime timer in a match
       
        startApp();					//start Timer for GUI
    }
    
    public Model getModel()		//Usage in listenerclasses
    {
        return m;
    }
    
    public void startApp()		
    {
        t.start();
    }
    
    public void actionPerformed(ActionEvent e)
    {   
        
        m.updateModel();
    }


   public void setScreenY() { m.setBorderY((int)screenSize.getHeight());  }
   public void setScreenX() { m.setBorderX((int)screenSize.getWidth()); }
}