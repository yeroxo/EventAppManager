package com.example.productList.model;

public class ListItem {
    int id;
    String name;
    boolean status;

    public ListItem(int id, String name){
        this.id = id;
        this.name = name;
        status = false;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
}
