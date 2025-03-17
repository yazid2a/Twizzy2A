package Twizzy_a_moi;

import javax.imageio.ImageIO;
import javax.media.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.media.control.FrameGrabbingControl;
import javax.media.control.FramePositioningControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
 

 
public class Videos implements  ControllerListener{
 
  javax.media.Buffer buf = null;
  Image img = null;
  BufferToImage btoi = null; 
  private Player player = null;
  
  
  private BufferedImage toBufferedImage(Image src) {
          int w = src.getWidth(null);
          int h = src.getHeight(null);
          int type = BufferedImage.TYPE_INT_RGB;  // other options
          BufferedImage dest = new BufferedImage(w, h, type);
          Graphics2D g2 = dest.createGraphics();
          g2.drawImage(src, 0, 0, null);
          g2.dispose();
          return dest;
      }

  
  //Constructeur
  public Videos(String a){
	  super();
	  
	  try{
		  /*La création du lecteur et le chargement du fichier à lire.*/
		  player = Manager.createPlayer( new MediaLocator(a) );
		  
		  /*L'ajout d'un écouteur sur le player pour pouvoir gérer les évenements
     		de ce dernier*/
		  player.addControllerListener( this ) ;
		  
		  /*Cette fonction permet au player d'acquérir toutes les informations et
     		toutes les ressources qui lui sont nécessaires sur le média*/
		  System.out.println("Acquésition des ressources et des informations média en cours.");
		  System.out.println("Patientez S'il vous plait."); 
		  
		  player.realize();
		 
	  } catch (Exception e) {
		  /*Traitement des erreurs qui peuvent survenir lors de la création du lecteur.*/
		  System.out.println("Error creating player");
	  }
  }
 
/*On implimente la fonction controllerUpdate de la classe ControllerListener qui nous permet
   de gérer les évenemnts du player.*/
  public void controllerUpdate( ControllerEvent ce ){
	  /*on passe au traitement.*/
	  if ( ce instanceof RealizeCompleteEvent ){
		  int NomImage = 1;
		  int dest;
		  FramePositioningControl fpc;
		  FrameGrabbingControl fgc;
		  Time duration = player.getDuration();
		  int totalFrames = FramePositioningControl.FRAME_UNKNOWN;
		  fpc = (FramePositioningControl) player.getControl("javax.media.control.FramePositioningControl");
		  if (fpc == null){
			  System.out.println("Le média ne supporte pas les FramePositioningControl.");
		  }
		  else{
			  /*
        		On calcul le nombre d'images dans le média.
			   */
			  totalFrames = fpc.mapTimeToFrame(duration);
			  System.out.println("Nombre total des images dans le média : " + totalFrames);
 
			  /*
      		boucle de parcours des images de le média.
			   */
			  while (NomImage <= totalFrames){
				  /*
		         La fonction skip nous permet d'avancer dans les images par exemple si 
		         le player pointe sur l'image 45 de le média et on fait dest = fpc.skip(10);
		         le player pointera sur l'image 55.
		         Pour la fonction seek on peut accéder directement à l'image qu'on désire
		         par exemple quelque soit l'image sur laquelle pointe le player et on fait
		         dest = fpc.seek(10) le player pointera sur l'image 10.
		         */
				  dest = fpc.skip(1);//avec dest = fpc.seek(NomImage); ça marche aussi.
		        /*
		        On capture l'image pointé par le player.     
		         */
				  fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
		        /*
		        On met l'image dans un buffer.
		         */
				  buf = fgc.grabFrame();
		        /*
		         On convertit l'image dans le buffer.
		         */
				  btoi = new BufferToImage( (VideoFormat) buf.getFormat());
				  img = btoi.createImage(buf);
				  try {
					ImageIO.write(toBufferedImage(img), "png", new File("C:\\Users\\valen\\Documents\\cours\\2A_ENSEM\\S8\\Twizzy\\Twizzy_test\\image_ panneaux\\img_registerd"+NomImage+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				  	System.out.println("Image " + NomImage + "extraite");
				  	NomImage = NomImage + 1;
			  }
		  }
	  }
  }
}