package Net.Request;

import java.sql.Timestamp;

public class LoginRequest extends Request{
    private static final long serialVersionUID = -8129681148652207732L;
    String user;
    String password;

    public LoginRequest(Timestamp sendTime, String type, String user, String password) {
        super(sendTime, type);
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
