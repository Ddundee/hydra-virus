import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException, IOException {
        int choice = JOptionPane.showConfirmDialog(null, "Do you wish to corrupt your computer?", "Hydra", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.NO_OPTION) System.exit(0);

        new ShutdownHook().start(System.getProperty("user.dir") + "\\src");

        new Main().start();
    }

    @Override
    public void run() {
        Color color = new Color((((int)(Math.random() * 256))), ((int)(Math.random() * 256)), ((int)(Math.random() * 256)));
        int redDirection = 1;
        int greenDirection = -1;
        int blueDirection = 1;
        JFrame frame = new JFrame("Hydra");
        frame.setSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new Main().start();
                new Main().start();
                stop();
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (Math.random() * (screenSize.getWidth() - frame.getWidth())), (int) (Math.random() * (screenSize.getHeight() - frame.getHeight())));
        JPanel panel = new JPanel();
        panel.setBackground(color);
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int newRed = color.getRed() + redDirection;
            int newGreen = color.getGreen() + greenDirection;
            int newBlue = color.getBlue() + blueDirection;

            if (newRed >= 255 || newRed <= 0) {
                redDirection *= -1;  // Reverse the direction
                newRed = color.getRed() + redDirection;
            }

            if (newGreen >= 255 || newGreen <= 0) {
                greenDirection *= -1;  // Reverse the direction
                newGreen = color.getGreen() + greenDirection;
            }

            if (newBlue >= 255 || newBlue <= 0) {
                blueDirection *= -1;  // Reverse the direction
                newBlue = color.getBlue() + blueDirection;
            }

            color = new Color(newRed, newGreen, newBlue);
            panel.setBackground(color);
        }

    }


}