import UI.ChatRoom;

import javax.swing.*;

public class QuickChat {
    public static void main(String[] args) {
        JFrame frame = new JFrame("QuickChat");
        ChatRoom chatRoom = new ChatRoom();
        frame.setContentPane(chatRoom.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
