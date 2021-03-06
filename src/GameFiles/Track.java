package GameFiles;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Track class is responsible for handling the hitboxes and checkboxes, also handles scaling all the images to be used for the track
 * @author Kevin
 * @version 2.1.3.0
 * @since 2021-03-05
 */
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
	/**
	 * Method to set the startposition for a car in tracks.
	 * Finds the midpoint of a line using the acquired list of checkpoint.
	 */
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
	 * Method to initiate the track UI. Loads images and work with them.
	 * @param map_name			string of path to image
	 * @param map_name2			string of path to image
	 * @param checkpointfile	string of path to image
	 * @param x					width of gamewindow
	 * @param y					height of gamewindow
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
	 * Method that is called on once and generates list of rimpoints
	 * @param color		color of rim
	 */
	public void setHitbox(int color)		//Set hitbox-list once. 
	{
		pixels = new getPixel(bi,color);
		this.list = pixels.getList();
	}
	/**
	 * Sets the start angle of the car
	 * @param a		double value
	 */
	public void setStartAngle(double a)				//Set start-angle
	{
		startAngle = a;
	}
	
	/**
	 * Method that is called on once and generates lists of checkpoints
	 * @param color1	integer of checkpoint1 color
	 * @param color2	integer of checkpoint2 color
	 * @param color3	integer of checkpoint3 color
	 * @param color4	integer of checkpoint4 color
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
	
	//Scales the images to x,y
	/**
	 * Helpermethod to scale images to specifik size
	 * @param b		BufferedImage to be scaled
	 * @param x		desired integer width
	 * @param y		desired integer height
	 * @return BufferedImage
	 */
	public BufferedImage scaleIMG(BufferedImage b, int x, int y)
	{
		Image resultingImage = b.getScaledInstance(x, y, Image.SCALE_DEFAULT);					//scale to desired size
		BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);			
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
	/**
	 * Method that sends the startpositions
	 * @return Point
	 */
    public Point getStartPositions()
    {
    	return new Point(startPositionX,startPositionY);
    }
	/**
	 * Method that sends the startangle
	 * @return Double
	 */
	public double getStartAngle()
	{
		return startAngle;
	}
	/**
	 * Method that sends the scaled icon of track used in map-selection.
	 * @param x		desired width
	 * @param y		desired height
	 * @return BufferedImage
	 */
	public BufferedImage getIcon(int x, int y)
	{
		return scaleIMG(icon,x,y);
	}
	/**
	 * Method that sends the image of the track
	 * @return BufferedImage
	 */
	public BufferedImage getMap()		//Return image, mostly needed in view.
	{
		return bi2;
	}
	/**
	 * Method that sends the image of checkpoints. Used as debug
	 * @return BufferedImage
	 */
	public BufferedImage getCheckpointsMap()		//Return image, mostly needed in view.
	{
		return checkbi;
	}	
	/**
	 * Method that sends the list of rimpoints
	 * @return ArrayList
	 */
	public ArrayList<Point> getHitbox()		//Get hitbox-list. Kallas hela tiden.
	{
		return this.list;
	}
	/**
	 * Method that sends the list of checkpoint1 points
	 * @return ArrayList
	 */
	public ArrayList<Point> getCheckpoints1Hitbox()		//Get checkpoints1
	{
		return this.checkpointlist1;
	}
	/**
	 * Method that sends the list of checkpoint2 points
	 * @return ArrayList
	 */
	public ArrayList<Point> getCheckpoints2Hitbox()		//Get checkpoints2
	{
		return this.checkpointlist2;
	}
	/**
	 * Method that sends the list of checkpoint3 points
	 * @return ArrayList
	 */
	public ArrayList<Point> getCheckpoints3Hitbox()		//Get checkpoints3
	{
		return this.checkpointlist3;
	}
	/**
	 * Method that sends the list of checkpoint4 points
	 * @return ArrayList
	 */
	public ArrayList<Point> getCheckpoints4Hitbox()		//Get checkpoints4
	{
		return this.checkpointlist4;
	}
}
