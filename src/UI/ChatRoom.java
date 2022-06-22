package UI;

import Chat.Message;
import Utils.*;
import Net.Request.PullMessageRequest;
import Net.Request.SendMessageRequest;
import Net.ServerConnection;
import Utils.Utils;

import javax.swing.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class ChatRoom {
    public JPanel root;
    private JTextArea textArea1;
    private JButton button1;
    private JList list1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JLabel useridLabel;

    public ChatRoom() {
        Timer timer = new Timer();
        timer.schedule(new QueryTask(), 0, 1000);
        button1.addActionListener(e -> {
            String message = textField1.getText();
            SendMessageRequest sendMessageRequest = new SendMessageRequest(Utils.getNowTimestamp(), new Message(StaticConfig.user, Utils.getNowTimestamp(), message));
            try {
                ServerConnection.SendObj(sendMessageRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        useridLabel.setText(StaticConfig.user.getUserid());
    }


    public class QueryTask extends TimerTask {
        @Override
        public void run() {
            PullMessageRequest req = new PullMessageRequest(Utils.getNowTimestamp());
            try {
                req.send();
                StringBuilder msgs = new StringBuilder();
                for(Message msg : ServerConnection.getAllMessAge()){
                    msgs.append(StaticConfig.df.format(msg.getTimestamp())).append(" ").append(msg.getUser().getNickName()).append(":").append(msg.getMassage()).append("\n");
                }
                textArea1.setText(msgs.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
