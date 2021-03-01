package src.GameServer;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class FileManager {
	
    private int nbHighscores = 10;
    private String filename = "Highscore.txt";
    
    //BUG CHECKING
	private ArrayList<Point> positionList = new ArrayList<Point>();
	private ArrayList<Double> angleList = new ArrayList<Double>();
	
	public FileManager(){
		
		positionList.add(new Point(5,1));
		positionList.add(new Point(2,5));
		angleList.add(7.7);
		angleList.add(7.7);
		//saveNewHighscore(1, positionList, angleList);
		//recieveScoreFromClient("1\n2,2-2,2-\n2-2\n");
		//System.out.println(getHighscores());
		//recieveHighscoreStringFromServer("fasfd");
		//System.out.println(sendScoreToServer(1, positionList, angleList));	
		
		/*
		try {
		positionList = getHighscorePositionList();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		for(int j = 0; j < positionList.size(); j++)
		{
			System.out.println(positionList.get(j).toString());
		}
		*/
		
		/*
		try {
			angleList = getHighscoreAngleList();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		for(int j = 0; j < angleList.size(); j++)
		{
			System.out.println(angleList.get(j).toString());
		}
		*/	
    }
	
	//HELPER FUNCTIONS:
	private void readDataFromTxtFile(Scanner reader, int[] scores, String highscorePositionString, String highscoreAngleString)
    {
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
    }
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
                System.out.println("newScore = \n" + newScore);
            }
            
            newScore = newScore + highscorePositionString + "\n";
            newScore = newScore + highscoreAngleString + "\n";
            writer.write(newScore);
            writer.close();
        }catch(Exception e){
            System.out.println("writer exception");
        }
    }

    
    //PUBLIC METHODS
    
    
    //CLIENT USES THIS TO SEND RACE RESULTS TO SERVER AFTER FINISHED RACE
    public String sendScoreToServer(int time, ArrayList<Point> positions, ArrayList<Double> angles)
    {
    	String positionString = "";
    	String angleString = "";
    	for(int j = 0; j < positions.size(); j++)
    	{
    		positionString = positionString + positions.get(j).x + "," + positions.get(j).y + "-";
    		angleString = angleString + angles.get(j).toString() + "-";
    	}
    	return time + "\n" + positionString + "\n"  + angleString;
    }
     
    // AFTER RACE, SERVER RECIEVES STRING WITH TIME, AND PATH THE DRIVER WENT
    // One row with time
    // One row of position x and y-values in  x,y-x,y-x,y-... format
    // One row of angles in angle-angle-angle-... format
    public void recieveScoreFromClient(String clientScoreString) {
    	try {
    		//TEMP VALUES
            String[] splitString = clientScoreString.split("\n");
            int playerScore = Integer.parseInt(splitString[0]);
            String highscorePositionString = "";
            String highscoreAngleString = "";
            int[] scores = new int[nbHighscores];
    		
            //READ TXT FILE, COLLECT DATA
            File file = new File(filename);
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
            
            //IF TIME IS NEW TOP SCORE, OVERWRITE POSITION AND ANGLE STRINGS
            if(playerScore < scores[0])
            {
            	highscorePositionString = splitString[1];
            	highscoreAngleString = splitString[2];
            }
            
            //SORT SUBMITTED TIME INTO HIGHSCORE ARRAY
            sortHighscores(playerScore, scores);
            
            //WRITE SORTED HIGHSCORE TIMES AND BEST DRIVER POSITION + ANGLE STRINGS, INTO TXT DOCUMENT
            writeFinishedStringsToDocument(file, scores, highscorePositionString, highscoreAngleString);
            
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
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
            File file = new File(filename);
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
    
    //CLIENT USES THIS TO OVERWRITE CLIENT TXT-FILE WITH NEW HIGHSCORE LIST FROM SERVER
    public void recieveHighscoreStringFromServer(String highscoreString)
    {
            try{
            	File file = new File(filename);
                FileWriter writer = new FileWriter(file);
                writer.write(highscoreString);
                writer.close();
            }catch(Exception e){
                System.out.println("writer exception");
            }
    }
    
    //RETURNS BEST DRIVER'S PATH AS AN ArrayList<Point>
    public ArrayList<Point> getHighscorePositionList() throws FileNotFoundException
    {
        String highscorePositionString = "";
        
        File file = new File(filename);
        Scanner reader = new Scanner(file);
        int i = 0;
        while (reader.hasNextLine()) {
        	if (i == nbHighscores)
        	{
        		String data = reader.nextLine();
        		highscorePositionString = data;
        	}
        	else {
        		reader.nextLine();
        	}
        	i++;
        }
        reader.close();
        
        ArrayList<Point> posList = new ArrayList<Point>();
        String[] positionStringArray = highscorePositionString.split("-");
        for(int j = 0; j < positionStringArray.length; j++)
        {
        	String[] positionXandY = positionStringArray[j].split(",");
        	posList.add(new Point(Integer.parseInt(positionXandY[0]), Integer.parseInt(positionXandY[1])));
        }
    	return posList;
    }
    
    //RETURNS BEST DRIVER'S ANGLES AS AN ArrayList<Double>
    public ArrayList<Double> getHighscoreAngleList() throws FileNotFoundException
    {
    	String highscoreAngleString = "";
        
    	File file = new File(filename);
        Scanner reader = new Scanner(file);
        int i = 0;
        while (reader.hasNextLine()) {
        	if (i == nbHighscores + 1)
        	{
        		String data = reader.nextLine();
        		highscoreAngleString = data;
        	}
        	else {
        		reader.nextLine();
        	}
        	i++;
        }
        reader.close();
        
        ArrayList<Double> list = new ArrayList<Double>();
        String[] angleStringArray = highscoreAngleString.split("-");
        for(int j = 0; j < angleStringArray.length; j++)
        {
        	list.add((Double.parseDouble(angleStringArray[j])));
        }
    	return list;
    }
}