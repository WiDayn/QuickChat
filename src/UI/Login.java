package UI;

import Main.QuickChat;
import Net.Request.LoginRequest;
import Utils.*;

import javax.swing.*;
import java.io.IOException;

public class Login {
    public JPanel root;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LoginButton;
    private JLabel Notice;
    private JButton RegisterButton;

    public Login() {

        class UpdateThread extends Thread{
            @Override
            public synchronized void run(){
                while(true){
                    if(StaticConfig.status.equals("login")){
                        Notice.setText(StaticBuffer.LoginMessage);
                    }
                    if(StaticConfig.status.equals("online")){
                        QuickChat.frame.setVisible(false);
                        QuickChat.frame.setContentPane(new ChatRoom().root);
                        QuickChat.frame.setSize(800, 400);
                        QuickChat.frame.setVisible(true);
                        break;
                    }
                }
            }
        }

        LoginButton.addActionListener(e -> {
            String user = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());
            LoginRequest req = new LoginRequest(Utils.getNowTimestamp(), "Login", user, password);
            try {
                req.send();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            UpdateThread updateThread = new UpdateThread();
            updateThread.start();
        });
        RegisterButton.addActionListener(e -> {
            StaticConfig.status = "register";
            QuickChat.frame.setVisible(false);
            QuickChat.frame.setContentPane(new Register().root);
            QuickChat.frame.setSize(400, 400);
            QuickChat.frame.setVisible(true);
        });
    }

}
