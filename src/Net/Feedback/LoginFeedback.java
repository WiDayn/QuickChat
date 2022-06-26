package Net.Feedback;

import Chat.User;

import java.io.Serial;
import java.sql.Timestamp;

public class LoginFeedback extends StatusMessageFeedback{
    @Serial
    private static final long serialVersionUID = 741597597076057742L;
    private User user;

    public LoginFeedback(Timestamp sendTime, int status, String message, User user) {
        super(sendTime, "Login", status, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
