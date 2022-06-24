package UI;

import Chat.Message;
import Chat.PrivateMessage;
import Chat.Room;
import Chat.User;
import Files.FilesStream;
import Net.Request.*;
import Utils.*;
import Net.ServerConnection;
import Utils.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.*;
import java.lang.invoke.StringConcatException;
import java.nio.file.FileStore;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class ChatRoom {
    public JPanel root;
    private JTextArea textArea1;
    private JButton button1;
    private JList<String> list1;
    private JTextField textField1;
    private JComboBox<Integer> comboBox1;
    private JLabel useridLabel;
    private JButton flushRoomButton;
    private JButton JoinRoomButton;
    private JButton pullRoomButton;
    private JButton createRoomButton;
    private JLabel roomName;
    private JScrollPane Jspane;
    private JScrollPane JSpane2;
    private JButton 上传文件Button;
    private JButton 发送表情Button;
    private JButton 文件列表Button;
    private JList list2;

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
            if(!StaticConfig.rooms.containsKey(room.getId())) StaticConfig.rooms.put(room.getId(), room);
        }
        timer.schedule(new QueryTask(), 0, 1000); //每秒查询一次消息
        timer.schedule(new QueryPrivateTask(), 0, 2000); //每秒查询一次私密消息
        timer.schedule(new SendActive(), 0, 300000); // 每30秒确认一次在线
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
        list1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() >= 2){
                    if(list1.getSelectedValue() != null){
                        new PrivateRoom(list1.getSelectedValue());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public class SendActive extends TimerTask{
        @Override
        public void run(){
            // 发送在线包
            SendActiveRequest sendActiveRequest = new SendActiveRequest(Utils.getNowTimestamp(), "SendActive", StaticConfig.users.get(StaticConfig.userid).getUserid());
            try {
                sendActiveRequest.send();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class QueryPrivateTask extends TimerTask{
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
            // 查看有没有未读的私密消息
            QueryUnreadPrivateMessageRequest queryUnreadPrivateMessageRequest = new QueryUnreadPrivateMessageRequest(Utils.getNowTimestamp(), StaticConfig.users.get(StaticConfig.userid).getUserid());
            try {
                queryUnreadPrivateMessageRequest.send();
                for(PrivateMessage privateMessage : StaticConfig.unreadPrivateMessages){
                    // 如果当前没有打开和该用户的聊天窗口就打开
                    if(!StaticBuffer.OpenPrivateMessageWindows.contains(privateMessage.getFrom_user())){
                        CreatePrivateWindow createPrivateWindow = new CreatePrivateWindow(privateMessage.getFrom_user());
                        StaticBuffer.OpenPrivateMessageWindows.add(privateMessage.getFrom_user());
                        createPrivateWindow.start();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public class CreatePrivateWindow extends Thread{

        String fromUser;

        CreatePrivateWindow(String fromUser){
            this.fromUser = fromUser;
        }
        @Override
        public void run(){
            new PrivateRoom(fromUser);
        }
    }

    public class QueryTask extends TimerTask {
        @Override
        public synchronized void run() {
            // 创建请求 请求当前所在房间上一条消息的时间戳之后的消息
            Room nowRoom = StaticConfig.rooms.get(StaticConfig.nowRoomId);
            PullMessageRequest req;
            if(nowRoom.getMessage().isEmpty()){
                // 如果之前没有记录，就从一天前开始获取
                Calendar beforeTime = Calendar.getInstance();
                beforeTime.add(Calendar.HOUR, -24);
                req = new PullMessageRequest(Utils.getNowTimestamp(), new Timestamp(beforeTime.getTime().getTime()), StaticConfig.rooms.get(StaticConfig.nowRoomId));
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

            // 请求当前房间在线名单
            QueryOnlineRequest queryOnlineRequest = new QueryOnlineRequest(Utils.getNowTimestamp(), "QueryOnline", StaticConfig.nowRoomId);
            try {
                queryOnlineRequest.send();
                DefaultListModel<String> defaultListModel = new DefaultListModel<>();
                int i = 0;
                for(User user : StaticBuffer.OnlineUser){
                    defaultListModel.add(i++, user.getUserid());
                }
                ((DefaultListModel)list1.getModel()).removeAllElements();
                list1.setModel(defaultListModel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
