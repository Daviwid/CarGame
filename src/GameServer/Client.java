package src.GameServer;

import java.awt.Point;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private static int port = 1767;
    private static String ip = "localhost";

    public Client(){
        try{ 
            sendScore();
            getHighscores();
        }catch(IOException e){
            System.out.println("Client: IO exception: ip not found");
        }catch(InterruptedException e){
            System.out.println("Client: Interrupted exception: got interrupted");
        }
    }

    private void getHighscores() throws IOException, InterruptedException{
       
        Socket socket = new Socket(ip, port);
        InputStream input = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        FileManager fm = new FileManager();

        String clientScore;
        String tmp = "";

        while((clientScore = reader.readLine()) != null)
        {
            tmp += clientScore + "\n";
        }

        fm.recieveHighscoreStringFromServer(clientScore);
        socket.close();
    }

    public void sendScore() throws IOException, InterruptedException
    {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();
        FileManager fm = new FileManager();

        ArrayList<Point> positionList = new ArrayList<Point>();
	    ArrayList<Double> angleList = new ArrayList<Double>();
        positionList.add(new Point(100,150));
		positionList.add(new Point(200,250));
		angleList.add(79.1);
		angleList.add(108.3);
        int time = 1;

        outputStream.write((fm.sendScoreToServer(time, positionList,angleList)).getBytes());
        socket.close();
    }
}