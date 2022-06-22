package Net.Feedback;

import java.io.Serializable;
import java.sql.Timestamp;

public class Feedback implements Serializable {

    private static final long serialVersionUID = -4479572790248717145L;
    private Timestamp sendTime;
    private String type;

    Feedback(Timestamp sendTime, String type){
        this.sendTime = sendTime;
        this.type = type;
    }
}
