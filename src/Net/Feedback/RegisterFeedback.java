package Net.Feedback;

import java.io.Serializable;
import java.sql.Timestamp;

public class RegisterFeedback extends Feedback implements Serializable {
    private static final long serialVersionUID = -4280885574609884172L;
    private int status;
    private String message;

    RegisterFeedback(Timestamp sendTime, String type, int status, String message) {
        super(sendTime, type);
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
