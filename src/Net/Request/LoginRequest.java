package Net.Request;

import java.io.Serial;
import java.sql.Timestamp;

public class LoginRequest extends Request{
    @Serial
    private static final long serialVersionUID = -8129681148652207732L;
    private String userid;
    private String password;

    public LoginRequest(Timestamp sendTime, String userid, String password) {
        super(sendTime, "Login");
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }
}
