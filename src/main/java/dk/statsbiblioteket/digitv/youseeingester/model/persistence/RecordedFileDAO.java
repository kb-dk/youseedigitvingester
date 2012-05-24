package dk.statsbiblioteket.digitv.youseeingester.model.persistence;

import dk.statsbiblioteket.digitv.youseeingester.model.RecordedFile;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.GenericHibernateDAO;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.HibernateUtilIF;

import java.util.Date;
import java.util.List;

/**
 * @author jrg
 */
public class RecordedFileDAO extends GenericHibernateDAO<RecordedFile, Long>
        implements RecordedFileDAOInterface {
    public RecordedFileDAO(HibernateUtilIF util) {
        super(RecordedFile.class, util);
    }

    /**
     * TODO ........
     * @param o
     * @return
     */
    @Override
    public Long create(RecordedFile o) {
        String nameOfFileToBeCreated = o.getFilename();
        RecordedFile existingFileWithThatName
                = getRecordedFileByFilename(nameOfFileToBeCreated);

        if (existingFileWithThatName == null) {
            // No entry with that filename already, so just make a new one.
        } else {
            // An entry exists with that name, so override it.
            delete(existingFileWithThatName);
        }

        return super.create(o);
    }


    public RecordedFile getRecordedFileByFilename(String filename) {
        List<RecordedFile> recordedFiles = (List<RecordedFile>) getSession().createQuery(
                "from RecordedFile "
                        + "where filename = :filename")
                .setParameter("filename", filename)
                .list();
        if (recordedFiles == null || recordedFiles.isEmpty()) {
            return null;
        } else {
            return recordedFiles.get(0);
        }
    }




}
