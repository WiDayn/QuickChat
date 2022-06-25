package Net.Feedback;

import Chat.File;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QueryFilesFeedback extends Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 5361397934738590334L;
    List<File> fileList = new ArrayList<>();

    public QueryFilesFeedback(Timestamp sendTime, List<File> fileList) {
        super(sendTime, "QueryFiles");
        this.fileList = fileList;
    }

    public List<File> getFileList() {
        return fileList;
    }
}
