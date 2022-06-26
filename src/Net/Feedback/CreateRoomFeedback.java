package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class CreateRoomFeedback extends StatusMessageFeedback implements Serializable {

    @Serial
    private static final long serialVersionUID = -4470474373876010558L;

    public CreateRoomFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "CreateRoom", status, message);
    }
}
