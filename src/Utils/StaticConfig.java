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

    public static List<User> recentUsers = new ArrayList<>();

    public static List<PrivateMessage> privateMessages = new ArrayList<>();

    public static List<PrivateMessage> unreadPrivateMessages = new ArrayList<>();

}
