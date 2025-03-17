package projet_twizzy;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.nio.file.Paths;

public class Capture {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath = "video.mp4";
        if (!Paths.get(filePath).toFile().exists()){
             System.out.println("File " + filePath + " does not exist!");
             return;
        }

        VideoCapture camera = new VideoCapture(filePath);
        camera.open(0);

        if (!camera.isOpened()) {
            System.out.println("Error! Camera can't be opened!");
            return;
        }
        Mat frame = new Mat();

        while(true){
            if (camera.read(frame)){
                System.out.println("Frame Obtained");
                System.out.println("Captured Frame Width " +
                        frame.width() + " Height " + frame.height());
                Highgui.imwrite("camera.jpg", frame);
                System.out.println("OK");
                break;
            }
        }

        BufferedImage bufferedImage = matToBufferedImage(frame);
        showWindow(bufferedImage);
        camera.release();
    }

    private static BufferedImage matToBufferedImage(Mat frame) {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    private static void showWindow(BufferedImage img) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured");
        frame.setVisible(true);
    }
}