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
 * @author Kevin
 * @version 2.1.3.0
 * @since 2021-03-05 
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
    
    /**
     * Method sends the image of red car
     * @return BufferedImage
     */
	public BufferedImage getRedCar()
    {
    	return redCar;
    }
    /**
     * Method sends the image of green car
     * @return BufferedImage
     */
    public BufferedImage getGreenCar()
    {
    	return greenCar;
    }
    /**
     * Method sends the image of blue car
     * @return BufferedImage
     */
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
    /**
     * Method sends the rectangle of highscore button
     * @return rectangle
     */
    public Rectangle getHighscoreBtn() {
    	return highscoreStringBtn;
    }
    /**
     * Method sends the rectangle of play again button
     * @return rectangle
     */
    public Rectangle getPlayAgainBtn() {
    	return playBtn;
    }
    /**
     * Method sends the rectangle of end game button
     * @return rectangle
     */
    public Rectangle getEndGameBtn() {
    	return quitBtn;
    }
    /**
     * Method sends the rectangle of play button
     * @return rectangle
     */
    public Rectangle getPlayBtn()
    {
        return playBtn;
    }
    /**
     * Method sends the rectangle of config button
     * @return rectangle
     */
    public Rectangle getConfigBtn()
    {
        return configBtn;
    }
    /**
     * Method sends the rectangle of map button
     * @return rectangle
     */
    public Rectangle getMapBtn()
    {
    	return mapBtn;
    }
    /**
     * Method sends the rectangle of quit button
     * @return rectangle
     */
    public Rectangle getQuitBtn()
    {
        return quitBtn;
    }
    /**
     * Method sends the rectangle of return button
     * @return rectangle
     */
    public Rectangle getReturnBtn()
    {
        return returnBtn;
    }
    /**
     * Method sends the rectangle of config return button
     * @return rectangle
     */
    public Rectangle getConfigReturnBtn()
    {
        return configReturnBtn;
    }
    /**
     * Method sends the rectangle of red car button
     * @return rectangle
     */
    public Rectangle getredCarBtn()
    {
        return redCarBtn;
    }
    /**
     * Method sends the rectangle of blue car button
     * @return rectangle
     */
    public Rectangle getblueCarBtn()
    {
        return blueCarBtn;
    }
    /**
     * Method sends the rectangle of green car button
     * @return rectangle
     */
    public Rectangle getgreenCarBtn()
    {
        return greenCarBtn;
    }
    /**
     * Method sends the gif of menu
     * @return ImageIcon
     */
    public ImageIcon getImg()
    {
    	return img;
    }
    /**
     * Method sends the gif of finished game menu
     * @return ImageIcon
     */
    public ImageIcon getFinishedImg()
    {
    	return finishedimg;
    }
    /**
     * Method sends the color of all button
     * @return Color
     */
    public Color getBtnClr()
    {
    	return btnclr;
    }
    /**
     * Method sends the color of the outline on all button
     * @return Color
     */
    public Color getBtnOutClr()
    {
    	return btnoutclr;
    }
    /**
     * Method sends the color of the title
     * @return Color
     */
    public Color getTitleClr()
    {
    	return titleclr;
    }
}

