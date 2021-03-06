package GameFiles;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * getPixel is used to search an image file for pixels of a given color to create an Arraylist of collision points of the rim of the track.
 * @author Victoria
 * @version 2.1.3.0
 * @since 2021-03-05 
 */
public class getPixel
{
    private ArrayList<Point> list;
    /**
     * Loops through all pixels on image and compares them to the color.
     * Adds the pixelpoint to the list if color matches
     * @param bi	
     * @param color
     */
    public getPixel(BufferedImage bi,int color)
    {
        list = new ArrayList<Point>();
        
		// work with the image here ...
        for (int x=0;x<bi.getWidth();x++)
        {
            for (int y=0;y<bi.getHeight();y++)
            {
                if(clr(color,bi.getRGB(x,y)))
                {
                    if(clr(color,bi.getRGB(x,y)))
                    {
                        list.add(new Point(x,y));
                    }
                }
            }
        }
    }
    
    
    /**
     * Comparison method. Compares color c with a with tolerance of 50 procent.
     * @param a			The desired color code
     * @param c			The pixel color code to be compared with
     * @return boolean	Returns boolean true if color matches and false if not.
     **/
    public boolean clr(int a, int c)
    {
        double A = (double)a;
        double C = (double)c;			//Color to be compared with
        double ac = A/C;
        if(ac >=0.5 && ac<=1.5)			//Range of 50%
        {
            return true;
        }
        return false;
    }
    
    /**
     * Method to return the generated list of found matching colors
     * @return list of points
     */
    public ArrayList<Point> getList()
    {
    	return this.list;
    }
   
}

