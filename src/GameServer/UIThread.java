package GameServer;

/**
 * this class is a thread that takes a server
 * @author David
 * @version 2.1.3.0
 * @since 2021-03-05
 */
public class UIThread extends Thread{

    private Server server;

    /**
    * this thread is needed so that the server class can wait for connections without the serverUI having to freeze meanwhile a client is connecting
    * @param server
    */
    public UIThread(Server server){
        this.server = server;
    }
    
    @Override
    public void run(){
        server.runServer();       
    }
}
