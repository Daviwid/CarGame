package GameFiles;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * getCheckpointspixel handles retrieving the cordinates for all checkpoints and adds them to seperate checkpointlists
 * @author Victoria
 * @version 2.1.3.0
 * @since 2021-03-05
 */
public class getCheckpointsPixel {
/*ArrayLists containing the point cordinates for all checkpoints*/    
private ArrayList<Point> checklist1;
private ArrayList<Point> checklist2;
private ArrayList<Point> checklist3;
private ArrayList<Point> checklist4;

   /**
    * constructor 
    * @param bi  bufferedimage with the checkpoints drawn to it
    * @param checkpointcolor1  the specific color checkpoint1 should have
    * @param checkpointcolor2  the specific color checkpoint2 should have
    * @param checkpointcolor3  the specific color checkpoint3 should have
    * @param checkpointcolor4  the specific color checkpoint4 should have
    * @param checkpointnr  checkpointnumber
    */
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
    
    
    /**
     * compares color a with b
     * @param a checkpoint n color
     * @param b color to be compared with
     * @param checknr decides the percent of color match to use depending of which checkpoint it is
     * @return boolean if color a matches color b
     */
    private boolean clr(int a, int b, int checknr) // A=COLOR B=RGB(X,Y)= each pixels color for the screen
    {
    	double A = (double)a;
        double B = (double)b;	
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
    
    /**
     * Method to return the generated list of found matching colors of checkpoint1
     * @return list of points
     */
    public ArrayList<Point> getCheckpointList1()
    {
    	return this.checklist1;
    }
    /**
     * Method to return the generated list of found matching colors of checkpoint2
     * @return list of points
     */
    public ArrayList<Point> getCheckpointList2()
    {
    	return this.checklist2;
    }
    /**
     * Method to return the generated list of found matching colors of checkpoint3
     * @return list of points
     */
    public ArrayList<Point> getCheckpointList3()
    {
    	return this.checklist3;
    }
    /**
     * Method to return the generated list of found matching colors of checkpoint4
     * @return list of points
     */
    public ArrayList<Point> getCheckpointList4()
    {
    	return this.checklist4;
    }
}
