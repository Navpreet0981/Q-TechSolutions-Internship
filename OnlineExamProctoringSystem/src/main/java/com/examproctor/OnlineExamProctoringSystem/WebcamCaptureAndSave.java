package com.examproctor.OnlineExamProctoringSystem;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebcamCaptureAndSave {
    public static void main(String[] args) throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        // Capture image from webcam
        BufferedImage image = webcam.getImage();

        // Save image to file
        File file = new File("cheating_candidate.jpg");
        ImageIO.write(image, "JPG", file);

        System.out.println("Image captured and saved as cheating_candidate.jpg");

        webcam.close();
    }
}

