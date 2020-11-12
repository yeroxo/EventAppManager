package com.example.productList.Service;

import com.example.productList.model.ListItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {
    List posts = new ArrayList<ListItem>();

    public ListService() {
        this.posts.add(new ListItem(0,"kvas"));
        this.posts.add(new ListItem(1,"cheese"));
    }

    public List<ListItem> listAllItems(){
        return posts;
    }

    public void create(String text) {
        posts.add(new ListItem(posts.size(), text));
    }

    public void remove(int id){
        posts.remove(id);
    }
}
