package GameFiles;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * FileManager provides helpful methods concerning handling the highscore and config files of CarGame. 
 */
public class FileManager {
	
    private int nbHighscores = 10;
    private int[] factoryPresetScores = {3000, 4000, 5000, 10000, 20000, 30000, 50000, 80000, 100000, 200000};
    private String highscoreFile = "Highscore.txt";
    private String configFile = "Config.txt";
	
    public FileManager(){
    	createNewFiles();
    }
	
	//HELPER METHODS:
    
    /**
     * Sorts player's time into an int array of highscores. The largest value is discarded. 
     * @param playerScore	Player's time
     * @param scores		Array of highscore times
     */
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
    
    /**
     * Writes sorted highscore times and car position + angle strings, into highscore document.
     * @param file						The file you are writing to
     * @param scores					int[] of highscore times
     * @param highscorePositionString	String representing the ArrayList<Point> of car positions
     * @param highscoreAngleString		String representing the ArrayList<Double> of car angles
     */
    private void writeFinishedStrings(File file, int[] scores, String highscorePositionString, String highscoreAngleString)
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
    
    /**
     * Creates a new highscore file and fills it with factory preset scores, if and only if no highscore file exists.
     */
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
    }
    
    /**
     * Creates a new config file and writes a 1 into it, if and only if no config file exists.
     */
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
    	
    }

    //--------------------
    // PRIVATE METHODS
    //--------------------
    
    /**
     * Creates new highscore and config files, if and only if they do not already exist.
     */
    private void createNewFiles()
    {
    	createNewHighscoreFile();
    	createNewConfigFile();
    }
    
    //--------------------
    // PUBLIC METHODS
    //--------------------    

    /**
     * Returns a String containing the entire highscore file. 
     * @return Highscore String with ten lines of best times, two lines of car positions and angles 
     */
    public String getHighscoreString()
    {
    	String highscoreString = "";
    	try 
    	{
            File file = new File(highscoreFile);
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) {
            	if(i < nbHighscores + 2)
            	{
            		highscoreString = highscoreString + reader.nextLine() + "\n";
                    i++;
            	}
            }
            reader.close();
        } catch (FileNotFoundException e) {
        	System.out.println("An error occurred.");
        	e.printStackTrace();
        }
    	return highscoreString;
    }
    
    /**
     * Returns a String[] of the Highscore file.
     * @return Highscore String[], element 0-9 are top 10 times, element 10 and 11 are car positions and angles.
     */
    public String[] getHighscoreStringArray()
    {
    	String[] splitString = getHighscoreString().split("\n");
    	return splitString;
    }
    
    /**
     * Returns the time for the specific highscore specified in the argument.
     * @param placement		The position in the highscore list
     * @return Highscore String
     */
    public String getHighscoreForPosition(int placement)
    {
    	String[] highscoreStringArray = getHighscoreStringArray();
    	String score = highscoreStringArray[placement - 1];
    	return score;
    }
    
    /**
     * Converts finished race time and driving path to String form.
     * @param time			Player's time
     * @param positions		List of car positions during the race
     * @param angles		List of car angles during the race
     * @return String with one line of time, one line of car positions and one line of angles.
     */
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
    
    /**
     * Compares incoming player score with server's highscore list and updates highscore list accordingly.
     * @param clientScoreString		Player's score in String format
     */
    public void recieveScoreFromClient(String clientScoreString) {
    	try {
    		// Temp values
    		File file = new File(highscoreFile);
            String[] clientScoreArray = clientScoreString.split("\n");
            String[] serverScoreArray = getHighscoreStringArray();
            String highscorePositionString = "";
            String highscoreAngleString = "";
            int playerScore = Integer.parseInt(clientScoreArray[0]);
            int[] scores = new int[nbHighscores];
    		
            // Collect top ten times from server highscores
            for(int i = 0; i < nbHighscores; i++)
            {
            	scores[i] = Integer.parseInt(serverScoreArray[i]);
            }
            
            // If player time is new best time, save player's driving path
            // Else, keep old driving path
            if(playerScore < scores[0])
            {
            	highscorePositionString = clientScoreArray[1];
            	highscoreAngleString = clientScoreArray[2];
            } else {
            	highscorePositionString = serverScoreArray[nbHighscores];
            	highscoreAngleString = serverScoreArray[nbHighscores + 1];
            }
            
            // Sort submitted time into highscore array
            sortHighscores(playerScore, scores);
            
            // Update highscore file with sorted times and best driver position + angle strings
            writeFinishedStrings(file, scores, highscorePositionString, highscoreAngleString);
            
        } catch (Exception e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
    /**
     * Replaces contents of Highscore file with incoming String of new highscores.
     * @param highscoreString	Server's highscores in String format
     */
    public void recieveStringFromServer(String highscoreString)
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
    
    /**
     * Returns best driver's path from highscore file.
     * return ArrayList of positions during the race.
     */
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
    
    /**
     * Returns best driver's angles from highscore file.
     * return ArrayList of angles during the race.
     */
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
    
    /**
     * Returns car color from config file.
     * 
     */
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
    
    /**
     * Saves car color into config file.
     * @param carColor	An integer of 1, 2 or 3 to represent different colors.
     */
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