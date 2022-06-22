package Net.Request;

import Net.ServerConnection;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

public class PullMessageRequest extends Request{
    private static final long serialVersionUID = -5474785724122187430L;
    public PullMessageRequest(Timestamp sendTime){
        super(sendTime, "PullMsg");
    }
}
