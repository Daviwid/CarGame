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

    /**
     * An inner Class of Controller to create a seperate timer to mesure time in the game.
     * @see Timer
     */
    public class GameTimer implements ActionListener{   
        private Timer t;    

        /**
         * Initiate the timer at 10 ms secounds and starts it immiditly.
         */
        public GameTimer(){
            t = new Timer(10, this);
            t.start();
        }
        /**
         * every 10ms the timer event increments the varible gameTimer in model. remember that this going on from the moment the application starts.
         */
        public void actionPerformed(ActionEvent e){
            m.setGameTimer();
        }
    }
    
    /**
     * This Constructor for Controller generates the Jframe as well as generates desired panels, listeners and timers that is need for the game.
     * there is two diffrent timers that creates its own thred, this is because the GUI runs on one and the in-game clock runs on its own timer.
     * @see GameTimer
     */
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
        g = new GameTimer();		//Playtime timer in a match
       
        startApp();					//start Timer for GUI
    }
    
    /**
     * this returns the instans of Model-class that Controller has access to.
     * this getter is maninly used by MouseInput-Class and Keyinput-class, to be able to manipulate the game with diffrent user inputs.
     * @return m    An instans of the Model-Class.
     */
    public Model getModel()		
    {
        return m;
    }
    
    /**
     * starts the GUI timer
     */
    public void startApp()		
    {
        t.start();
    }
    

    /**
     *  Calls for an update of an Model-instans every time the "GUI" timer generates an ActionEvent.
     * @see Model
     */
    public void actionPerformed(ActionEvent e)
    {   
        
        m.updateModel();
    }

    /**
     * relays the information of the JFrames Y-size to a Model-instans.
     */
    public void setScreenY() { m.setBorderY((int)screenSize.getHeight());  }

     /**
     * relays the information of the JFrames X-size to a Model-instans.
     */
    public void setScreenX() { m.setBorderX((int)screenSize.getWidth()); }
}