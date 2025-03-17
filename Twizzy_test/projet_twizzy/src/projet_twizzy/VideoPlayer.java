package projet_twizzy;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class VideoPlayer extends Application {
	private static JPanel panel2 ;
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		for(int i=2;i<11;i++) {
		Run nouvelleimage=new Run("p"+i+".png");
		nouvelleimage.go();
		System.out.println(nouvelleimage.getPanneaudetecter());
		}
		//launch(args);
		
		/*DetectionImage.ImShow("pascontour",image.getimageread());
		DetectionImage.ImShow("contour",image.getContours());*/
			}

    @Override
    public void start(Stage primaryStage) {
        String path = "https://arche.univ-lorraine.fr/pluginfile.php/1004382/mod_resource/content/0/video1.mp4";
        //Media media = new Media(new File(path).toURI().toString());
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Pane pane = new Pane();
        pane.getChildren().add(mediaView);
        Scene scene = new Scene(pane, 640, 480);
        //mediaView.setFitWidth(scene.getWidth());
        mediaView.setFitHeight(scene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        mediaPlayer.play();
    }
    
    public static void LectureVideo(String nomVideo) {
    	Mat frame = new Mat();
    	VideoCapture camera = new VideoCapture(nomVideo);

    	while (camera.read(frame)) {
    		Run nouvelleimage=new Run(frame);
    		nouvelleimage.go();		 		
    		}
    	}
    
}