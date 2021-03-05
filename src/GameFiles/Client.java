package GameFiles;

import java.awt.Point;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/*
this class connects to the server with a port and an IP-address
*/

public class Client {

    private static int port = 25565;
    private static String ip = "46.239.99.69";
    
    /**
     * Constructor that takes variables and sends them to the server.
     * @param time			Int representing time
     * @param positionList	Driver's path
     * @param angleList		Driver's angles
     */
    public Client(int time, ArrayList<Point> positionList, ArrayList<Double> angleList){
        try{
            Socket socket = new Socket(ip, port);

            InputStream input = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();

            sendScore(outputStream, time, positionList, angleList);
            socket.shutdownOutput();
            getHighscores(reader);

            socket.close();
        }catch(IOException e){
            System.out.println("Client: IO exception: ip not found");
        }catch(InterruptedException e){
            System.out.println("Client: Interrupted exception: got interrupted");
        }
        
    }
    
    /**
     * Gets the contents of server's highscore file and then overwrites client's highscore file.
     * @param reader	Reads the inputstream from the server
     * @throws IOException if the socket is unreachable
     * @throws InterruptedException if the connection with the socket drops
     */
    private void getHighscores(BufferedReader reader) throws IOException, InterruptedException{
       
        
        FileManager fileManager = new FileManager();

        String clientScore;
        String totalScore = "";

        while((clientScore = reader.readLine()) != null)
        {
            totalScore += clientScore + "\n";
        }
        fileManager.recieveStringFromServer(totalScore);

    }

    /*
    this method sends the score to the server via outputstream
    */
    
    /**
     * Sends the score to the server via outputstream.
     * @param outputStream				Used to send data to the server.
     * @param time						Int representing time
     * @param positionList				Driver's path
     * @param angleList					Driver's angles
     * @throws IOException if the socket is unreachable
     * @throws InterruptedException if the connection with the socket drops
     */
    private void sendScore(OutputStream outputStream, int time, ArrayList<Point> positionList, ArrayList<Double> angleList) throws IOException, InterruptedException
    {
        
        FileManager fileManager = new FileManager();

        outputStream.write((fileManager.sendScoreToServer(time,positionList,angleList)).getBytes());
        
    }
}