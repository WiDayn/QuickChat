package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class CreateRoomFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = -4470474373876010558L;

    private int status;
    private String message;

    public CreateRoomFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "CreateRoom");
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
