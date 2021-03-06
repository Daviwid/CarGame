package GameServer;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * this class is using a serversocket to start a connection with a client
 * @author David
 * @version 2.1.3.0
 * @since 2021-03-05
 */
public class Server{

    private ServerSocket serverSocket;

    /**
     * @param serverSocket  server socket is passed from the serverUI class
     */
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;   
    }

    /**
     * Checks if the serversocket gets a connection from a client and then puts the client on a separate thread
     */
    public void runServer(){
        System.out.println("Server: Starting server..");
        try {  
            System.out.println("Server: Server socket is running...");
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                ServerWorker worker = new ServerWorker(clientSocket);
                worker.start();
                System.out.println("Server: Client connected");
            }
        } catch (Exception e) {
            System.out.println("Server: Server socket closed.");
            //TODO: handle exception
        }
    }
}
