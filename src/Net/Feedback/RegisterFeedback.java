package Net.Feedback;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class RegisterFeedback extends StatusMessageFeedback implements Serializable {
    @Serial
    private static final long serialVersionUID = -4280885574609884172L;

    public RegisterFeedback(Timestamp sendTime, int status, String message) {
        super(sendTime, "Register", status, message);
    }
}
