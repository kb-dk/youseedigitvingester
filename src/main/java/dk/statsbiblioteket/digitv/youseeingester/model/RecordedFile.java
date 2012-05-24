package dk.statsbiblioteket.digitv.youseeingester.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * @author jrg
 */
@Entity
public class RecordedFile {
    private Long Id;
    private String filename;
    private Date start_date;
    private Date stop_date;
    private String channel_id;

    public RecordedFile() {
    }

    public RecordedFile(String filename, Date start_date, Date stop_date,
                        String channel_id) {
        this.filename = filename;
        this.start_date = start_date;
        this.stop_date = stop_date;
        this.channel_id = channel_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getStop_date() {
        return stop_date;
    }

    public void setStop_date(Date stop_date) {
        this.stop_date = stop_date;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("RecordedFile (" + Id +  "): ");
        sb.append("[").append(filename).append("]");
        sb.append("[").append(start_date).append("]");
        sb.append("[").append(stop_date).append("]");
        sb.append("[").append(channel_id).append("]");
        return sb.toString();
    }
}
