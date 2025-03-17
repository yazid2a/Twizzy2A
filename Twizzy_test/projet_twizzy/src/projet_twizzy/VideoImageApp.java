package projet_twizzy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public class VideoImageApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VideoImageFrame2 frame = new VideoImageFrame2();
            MyThread myThread = new MyThread(frame.videoPath);
            frame.setVisible(true);
        });
        
    }
   
}