package Net;

import Chat.Room;
import Chat.User;
import Net.Feedback.*;
import Net.Request.QueryUnreadPrivateMessageRequest;
import Utils.StaticBuffer;
import Utils.StaticConfig;

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
        if(obj instanceof QueryOnlineFeedback queryOnlineFeedback){
            StaticBuffer.OnlineUser.addAll(queryOnlineFeedback.getUserList());
        }
        if(obj instanceof JoinRoomFeedback joinRoomFeedback){
            StaticBuffer.JoinRoomMessage = joinRoomFeedback.getMessage();
        }
        if(obj instanceof CreateRoomFeedback createRoomFeedback){
            StaticBuffer.CreateRoomMessage = createRoomFeedback.getMessage();
        }
        if(obj instanceof PullRoomFeedback pullRoomFeedback){
            StaticBuffer.PullRoomMessage = pullRoomFeedback.getMessage();
        }
        if(obj instanceof QueryUnreadPrivateMessageFeedback queryUnreadPrivateMessageFeedback){
            StaticConfig.unreadPrivateMessages.addAll(queryUnreadPrivateMessageFeedback.getPrivateMessageList());
        }
        if(obj instanceof QueryFilesFeedback queryFilesFeedback){
            StaticBuffer.fileList.clear();
            StaticBuffer.fileList.addAll(queryFilesFeedback.getFileList());
        }
    }
}
