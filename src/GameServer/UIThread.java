package GameServer;

/*
this thread is needed so that the server class can wait for connections without the serverUI having to freeze meanwhile a client is connecting
*/
public class UIThread extends Thread{

    private Server server;

    public UIThread(Server server){
        this.server = server;
    }
    
    @Override
    public void run(){
        server.runServer();       
    }
}
