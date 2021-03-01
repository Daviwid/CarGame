package GameServer;


import javax.swing.JFrame;
import  java.awt.*; 
import  java.awt.event.*;
import  javax.swing.*;
import java.net.ServerSocket;

public class ServerUI extends JFrame implements KeyListener, ActionListener {
    
    public ServerSocket serverSocket;
    private static int port = 1767;
    private Server s;
    private UIThread t;
    private JPanel serverWindow;
    private JButton[] buttons  = new JButton[3];

    public ServerUI(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Server: Failed to create new serversocket...");
        }

        this.setTitle("Server");
        s = new Server(serverSocket);
        createFrame();
        t = new UIThread(s);
        t.start();
    }

    public void createFrame()
    {
        GridLayout layout = new GridLayout(4,4,2,2);
        serverWindow = new JPanel(layout);
        
        
    	serverWindow.setPreferredSize(new Dimension(400,400));
        serverWindow.setBackground(Color.WHITE);
        serverWindow.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CarGame - Server Menu"));

        JButton sButton = new JButton("Close Server");
        sButton.addActionListener(this);
        buttons[0] = sButton;
        
        add(serverWindow);
        serverWindow.add(sButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
         
    }

    public void actionPerformed(ActionEvent e){
        Object b = e.getSource();
        if(b == buttons[0]){
            try {
                serverSocket.close();
            } catch (Exception ex) {
                System.out.println("IO exception in button");
            }
        }
    }

    public void keyPressed(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
