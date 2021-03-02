import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
        try {
            clientSocket.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void handleClientSocket() throws IOException, InterruptedException{
        
        getData();
        sendData();
        System.out.println("Client closed the connection");
        

    }

    private void getData(){
        
        try {
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
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    private void sendData()
    {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            FileManager fm = new FileManager();
            String tmpHighscore = fm.getHighscores();
            outputStream.write((tmpHighscore).getBytes());
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}
