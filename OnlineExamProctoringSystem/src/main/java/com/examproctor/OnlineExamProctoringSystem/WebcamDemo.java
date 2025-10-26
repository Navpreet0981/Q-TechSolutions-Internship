package com.examproctor.OnlineExamProctoringSystem;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javax.swing.*;

public class WebcamDemo {
    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        WebcamPanel panel = new WebcamPanel(webcam);

        JFrame window = new JFrame("Webcam Feed");
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}

