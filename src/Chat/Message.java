package Chat;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private static final long serialVersionUID = -3540415618141048319L;
    String userid;
    Timestamp timestamp;
    String massage;

    public Message(String userid, Timestamp timestamp, String massage){
        this.userid = userid;
        this.timestamp = timestamp;
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getUserid() {
        return userid;
    }
}
