package UI;

import Net.Request.JoinRoomRequest;
import Utils.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class JoinRoom extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JLabel roomIdLabel;

    public JoinRoom() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocation(300, 300);
        this.setSize(300, 150);
        this.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws IOException {
        // add your code here
        int roomId = Integer.parseInt(textField1.getText());
        JoinRoomRequest joinRoomRequest = new JoinRoomRequest(Utils.getNowTimestamp(), "JoinRoom", StaticConfig.users.get(StaticConfig.userid).getUserid(), roomId);
        joinRoomRequest.send();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        JoinRoom dialog = new JoinRoom();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
