package Utils;

import Chat.Message;
import Chat.PrivateMessage;
import Chat.Room;
import Chat.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaticConfig {
    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String status;
    public static int userid;

    public static int nowRoomId = -1;

    public static Map<Integer, Room> rooms = new HashMap<>();

    public static Map<Integer, User> users = new HashMap<>();

    public static Map<String, List<String>> recentUsers = new HashMap<>();

    public static List<PrivateMessage> privateMessages = new ArrayList<>();

    public static List<PrivateMessage> unreadPrivateMessages = new ArrayList<>();

    public static final String FILE_PATH = "./Files/";

    public static final int DEFAULT_PORT = 25570; // �Զ���˿�

    public static final int FILE_PORT = 25571; // �ļ�����˿�

}
