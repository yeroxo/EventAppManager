package com.example.productList.model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;


@Entity
public class EventItem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date start;
    private Date end;
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MyUser author;

    public EventItem() {
    }

    public EventItem(Long id, String name, Date start, Date end,MyUser author) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.author = author;
        status = false;
    }

    public EventItem(Long id) {
        this.id = id;
        this.name = null;
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public String getEnd() { return end.toString().substring(0,end.toString().length()-5); }

    public String getStart() { return start.toString().substring(0,start.toString().length()-5); }

    public MyUser getAuthor(){return author;}

    public String getEndFormatted() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        return format.format(this.end);
    }

    public Date getFullEnd(){
        return this.end;
    }

    public String getStartFormatted() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        return format.format(this.start);
    }

    public void setEnd(Date end) { this.end = end; }

    public void setStart(Date start) { this.start = start; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
