package GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/*
The thread that client comminucation is done on. 

The thread gets a connection with a client and does all the handeling
*/

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

    /*
    this method is used to get data and send data to the client.
    */
    public void handleClientSocket() throws IOException, InterruptedException{
        
        getData();
        sendData();
        System.out.println("Client closed the connection");
        

    }
    /*
    this method takes the inputstream from a client and reads it and then checks if the data on the inputstream is a highscore
    if it is, then the server will update the highscore text file, otherwise nothing will happen
    */
    private void getData(){
        
        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String clientScore;
            String totScore = "";

            while((clientScore = reader.readLine()) != null)
            {
                totScore += clientScore + "\n";
            }

            FileManager fileManager = new FileManager();
            fileManager.recieveScoreFromClient(totScore);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    /*
    this method gets the outputstream from the client and then send the textfile with updated highscores so that the client
    can read the data and overwrite its own highscore file.
    */
    private void sendData()
    {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            FileManager fileManager = new FileManager();
            String tmpHighscore = fileManager.getHighscoreString();
            outputStream.write((tmpHighscore).getBytes());
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}
