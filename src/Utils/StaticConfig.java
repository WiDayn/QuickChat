package Utils;

import Chat.Room;
import Chat.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaticConfig {
    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String status;
    public static int userid;

    public static int nowRoomId = -1;

    public static Map<Integer, Room> rooms = new HashMap<>();

    public static Map<Integer, User> users = new HashMap<>();
}
