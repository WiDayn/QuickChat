package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class JoinRoomFeedback extends StatusMessageFeedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 86952255089070870L;


    public JoinRoomFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "JoinRoom", status, message);
    }
}
