package Chat;

import Net.Feedback.RegisterFeedback;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

    private static final long serialVersionUID = -1695936838040146977L;
    String name;
    List<User> onlineUser;
    List<Message> message;

    public Room(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessage() {
        return message;
    }

    public List<User> getOnlineUser() {
        return onlineUser;
    }
}
