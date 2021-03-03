package GameFiles;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class FileManager {
	
    private int nbHighscores = 10;
    private int[] factoryPresetScores = new int[nbHighscores] {3000, 4000, 5000, 10000, 20000, 30000, 50000, 80000, 100000, 200000};
    private String highscoreFile = "Highscore.txt";
    private String configFile = "Config.txt";
	
    public FileManager(){
		
    }
	
	//HELPER METHODS:
    private void sortHighscores(int playerScore, int[] scores)
    {
    	int oldscore;
    	for(int j = 0; j < nbHighscores; j++){
            if(playerScore < scores[j]){
                oldscore = scores[j];
                scores[j] = playerScore;
                playerScore = oldscore;
            }
        }
    }
    private void writeFinishedStringsToDocument(File file, int[] scores, String highscorePositionString, String highscoreAngleString)
    {
    	try{
            FileWriter writer = new FileWriter(file);
            String newScore = "";
            for(int j = 0; j < nbHighscores; j++){
                newScore = newScore + scores[j]+"\n";
            }
            
            newScore = newScore + highscorePositionString + "\n";
            newScore = newScore + highscoreAngleString + "\n";
            writer.write(newScore);
            writer.close();
        }catch(Exception e){
            System.out.println("writer exception");
        }
    }
    
    //IF NO HIGHSCORE FILE EXISTS, THIS METHOD CREATES ONE AND FILLS IT WITH FACTORY PRESET SCORES
    private void createNewHighscoreFile()
    {
    	try {
    		File file = new File(highscoreFile);
    		if (file.createNewFile()) {
    	        System.out.println("Highscore file created.");
    	        FileWriter writer = new FileWriter(file);
    	        String newScore = "";
            	for(int i = 0; i < nbHighscores; i++){
                    newScore = newScore + factoryPresetScores[i]+"\n";
                }
            	newScore = newScore + "0,0-" + "\n" + "0-" + "\n";
            	writer.write(newScore);
            	writer.close();
    	      } else {
    	        System.out.println("Highscore file already exists.");
    	      }
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    //IF NO CONFIG FILE EXISTS, CREATE ONE
    private void createNewConfigFile()
    {
    	try {
    		File file = new File(configFile);
    		if (file.createNewFile()) {
    	        System.out.println("Config file created.");
    	        FileWriter writer = new FileWriter(file);
            	writer.write("1");
            	writer.close();
    	      } else {
    	        System.out.println("Config file already exists.");
    	      }
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }

    
    //--------------------
    //PUBLIC METHODS
    //--------------------
    
    // CREATES NEW 
    public void createNewFiles()
    {
    	createNewHighscoreFile();
    	createNewConfigFile();
    }
    
    //RETURNS A STRING CONTAINING THE CONTENTS OF THE ENTIRE TXT-FILE
    //Ten rows of best times
    //One row of position x and y-values in  x,y-x,y-x,y-... format
    //One row of angles in angle-angle-angle-... format
    public String getHighscores()
    {
    	String highScoreFileString = "";
    	try 
    	{
            File file = new File(highscoreFile);
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) {
            	if(i < nbHighscores + 2)
            	{
            		highScoreFileString = highScoreFileString + reader.nextLine() + "\n";
                    i++;
            	}
            }
            reader.close();
        } catch (FileNotFoundException e) {
        	System.out.println("An error occurred.");
        	e.printStackTrace();
        }
    	return highScoreFileString;
    }
    
    // RETURNS A STRING-ARRAY OF HIGHSCORE.TXT WHERE:
    // Element 0-9 is top 10 times
    // Element 10 is the position-string
    // Element 11 is the angle-string
    public String[] getHighscoreStringArray()
    {
    	String[] splitString = getHighscores().split("\n");
    	return splitString;
    }
    
    // RETURNS HIGHSCORE FOR POSITION int placement AS A STRING
    // Placement is 1-10
    public String getHighscoreForPosition(int placement)
    {
    	String[] highscoreStringArray = getHighscoreStringArray();
    	String score = highscoreStringArray[placement - 1];
    	return score;
    }
    
    //CLIENT USES THIS TO SEND RACE RESULTS AND DRIVING PATH TO SERVER AFTER FINISHED RACE
    // One row with time
    // One row of position x and y-values in  x,y-x,y-x,y-... format
    // One row of angles in angle-angle-angle-... format
    public String sendScoreToServer(int time, ArrayList<Point> positions, ArrayList<Double> angles)
    {
    	String positionString = "";
    	String angleString = "";
    	for(int j = 0; j < positions.size(); j++)
    	{
    		positionString = positionString + positions.get(j).x + "," + positions.get(j).y + "-";
    		if(angles.get(j).toString().contains("E"))
    		{
    			angleString = angleString + "0.0" + "-";
    		}else {
    			angleString = angleString + angles.get(j).toString() + "-";
    		}
    	}
    	return time + "\n" + positionString + "\n"  + angleString;
    }
    
    // AFTER RACE, SERVER RECIEVES STRING WITH RACE RESULTS FROM CLIENT
    // One row with time
    // One row of position x and y-values in  x,y-x,y-x,y-... format
    // One row of angles in angle-angle-angle-... format
    public void recieveScoreFromClient(String clientScoreString) {
    	try {
    		//TEMP VALUES
    		File file = new File(highscoreFile);
            String[] clientScoreArray = clientScoreString.split("\n");
            String[] serverScoreArray = getHighscoreStringArray();
            String highscorePositionString = "";
            String highscoreAngleString = "";
            int playerScore = Integer.parseInt(clientScoreArray[0]);
            int[] scores = new int[nbHighscores];
    		
            //COLLECT TOP TEN TIMES FROM SERVER HIGHSCORES
            for(int i = 0; i < nbHighscores; i++)
            {
            	scores[i] = Integer.parseInt(serverScoreArray[i]);
            }
            
            // IF PLAYER TIME IS NEW BEST TIME, SAVE PLAYER'S DRIVING PATH
            // ELSE, KEEP OLD DRIVING PATH
            if(playerScore < scores[0])
            {
            	highscorePositionString = clientScoreArray[1];
            	highscoreAngleString = clientScoreArray[2];
            } else {
            	highscorePositionString = serverScoreArray[nbHighscores];
            	highscoreAngleString = serverScoreArray[nbHighscores + 1];
            }
            
            //Förfinar kod / Erik
            /*
            File file = new File(highscoreFile);
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) {
            	//PUT HIGHSCORE TIMES FROM TXT FILES INTO scores[] ARRAY
            	if(i < nbHighscores)
            	{
            		String data = reader.nextLine(); 
                    scores[i] = Integer.parseInt(data);
                    i++;
            	}
            	//SAVE POSITION LIST FROM TXT FILE
            	else if (i == nbHighscores)
            	{
            		String data = reader.nextLine();
            		highscorePositionString = data;
            		i++;
            	}
            	//SAVE ANGLE LIST FROM TXT FILE
            	else 
            	{
            		String data = reader.nextLine();
            		highscoreAngleString = data;
            	}
            }
            reader.close();
            */
            
            
            //SORT SUBMITTED TIME INTO HIGHSCORE ARRAY
            sortHighscores(playerScore, scores);
            
            //WRITE SORTED HIGHSCORE TIMES AND BEST DRIVER POSITION + ANGLE STRINGS, INTO TXT DOCUMENT
            writeFinishedStringsToDocument(file, scores, highscorePositionString, highscoreAngleString);
            
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
    //CLIENT RECIEVES A STRING FROM SERVER CONTAINING THE UPDATED HIGHSCORE FILE
    //Ten rows of best times
    //One row of position x and y-values in  x,y-x,y-x,y-... format
    //One row of angles in angle-angle-angle-... format
    public void recieveHighscoreStringFromServer(String highscoreString)
    {
            try{
            	File file = new File(highscoreFile);
                FileWriter writer = new FileWriter(file);
                writer.write(highscoreString);
                writer.close();
            }catch(Exception e){
                System.out.println("writer exception");
            }
    }
    
    //RETURNS BEST DRIVER'S PATH AS AN ArrayList<Point>
    public ArrayList<Point> getHighscorePositionList()
    {
    	String[] highscoreStringArray = getHighscoreStringArray();
    	
    	ArrayList<Point> posList = new ArrayList<Point>();
        String[] positionStringArray = highscoreStringArray[nbHighscores].split("-");
        for(int j = 0; j < positionStringArray.length; j++)
        {
        	String[] positionXandY = positionStringArray[j].split(",");
        	posList.add(new Point(Integer.parseInt(positionXandY[0]), Integer.parseInt(positionXandY[1])));
        }
    	return posList;
    }
    
    //RETURNS BEST DRIVER'S ANGLES AS AN ArrayList<Double>
    public ArrayList<Double> getHighscoreAngleList()
    {
    	String[] highscoreStringArray= getHighscoreStringArray();
    	
    	ArrayList<Double> list = new ArrayList<Double>();
        String[] angleStringArray = highscoreStringArray[nbHighscores + 1].split("-");
        for(int j = 0; j < angleStringArray.length; j++)
        {
        	list.add((Double.parseDouble(angleStringArray[j])));
        }
    	return list;
    }
    
    public int configGetCarColor() 
    {
    	int carColor = 0;
    	try {
    		File file = new File(configFile);
            Scanner reader = new Scanner(file);
            carColor = reader.nextInt();
            reader.close();
    	}catch(FileNotFoundException e){
    		System.out.println("writer exception");
    	}
    	return carColor;
    	
    }
    
    public void configSetCarColor(int carColor) 
    {
    	try{
        	File file = new File(configFile);
            FileWriter writer = new FileWriter(file);
            writer.write(Integer.toString(carColor));
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }catch(IOException e) {
        	System.out.println("IOExeption");
        }
    }
    
}