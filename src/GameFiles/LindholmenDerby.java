package GameFiles;

/**
 * Subclass of Track. Holds information for a specifik track.
 * @version 2.1.3.0
 * @since 2021-03-05
 *
 */
public class LindholmenDerby extends Track {
	
	private String mapHitbox, map, checkpointmap;
	private int color;
	private int checkpointcolor1;
	private int checkpointcolor2;
	private int checkpointcolor3;
	private int checkpointcolor4;

	private double angle;
	
	//Creates the map LindholmenDerby
	
	/**
	 * Constructor for the map LindholmenDerby.
	 * Gives local strings the paths to the images that has to be loaded.
	 * Sets the starting values of the car.
	 * Sets the colorvalues that will be used to analyse the images.
	 * Then sends the local variables to the methods inherited from Track that initiates the map rims, checkpoints etc.
	 * 
	 * @param x
	 * @param y
	 */
	public LindholmenDerby(int x, int y)
	{
		mapHitbox = "/Resources/s2_pixelerad.png";		//rim of the map
		map = "/Resources/s1.jpg"; 					  //the jpg to be drawn as map
		checkpointmap= "/Resources/checkpoints.png";  //png containing the checkpoints

		color = 0xFFed1c24;								//Color of the rim
		
		angle = 0.0;
		
		setMap(mapHitbox,map, checkpointmap, x,y);			//Initiate the map scaled to x,y
		setHitbox(color);									//Makes the hitbox list
		
		setStartAngle(angle);

		checkpointcolor1 = 0xFFfff200;         //Color which belong to checkpoint1	Yellow
		checkpointcolor2= 0xFFff7f27;          //Color which belong to checkpoint2	orange
		checkpointcolor3 = 0xFF22b14c;			//Color which belong to checkpoint3	green
		checkpointcolor4 = 0xFFffaec9;			//Color which belong to checkpoint4	pink
		
		setCheckpointHitbox(checkpointcolor1, checkpointcolor2, checkpointcolor3, checkpointcolor4);  //creates checkpoint list
		createStartPositions();  //creates the Lindholmen track start position
	}
}

