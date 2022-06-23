package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class PullRoomFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = -3790781463624417862L;
    private int status;
    public String message;

    public PullRoomFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "PullRoom");
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
