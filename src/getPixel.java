import java.util.*;
import java.awt.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class getPixel
{
	     private ArrayList<Point> list;
	     private BufferedImage bi;
	     
	    public getPixel()
	    {
	        list = new ArrayList<Point>();

	        try
	        {
				// the line that reads the image file
				bi= ImageIO.read(new File("tests.jpg"));		//local jpg/png
				Image resultingImage = bi.getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);	//scale to desired size
				BufferedImage outputImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
				outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	            int color= bi.getRGB(1,1); //Color at 1,1
	            int lcolor =bi.getRGB(1,3);	//Lowest "shade" at 1,3

				System.out.println("color " +color);
				System.out.println("lowcolor " +lcolor);

				System.out.println("grey " +bi.getRGB(0,0));	//Ex of backg color


				// work with the image here ...
				for (int x=0;x<outputImage.getWidth();x++)
				{
	                for (int y=0;y<outputImage.getHeight();y++)
	                {
	                    if(clr(color,lcolor,outputImage.getRGB(x,y)))
	                    {
	                        list.add(new Point(x,y));
	                    }
	                }
				}
	        } 
	        catch (IOException e)
	        {
				System.out.println("Error bild");
	          // log the exception
	          // re-throw if desired
	        }


	        System.out.println("antal " +list.size());
	    }

	    public boolean clr(int a, int b, int c)
	    {
	        double A = (double)a;
	        double B = (double)b;
	        double C = (double)c;	//Color to be compared with
	        double ac = A/C;
	        double bc = B/C;
	        if(ac >=0.9 && ac<=1.1)
	        {
	            return true;
	        }
	        if(bc>=0.9 && bc<=1.1)
	        {
	            return true;
	        }
	        return false;
	    }

	    public ArrayList<Point> getList()
	    {
	    	return this.list;

	    }

	}

