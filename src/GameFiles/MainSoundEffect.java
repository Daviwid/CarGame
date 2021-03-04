package GameFiles;

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
			   /*URL url = new URL(
		   	            "https://wavlist.com/wav/car-burnout1.wav");*/

		   	         clip = AudioSystem.getClip();  //Obtains a clip that can be used for playing back an audio file or an audio stream
		   	        
		   	        //AudioInputStream ais = AudioSystem.getAudioInputStream( url );
		   	      AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource("/Resources/gammalbilloopad4ggr.wav") ); //Obtains an audio input stream from the URL
		   	         
		   	        clip.open(ais);  //Opens the clip 
				
					   /*   FloatControl gainControl = 
					 (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);     
			   	gainControl.setValue(-20.0f); // Reduce volume by 20 decibels.*/

		   	        clip.loop(clip.LOOP_CONTINUOUSLY);
		   	        SwingUtilities.invokeLater(new Runnable() {  //creates new runnable thread for playing the audio
		   	            public void run() {  //run thread
		   	               
		   	            
		   	            	}
		   	            
		   	        });
		   }
		   public void closeAudio() {  //closes the audio
			   clip.close();
		   }
		   public void startAudio()
		   {
		 	  clip.setMicrosecondPosition(0);
		 	  clip.start();
		   }
		   public void stopAudio()  //stop the audio
		   {
		 	  clip.stop();
		   }
		   

   
}
