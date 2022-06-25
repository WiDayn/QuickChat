package Net.Feedback;

import Chat.User;

import java.io.Serial;
import java.sql.Timestamp;

public class LoginFeedback extends Feedback{
    @Serial
    private static final long serialVersionUID = 741597597076057742L;
    private int status;
    private String message;
    private User user;

    public LoginFeedback(Timestamp sendTime, int status, String message, User user) {
        super(sendTime, "Login");
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getMessage(){
        return message;
    }

    public int getStatus() {
        return status;
    }
}
