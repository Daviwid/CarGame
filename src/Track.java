import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Track {
	private int startPositionX;
	private int startPositionY;
	private int startAngle;
	private BufferedImage bi;
	private BufferedImage checkbi;
	
	private BufferedImage icon;

	private getPixel pixels;
	private getCheckpointsPixel cpixels;
	private ArrayList<Point> list;
	private ArrayList<Point> checkpointlist1;
	private ArrayList<Point> checkpointlist2;
	private ArrayList<Point> checkpointlist3;
	private ArrayList<Point> checkpointlist4;
	
	
	//getters&setters
	public int getStartPositionX()
	{
		return startPositionX;
	}
	public int getStartPositionY()
	{
		return startPositionY;
	}
	public int getStartAngle()
	{
		return startAngle;
	}

	public void setMap(String map_name,int x, int y)
	{
		 try
	        {
				this.icon= ImageIO.read(new File(map_name));											//local jpg/png
				Image resultingImage = icon.getScaledInstance(x, y, Image.SCALE_DEFAULT);				//scale to desired size
				BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);
				outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
				this.bi = outputImage;
	        } 
	        catch (IOException e)
	        {
				System.out.println("Error hittar ej bild");
	        }
	}
	
	public void setCheckpoints(String checkpoint_name, int x, int y)  //gör om setcheckpoints och setmap till samma kod sen
	{
		 try
	        {
				this.icon= ImageIO.read(new File(checkpoint_name));											//local jpg/png
				Image resultingImage = icon.getScaledInstance(x, y, Image.SCALE_DEFAULT);				//scale to desired size
				BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);
				outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
				this.checkbi = outputImage;
	        } 
	        catch (IOException e)
	        {
				System.out.println("Error hittar ej bild");
	        }
	}
	
	
  
	public BufferedImage getIcon(int x, int y)
	{
		Image resultingImage = icon.getScaledInstance(x, y, Image.SCALE_DEFAULT);				//scale to desired size
		BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	public BufferedImage getMap()		//Return image, mostly needed in view.
	{
		return bi;
	}
	
	public BufferedImage getCheckpointsMap()		//Return image, mostly needed in view.
	{
		return checkbi;
	}

	public void setHitbox(int color,int lcolor)		//Set hitbox-list once. 
	{
		pixels = new getPixel(bi,color,lcolor);
		this.list = pixels.getList();
	}
	
	public void setCheckpointHitbox(int color1, int color2, int color3, int color4, int lcolor, int lcolor3, int lcolor4)		//Set hitbox-list once. 
	{
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, lcolor, lcolor3, lcolor4, 1); //checkbi
		this.checkpointlist1 = cpixels.getCheckpointList1();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, lcolor, lcolor3,lcolor4, 2); //checkbi
		this.checkpointlist2 = cpixels.getCheckpointList2();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, lcolor, lcolor3,lcolor4, 3); //checkbi
		this.checkpointlist3 = cpixels.getCheckpointList3();
		
		cpixels = new getCheckpointsPixel(checkbi,color1, color2, color3, color4, lcolor, lcolor3,lcolor4, 4); //checkbi
		this.checkpointlist4 = cpixels.getCheckpointList4();
		
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