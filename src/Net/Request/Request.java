package Net.Request;

import Net.ServerConnection;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -8345207475385392718L;
    private final Timestamp sendTime;
    private final String type;

    public Request(Timestamp sendTime, String type) {
        this.sendTime = sendTime;
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public Timestamp getSendTime(){
        return sendTime;
    }

    public void send() throws IOException {
        ServerConnection.SendObj(this);
    }
}
