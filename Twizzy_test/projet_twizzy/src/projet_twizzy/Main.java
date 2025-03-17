package projet_twizzy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        // Initialiser OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Créer un objet VideoCapture pour lire à partir d'une source vidéo
        VideoCapture videoCapture = new VideoCapture(0);

        // Créer une fenêtre JFrame pour afficher l'image
        JFrame frame = new JFrame("Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        JLabel label = new JLabel();
        frame.add(label);
        frame.setVisible(true);

        // Lire des images à partir de la source vidéo et les afficher
        Mat mat = new Mat();
        while (true) {
            if (videoCapture.read(mat)) {
                BufferedImage image = FrameMatToImageConverter.convert(mat);
                label.setIcon(new ImageIcon(image));
                frame.pack();
            } else {
                System.out.println("Impossible de lire une image à partir de la source vidéo.");
                break;
            }
        }
    }
}


