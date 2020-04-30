package Frames;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroudMusic {
	
	void music(String songLocation) {
			try {
				File songPath = new File(songLocation);
				
				if(songPath.exists()) {
					AudioInputStream backgroundSong = AudioSystem.getAudioInputStream(songPath);
					Clip clip = AudioSystem.getClip();
					clip.open(backgroundSong);
					clip.start();
					clip.loop(clip.LOOP_CONTINUOUSLY);
				}
				
				else {
					System.out.printf("Invalid document path");
				}
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
			
	}
	
	
	
	
}
