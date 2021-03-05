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

    /**
     * Constructor that takes a clientsocket
     * @param clientSocket is used to managed a specific connection with a client.
     */
    public ServerWorker(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    /**
     * runs the thread and does all the work that the server is supposed to do
     */
    @Override
    public void run(){
        try {
            handleClientSocket();
            System.out.println("Server: handled the socket");
            clientSocket.close();
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();

            //System.out.println("Server: IO exception in worker thread");
        } catch(InterruptedException e){
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get data and send data to the client.
     * @throws IOException
     * @throws InterruptedException
     */
    private void handleClientSocket() throws IOException, InterruptedException{   
        getData();
        sendData();
        System.out.println("Client closed the connection");
    }

    /**
     * this method takes the inputstream from a client and reads it and then checks if the data on the inputstream is a highscore.
     * if it is, then the server will update the highscore text file, otherwise nothing will happen
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method gets the outputstream from the client and then sends the 
     * textfile with updated highscores so that the client
     * can read the data and overwrite its own highscore file.
     */
    private void sendData()
    {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            FileManager fileManager = new FileManager();
            String tmpHighscore = fileManager.getHighscoreString();
            outputStream.write((tmpHighscore).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}
