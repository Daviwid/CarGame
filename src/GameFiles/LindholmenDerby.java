package GameFiles;

public class LindholmenDerby extends Track {
	
	private String mapHitbox, map, checkpointmap;
	private int color, lcolor;
	private int checkpointcolor1,checkpointcolor2,checkpointcolor3,checkpointcolor4;
	private double angle;
	
	//Creates the map LindholmenDerby
	public LindholmenDerby(int x, int y)
	{
		mapHitbox = "/Resources/s2_pixelerad.png";		//rim of the map
		map = "/Resources/s1.jpg"; 					  //the jpg to be drawn as map
		checkpointmap= "/Resources/checkpoints.png";  //png containing the checkpoints
		
		color = 0xFFed1c24;								//Color of the rim
		lcolor = 0xFFed1c24;							//Lower shade of the rim color 
		
		angle = 0.0;
		
		setMap(mapHitbox,map, checkpointmap, x,y);			//Initiate the map scaled to x,y
		setHitbox(color,lcolor);							//Makes the hitbox list
		
		setStartAngle(angle);

		checkpointcolor1 = 0xFFfff200;         //Color som ska tillhöra checkpoint1	Gul
		checkpointcolor2= 0xFFff7f27;          ////Color som ska tillhöra checkpoint2	orange
		checkpointcolor3 = 0xFF22b14c;			//Color som ska tillhöra checkpoint3	green
		checkpointcolor4 = 0xFFffaec9;			//Color som ska tillhöra checkpoint4	pink
		
		setCheckpointHitbox(checkpointcolor1, checkpointcolor2, checkpointcolor3, checkpointcolor4);
		createStartPositions();
	}
}

