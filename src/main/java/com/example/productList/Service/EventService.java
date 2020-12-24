package com.example.productList.Service;

import com.example.productList.model.EventItem;
import com.example.productList.model.MyUser;
import com.example.productList.repos.ItemRepo;
import com.example.productList.repos.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class EventService {

    private final MyUserRepository userRepo;

    private final ItemRepo itemRepo;

    @Autowired
    public EventService(MyUserRepository userRepo, ItemRepo itemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    public Iterable<EventItem> listAllItems(String userName) {
        return itemRepo.findEventsByLogin(userName);
    }

    public EventItem create(String name, String start, String end, String userName) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        MyUser user = userRepo.findByLogin(userName);
        EventItem item = new EventItem(null, name, startDate, endDate, user);
        itemRepo.save(item);
        return item;
    }

    public Boolean remove(Long id, String userName) {
        boolean state = false;
        if (itemRepo.findById(id).get().getAuthor().getLogin().equals(userName)) {
            itemRepo.deleteById(id);
            state = true;
        }
        return state;
    }

    public boolean update(Long id, @Nullable String name, @Nullable String dateStart, @Nullable String dateEnd, String userName) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        boolean state = false;
        EventItem item = itemRepo.findById(id).get();
        if (item.getAuthor().getLogin().equals(userName)) {
            if ((name != null) && (!name.isEmpty())) {
                item.setName(name);
                itemRepo.save(item);
                state = true;
            }

            if ((dateStart != null) && (!dateStart.isEmpty())) {
                Date startDate = format.parse(dateStart);
                item.setStart(startDate);
                itemRepo.save(item);
                state = true;
            }

            if ((dateEnd != null) && (!dateEnd.isEmpty())) {
                Date endDate = format.parse(dateEnd);
                item.setEnd(endDate);
                itemRepo.save(item);
                state = true;
            }
        }
        return state;
    }

    public boolean check(Long itemId, String userName) {
        EventItem item = itemRepo.findById(itemId).get();
        if (item.getAuthor() == userRepo.findByLogin(userName)) {
            item.setStatus(!item.getStatus());
            itemRepo.save(item);
        }
        return item.getStatus();
    }
}
