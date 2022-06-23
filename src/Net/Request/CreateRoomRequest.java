package Net.Request;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class CreateRoomRequest extends Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -3283672335915216889L;

    private String name;
    private String userid;

    public CreateRoomRequest(Timestamp sendTime, String name, String userid) {
        super(sendTime, "CreateRoom");
        this.name = name;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public String getUserid() {
        return userid;
    }
}
