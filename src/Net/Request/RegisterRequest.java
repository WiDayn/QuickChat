package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class RegisterRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 5269013287221327143L;

    private final String userid;
    private final String nickname;
    private final String password;

    public RegisterRequest(Timestamp sendTime, String userid, String nickname, String password) {
        super(sendTime, "Register");
        this.nickname = nickname;
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
