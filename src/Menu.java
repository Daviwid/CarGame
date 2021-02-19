import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Menu
{
    private Rectangle playBtn, quitBtn;
    private ImageIcon img;
    private Color btnclr, btnoutclr, titleclr;
    
    public Menu(int borderX, int borderY)
    {
        playBtn = new Rectangle(30,borderY/2-150,300,100);
        quitBtn = new Rectangle(30,borderY/2,300,100);
        btnclr = Color.decode("#FA11C6");
        btnoutclr = Color.white;
        titleclr = Color.white;
		img = new ImageIcon("back.gif");
		img.setImage(img.getImage().getScaledInstance(borderX, borderY, Image.SCALE_DEFAULT));	
    }
    
    public Rectangle getPlayBtn()
    {
        return playBtn;
    }
    public Rectangle getQuitBtn()
    {
        return quitBtn;
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
