import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Menu
{
    private Rectangle playBtn, quitBtn, configBtn,
    redCarBtn, blueCarBtn, greenCarBtn, returnBtn,mapBtn;
    private ImageIcon img;
    private Color btnclr, btnoutclr, titleclr;
    
    public Menu(int borderX, int borderY)
    {
        playBtn = new Rectangle(30,borderY/2-150,300,100);
        configBtn= new Rectangle(30,borderY/2,300,100);
        quitBtn = new Rectangle(30,borderY/2 + 150,300,100);
        redCarBtn = new Rectangle(30,borderY/2-150,300,100);
        blueCarBtn= new Rectangle(30,borderY/2,300,100);
        greenCarBtn = new Rectangle(30,borderY/2 + 150,300,100);
        returnBtn = new Rectangle(600,borderY/2-150,300,100);
        
        btnclr = new Color(1F, 0F, 0F, .5F);
        mapBtn = new Rectangle(borderX/2-350,borderY/2-200,300,300);
        btnoutclr = Color.white;
        titleclr = Color.white;
		img = new ImageIcon("back.gif");
		img.setImage(img.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));	
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

