public class LindholmenDerby extends Track {
	
	private String map,map2;
	private int color;
	private int lcolor;
	
	public LindholmenDerby(int x, int y)
	{
		map = "s2.jpg";			//Laddar in banan Lindholmen...
		map2 = "s1.jpg";
		color = 0xFFFF0000;			//Color som ska vara sarg
		lcolor = 0xFFfe2a2a;		//Low shade av color som ska vara sarg
		setMap(map,map2,x,y);			//Skapar bana och scale till screensize
		setHitbox(color,lcolor);	//Skapar hitbox-lista
	}
}

