import  java.awt.*; 
import  java.awt.event.*;   
import  javax.swing.*; 

    
public class Controller extends JFrame implements ActionListener
{
    private Model m;
    private View v;
    private Dimension screenSize;
    private Timer t;    
    
    public Controller()
    {
        
        m = new Model();    
        v = new View(m);
        m.addObserver(v);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(25,15);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth()-100, screenSize.getHeight()-100);
        setPreferredSize(screenSize);
        
        add(v);
        pack();
        
        addKeyListener(new KeyInput(this));			//Tangent-input
        addMouseListener(new MouseInput(this));		//Mus-input
        setFocusable(true);      
        setVisible(true);  

        setScreenY();
        setScreenX();
        
        m.menuInit();				//Skapar en meny
        
        t = new Timer(10,this);   
        
        startApp();
        
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
