package GameFiles;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class getPixel
{
    private ArrayList<Point> list;
    
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
                    list.add(new Point(x,y));
                }
            }
        }
    }
    
    
    //Comparison method. Compares color c with a.
    //Returns a boolean if the color c is acceptable.
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
    
    public ArrayList<Point> getList()
    {
    	return this.list;
    }
   
}

