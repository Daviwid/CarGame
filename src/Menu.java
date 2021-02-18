import java.awt.Rectangle;

public class Menu
{
    private Rectangle playBtn, quitBtn;

    public Menu(int borderX, int borderY)
    {
        playBtn = new Rectangle(30,borderY/2-150,300,100);
        quitBtn = new Rectangle(30,borderY/2,300,100);
    }
    
    public Rectangle getPlayBtn()
    {
        return playBtn;
    }
    public Rectangle getQuitBtn()
    {
        return quitBtn;
    }
}
