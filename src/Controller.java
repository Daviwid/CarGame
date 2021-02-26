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
            t = new Timer(1000, this);
            t.start();
        }
        public void actionPerformed(ActionEvent e){
            m.setGameTime();
        }
    }
    
    public Controller()
    {
        
        m = new Model();  

        v = new View(m);
        m.addObserver(v);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(25,15);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth()-100, screenSize.getHeight()-100);
        v.setPreferredSize(screenSize);
        add(v);
        pack();
        addKeyListener(new KeyInput(this));			//Tangent-input
        addMouseListener(new MouseInput(this));		//Mus-input
        setFocusable(true);      
        setVisible(true);                                 
        
        setScreenY();
        setScreenX();
        
        m.menuInit();				//Skapar en meny
        m.mapInit();
        
        t = new Timer(10,this);
       GameTimer g = new GameTimer(m);
       
        

        startApp();
    }
    
    public void createWindow(View v)
    {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(25,15);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth()-100, screenSize.getHeight()-100);
        v.setPreferredSize(screenSize);
        add(v);
        pack();
        v.addKeyListener(new KeyInput(this));			//Tangent-input
        v.addMouseListener(new MouseInput(this));		//Mus-input
        v.setFocusable(true);      
        setVisible(true); 
    }
    
    
    public Model getModel()		//Kallas i listener-klassser
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

