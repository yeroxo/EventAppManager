package com.example.productList.Service;

import com.example.productList.model.EventItem;
import com.example.productList.model.MyUser;
import com.example.productList.repos.ItemRepo;
import com.example.productList.repos.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class EventService {

    private final MyUserRepository userRepo;

    private final ItemRepo itemRepo;

    @Autowired
    public EventService(MyUserRepository userRepo,ItemRepo itemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    public Iterable<EventItem> listAllItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        MyUser user = userRepo.findByLogin(currentUserName);
        return user.getEvents();
    }

    public EventItem create(String name, String start, String end) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        Date startDate = format.parse(start);
        Date endDate = format.parse(end);
        EventItem item = new EventItem(null, name, startDate,endDate);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        MyUser user = userRepo.findByLogin(currentUserName);
        itemRepo.save(item);
        user.addEvent(item);
        userRepo.save(user);
        return item;
    }

    public Boolean remove(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        MyUser user = userRepo.findByLogin(currentUserName);
        user.removeEvent(itemRepo.findById(id).get());
        itemRepo.deleteById(id);
        userRepo.save(user);
        return true;
    }

    public EventItem getItem(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        MyUser user = userRepo.findByLogin(currentUserName);
        return user.getEventById(id);
    }

    public boolean update(Long id, @Nullable String name,@Nullable String dateStart,@Nullable String dateEnd) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        Boolean state = false;
         EventItem item =  itemRepo.findById(id).get();
        if ((name != null) && (!name.isEmpty())){
            item.setName(name);
            itemRepo.save(item);
            state = true;
        }

        if ((dateStart != null) && (!dateStart.isEmpty())){
            Date startDate = format.parse(dateStart);
            item.setStart(startDate);
            itemRepo.save(item);
            state = true;
        }

        if ((dateEnd != null) && (!dateEnd.isEmpty())){
            Date endDate = format.parse(dateEnd);
            item.setEnd(endDate);
            itemRepo.save(item);
            state = true;
        }
        return state;
    }

    public boolean check(Long itemId) {
        EventItem item  = itemRepo.findById(itemId).get();
        if (item.getStatus())
            item.setStatus(false);
        else
            item.setStatus(true);
        itemRepo.save(item);
        return item.getStatus();
    }
}
