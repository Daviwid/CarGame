package src.GameServer;

public class ClientThread extends Thread{


    public ClientThread(){

    }
    
    @Override
    public void run(){
        System.out.println("Server is on a new thread");
        try {
            new Client();
        } catch (Exception e) {
            System.out.println("creation of client not working");
        }
               
    }
}
