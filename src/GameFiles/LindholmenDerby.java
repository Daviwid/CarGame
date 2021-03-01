package GameFiles;

public class LindholmenDerby extends Track {
	
	private String map, map2, checkpointmap;
	private int color;
	private int lcolor;
	private int checkpointcolor1;
	
	private int checkpointcolor2;
	
	private int checkpointcolor3;
	
	private int checkpointcolor4;
	
	public LindholmenDerby(int x, int y)
	{
		

		map = "/Resources/s2_pixelerad.png";		//bilbanans sarg
		map2 = "/Resources/s1.jpg";   //bilbanan vi kommer se
		checkpointmap= "/Resources/checkpoints.png";  //png fil med alla checkpoints
		
    color = 0xFFed1c24;			//Color som ska vara sarg
		lcolor = 0xFFed1c24;		//Low shade av color som ska vara sarg
		setMap(map,map2, checkpointmap, x,y);			//Skapar bana och scale till screensize
		setHitbox(color,lcolor);	//Skapar hitbox-lista
		
		

		checkpointcolor1 = 0xFFffaec9;			//Color som ska tillhöra checkpoint1
		checkpointcolor2 = 0xFF22b14c;			//Color som ska tillhöra checkpoint2
		checkpointcolor3= 0xFFff7f27;          ////Color som ska tillhöra checkpoint3
		checkpointcolor4 = 0xFFfff200;         //Color som ska tillhöra checkpoint4
		
		setCheckpointHitbox(checkpointcolor1, checkpointcolor2, checkpointcolor3, checkpointcolor4);
		
	}
}

