package Net.Feedback;

import java.io.Serializable;
import java.sql.Timestamp;

public class JoinRoomFeedback extends Feedback implements Serializable {

    private static final long serialVersionUID = 86952255089070870L;

    int status;
    String message;

    JoinRoomFeedback(Timestamp sendTime, String type, int status, String message) {
        super(sendTime, type);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
