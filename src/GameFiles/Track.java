package GameFiles;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Track class is responsible for handling the hitboxes and checkboxes , also handles scaling all the images to be used for the track
 * @version 2.0.3.0
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
    public void createStartPositions()
    {
        firstX = checkpointlist1.get(0).x;
        lastX = checkpointlist1.get(checkpointlist1.size() - 1).x;
        startPositionX = ((lastX - firstX) / 2) + firstX;
    	
        firstY = checkpointlist1.get(0).y;
        lastY = checkpointlist1.get(checkpointlist1.size() - 1).y;
        startPositionY = ((lastY - firstY) / 2) + firstY;
    }
    
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
	
	public void setHitbox(int color)		//Set hitbox-list once. 
	{
		pixels = new getPixel(bi,color);
		this.list = pixels.getList();
	}
	public void setStartAngle(double a)				//Set start-angle
	{
		startAngle = a;
	}
	
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
