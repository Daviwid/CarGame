public class LindholmenDerby extends Track {
	
	private String map;
	private int color;
	private int lcolor;
	
	public LindholmenDerby(int x, int y)
	{
		map = "tests.jpg";			//Laddar in banan Lindholmen...
		color = 0xFF34CE85;			//Color som ska vara sarg
		lcolor = 0xFF067F00;		//Low shade av color som ska vara sarg
		setMap(map,x,y);			//Skapar bana och scale till screensize
		setHitbox(color,lcolor);	//Skapar hitbox-lista
	}
}

