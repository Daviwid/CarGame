import javax.sound.sampled.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.swing.*;

public class SoundEffectCarCollision{
	private Clip clip;
	   public SoundEffectCarCollision() throws Exception {
		   openAudio();
	    	        
	   
	   }
	   public void openAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		   URL url = new URL(
	   	            "https://wavlist.com/wav/car-crash2.wav");
	   	         clip = AudioSystem.getClip();
	   	        // getAudioInputStream() also accepts a File or InputStream
	   	        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
	   	        
	   	        clip.open(ais);
	   	        clip.loop(1);
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