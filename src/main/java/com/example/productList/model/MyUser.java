package com.example.productList.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String login;
    private String password;
    private String position;
    private String role;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<EventItem> events;

    public void addEvent(EventItem event){
        this.events.add(event);
    }

    public void removeEvent(EventItem event){
        this.events.remove(event);
    }

    public MyUser() {

    }

    public MyUser(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = "USER";
        this.position = "default";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

//    public List<EventItem> getEvents(){ return events; }
//
//    public void setEvent(EventItem event){
//        this.events.add(event);
//    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<EventItem> getEvents() { return events; }

    public EventItem getEventById(Long id) {return events.get(id.intValue());}

    public void setEvents(List<EventItem> events) { this.events = events; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
