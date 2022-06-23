package UI;

import Net.Request.CreateRoomRequest;
import Utils.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CreateRoom extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;
    private JLabel feedbackLabel;

    public CreateRoom() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                CreateRoomRequest createRoomRequest = new CreateRoomRequest(Utils.getNowTimestamp(), name, StaticConfig.users.get(StaticConfig.userid).getUserid());
                try {
                    createRoomRequest.send();
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
        java.util.Timer timer = new Timer();
        timer.schedule(new QueryTask(), 0, 500);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocation(300, 300);
        this.setSize(300, 150);
        this.setVisible(true);
    }

    private class QueryTask extends TimerTask {
        @Override
        public void run(){
            feedbackLabel.setText(StaticBuffer.CreateRoomMessage);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateRoom dialog = new CreateRoom();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
