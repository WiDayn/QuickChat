package Chat;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private static final long serialVersionUID = -3540415618141048319L;
    User user;
    Timestamp timestamp;
    String massage;

    public Message(User user, Timestamp timestamp, String massage){
        this.user = user;
        this.timestamp = timestamp;
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }
}
