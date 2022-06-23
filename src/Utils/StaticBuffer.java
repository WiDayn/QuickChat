package Utils;

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
}
