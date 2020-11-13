package com.example.productList.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class ShopItem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean status;

    public ShopItem() {
    }

    public ShopItem(Long id, String name) {
        this.id = id;
        this.name = name;
        status = false;
    }

    public ShopItem(Long id) {
        this.id = id;
        this.name = null;
        this.status = false;
    }

    public Long getId() {
        return id;
    }


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
