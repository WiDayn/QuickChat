package UI;

import Chat.Message;
import Chat.Room;
import Net.Request.QueryRoomRequest;
import Utils.*;
import Net.Request.PullMessageRequest;
import Net.Request.SendMessageRequest;
import Net.ServerConnection;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class ChatRoom {
    public JPanel root;
    private JTextArea textArea1;
    private JButton button1;
    private JList list1;
    private JTextField textField1;
    private JComboBox<Integer> comboBox1;
    private JLabel useridLabel;
    private JButton flushRoomButton;
    private JButton JoinRoomButton;
    private JButton pullRoomButton;
    private JButton createRoomButton;
    private JLabel roomName;

    public ChatRoom() {
        Timer timer = new Timer();
        button1.addActionListener(e -> {
            String message = textField1.getText();
            Message sendMessage = new Message(StaticConfig.users.get(StaticConfig.userid).getUserid(), Utils.getNowTimestamp(), message);
            StaticConfig.rooms.get(StaticConfig.nowRoomId).getMessage().add(sendMessage);
            SendMessageRequest sendMessageRequest = new SendMessageRequest(Utils.getNowTimestamp(), sendMessage, StaticConfig.rooms.get(StaticConfig.nowRoomId));
            try {
                ServerConnection.SendObj(sendMessageRequest);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            textField1.setText("");
        });
        useridLabel.setText(StaticConfig.users.get(StaticConfig.userid).getUserid());
        for(Room room : StaticConfig.users.get(StaticConfig.userid).getRoomList()){
            // 初始化
            if(StaticConfig.nowRoomId == -1) StaticConfig.nowRoomId = room.getId();
            comboBox1.addItem(room.getId());
            StaticConfig.rooms.put(room.getId(), room);
        }
        timer.schedule(new QueryTask(), 0, 1000);
        roomName.setText(StaticConfig.rooms.get(StaticConfig.nowRoomId).getName());
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getItemCount() == 0) return;
                StaticConfig.nowRoomId = comboBox1.getItemAt(comboBox1.getSelectedIndex());
                roomName.setText(StaticConfig.rooms.get(StaticConfig.nowRoomId).getName());
            }
        });
        flushRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryRoomRequest queryRoomRequest = new QueryRoomRequest(Utils.getNowTimestamp(), "QueryRoom", StaticConfig.users.get(StaticConfig.userid).getUserid());
                try {
                    StaticBuffer.ReceiveFeedback = false;
                    queryRoomRequest.send();
                    comboBox1.removeAllItems();
                    while(comboBox1.getItemCount() == 0){
                        for(Room room : StaticConfig.users.get(StaticConfig.userid).getRoomList()){
                            // 初始化
                            if(StaticConfig.nowRoomId == -1) StaticConfig.nowRoomId = room.getId();
                            comboBox1.addItem(room.getId());
                            StaticConfig.rooms.put(room.getId(), room);
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JoinRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinRoom();
            }
        });

        pullRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PullInRoom();
            }
        });
        createRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateRoom();
            }
        });
    }

    public class QueryTask extends TimerTask {
        @Override
        public void run() {
            // 创建请求 请求当前所在房间上一条消息的时间戳之后的消息
            Room nowRoom = StaticConfig.rooms.get(StaticConfig.nowRoomId);
            PullMessageRequest req;
            if(nowRoom.getMessage().isEmpty()){
                // 如果之前没有记录，就从现在的时间开始获取
                req = new PullMessageRequest(Utils.getNowTimestamp(), Utils.getNowTimestamp(), StaticConfig.rooms.get(StaticConfig.nowRoomId));
            } else {
                // 否则就从上一条信息的发送时间后开始获取
                req = new PullMessageRequest(Utils.getNowTimestamp(), nowRoom.getMessage().get(nowRoom.getMessage().size() - 1).getTimestamp(), StaticConfig.rooms.get(StaticConfig.nowRoomId));
            }
            try {
                req.send();
                StringBuilder msgs = new StringBuilder();
                for(Message msg : StaticConfig.rooms.get(StaticConfig.nowRoomId).getMessage()){
                    msgs.append(StaticConfig.df.format(msg.getTimestamp())).append(" ").append(msg.getUserid()).append(":").append(msg.getMassage()).append("\n");
                }
                textArea1.setText(msgs.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
