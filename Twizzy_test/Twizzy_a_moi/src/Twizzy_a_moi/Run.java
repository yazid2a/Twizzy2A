package Twizzy_a_moi;

import org.opencv.core.*;

public class Run {
	
	private Init imageRef; //image reference
	private Init imageTraite; //image qu'on veut comparer
	private String stringImage; //image qu'on veut comparer
	
	public Run(String stringImage) {
		this.stringImage = stringImage;
	}
	
	public void setstringImage(String stringImage) {
		this.stringImage = stringImage;
	}
	
	public String getstringImage() {
		return stringImage;
	}

	public void run(){
		imageTraite = new Init(stringImage);
		imageTraite.getimageseuildone();
	}
}
