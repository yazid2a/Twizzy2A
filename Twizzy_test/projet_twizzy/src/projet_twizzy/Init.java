package projet_twizzy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.imgproc.*;

public class Init {
	private  Mat imageread;
	private  Mat seuilplusieurdone;
	private Mat hsvimagemade;
	private Vector<Mat> channelsmade;
	private String fichier;
	private Random rand;
	private Mat contours;
	private  List<MatOfPoint> detectercontourlist;
	
	
	
	public Init(String fichier) {
		this.fichier=fichier;
		setimageread(fichier);
		sethsvimagemade(imageread);
		setchannels(imageread);
		setseuildone(hsvimagemade);
		this.rand=new Random();
		setContours(seuilplusieurdone, rand);
		setDetectercontourlist (seuilplusieurdone,rand);
		
	}
	@SuppressWarnings("exports")
	public Init(Mat frame) {
		this.imageread=frame;
	/*setimageread(fichier);*/
		sethsvimagemade(imageread);
		setchannels(imageread);
		setseuildone(hsvimagemade);
		this.rand=new Random();
		setContours(seuilplusieurdone, rand);
		setDetectercontourlist (seuilplusieurdone,rand);
		
	}
	
	
	
	public Vector<Mat> channels(Mat m) {
			Vector<Mat> channel = new Vector<>();
			Core.split(m,  channel);
			return channel;
		}
	
		
	 private Mat ReadImage(String fichier) {
			File f = new File(fichier);
			return Highgui.imread(f.getAbsolutePath());
			
		}	
	
	public  Mat seuilplusieur(Mat hsv_image) {
			Mat threshold_img=new Mat();
			Mat threshold_img1=new Mat();
			Mat threshold_img2=new Mat();
			Core.inRange(hsv_image, new Scalar(0,100,100), new Scalar(10,255,255), threshold_img1);
			Core.inRange(hsv_image, new Scalar(160,100,100), new Scalar(179,255,255), threshold_img2);
			Core.bitwise_or(threshold_img1, threshold_img2, threshold_img);
			Imgproc.GaussianBlur(threshold_img, threshold_img, new Size(9,9), 2,2);
			return threshold_img;
		}
	public Mat hsvimagemaker(Mat m) {
		Mat hsv_image= Mat.zeros(m.size(),m.type());
		Imgproc.cvtColor(m,hsv_image, Imgproc.COLOR_BGR2HSV);
		return hsv_image;
	}
	public  Mat detectercontours(Mat seuil,Random rand) {
		
		int thresh=100;
		Mat canny_output=new Mat();
		List<MatOfPoint>cont0ur= new ArrayList<>();
		MatOfInt4 hierarchy= new MatOfInt4();
		Imgproc.Canny(seuil,canny_output,thresh,thresh*(double)2);
		Imgproc.findContours(canny_output, cont0ur, hierarchy,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
		Mat drawing=Mat.zeros(canny_output.size(),CvType.CV_8UC3);
		for(int i=0;i<cont0ur.size();i++) {
			Scalar color= new Scalar(rand.nextInt(255 -0 +1),rand.nextInt(255 -0 +1),rand.nextInt(255 -0 +1));
			Imgproc.drawContours(drawing, cont0ur,i, color,1,8,hierarchy,0,new Point());
		}
		return  drawing;
	}
	public  List<MatOfPoint> detectercontourslist(Mat seuil,Random rand) {
		
		int thresh=100;
		Mat canny_output=new Mat();
		List<MatOfPoint>cont0ur= new ArrayList<>();
		MatOfInt4 hierarchy= new MatOfInt4();
		Imgproc.Canny(seuil,canny_output,thresh,thresh*(double)2);
		Imgproc.findContours(canny_output, cont0ur, hierarchy,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
		Mat drawing=Mat.zeros(canny_output.size(),CvType.CV_8UC3);
		for(int i=0;i<cont0ur.size();i++) {
			Scalar color= new Scalar(rand.nextInt(255 -0 +1),rand.nextInt(255 -0 +1),rand.nextInt(255 -0 +1));
			Imgproc.drawContours(drawing, cont0ur,i, color,1,8,hierarchy,0,new Point());
		}
		return  cont0ur;
	}
	
	public void setimageread(String fichier) {
		this.imageread= ReadImage(fichier);
	}
	public void setchannels(Mat m){
		  this.channelsmade=channels(m);
	 }
public void setFichier(String fichier) {
		this.fichier = fichier;
	}
public void sethsvimagemade(Mat m) {
	this.hsvimagemade=hsvimagemaker(m);
}
public void setseuildone(Mat hsv_image) {
	this.seuilplusieurdone=seuilplusieur(hsv_image);
}
	
		
	public  Mat getimageread() {
			return imageread;
			
		}
	public Mat getimageseuildone() {
			return seuilplusieurdone;
		}
	public Mat gethsv() {
			return hsvimagemade;
		}
	public Vector<Mat> getchannels(){
			return channelsmade;
		}
	
	public String getFichier() {
			return fichier;
		}



	public Random getRand() {
		return rand;
	}



	public void setRand() {
		this.rand =new Random();
	}
	
	public void changeimageread(Mat m) {
		this.imageread=m;
	}



	public Mat getContours() {
		return contours;
	}



	public void setContours(Mat seuil,Random rand) {
		this.contours =detectercontours(seuil,rand) ;
	}



	public List<MatOfPoint> getDetectercontourlist() {
		return detectercontourlist;
	}



	public void setDetectercontourlist(Mat seuil,Random rand) {
		this.detectercontourlist = detectercontourslist(seuil,rand);
	}
	
}