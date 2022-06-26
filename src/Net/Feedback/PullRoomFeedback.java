package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class PullRoomFeedback extends StatusMessageFeedback implements Serializable {

    @Serial
    private static final long serialVersionUID = -3790781463624417862L;

    public PullRoomFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "PullRoom", status, message);
    }
}
