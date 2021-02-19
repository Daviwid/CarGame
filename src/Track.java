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
	private BufferedImage icon;

	private getPixel pixels;
	private ArrayList<Point> list;
	
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

	public void setHitbox(int color,int lcolor)		//Set hitbox-list once. 
	{
		pixels = new getPixel(bi,color,lcolor);
		this.list = pixels.getList();
	}

	public ArrayList<Point> getHitbox()		//Get hitbox-list. Kallas hela tiden.
	{
		return this.list;
	}

}
