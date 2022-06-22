package UI;

import Chat.Message;
import Chat.User;
import Utils.*;
import Net.Request.PullMessageRequest;
import Net.Request.SendMessageRequest;
import Net.ServerConnection;
import Utils.Utils;

import javax.swing.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ChatRoom {
    public JPanel root;
    private JTextArea textArea1;
    private JButton button1;
    private JList list1;
    private JTextField textField1;

    public ChatRoom() {
        Timer timer = new Timer();
        timer.schedule(new QueryTask(), 0, 1000);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField1.getText();
                SendMessageRequest sendMessageRequest = new SendMessageRequest(Utils.getNowTimestamp(), new Message(new User("WiDayn"), Utils.getNowTimestamp(), message));
                try {
                    ServerConnection.SendObj(sendMessageRequest);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public class QueryTask extends TimerTask {
        @Override
        public void run() {
            PullMessageRequest req = new PullMessageRequest(Utils.getNowTimestamp());
            try {
                req.send();
                StringBuilder msgs = new StringBuilder();
                for(Message msg : ServerConnection.getAllMessAge()){
                    msgs.append(StaticConfig.df.format(msg.getTimestamp()) + " " + msg.getUser().getName() + ":" + msg.getMassage()).append("\n");
                }
                textArea1.setText(msgs.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
