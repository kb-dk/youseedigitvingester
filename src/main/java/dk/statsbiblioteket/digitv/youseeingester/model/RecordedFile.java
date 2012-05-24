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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecordedFile file = (RecordedFile) o;

        if (Id != null ? !Id.equals(file.Id) : file.Id != null) {
            return false;
        }
        if (filename != null ? !filename.equals(file.filename)
                : file.filename != null) {
            return false;
        }
        if (start_date != null ? !start_date.equals(file.start_date)
                : file.start_date != null) {
            return false;
        }
        if (stop_date != null ? !stop_date.equals(file.stop_date)
                : file.stop_date != null) {
            return false;
        }
        if (channel_id != null ? !channel_id.equals(file.channel_id)
                : file.channel_id != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (Id != null ? Id.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (start_date != null ? start_date.hashCode() : 0);
        result = 31 * result + (stop_date != null ? stop_date.hashCode() : 0);
        result = 31 * result + (channel_id != null ? channel_id.hashCode() : 0);
        return result;
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
