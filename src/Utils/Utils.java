package Utils;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {
    public static Timestamp getNowTimestamp(){
        return new Timestamp(new Date().getTime());
    }
}
