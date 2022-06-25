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

                // ����Ĭ����ʾ���ļ���Ϊ��ǰ�ļ���
                fileChooser.setCurrentDirectory(new File("."));

                // �����ļ�ѡ���ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                // �����Ƿ������ѡ
                fileChooser.setMultiSelectionEnabled(false);

                // ��ӿ��õ��ļ���������FileNameExtensionFilter �ĵ�һ������������, ��������Ҫ���˵��ļ���չ�� �ɱ������
                // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
                // ����Ĭ��ʹ�õ��ļ�������
                // fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

                // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
                int result = fileChooser.showOpenDialog(parent);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // ��������"ȷ��", ���ȡѡ����ļ�·��
                    File file = fileChooser.getSelectedFile();

                    fileNameLabel.setText(file.getName());

                    progressBar1.setString(null);//���ý���������Ϊ��
                    progressBar1.setStringPainted(true);//���ý�������ʾ�ٷֱ�

                    uploadThread = new UploadThread(file);
                    // �������ѡ�����ļ�, ��ͨ�����淽����ȡѡ��������ļ�
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
            double percentage = 0;//�ٷֱ�
            FileServerConnection fileServerConnection = new FileServerConnection();
            FileInputStream fileInputStream = null;
            DataOutputStream dataOutputStream = null;
            OutputStream outputStream = fileServerConnection.getOutputStream();
            try {
                long AllSize = file.length();//��ȡ�ļ��Ĵ�С,��λ�ֽ�
                fileInputStream = new FileInputStream(file);
                dataOutputStream = new DataOutputStream(fileServerConnection.getOutputStream());
                long read_size = 0;
                byte[] filebuffeer = new byte[1024];
                int len = 0;//ÿ���ϴ��Ĵ�С

                //�����ļ���С
                dataOutputStream.writeLong(AllSize);

                dataOutputStream.writeInt(StaticConfig.nowRoomId);

                while((len = fileInputStream.read(filebuffeer)) != -1){
                    read_size += len;//���㵱ǰ��������ֽ���
                    outputStream.write(filebuffeer,0,len);
                    outputStream.flush();
                    percentage = (double) read_size / AllSize;//���㵱ǰ�������
                    progressBar1.setValue((int) (percentage * 100));
                }
                progressBar1.setString("�ϴ����");
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
