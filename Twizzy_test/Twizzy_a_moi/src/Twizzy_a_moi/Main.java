package Twizzy_a_moi;

import org.opencv.core.*;

public class Main{
	
	public static void main(String[] args){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Init image=new Init("circles_rectangles.jpg");
		DetectionImage.ImShow("hsv",image.gethsv());
		DetectionImage.ImShow("pascontour",image.getimageread());
		DetectionImage.ImShow("contour",image.getContours());
		
	}

}