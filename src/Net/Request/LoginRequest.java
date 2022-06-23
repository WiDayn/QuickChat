package Net.Request;

import java.io.Serial;
import java.sql.Timestamp;

public class LoginRequest extends Request{
    @Serial
    private static final long serialVersionUID = -8129681148652207732L;
    private String user;
    private String password;

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
