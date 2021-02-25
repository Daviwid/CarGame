public class LindholmenDerby extends Track {
	
	private String map;
	private String checkpointmap;
	
	private int color;
	private int lcolor;
	private int checkpointcolor1;
	private int checkpointlcolor;
	private int checkpointlcolor3;
	private int checkpointcolor2;
	
	private int checkpointcolor3;
	
	private int checkpointcolor4;
	private int checkpointlcolor4;
	
	public LindholmenDerby(int x, int y)
	{
		map = "tests.jpg";			//Laddar in banan Lindholmen...  //byt ut till tests.jpg när debug är klar
		color = 0xFF34CE85;			//Color som ska vara sarg
		lcolor = 0xFF067F00;		//Low shade av color som ska vara sarg
		setMap(map,x,y);			//Skapar bana och scale till screensize
		setHitbox(color,lcolor);	//Skapar hitbox-lista
		
		checkpointmap= "banan-med-alla-streck.jpg";
		checkpointcolor1 = 0xFFCC3F9D;			//Color som ska tillhöra checkpoint1
		checkpointlcolor = 0xFFAF4A8E;		//Low shade av color som ska tillhör checkpoint, men checkpoint kanske inte behöver low shade?
		setCheckpoints(checkpointmap, x, y);
		
		
		
		
		checkpointcolor2 = 0xFF3F48CC;			//Color som ska tillhöra checkpoint2
		checkpointcolor3 = 0xFFec1c24;			//Color som ska tillhöra checkpoint3
		checkpointlcolor3 = 0xFFEC1C24;        //lColor som ska tillhöra checkpoint3
		checkpointcolor4 = 0xFFffaec8;         //Color som ska tillhöra checkpoint4
		checkpointlcolor4 = 0xFFFFAEC8;       //lColor som ska tillhöra checkpoint4
		setCheckpointHitbox(checkpointcolor1, checkpointcolor2, checkpointcolor3, checkpointcolor4, checkpointlcolor, checkpointlcolor3, checkpointlcolor4);
		
	}
}