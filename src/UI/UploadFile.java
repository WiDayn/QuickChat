package UI;

import Net.FileServerConnection;
import Net.ServerConnection;
import Utils.StaticConfig;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.*;

public class UploadFile extends JDialog {
    private JPanel contentPane;
    private JButton buttonChoose;
    private JButton buttonSend;
    private JProgressBar progressBar1;
    private JLabel fileNameLabel;

    private final UploadFile parent = this;

    private UploadThread uploadThread;

    public UploadFile() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonChoose);

        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // 设置默认显示的文件夹为当前文件夹
                fileChooser.setCurrentDirectory(new File("."));

                // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                // 设置是否允许多选
                fileChooser.setMultiSelectionEnabled(false);

                // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
                // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
                // 设置默认使用的文件过滤器
                // fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

                // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
                int result = fileChooser.showOpenDialog(parent);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // 如果点击了"确定", 则获取选择的文件路径
                    File file = fileChooser.getSelectedFile();

                    fileNameLabel.setText(file.getName());

                    progressBar1.setString(null);//设置进度条内容为空
                    progressBar1.setStringPainted(true);//设置进度条显示百分比

                    uploadThread = new UploadThread(file);
                    // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
                    // File[] files = fileChooser.getSelectedFiles();
                }
            }
        });

        buttonSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                uploadThread.start();
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

        setContentPane(contentPane);
        setModal(true);
        this.setLocation(300, 300);
        this.setSize(300, 150);
        this.setVisible(true);
    }

    class UploadThread extends Thread {

        File file;

        UploadThread(File file){
            this.file = file;
        }

        @Override
        public void run(){
            double percentage = 0;//百分比
            FileServerConnection fileServerConnection = new FileServerConnection();
            FileInputStream fileInputStream = null;
            DataOutputStream dataOutputStream = null;
            OutputStream outputStream = fileServerConnection.getOutputStream();
            try {
                long AllSize = file.length();//获取文件的大小,单位字节
                fileInputStream = new FileInputStream(file);
                dataOutputStream = new DataOutputStream(fileServerConnection.getOutputStream());
                long read_size = 0;
                byte[] filebuffeer = new byte[1024];
                int len = 0;//每次上传的大小

                //发送文件大小
                dataOutputStream.writeLong(AllSize);

                dataOutputStream.writeInt(StaticConfig.nowRoomId);

                while((len = fileInputStream.read(filebuffeer)) != -1){
                    read_size += len;//计算当前传输的总字节数
                    outputStream.write(filebuffeer,0,len);
                    outputStream.flush();
                    percentage = (double) read_size / AllSize;//计算当前传输进度
                    progressBar1.setValue((int) (percentage * 100));
                }
                progressBar1.setString("上传完成");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                if(fileInputStream != null){
                    try {
                        fileInputStream.close();
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


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
