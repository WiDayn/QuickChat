package Files;

import Chat.Room;
import Chat.User;
import Utils.StaticConfig;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FilesStream {
    public static void save() throws IOException {
        File file = new File("roomsData.qc");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream("roomsData.qc");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StaticConfig.rooms);

        file = new File("usersData.qc");
        if(!file.exists()){
            file.createNewFile();
        }
        fileOutputStream = new FileOutputStream("usersData.qc");
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StaticConfig.users);
    }

    public static void load() throws IOException, ClassNotFoundException {
        File file = new File("roomsData.qc");
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream("roomsData.qc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if(object instanceof HashMap){
                StaticConfig.rooms = (Map<Integer, Room>) object;
            }
        }
        file = new File("usersData.qc");
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream("usersData.qc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if(object instanceof HashMap){
                StaticConfig.users = (Map<Integer, User>) object;
            }
        }
    }
}
