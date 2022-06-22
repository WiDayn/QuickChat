package Net;

import Chat.Room;
import Chat.User;
import Net.Feedback.LoginFeedback;
import Net.Feedback.PullMessageFeedback;
import Net.Feedback.QueryRoomFeedback;
import Net.Feedback.RegisterFeedback;
import Utils.StaticBuffer;
import Utils.StaticConfig;

import static Net.ServerConnection.AllMessAge;

public class Handler {
    public static void handler(Object obj){
        if(obj instanceof PullMessageFeedback pullMessageFeedback){
            Room room = StaticConfig.rooms.get(pullMessageFeedback.getRoomId());
            room.getMessage().addAll(pullMessageFeedback.getMessages());
        }
        if(obj instanceof LoginFeedback loginFeedback){
            if (loginFeedback.getStatus() == 200){
                User user = loginFeedback.getUser();
                StaticConfig.users.put(user.getId(), user);
                StaticConfig.userid = user.getId();
                StaticConfig.status = "online";
            } else {
                StaticBuffer.LoginMessage = loginFeedback.getMessage();
            }
        }
        if(obj instanceof RegisterFeedback registerFeedback){
            StaticBuffer.RegisterMessage = registerFeedback.getMessage();
        }
        if(obj instanceof QueryRoomFeedback queryRoomFeedback){
            StaticConfig.users.get(StaticConfig.userid).getRoomList().clear();
            StaticConfig.users.get(StaticConfig.userid).getRoomList().addAll(queryRoomFeedback.getRoomList());
            StaticBuffer.ReceiveFeedback = true;
        }
    }
}
