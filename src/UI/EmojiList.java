package UI;

import Utils.EmojiParsers;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmojiList extends JDialog {
    private JPanel contentPane;
    private JList<String> list1;
    private int selcetIndex = -1;

    private EmojiList emojiList = this;

    public EmojiList() {
        this.setTitle("±Ì«È—°‘Ò∆˜");
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for(int i = 0; i < EmojiParsers.length; i++) {
            defaultListModel.add(i, EmojiParsers.EmojiName[i]);
        }
        list1.setModel(defaultListModel);

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selcetIndex = list1.getSelectedIndex();
                emojiList.dispose();
            }
        });

        setContentPane(contentPane);
        setModal(true);
        this.setLocation(300, 300);
        this.setSize(350, 500);
        this.setVisible(true);

    }

    public int getSelcetIndex() {
        return selcetIndex;
    }
}
