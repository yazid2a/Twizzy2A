package projet_twizzy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public class Run {
		private List<Init> imageRef; //image reference
		private Init imageTraite; //image qu'on veut comparer
		private String stringImage; //image qu'on veut comparer
		private Mat mat;
		private String panneaudetecter;
		
		
		public Run(String stringImage) {
			this.stringImage = stringImage;
			setImageRef();
			setimageTraite(stringImage);
			this.setPanneaudetecter("aucun paneau detecter");
		}
		public Run(Mat mat) {
			setImageRef();
			this.imageTraite = new Init(mat);
			this.setPanneaudetecter("aucun paneau detecter");
		}
		
		public void go(){
			Mat dect= DetectionImage.detection_ball(imageTraite);
			System.out.println(dect);
			System.out.println(dect.channels());
			imageTraite.changeimageread(dect);
			List<Double> listedesref=new ArrayList<>();
			int indicemin=0;		
			for (int i=0;i<imageRef.size();i++) {
				//System.out.println(i);
				double a=DetectionImage.misealecchelle5(imageRef.get(i).getFichier(),imageTraite);  
				    listedesref.add(a);
				
			}
			
			for(int i=0;i<listedesref.size();i++) {
				
				if(listedesref.get(i)<=listedesref.get(indicemin)) {
					indicemin=i;	
				}}
			switch(indicemin) {
			case 0:
				this.panneaudetecter="panneau30";
				break;
			
			case 1:
				this.panneaudetecter="panneau50";
				break;
			case 2:
			
				this.panneaudetecter="panneau70";
				break;
			case 3:
				this.panneaudetecter="panneau90";
				break;
			case 4:
				this.panneaudetecter="panneau110";
				break;
			case 5:
				this.panneaudetecter="rien";
				break;
			default:
				this.panneaudetecter="pas de panneau";
				break;
			}
			}
			
	
		
		
		public void setstringImage(String stringImage) {
			this.stringImage = stringImage;
		}
		
		public String getstringImage() {
			return stringImage;
		}
		

	

		public List<Init> getImageRef() {
			return imageRef;
		}

		public void setImageRef() {
			 List<Init> imageRef=new ArrayList<>();
					Init a1=new Init("ref30.2.png");
					Init a2=new Init("ref50.2.png");
					Init a3=new Init("ref70.2.png");
					Init a4=new Init("ref90.2.png");
					Init a5=new Init("ref110.2.png");
					Init a6=new Init("refdouble.2.png");
					imageRef.add(a1);
					imageRef.add(a2);
					imageRef.add(a3);
					imageRef.add(a4);
					imageRef.add(a5);
					imageRef.add(a6);
			this.imageRef = imageRef;
		}
	
public void setimageTraite(String stringimage) {
	this.imageTraite = new Init(stringimage);
}
public void setimageTraite2(Mat mat) {
	this.imageTraite= new Init(mat); 
}
public String getPanneaudetecter() {
	return panneaudetecter;
}
public void setPanneaudetecter(String panneaudetecter) {
	this.panneaudetecter = panneaudetecter;
}

}