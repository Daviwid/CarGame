package GameServer;


import javax.swing.JFrame;
import  java.awt.*; 
import  java.awt.event.*;
import  javax.swing.*;
import java.net.ServerSocket;

/*
ServerUI manages the visual UI and starts an instance of the server class with the port that the client will connect to


*/

public class ServerUI extends JFrame implements ActionListener {
    
    private ServerSocket serverSocket;
    private static int port = 25565;
    private Server server;
    private UIThread uiThread;
    private JPanel serverWindow;
    private JButton[] buttons  = new JButton[1];

    /**
     * Starts the server socket and passes it into server.java
     * also starts a thread and puts the server on it. 
     * this is so that the server can wait for connections meanwhile the serverUI can be managed simultanious
     */
    public ServerUI(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Server: Failed to create new serversocket...");
        }

        this.setTitle("Server");
        server = new Server(serverSocket);
        createFrame();
        uiThread = new UIThread(server);
        uiThread.start();
    }

    /**
     * this method creates the frame with the button that closes the serversocket
     */
    private void createFrame()
    {
        GridLayout layout = new GridLayout(4,4,2,2);
        serverWindow = new JPanel(layout);
        
    	serverWindow.setPreferredSize(new Dimension(400,400));
        serverWindow.setBackground(Color.WHITE);
        serverWindow.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CarGame - Server Menu"));

        JButton closeServerBTN = new JButton("Close Server");
        closeServerBTN.addActionListener(this);
        buttons[0] = closeServerBTN;
        
        add(serverWindow);
        serverWindow.add(closeServerBTN);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
         
    }

    /**
     * checks if the button is pressed, then the serversocket will close - making connection to the server impossible
     */
    public void actionPerformed(ActionEvent e){
        Object button = e.getSource();
        if(button == buttons[0]){
            try {
                serverSocket.close();
            } catch (Exception ex) {
                System.out.println("IO exception in button");
            }
        }
    }
}
