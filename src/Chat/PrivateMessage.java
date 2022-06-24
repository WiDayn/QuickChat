package Chat;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class PrivateMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 2071867071950934706L;

    private final Timestamp sendTime;
    private final String from_user;
    private final String to_user;
    private final String message;

    public PrivateMessage(Timestamp sendTime, String from_user, String to_user, String message) {
        this.sendTime = sendTime;
        this.from_user = from_user;
        this.to_user = to_user;
        this.message = message;
    }

    public String getFrom_user() {
        return from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }
}
