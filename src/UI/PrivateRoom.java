package UI;

import Chat.Message;
import Chat.PrivateMessage;
import Net.Request.SendMessageRequest;
import Net.Request.SendPrivateMessageRequest;
import Net.ChatServerConnection;
import Utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PrivateRoom extends JDialog {
    private JPanel contentPane;
    private JButton sendButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JLabel titleLabel;
    private JTextPane textPane1;
    private JButton buttonOK;

    private StringBuilder lastMsgs = new StringBuilder();

    String userid;

    public PrivateRoom(String userid) {
        Font font = new Font("Noto Sans SC", Font.BOLD, 14);
        textPane1.setFont(font);

        this.userid = userid;
        titleLabel.setText("�� " +userid + " ��˽������");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrivateMessage privateMessage = new PrivateMessage(Utils.getNowTimestamp(), StaticConfig.users.get(StaticConfig.userid).getUserid(), userid, textField1.getText());
                SendPrivateMessageRequest sendPrivateMessageRequest = new SendPrivateMessageRequest(Utils.getNowTimestamp(), privateMessage);
                try {
                    sendPrivateMessageRequest.send();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                textField1.setText("");
                StaticConfig.privateMessages.add(privateMessage);
            }
        });

        java.util.Timer timer = new Timer();
        timer.schedule(new QueryTask(), 0, 2000);
        timer.schedule(new UpdateTask(), 0, 500);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StaticBuffer.OpenPrivateMessageWindows.remove(userid);
                timer.cancel(); // �رն�ʱ��
                dispose();
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) //�жϰ��µļ��Ƿ��ǻس���
                {
                    String message = textField1.getText();
                    Message sendMessage = new Message(StaticConfig.users.get(StaticConfig.userid).getUserid(), Utils.getNowTimestamp(), message);
                    StaticConfig.rooms.get(StaticConfig.nowRoomId).getMessage().add(sendMessage);
                    SendMessageRequest sendMessageRequest = new SendMessageRequest(Utils.getNowTimestamp(), sendMessage, StaticConfig.rooms.get(StaticConfig.nowRoomId));
                    try {
                        ChatServerConnection.SendObj(sendMessageRequest);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    textField1.setText("");
                }
            }
        });
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocation(300, 300);
        this.setSize(400, 250);
        this.setVisible(true);
    }

    private class UpdateTask extends TimerTask{
        @Override
        public void run(){
            // ��ӡ������Ϣ
            StringBuilder msgs = new StringBuilder();
            for (PrivateMessage privateMessage : StaticConfig.privateMessages){
                // ƥ���Ƿ��������������Ϣ(�Ա�From_user��To_user)
                if((privateMessage.getFrom_user().equals(userid) && privateMessage.getTo_user().equals(StaticConfig.users.get(StaticConfig.userid).getUserid()))
                        || privateMessage.getFrom_user().equals(StaticConfig.users.get(StaticConfig.userid).getUserid()) && privateMessage.getTo_user().equals(userid)){
                    msgs.append(StaticConfig.df.format(privateMessage.getSendTime())).append(" ").append(privateMessage.getFrom_user()).append(":").append(privateMessage.getMessage()).append("<br>");
                }
            }
            if(!lastMsgs.toString().equals(msgs.toString())){
                textPane1.setText(EmojiParsers.Decode(msgs.toString()));
            }
            lastMsgs = msgs;
        }
    }

    private class QueryTask extends TimerTask {
        @Override
        public void run(){
            while(StaticBuffer.PrivateLock){
                try {
                    synchronized(this){
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            StaticBuffer.PrivateLock = true;
            // �ȱ������л�û���������������������Ϣ���Ƶ��Ѷ�
            for(PrivateMessage privateMessage : StaticConfig.unreadPrivateMessages){
                if(privateMessage.getFrom_user().equals(userid)){
                    StaticConfig.privateMessages.add(privateMessage);
                }
            }
            StaticConfig.unreadPrivateMessages.removeIf(next->(next.getFrom_user().equals(userid)));
            StaticBuffer.PrivateLock = false;
            synchronized(this){
                this.notifyAll();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
