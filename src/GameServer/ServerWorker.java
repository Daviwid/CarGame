import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread{
    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try {
            handleClientSocket();
            System.out.println("Server: handled the socket");
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();

            //System.out.println("Server: IO exception in worker thread");
        } catch(InterruptedException e){
            //TODO: handle exception
            System.out.println("Server: Interrupted exception in worker thread");
        }
    }

    public void handleClientSocket() throws IOException, InterruptedException{

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String clientScore;
        String tmp = "";

        while((clientScore = reader.readLine()) != null)
        {
            tmp += clientScore + "\n";
        }

        FileManager fm = new FileManager();
        fm.recieveScoreFromClient(tmp);
        
        String tmpHighscore = fm.getHighscores();
        outputStream.write((tmpHighscore).getBytes());

        System.out.println("Client closed the connection");
        clientSocket.close();

    }
}
