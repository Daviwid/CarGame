package GameFiles;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
	
public class MainSoundEffect {
 
private Clip clip;
		//constructor  
		public MainSoundEffect() throws Exception {
			   openAudio();
		   
		}
		/**
		 * method for opening audio file and playing the audio
		 * @throws LineUnavailableException line cannot be opened because it is unavailable 
		 * @throws IOException Signals that an I/O exception of some sort has occurred.
		 * @throws UnsupportedAudioFileException operation failed because a file did not contain valid data of a recognized file type and format
		 */
		public void openAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			  
		   	         clip = AudioSystem.getClip();  //Obtains a clip that can be used for playing back an audio file or an audio stream
		   	        
		   	        //AudioInputStream ais = AudioSystem.getAudioInputStream( url );
		   	      AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource("/Resources/gammalbilloopad4ggr.wav") ); //Obtains an audio input stream from the URL
		   	         
		   	        clip.open(ais);  //Opens the clip 
				
					      FloatControl gainControl = 
					 (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);     
			   	gainControl.setValue(-10.0f); // Reduce volume by 20 decibels.

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
