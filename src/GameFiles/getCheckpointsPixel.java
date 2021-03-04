package GameFiles;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class getCheckpointsPixel {
/*ArrayLists containing the point cordinates for all checkpoints*/    
private ArrayList<Point> checklist1;
private ArrayList<Point> checklist2;
private ArrayList<Point> checklist3;
private ArrayList<Point> checklist4;

   /*constructor receives a bufferedimage with the checkpoints drawn to it, the specific color each checkpoint should have and a checkpointnumber*/ 
    public getCheckpointsPixel(BufferedImage bi,int checkpointcolor1, int checkpointcolor2, int checkpointcolor3, int checkpointcolor4, int checkpointnr)
    {
        checklist1 = new ArrayList<Point>();
        checklist2 = new ArrayList<Point>();
        checklist3 = new ArrayList<Point>();
        checklist4 = new ArrayList<Point>();
        
		// work with the image here ...
		for (int x=0;x<bi.getWidth();x++)
		{
            for (int y=0;y<bi.getHeight();y++)   //send all pixels colors from the screen to compare with the designated checkpoints pixel color
            {
                if(checkpointnr==1 && clr(checkpointcolor1, bi.getRGB(x,y), 1)) //for each checkpoint, adds the current pixel cordinates to checkpointlist if clr method detects the specific color in the BufferedImage
                {
                	 checklist1.add(new Point(x,y)); 
                }
                if(checkpointnr==2 && clr(checkpointcolor2, bi.getRGB(x,y), 2)) 
                {
                	 checklist2.add(new Point(x,y));
                }
                if(checkpointnr==3 && clr(checkpointcolor3, bi.getRGB(x,y), 3)) 
                {
                	 checklist3.add(new Point(x,y));
                }
                if(checkpointnr==4 && clr(checkpointcolor4, bi.getRGB(x,y), 4)) 
                {
                	 checklist4.add(new Point(x,y));
                }
                
                
            }
		}
		
    }
    
    
    
    private boolean clr(int a, int b, int checknr) // A=COLOR B=RGB(X,Y)= each pixels color for the screen
    {
    	double A = (double)a;
        double B = (double)b;	//Color to be compared with
        double ab = A/B;
        
        
        
        if((checknr==2 || checknr==3)&& ab >=0.9 && ab<=1.1)//checks if a certain procent of the color code can be detected from the screen 
        {
            return true;
        }
        if((checknr==1 || checknr==4)&& ab >=1 && ab<=1.0)
        {
            return true;
        }
        
        return false;
    }
    /*getters returning arrayList containing point cordinates for all checkpoints*/
    public ArrayList<Point> getCheckpointList1()
    {
    	return this.checklist1;
    	
    }
    public ArrayList<Point> getCheckpointList2()
    {
    	return this.checklist2;
    	
    }
    public ArrayList<Point> getCheckpointList3()
    {
    	return this.checklist3;
    	
    }
    public ArrayList<Point> getCheckpointList4()
    {
    	return this.checklist4;
    	
    }
}
