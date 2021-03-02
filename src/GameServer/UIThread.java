package GameServer;


public class UIThread extends Thread{

    private Server s;

    public UIThread(Server s){
        this.s = s;
    }
    
    @Override
    public void run(){
        System.out.println("Server: Server is on a new thread");
        s.runServer();       
    }
}
