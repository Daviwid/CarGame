package GameFiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Menu
{
    private Rectangle playBtn, quitBtn, configBtn,
    redCarBtn, blueCarBtn, greenCarBtn, returnBtn,mapBtn, configReturnBtn, highscoreStringBtn;
    private ImageIcon img, finishedimg;
    private BufferedImage redCar,greenCar,blueCar;
    private Color btnclr, btnoutclr, titleclr;
    
    public Menu(int borderX, int borderY)
    { 
    	
    	playBtn = new Rectangle(30,borderY/2-150,300,100);
        configBtn= new Rectangle(30,borderY/2,300,100);
        quitBtn = new Rectangle(30,borderY/2 + 150,300,100);
        highscoreStringBtn= new Rectangle(30, borderY/2 + 300, 300, 100);  //kolla om position funkar sen
        
        
        returnBtn = new Rectangle(borderX/2-150,borderY-200,300,100);
        
        redCarBtn = new Rectangle(borderX/2-550,borderY/2-220,150,80);
        greenCarBtn = new Rectangle(borderX/2-100,borderY/2-220,180,80);
        blueCarBtn= new Rectangle(borderX/2+200,borderY/2-220,150,80);
       
        configReturnBtn= new Rectangle(borderX/2-150,borderY/2 + 260,250,100);
    	
        btnclr = new Color(1F, 0F, 0F, .5F);
        mapBtn = new Rectangle(borderX/2-150,borderY/2-180,300,300);
        btnoutclr = Color.white;
        titleclr = Color.white;
		
		try {
            img = new ImageIcon(getClass().getResource("/Resources/back.gif"));
		    finishedimg= new ImageIcon(getClass().getResource("/Resources/finito.gif"));
			redCar= ImageIO.read(getClass().getResource("/Resources/red.png"));		
			greenCar=ImageIO.read(getClass().getResource("/Resources/green.png"));	
			blueCar=ImageIO.read(getClass().getResource("/Resources/blue.png"));	
		}
		catch(IOException e) {}
		img.setImage(img.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));	
		finishedimg.setImage(finishedimg.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));	
    }
    
    

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

