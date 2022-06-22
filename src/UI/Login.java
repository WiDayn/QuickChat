package UI;

import Net.Request.LoginRequest;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Login {
    public JPanel root;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LoginButton;
    private JLabel Notice;

    public Login() {
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());
                LoginRequest req = new LoginRequest(Utils.getNowTimestamp(), "Login", user, password);
                try {
                    req.send();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
