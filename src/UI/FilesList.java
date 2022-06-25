package UI;

import Chat.File;
import Net.Request.QueryFilesRequest;
import jdk.jshell.execution.Util;
import Utils.*;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class FilesList extends JDialog {
    private JPanel contentPane;
    private JTable table1;
    private JButton buttonOK;


    public FilesList() throws IOException {
        QueryFilesRequest queryFilesRequest = new QueryFilesRequest(Utils.getNowTimestamp(), StaticConfig.nowRoomId);
        queryFilesRequest.send();
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() >= 2){
                    new DownloadFile(table1.getSelectedColumn());
                }
            }
        });
        java.util.Timer timer = new Timer();
        timer.schedule(new UpdateTask(), 0, 500);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocation(300, 300);
        this.setSize(350, 500);
        this.setVisible(true);
    }

    public class UpdateTask extends TimerTask{
        @Override
        public void run(){
            Vector<String> columnTitle = new Vector<>();
            columnTitle.add("文件名");
            columnTitle.add("上传时间");
            columnTitle.add("大小");
            Vector<Vector> tableData = new Vector<>();
            for(File file : StaticBuffer.fileList){
                Vector<String> vector = new Vector<>();
                vector.add(file.getRealname());
                vector.add(StaticConfig.df.format(file.getTimestamp()));
                vector.add(file.getSize().toString());
                tableData.add(vector);
            }
            TableModel tableModel = new DefaultTableModel(tableData, columnTitle){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table1.setModel(tableModel);
        }
    }

}
