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

    // �����ļ��������ԣ�ÿ�ζ���Ҫ���·�������ȡ�����û��Ҫ��¼�����д���ļ�����
    public static List<File> fileList = new ArrayList<>();
}
