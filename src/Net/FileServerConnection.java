package Net;

import Utils.StaticConfig;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;

public class FileServerConnection {
    private static final String TLS = "TLSv1.2";
    private static final String PROVIDER = "SunX509";
    private static final String STORE_TYPE = "JKS";
    private static final String TRUST_STORE_NAME = "serverTrust_ks.jks";
    private static final String KEY_STORE_NAME = "client_ks.jks";
    private static final String CLIENT_KEY_STORE_PASSWORD = "client_password"; //����
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "client";//����

    public static InputStream inputStream;
    public static OutputStream outputStream;

    public FileServerConnection(){
        SSLSocket socket = null;

        try {
            SSLContext sslContext = SSLContext.getInstance(TLS);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(PROVIDER);
            //��������֤��Manager,Ĭ��ϵͳ������CA�����䷢��֤��,�Զ���֤����Ҫ�ֶ��ļ���
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(PROVIDER);
            KeyStore keyStoreOne = KeyStore.getInstance(STORE_TYPE);
            KeyStore keyStoreTwo = KeyStore.getInstance(STORE_TYPE);
            //����client����Կ
            keyStoreOne.load(new FileInputStream(KEY_STORE_NAME), CLIENT_KEY_STORE_PASSWORD.toCharArray());
            //����֤��
            keyStoreTwo.load(new FileInputStream(TRUST_STORE_NAME), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
            keyManagerFactory.init(keyStoreOne, CLIENT_KEY_STORE_PASSWORD.toCharArray());
            trustManagerFactory.init(keyStoreTwo);

            //��ʼ��
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            socket = (SSLSocket) sslContext.getSocketFactory().createSocket("localhost", StaticConfig.FILE_PORT);
            socket.setKeepAlive(true);

            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();

            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("�ͻ��˹ر�");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
