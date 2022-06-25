package Net;

import Chat.Room;
import Chat.User;
import Net.Feedback.*;
import Net.Request.QueryUnreadPrivateMessageRequest;
import Net.Request.Request;
import Utils.StaticBuffer;
import Utils.StaticConfig;

public class Handler {
    public static void handler(Object obj){
        Feedback req = (Feedback) obj;
        if(req.getType().equals("PullMessage")){
            PullMessageFeedback pullMessageFeedback = (PullMessageFeedback) obj;
            Room room = StaticConfig.rooms.get(pullMessageFeedback.getRoomId());
            room.getMessage().addAll(pullMessageFeedback.getMessages());
        }
        if(req.getType().equals("Login")){
            LoginFeedback loginFeedback = (LoginFeedback) obj;
            if (loginFeedback.getStatus() == 200){
                User user = loginFeedback.getUser();
                StaticConfig.users.put(user.getId(), user);
                StaticConfig.userid = user.getId();
                StaticConfig.status = "online";
            } else {
                StaticBuffer.LoginMessage = loginFeedback.getMessage();
            }
        }
        if(req.getType().equals("Register")){
            RegisterFeedback registerFeedback = (RegisterFeedback) obj;
            StaticBuffer.RegisterMessage = registerFeedback.getMessage();
        }
        if(req.getType().equals("QueryRoom")){
            QueryRoomFeedback queryRoomFeedback = (QueryRoomFeedback) obj;
            StaticConfig.users.get(StaticConfig.userid).getRoomList().clear();
            StaticConfig.users.get(StaticConfig.userid).getRoomList().addAll(queryRoomFeedback.getRoomList());
            StaticBuffer.ReceiveFeedback = true;
        }
        if(req.getType().equals("QueryOnline")){
            QueryOnlineFeedback queryOnlineFeedback = (QueryOnlineFeedback) obj;
            StaticBuffer.OnlineUser.addAll(queryOnlineFeedback.getUserList());
        }
        if(req.getType().equals("JoinRoom")){
            JoinRoomFeedback joinRoomFeedback = (JoinRoomFeedback) obj;
            StaticBuffer.JoinRoomMessage = joinRoomFeedback.getMessage();
        }
        if(req.getType().equals("CreateRoom")){
            CreateRoomFeedback createRoomFeedback = (CreateRoomFeedback) obj;
            StaticBuffer.CreateRoomMessage = createRoomFeedback.getMessage();
        }
        if(req.getType().equals("PullRoom")){
            PullRoomFeedback pullRoomFeedback = (PullRoomFeedback) obj;
            StaticBuffer.PullRoomMessage = pullRoomFeedback.getMessage();
        }
        if(req.getType().equals("QueryUnreadPrivateMessage")){
            QueryUnreadPrivateMessageFeedback queryUnreadPrivateMessageFeedback = (QueryUnreadPrivateMessageFeedback) obj;
            StaticConfig.unreadPrivateMessages.addAll(queryUnreadPrivateMessageFeedback.getPrivateMessageList());
        }
        if(req.getType().equals("QueryFiles")){
            QueryFilesFeedback queryFilesFeedback = (QueryFilesFeedback) obj;
            StaticBuffer.fileList.clear();
            StaticBuffer.fileList.addAll(queryFilesFeedback.getFileList());
        }
    }
}
