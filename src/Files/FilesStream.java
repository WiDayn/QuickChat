package Files;

import Chat.PrivateMessage;
import Chat.Room;
import Chat.User;
import Utils.StaticConfig;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesStream {
    public static void save() throws IOException {
        // ���ж��ļ����ڲ���
        File dir = new File("data/");
        if(!dir.exists()){
            dir.mkdir();
        }

        // ������Ϣ
        File file = new File("data/roomsData.qc");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream("data/roomsData.qc");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StaticConfig.rooms);

        // �û���Ϣ
        file = new File("data/usersData.qc");
        if(!file.exists()){
            file.createNewFile();
        }
        fileOutputStream = new FileOutputStream("data/usersData.qc");
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StaticConfig.users);

        // ˽����Ϣ
        file = new File("data/privateMessageData.qc");
        if(!file.exists()){
            file.createNewFile();
        }
        fileOutputStream = new FileOutputStream("data/privateMessageData.qc");
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StaticConfig.privateMessages);
    }

    public static void load() throws IOException, ClassNotFoundException {
        // ���ж��ļ����ڲ���
        File dir = new File("data/");
        if(!dir.exists()){
            return;
        }

        // ������Ϣ
        File file = new File("data/roomsData.qc");
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream("data/roomsData.qc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if(object instanceof HashMap){
                StaticConfig.rooms = (Map<Integer, Room>) object;
            }
        }

        // �û���Ϣ
        file = new File("data/usersData.qc");
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream("data/usersData.qc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if(object instanceof HashMap){
                StaticConfig.users = (Map<Integer, User>) object;
            }
        }

        // ˽����Ϣ
        file = new File("data/privateMessageData.qc");
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream("data/privateMessageData.qc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if(object instanceof List){
                StaticConfig.privateMessages = (List<PrivateMessage>) object;
            }
        }
    }
}
