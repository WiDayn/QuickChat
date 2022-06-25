package UI;

import Net.FileServerConnection;
import Utils.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DownloadFile extends JDialog {
    private JPanel contentPane;
    private JButton buttonStart;
    private JProgressBar progressBar1;
    private JLabel messageLabel;

    public DownloadFile(int selectIndex) {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Download download = new Download(selectIndex);
                download.start();
            }
        });
        setContentPane(contentPane);
        setModal(true);
        this.setLocation(300, 300);
        this.setSize(300, 200);
        this.setVisible(true);
    }

    public class Download extends Thread{
        int selectIndex;

        Download(int selectIndex){
            this.selectIndex = selectIndex;
        }

        @Override
        public void run(){
            double percentage = 0;//百分比
            FileServerConnection fileServerConnection = new FileServerConnection();
            DataOutputStream dataOutputStream = new DataOutputStream(fileServerConnection.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(fileServerConnection.getInputStream());
            OutputStream outputStream = fileServerConnection.getOutputStream();
            InputStream inputStream = fileServerConnection.getInputStream();
            FileOutputStream fileOutputStream = null;

            try {
                String fileName = StaticBuffer.fileList.get(selectIndex).getFilename();
                String realName = StaticBuffer.fileList.get(selectIndex).getRealname();
                File file = new File(StaticConfig.FILE_PATH + realName);
                int i = 1;
                while(file.exists()){
                    String[] realNameSplit = realName.split("\\.");
                    // 如果已经存在了就在后面加 - 数字
                    if(realNameSplit.length >= 2){
                        file = new File(StaticConfig.FILE_PATH + realNameSplit[realNameSplit.length - 2] + "-" + i + "." + realNameSplit[realNameSplit.length - 1]);
                    } else {
                        break;
                    }
                    i++;
                }
                fileOutputStream  = new FileOutputStream(file);
                long read_size = 0;

                dataOutputStream.writeUTF("download");
                dataOutputStream.writeUTF(fileName);

                long filesize = dataInputStream.readLong();// 获取文件大小

                int len = 0;// 每次接收的大小
                byte[] bytes = new byte[1024];
                // 循环读
                while((len = inputStream.read(bytes)) != -1){
                    read_size += len;
                    fileOutputStream.write(bytes,0,len);
                    fileOutputStream.flush();
                    percentage = (double) read_size / filesize;//计算当前传输进度
                    progressBar1.setValue((int) (percentage * 100));
                    if(read_size == filesize){
                        messageLabel.setText("下载完成");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if(fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(dataOutputStream != null){
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
