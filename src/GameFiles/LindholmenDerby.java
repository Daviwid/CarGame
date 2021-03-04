package GameFiles;

public class LindholmenDerby extends Track {
	
	private String map, map2, checkpointmap;
	private int color;
	private int lcolor;
	private int checkpointcolor1;
	private int checkpointcolor2;
	private int checkpointcolor3;
	private int checkpointcolor4;
	private double angle;
	
	public LindholmenDerby(int x, int y)
	{
		

		map = "/Resources/s2_pixelerad.png";		//track rim
		map2 = "/Resources/s1.jpg";   //current track we will be able to see
		checkpointmap= "/Resources/checkpoints.png";  //png file containing all checkpoints
		
		color = 0xFFed1c24;			//track rim color
		lcolor = 0xFFed1c24;		//track rim low shade color
		
		angle = 0.0;
		
		setMap(map,map2, checkpointmap, x,y);			//Creates track and scale it to screensize
		setHitbox(color,lcolor);	//Creates hitbox list
		
		setStartAngle(angle);

		checkpointcolor1 = 0xFFfff200;         //Color which belong to checkpoint1	Yellow
		checkpointcolor2= 0xFFff7f27;          //Color which belong to checkpoint2	orange
		checkpointcolor3 = 0xFF22b14c;			//Color which belong to checkpoint3	green
		checkpointcolor4 = 0xFFffaec9;			//Color which belong to checkpoint4	pink
		
		setCheckpointHitbox(checkpointcolor1, checkpointcolor2, checkpointcolor3, checkpointcolor4);  //creates checkpoint list
		createStartPositions();  //creates the Lindholmen track start position
	}
}

