package Twizzy_a_moi;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyThread extends Thread {
	private String video;
	private JTextArea panneau;
	private JPanel panel_1;
	
	static {
		System.loadLibrary("opencv_ffmpeg2413");
	}
	
	public MyThread(String video, JTextArea panneau, JPanel panel_1) {
		this.video = video;
		this.panneau = panneau;
		this.panel_1 = panel_1;
		return;
	}
	
	public void run() {
		//inserer ligne qui appelle run
	}
}
