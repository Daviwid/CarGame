package src.GameFiles;

import java.awt.Point;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class testclient {

    private static int port = 25565;
    private static String ip = "";

    public testclient(int time, ArrayList<Point> positionList, ArrayList<Double> angleList){
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

    private void getHighscores(BufferedReader reader) throws IOException, InterruptedException{
       
        
        FileManager fm = new FileManager();

        String clientScore;
        String tmp = "";

        while((clientScore = reader.readLine()) != null)
        {
            tmp += clientScore + "\n";
        }
        System.out.println(tmp);
        fm.recieveHighscoreStringFromServer(tmp);

    }

    public void sendScore(OutputStream outputStream, int time, ArrayList<Point> positionList, ArrayList<Double> angleList) throws IOException, InterruptedException
    {
        
        FileManager fm = new FileManager();

        outputStream.write((fm.sendScoreToServer(time,positionList,angleList)).getBytes());
        
    }

    public static void main(String[] args) {
        ArrayList<Point> positionList = new ArrayList<Point>();
        ArrayList<Double> angleList = new ArrayList<Double>();
        positionList.add(new Point(4,6));
        positionList.add(new Point(56,114));
        angleList.add(2.6);
        angleList.add(65.5);
        new testclient(1, positionList, angleList);
    }
}