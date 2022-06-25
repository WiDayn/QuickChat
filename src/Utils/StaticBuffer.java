package Utils;

import Chat.File;
import Chat.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticBuffer {
    public static String LoginMessage = "";

    public static String RegisterMessage = "";

    public static boolean ReceiveFeedback = true;

    public static Set<User> OnlineUser = new HashSet<>();
    public static String JoinRoomMessage = "";

    public static String CreateRoomMessage = "";

    public static String PullRoomMessage = "";

    public static Set<String> OpenPrivateMessageWindows = new HashSet<>();

    public static boolean PrivateLock = false;

    // 由于文件的特殊性，每次都需要重新服务器获取，因此没必要记录房间或写入文件留存
    public static List<File> fileList = new ArrayList<>();
}
