import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
	
public class MainSoundEffect {
 
private Clip clip;
		   
		public MainSoundEffect() throws Exception {
			   openAudio();
		    	        
		   
		}
		public void openAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			   URL url = new URL(
		   	            "https://wavlist.com/wav/car-burnout1.wav");
		   	         clip = AudioSystem.getClip();
		   	        // getAudioInputStream() also accepts a File or InputStream
		   	        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
		   	        
		   	        clip.open(ais);
		   	        clip.loop(clip.LOOP_CONTINUOUSLY);
		   	        SwingUtilities.invokeLater(new Runnable() {
		   	            public void run() {
		   	               
		   	            
		   	            	}
		   	            
		   	        });
		   }
		   public void closeAudio() {
			   clip.close();
		   }
		   public void startAudio()
		   {
		 	  clip.setMicrosecondPosition(0);
		 	  clip.start();
		   }
		   public void stopAudio()
		   {
		 	  clip.stop();
		   }
		   public boolean audioRunning()
		   {
		 	  return clip.isRunning();
		   }

   
}
