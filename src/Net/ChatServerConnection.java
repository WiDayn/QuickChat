package Net;

import Utils.StaticConfig;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.SocketException;
import java.security.KeyStore;

public class ChatServerConnection {
    private static final String TLS = "TLSv1.2";
    private static final String PROVIDER = "SunX509";
    private static final String STORE_TYPE = "JKS";
    private static final String TRUST_STORE_NAME = "serverTrust_ks.jks";
    private static final String KEY_STORE_NAME = "client_ks.jks";
    private static final String CLIENT_KEY_STORE_PASSWORD = "client_password"; //密码
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "client";//密码

    public static InputStream inputStream;
    public static OutputStream outputStream;

    static {
        SSLSocket socket = null;

        try {
            SSLContext sslContext = SSLContext.getInstance(TLS);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(PROVIDER);
            //生成信任证书Manager,默认系统会信任CA机构颁发的证书,自定的证书需要手动的加载
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(PROVIDER);
            KeyStore keyStoreOne = KeyStore.getInstance(STORE_TYPE);
            KeyStore keyStoreTwo = KeyStore.getInstance(STORE_TYPE);
            //加载client端密钥
            keyStoreOne.load(new FileInputStream(KEY_STORE_NAME), CLIENT_KEY_STORE_PASSWORD.toCharArray());
            //信任证书
            keyStoreTwo.load(new FileInputStream(TRUST_STORE_NAME), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
            keyManagerFactory.init(keyStoreOne, CLIENT_KEY_STORE_PASSWORD.toCharArray());
            trustManagerFactory.init(keyStoreTwo);

            //初始化
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            socket = (SSLSocket) sslContext.getSocketFactory().createSocket("localhost", StaticConfig.DEFAULT_PORT);
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
                    System.out.println("客户端关闭");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        ReceiveMsg receiveMsg = new ReceiveMsg();
        receiveMsg.start();
    }

    public static class ReceiveMsg extends Thread{
        @Override
        public synchronized void run(){
            while(true){
                try {
                    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(inputStream));
                    Object obj = ois.readObject();
                    Handler.handler(obj);
                } catch (SocketException e){
                    System.out.println("服务器断开连接: " + e.getMessage());
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void SendObj(Object object) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(object);
        oos.flush();
    }
}
