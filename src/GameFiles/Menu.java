package GameFiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Oversees the components of the menu UI that is going to be drawn.
 * 
 */
public class Menu
{
    private Rectangle playBtn, quitBtn, configBtn,
    redCarBtn, blueCarBtn, greenCarBtn, returnBtn,mapBtn, configReturnBtn, highscoreStringBtn;
    private ImageIcon img, finishedimg;
    private BufferedImage redCar,greenCar,blueCar;
    private Color btnclr, btnoutclr, titleclr;
    
    /**
     * Contructor for Menu, creates rectangles that will be drawn out and function as buttons.
     * Also loads images and gifs relevant to the menu from the resource folder.
     * 
     * @param borderX		width size of the game window
     * @param borderY		height size of the game window
     */
    public Menu(int borderX, int borderY)
    {  	
    	playBtn = new Rectangle(30,borderY/2-150,300,100);
        configBtn= new Rectangle(30,borderY/2,300,100);
        quitBtn = new Rectangle(30,borderY/2 + 150,300,100);
        highscoreStringBtn= new Rectangle(30, borderY/2 + 300, 300, 100);  
        returnBtn = new Rectangle(borderX/2-150,borderY-200,300,100);

        redCarBtn = new Rectangle(borderX/2-550,borderY/2-120,150,80);
        greenCarBtn = new Rectangle(borderX/2-160,borderY/2-120,180,80);
        blueCarBtn= new Rectangle(borderX/2+200,borderY/2-120,150,80);
        configReturnBtn= new Rectangle(borderX/2-150,borderY/2 + 260,250,100);
    	
        btnclr = new Color(1F, 0F, 0F, .5F);
        mapBtn = new Rectangle(borderX/2-150,borderY/2-180,300,300);
        btnoutclr = Color.white;
        titleclr = Color.white;
		
		try {
            img = new ImageIcon(getClass().getResource("/Resources/back.gif"));				//Gif background
		    finishedimg= new ImageIcon(getClass().getResource("/Resources/finito.gif"));	//Gif when finished
			redCar= ImageIO.read(getClass().getResource("/Resources/red.png"));		
			greenCar=ImageIO.read(getClass().getResource("/Resources/green.png"));	
			blueCar=ImageIO.read(getClass().getResource("/Resources/blue.png"));	
		}
		catch(IOException e) {}
		//Scale the gifs
		img.setImage(img.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));		
		finishedimg.setImage(finishedimg.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));	
    }
    
    //getters
	public BufferedImage getRedCar()
    {
    	return redCar;
    }
    public BufferedImage getGreenCar()
    {
    	return greenCar;
    }
    public BufferedImage getBlueCar()
    {
    	return blueCar;
    }
    /**
     * Method to resize images in car config by scaling it to the given parameters.
     * The method makes use of the Image class's getScaledInstance method and returns a new BufferedImage,
     * that has been scaled and then redrawn from the given BufferedImage.
     * 
     * 
     * @param bi		image to be resized
     * @param x			desired width
     * @param y			desired height
     * @return			returns the resized image
     */
    public BufferedImage resize(BufferedImage bi,int x,int y)
    {
    	Image resultingImage = bi.getScaledInstance(x, y, Image.SCALE_DEFAULT);				//scale to desired size
		BufferedImage outputImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_ARGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
    }
    public Rectangle getHighscoreBtn() {
    	return highscoreStringBtn;
    }
    public Rectangle getPlayAgainBtn() {
    	return playBtn;
    }
    public Rectangle getEndGameBtn() {
    	return quitBtn;
    }
    public Rectangle getPlayBtn()
    {
        return playBtn;
    }
    public Rectangle getConfigBtn()
    {
        return configBtn;
    }
    public Rectangle getMapBtn()
    {
    	return mapBtn;
    }
    public Rectangle getQuitBtn()
    {
        return quitBtn;
    }

    public Rectangle getReturnBtn()
    {
        return returnBtn;
    }
    public Rectangle getConfigReturnBtn()
    {
        return configReturnBtn;
    }
    public Rectangle getredCarBtn()
    {
        return redCarBtn;
    }
    public Rectangle getblueCarBtn()
    {
        return blueCarBtn;
    }
    public Rectangle getgreenCarBtn()
    {
        return greenCarBtn;
    }

    public ImageIcon getImg()
    {
    	return img;
    }
    public ImageIcon getFinishedImg()
    {
    	return finishedimg;
    }
    public Color getBtnClr()
    {
    	return btnclr;
    }
    public Color getBtnOutClr()
    {
    	return btnoutclr;
    }
    public Color getTitleClr()
    {
    	return titleclr;
    }
}

