package dk.statsbiblioteket.digitv.youseeingester.model.persistence;

import dk.statsbiblioteket.digitv.youseeingester.model.RecordedFile;
import java.util.Date;
import java.util.List;

/**
 * @author jrg
 */
public interface RecordedFileDAOInterface {
    /**
     * Returns the recorded file object corresponding to the given channel ID
     * on a given date.
     * @param channel_id Channel ID for the recorded file object wanted.
     * @param on_date Date for the recorded file object wanted.
     * @return
     */
    public RecordedFile getRecordedFileByChannelAndDate(String channel_id,
                                                        Date on_date);

    /* TODO Make method that returns the "first" 100 files that have been
       ingested - for testing purposes. */
    // public List<> getFirst100Files();

    /*
     * Returns a sorted list of tuples, each of which is a pair
     * (channel name, display name)
     * The list is of distinct values, sorted by display name
     * @return
     */
   // public List<Object[]> getNameMappings();
}
