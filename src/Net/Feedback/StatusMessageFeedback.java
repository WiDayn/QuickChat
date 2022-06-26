package Net.Feedback;

import java.sql.Timestamp;

public class StatusMessageFeedback extends Feedback{
    private int status;
    private String message;

    StatusMessageFeedback(Timestamp sendTime, String type, int status, String message) {
        super(sendTime, type);
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
