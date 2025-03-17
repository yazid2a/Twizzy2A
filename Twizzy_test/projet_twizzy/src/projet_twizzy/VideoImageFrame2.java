package projet_twizzy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

class VideoImageFrame2 extends JFrame {
	
public static void main(String[] args) {	
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    EventQueue.invokeLater(() -> {
            VideoImageFrame2 frame = new VideoImageFrame2(); 
            frame.setVisible(true);     
        });
	   			
			}

    private JFXPanel jfxPanel;
    
    private static GridPane gridPane;
    private MediaPlayer mediaPlayer;
    private static ImageView imageView;
    private Scene scene;
    public String videoPath;
    public static String imagePath;
    public MyThread myThread;
    private static JPanel panelPrinc;
    private static JPanel imageDetec;
    private JPanel controlPanel;
   

    public VideoImageFrame2() {

        setTitle("Twuzzy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,1,1300,700);
        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            initJavaFX();
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(controlPanel);
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.blue);
        
        JPanel panel = new JPanel();
        panel.setBounds(10,10,1260,639);
        panel.setLayout(null);
        
        
        panelPrinc = new JPanel();
        imageDetec = new JPanel();
        imageDetec.setBackground(Color.lightGray);
        imageDetec.setBounds(10, 240, 254, 254);
        panelPrinc.setBackground(Color.lightGray);
        panelPrinc.setBounds(274, 46, 880, 582);
        JButton loadVideoButton = new JButton("Charger vidéo");
        loadVideoButton.addActionListener(e -> loadVideo());
        loadVideoButton.setBounds(10,70,200,20);
        JButton playPauseButton = new JButton("Lancer/Pause");
        playPauseButton.addActionListener(e -> togglePlayPause());
        playPauseButton.setBounds(10,40,200,20);
        panel.add(panelPrinc);
        panel.add(imageDetec);
        
        
        controlPanel.add(loadVideoButton);
        controlPanel.add(playPauseButton);
        controlPanel.add(panel);
       
        
    }
    private void initJavaFX() {
       
        gridPane = new GridPane();
       
        scene = new Scene(gridPane);

        jfxPanel.setScene(scene);
    }
    
    private static void chargerImage(String chemin) {
    	 Platform.runLater(() -> {  
    		 System.out.println("ok");
    	Image image = new Image(new File(chemin).toURI().toString());
    	  Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
          double screenWidth = screenBounds.getWidth();
          double screenHeight = screenBounds.getHeight();
        
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(screenWidth/2);
        imageView.setFitWidth(screenHeight);

  
        gridPane.add(imageView, 1, 0);
    	 });}
    
    private void loadVideo() {
       
        //videoPath="https://arche.univ-lorraine.fr/pluginfile.php/1004382/mod_resource/content/0/video1.mp4";
        //videoPath ="video.mp4";
        videoPath = "C:/Users/idris/Downloads/videoFinale.mp4";
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        if (videoPath.equalsIgnoreCase("")) {
        	 JFileChooser fileChooser = new JFileChooser();
             fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers vidéo", "mp4", "avi", "flv", "mkv", "mov"));
             int returnValue = fileChooser.showOpenDialog(null);
        	 if (returnValue == JFileChooser.APPROVE_OPTION) {
                 File selectedFile = fileChooser.getSelectedFile();
                 Platform.runLater(() -> {
                     if (mediaPlayer != null) {
                         mediaPlayer.stop();
                     }
                     Media media = new Media(selectedFile.toURI().toString());
                  
                     
                     mediaPlayer = new MediaPlayer(media);
                     MediaView mediaView = new MediaView(mediaPlayer);
                     mediaView.setPreserveRatio(true);
                     mediaView.setFitHeight(screenWidth/2);
                     mediaView.setFitWidth(screenHeight);
                   
                     gridPane.add(mediaView, 0, 0);
                 });
                 MyThread myThread = new MyThread(videoPath);
                 myThread.run();
             }
        }else {
                 Platform.runLater(() -> {
                     if (mediaPlayer != null) {
                         mediaPlayer.stop();
                     }
                     Media media = new Media(new File(videoPath).toURI().toString());
                     //Media media = new Media(videoPath);
                     
                     System.out.println(new File(videoPath).toURI().toString());
                    
                     mediaPlayer = new MediaPlayer(media);
                     MediaView mediaView = new MediaView(mediaPlayer);
                     mediaView.setPreserveRatio(true);
                     mediaView.setFitHeight(screenWidth/2);
                     mediaView.setFitWidth(screenHeight);
                   
                     gridPane.add(mediaView, 1, 0);
                 });
                
            
        }
       
    }
    private void togglePlayPause() {
        if (mediaPlayer != null) {
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.READY) {
                mediaPlayer.play();
                MyThread myThread = new MyThread(videoPath);
                //MyThread myThread = new MyThread(new File(videoPath).toURI().toString());
                myThread.start();
                //myThread.run();
            }
        }
        
    }
    public static void LectureVideo(String nomVideo) {
    	System.load("C:\\Users\\idris\\Downloads\\opencv\\build\\x64\\vc14\\bin\\opencv_ffmpeg2413_64.dll");
		VideoCapture camera = new VideoCapture(nomVideo);
		
		camera.open(nomVideo);
		String im ="";
    	Mat frame = new Mat();
    	if (!camera.isOpened()) {
    		System.out.println("camera is closed my thread");
    	}else {
    		int n = 0;
    		while (camera.read(frame)) {
        		System.out.println("Frame Obtained");
        		//Imgproc.resize(frame, frame, new Size(800, 600));
        		System.out.println(frame.size());
        		//Highgui.imwrite("camera"+Integer.toString(n)+".png", frame);
        		Run nouvelleimage=new Run(frame);
        		nouvelleimage.go();
        		System.out.println(nouvelleimage.getPanneaudetecter());
        			im = nouvelleimage.getPanneaudetecter();
		
    	}

    	}
    	
    	}
    public static  void LectureVideo2(String nomVideo) {
    	System.load("C:\\Users\\idris\\Downloads\\opencv\\build\\x64\\vc14\\bin\\opencv_ffmpeg2413_64.dll");
		VideoCapture camera = new VideoCapture(nomVideo);
		
		camera.open(nomVideo);
    	Mat frame = new Mat();
    	
    	
    	if (!camera.isOpened()) {
    		System.out.println("camera is closed");
    	}else {
    		while(camera.read(frame)) {
    			String fileImg = "";
    			panelPrinc.removeAll();
    			panelPrinc.add(new JLabel(new ImageIcon(mat2bufferedImage(frame))));
    			panelPrinc.repaint();
    			panelPrinc.validate();
    			
    			
    			System.out.println(frame);
    			
    	        Run nouvelleImage = new Run(frame);
    	        nouvelleImage.go();
    			String detect = nouvelleImage.getPanneaudetecter();
    			
    			
    			switch(detect) {
    			case "panneau30":
    				fileImg = "ref30.png";
    				break;
    			case "panneau50":
    				fileImg = "ref50.png";
    				break;
    			case "panneau70":
    				fileImg = "ref70.png";
    				break;
    			case "panneau90":
    				fileImg = "ref90.png";
    				break;
    			case "panneau110":
    				fileImg = "ref110.png";
    				break;
    			case "rien":
    				fileImg = "vide.png";
    				break;
    			default:
    				fileImg = "vide.jpg";
    				break;
    			
    			}
    			imageDetec.removeAll();
				imageDetec.repaint();
				imageDetec.add(new JLabel(new ImageIcon(fileImg)));
    			imageDetec.validate();
    	}
    	}
    	}
	private static BufferedImage mat2bufferedImage(Mat mat) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((java.awt.image.DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
	}
	
	 

}
