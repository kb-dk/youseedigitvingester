package dk.statsbiblioteket.digitv.youseeingester.model.persistence;

import dk.statsbiblioteket.digitv.youseeingester.model.RecordedFile;
import java.util.Date;
import java.util.List;

/**
 * @author jrg
 */
public interface RecordedFileDAOInterface {

    public RecordedFile getRecordedFileByFilename(String filename);
}
