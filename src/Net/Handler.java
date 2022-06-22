package Net;

import Net.Feedback.LoginFeedback;
import Net.Feedback.PullMessageFeedback;
import Net.Feedback.RegisterFeedback;
import Utils.StaticBuffer;
import Utils.StaticConfig;

import static Net.ServerConnection.AllMessAge;

public class Handler {
    public static void handler(Object obj){
        if(obj instanceof PullMessageFeedback pullMessageFeedback){
            AllMessAge.addAll(pullMessageFeedback.getMessages());
        }
        if(obj instanceof LoginFeedback loginFeedback){
            if (loginFeedback.getStatus() == 200){
                StaticConfig.status = "online";
                StaticConfig.user = loginFeedback.getUser();
            } else {
                StaticBuffer.LoginMessage = loginFeedback.getMessage();
            }
        }
        if(obj instanceof RegisterFeedback registerFeedback){
            StaticBuffer.RegisterMessage = registerFeedback.getMessage();
        }
    }
}
