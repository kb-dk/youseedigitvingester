package dk.statsbiblioteket.digitv.youseeingester;

import dk.statsbiblioteket.digitv.youseeingester.model.RecordedFile;
import dk.statsbiblioteket.digitv.youseeingester.model.persistence.RecordedFileDAO;
import dk.statsbiblioteket.digitv.youseeingester.model.persistence.YouseeDigitvIngesterHibernateUtil;
import dk.statsbiblioteket.mediaplatform.ingest.model.persistence.HibernateUtilIF;
import junit.framework.TestCase;

import java.io.File;
import java.util.Date;

/**
 * @author jrg
 */
public class YouseeDigitvIngesterTest extends TestCase {
    @org.junit.Test
    public void testPersistence()  throws Exception {
        /*YouseeDigitvIngester.main(new String[]{
                "-filename","testfile.mux",
                "-starttime","10",
                "-stoptime","20",
                "-channelid","dr1",
                "-config",""
        });*/

        File cfgFile = new File("src/test/resources/hibernate.cfg.xml");
        HibernateUtilIF util = YouseeDigitvIngesterHibernateUtil.initialiseFactory(cfgFile);

        util.getSession();

        String filename;
        Date start_date;
        Date stop_date;
        String channel_id;
        RecordedFileDAO recordedFileDAO = new RecordedFileDAO(util);

        // First create
        filename = "testfilename";
        start_date = new Date();
        stop_date = new Date();
        channel_id = "dr1";
        RecordedFile recordedFile = new RecordedFile(filename, start_date,
                stop_date, channel_id);
        Long firstReturnedId = recordedFileDAO.create(recordedFile);

        // Second create
        filename = "testfilename";
        start_date = new Date();
        stop_date = new Date();
        channel_id = "tv2";
        recordedFile = new RecordedFile(filename, start_date, stop_date,
                channel_id);
        Long secondReturnedId = recordedFileDAO.create(recordedFile);

        assertNotSame(firstReturnedId, secondReturnedId);
    }

   @org.junit.Test
   public void testMain() {
      YouseeDigitvIngester.main(new String[]{
                "-filename","testfile.mux",
                "-starttime","20120512120000",
                "-stoptime","20120512130000",
                "-channelid","dr1",
                "-config","src/test/resources/ingester.properties"
        });
   }

}
