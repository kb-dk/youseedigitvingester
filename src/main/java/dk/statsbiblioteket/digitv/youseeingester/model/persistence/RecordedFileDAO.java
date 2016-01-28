package dk.statsbiblioteket.digitv.youseeingester.model.persistence;

import dk.statsbiblioteket.digitv.youseeingester.model.RecordedFile;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.GenericHibernateDAO;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.HibernateUtilIF;
import org.hibernate.Session;

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
     * Overrides create() to allow create to ingest a file already in the
     * database. The create() method will now overwrite the old entry in the
     * database with the new.
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
        Session session = null;
        try {
            session = getSession();
            List<RecordedFile> recordedFiles = (List<RecordedFile>) session.createQuery(
                    "from RecordedFile "
                            + "where filename = :filename")
                    .setParameter("filename", filename)
                    .list();
            if (recordedFiles == null || recordedFiles.isEmpty()) {
                return null;
            } else {
                return recordedFiles.get(0);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }




}
