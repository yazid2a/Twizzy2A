package projet_twizzy;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.*;

public class MyThread extends Thread {
	private String video;
	private static int compte = 0;
	
	static {
		//System.loadLibrary("opencv_ffmpeg2413");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
	}
	
	public MyThread(String video) {
		this.video = video;
		//return;
	}
	public void run() {
		VideoImageFrame2.LectureVideo2(this.video);
	}
 
	
}
