package Main;

import UI.Login;
import Utils.StaticConfig;

import javax.swing.*;

public class QuickChat {
    public static JFrame frame = new JFrame("Login");
    public static void main(String[] args) {
        StaticConfig.status = "login";
        Login login = new Login();
        frame.setContentPane(login.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(300, 300);
        frame.setVisible(true);
    }
}
