package GameServer;


import java.net.ServerSocket;
import java.net.Socket;

/* 
Checks if the serversocket gets a connection from a client and then puts the client on a separate thread
*/
public class Server{

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
                System.out.println("Server: Client connected");
            }
        } catch (Exception e) {
            System.out.println("Server: Server socket closed.");
            //TODO: handle exception
        }
    }
}
