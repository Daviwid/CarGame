import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class getCheckpointsPixel {
private ArrayList<Point> checklist1;
private ArrayList<Point> checklist2;
private ArrayList<Point> checklist3;
private ArrayList<Point> checklist4;

   
    public getCheckpointsPixel(BufferedImage bi,int checkpointcolor1, int checkpointcolor2, int checkpointcolor3, int checkpointcolor4, int lcolor, int lcolor3, int lcolor4, int checkpointnr)
    {
        checklist1 = new ArrayList<Point>();
        checklist2 = new ArrayList<Point>();
        checklist3 = new ArrayList<Point>();
        checklist4 = new ArrayList<Point>();
        
		// work with the image here ...
		for (int x=0;x<bi.getWidth();x++)
		{
            for (int y=0;y<bi.getHeight();y++)
            {
                if(checkpointnr==1 && clr(checkpointcolor1,lcolor, bi.getRGB(x,y), 1)) //send all pixels colors from the screen to compare with the designated checkpoints pixel color
                {
                	 checklist1.add(new Point(x,y)); 
                }
                if(checkpointnr==2 && clr(checkpointcolor2,lcolor, bi.getRGB(x,y), 2)) //send all pixels colors from the screen to compare with the designated checkpoints pixel color
                {
                	 checklist2.add(new Point(x,y));
                }
                if(checkpointnr==4 && clr(checkpointcolor4,lcolor4, bi.getRGB(x,y), 4)) //send all pixels colors from the screen to compare with the designated checkpoints pixel color
                {
                	 checklist4.add(new Point(x,y));
                }
                if(checkpointnr==3 && clr(checkpointcolor3,lcolor3, bi.getRGB(x,y), 3)) //send all pixels colors from the screen to compare with the designated checkpoints pixel color
                {
                	 checklist3.add(new Point(x,y));
                }
                
            }
		}
		
    }
    
    
    
    public boolean clr(int a, int b, int c, int checknr) // A=COLOR B=LCOLOR C=RGB(X,Y)=VARJE FÄRGPIXEL FÖR SKÄRMEN
    {
    	double A = (double)a;
        double B = (double)b;
        double C = (double)c;	//Color to be compared with
        double ac = A/C;
        double bc = B/C;
        
        if((checknr==3) && ac >=0.99 && ac <= 1.01)
        {
            return true;
        }
        if((checknr==2 )&& ac >=0.9 && ac<=1.1)
        {
            return true;
        }
        if((checknr==1 )&& ac >=1 && ac<=1.0)
        {
            return true;
        }
        if((checknr==4 )&& ac >=1 && ac<=1.0)
        {
            return true;
        }
        if((checknr==4)&& bc>=0.9 && bc<=1.1)
        {
            return true;
        }
        return false;
    }
    
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
