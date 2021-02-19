public class Point2D {
	private double x,  y;  
    public  Point2D(double  x,  double  y)
    {   
            this.x  =   x;  
            this.y  =   y;  
    }   
    public  int getIntX()   {   return  (int)x; }   
    public  int getIntY()   {   return  (int)y; }   
    public  Point2D rotate(double   angle)  
    {   
        //angle   =   Math.toRadians(angle);  
        return  new Point2D(Rotate2D.getX(x,y,angle),   
    Rotate2D.getY(x,y,angle)); 
}
	
}