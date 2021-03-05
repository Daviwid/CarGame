package GameFiles;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Track {
	private int startPositionX;
	private int startPositionY;
	private double startAngle;
	private BufferedImage bi, bi2,checkbi,tmp;
	private BufferedImage icon;
	private getPixel pixels;
	private getCheckpointsPixel cpixels;
	private ArrayList<Point> list;
	private ArrayList<Point> checkpointlist1;
	private ArrayList<Point> checkpointlist2;
	private ArrayList<Point> checkpointlist3;
	private ArrayList<Point> checkpointlist4;
	private int firstX, lastX, firstY, lastY;
	
	
	//getters&setters
    public void createStartPositions()
    {
        firstX = checkpointlist1.get(0).x;
        lastX = checkpointlist1.get(checkpointlist1.size() - 1).x;
        startPositionX = ((lastX - firstX) / 2) + firstX;
    	
        firstY = checkpointlist1.get(0).y;
        lastY = checkpointlist1.get(checkpointlist1.size() - 1).y;
        startPositionY = ((lastY - firstY) / 2) + firstY;
    }
    /**
	 * method scale images so they match the size of the game screen
	 * @param map_name  png containing the rim of the map
	 * @param map_name2  png containing the visual map we will see in game mode
	 * @param checkpointfile png containing the checkpoints
	 * @param x screensize x axis
	 * @param y screensize y axis
	 * @see LindHolmenDerby
	 */
	public void setMap(String map_name,String map_name2, String checkpointfile, int x, int y)
	{
		//Find the images in resource folder and scale them to the screensize window x,y
		try
	    {
			tmp= ImageIO.read(getClass().getResource(map_name));								
			this.bi = scaleIMG(tmp,x,y);																	
			
			this.icon= ImageIO.read(getClass().getResource(map_name2));		//Save unscaled image as icon for map-selection					
			this.bi2 = scaleIMG(icon,x,y);									
			
			tmp= ImageIO.read(getClass().getResource(checkpointfile));											
			this.checkbi = scaleIMG(tmp,x,y);
	    } 
	    catch (IOException e)
	    {
			System.out.println("Error hittar ej bild");
	    }
	}
	/**
	 * method take the color of the rim and creates list containing the hitbox for the rim
	 * @param color color of the rim
	 */
	public void setHitbox(int color)		//Set hitbox-list once. 
	{
		pixels = new getPixel(bi,color);
		this.list = pixels.getList();
	}
	
	public void setStartAngle(double a)				//Set start-angle
	{
		startAngle = a;
	}
	/**
	 * method calls on getCheckpointsPixel to get 4 seperate lists containing the cordinates for each chechpoint
	 * @param color1 checkpoint color 1 
	 * @param color2 checkpoint color 2 
	 * @param color3 checkpoint color 3 
	 * @param color4 checkpoint color 4 
	 * @see LindholmenDerby
	 */
	public void setCheckpointHitbox(int color1, int color2, int color3, int color4)		//calls on getCheckpointsPixel and retrieves list with the checkpointpixels for each checkpoint 
	{
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, 1); 
		this.checkpointlist1 = cpixels.getCheckpointList1();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, 2); 
		this.checkpointlist2 = cpixels.getCheckpointList2();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, 3); 
		this.checkpointlist3 = cpixels.getCheckpointList3();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, 4); 
		this.checkpointlist4 = cpixels.getCheckpointList4();
	}
	/**
	 * method scales the images to x,y
	 * @param b  bufferedImage to be scaled
	 * @param x  screensize in x axis
	 * @param y  screensize in y axis
	 * @return a scaled bufferedImage to x,y
	 * @see setMap
	 */
	public BufferedImage scaleIMG(BufferedImage b, int x, int y)
	{
		Image resultingImage = b.getScaledInstance(x, y, Image.SCALE_DEFAULT);					//scale to desired size
		BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);			
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
	
    public Point getStartPositions()
    {
    	return new Point(startPositionX,startPositionY);
    }
	
	public double getStartAngle()
	{
		return startAngle;
	}
	
	public BufferedImage getIcon(int x, int y)
	{
		return scaleIMG(icon,x,y);
	}

	public BufferedImage getMap()		//Return image, mostly needed in view.
	{
		return bi2;
	}
	
	public BufferedImage getCheckpointsMap()		//Return image, mostly needed in view.
	{
		return checkbi;
	}	

	public ArrayList<Point> getHitbox()		//Get hitbox-list. Kallas hela tiden.
	{
		return this.list;
	}
	public ArrayList<Point> getCheckpoints1Hitbox()		//Get checkpoints1
	{
		return this.checkpointlist1;
	}
	public ArrayList<Point> getCheckpoints2Hitbox()		//Get checkpoints2
	{
		return this.checkpointlist2;
	}
	public ArrayList<Point> getCheckpoints3Hitbox()		//Get checkpoints3
	{
		return this.checkpointlist3;
	}
	public ArrayList<Point> getCheckpoints4Hitbox()		//Get checkpoints4
	{
		return this.checkpointlist4;
	}
}
