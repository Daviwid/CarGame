import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class getPixel
{
     private ArrayList<Point> list;
    
    public getPixel(BufferedImage bi,int color,int lcolor)
    {
        list = new ArrayList<Point>();
        
		// work with the image here ...
		for (int x=0;x<bi.getWidth();x++)
		{
            for (int y=0;y<bi.getHeight();y++)
            {
                if(clr(color,lcolor,bi.getRGB(x,y)))
                {
                    list.add(new Point(x,y));
                }
            }
		}
		
        //debug
//		System.out.println("color " +color);
//		System.out.println("lowcolor " +lcolor);
//		System.out.println("grey " +bi.getRGB(0,0));	//I.e background color
//      System.out.println("antal " +list.size());
		//end of debug
    }
    
    
    
    public boolean clr(int a, int b, int c)
    {
        double A = (double)a;
        double B = (double)b;
        double C = (double)c;	//Color to be compared with
        double ac = A/C;
        double bc = B/C;
        if(ac >=0.5 && ac<=1.5)
        {
            return true;
        }
        if(bc>=0.5 && bc<=1.5)
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

