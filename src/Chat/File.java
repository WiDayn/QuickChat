package Chat;

import java.io.Serializable;
import java.sql.Timestamp;

public class File implements Serializable {
    private static final long serialVersionUID = -6906523998579944784L;

    private String filename;

    private String realname;

    private Timestamp timestamp;

    private int roomId;

    private Long size;

    public File(String filename, String realname, Timestamp timestamp, int roomId, Long size){
        this.filename = filename;
        this.realname = realname;
        this.timestamp = timestamp;
        this.roomId = roomId;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public String getRealname() {
        return realname;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getRoomId() {
        return roomId;
    }

    public Long getSize() {
        return size;
    }
}
