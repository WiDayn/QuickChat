package UI;

import Main.QuickChat;
import Net.Request.RegisterRequest;
import Net.ServerConnection;
import Utils.StaticBuffer;
import Utils.StaticConfig;

import javax.swing.*;
import java.io.IOException;

public class Register {
    private JTextField textField1;
    private JButton RegisterButton;
    private JTextField textField3;
    private JPasswordField passwordField1;
    JPanel root;
    private JButton LoginButton;
    private JLabel Notice;

    class UpdateThread extends Thread{
        @Override
        public synchronized void run(){
            while(true){
                if(StaticConfig.status.equals("register")){
                    Notice.setText(StaticBuffer.RegisterMessage);
                }
                if(StaticConfig.status.equals("login")){
                    break;
                }
            }
        }
    }

    public Register() {
        LoginButton.addActionListener(e -> {
            StaticConfig.status = "login";
            QuickChat.frame.setVisible(false);
            QuickChat.frame.setContentPane(new Login().root);
            QuickChat.frame.setSize(400, 400);
            QuickChat.frame.setVisible(true);
        });
        RegisterButton.addActionListener(e -> {
            String userid = textField1.getText();
            String nickname = textField3.getText();
            String passwd = String.valueOf(passwordField1.getPassword());
            RegisterRequest registerRequest = new RegisterRequest(Utils.Utils.getNowTimestamp(), userid, nickname, passwd);
            try {
                ServerConnection.SendObj(registerRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        UpdateThread updateThread = new UpdateThread();
        updateThread.start();
    }
}
