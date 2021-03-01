package src.GameServer;

import java.net.ServerSocket;
import java.net.Socket;


public class Server{

    private int nbClients = 0;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;   
    }

    public void runServer(){
        System.out.println("Server: Starting server..");
        try {  
            System.out.println("Server: Server socket is running...");
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                ServerWorker worker = new ServerWorker(clientSocket);
                worker.start();
                nbClients++;
                System.out.println("Server: Client connected");
            }
        } catch (Exception e) {
            System.out.println("Server: Server socket closed.");
            //TODO: handle exception
        }
    }

    public int getClients(){
        return nbClients;
    }
}
