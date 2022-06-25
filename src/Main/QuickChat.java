package Main;

import Files.FilesStream;
import UI.Login;
import Utils.StaticConfig;

import javax.swing.*;
import java.io.IOException;

public class QuickChat {
    public static JFrame frame = new JFrame("Login");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FilesStream.load();
        StaticConfig.status = "login";
        Login login = new Login();
        frame.setContentPane(login.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(50, 50);
        frame.setVisible(true);
        Thread shutdownHook = new PlatformShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }





    protected static class PlatformShutdownHook extends Thread {
        public void run() {
            try {
                FilesStream.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
