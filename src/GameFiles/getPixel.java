package GameFiles;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class getPixel
{
    /*ArrayList containing the point cordinates for the hitbox of the rim*/
    private ArrayList<Point> list;
    
    /**
     * constructor
     * @param bi bufferedimage with the hitbox of the rim drawn to it
     * @param color color of the rim
     */
    public getPixel(BufferedImage bi,int color)
    {
        list = new ArrayList<Point>();
        
		// work with the image here ...
        for (int x=0;x<bi.getWidth();x++)//send all pixels colors from the screen to compare with the hitbox pixel color
        {
            for (int y=0;y<bi.getHeight();y++)
            {
                if(clr(color,bi.getRGB(x,y)))
                {

                    if(clr(color,bi.getRGB(x,y)))
                    {
                        list.add(new Point(x,y));  //adds the current pixel cordinates to hitboxlist if clr method detects the color of the rim in the BufferedImage
                    }

                }
            }
        }
    }
    
    
    
    /**
     * Comparison method. Compares color c with a.
     * @param a color of the hitbox
     * @param c color to be compared with
     * @return Returns a boolean if the color c is acceptable.
     */
    public boolean clr(int a, int c) // A=COLOR C=RGB(X,Y)= each pixels color for the screen
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
    /*returning arrayList containing point cordinates for the hitboxes*/
    public ArrayList<Point> getList()
    {
    	return this.list;
    }
   
}

