package com.example.productList.Scheduler;

import com.example.productList.Service.Email.EmailService;
import com.example.productList.model.EventItem;
import com.example.productList.model.MyUser;
import com.example.productList.repos.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private EmailService emailService;


    @Scheduled(cron = "*/10 * * * * *")
    public void sendStartMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int year = date.getYear();
        List<EventItem> list = itemRepo.findByStartMatch(day, month, year);
        sendMail(list, date, true);
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendEndMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int year = date.getYear();
        List<EventItem> list = itemRepo.findByEndMatch(day, month, year);
        sendMail(list, date, false);
    }

    public void sendMail(List<EventItem> list, LocalDate date, Boolean start) {
        if (!list.isEmpty()) {
            list.forEach(eventItem -> {
                if (!eventItem.getStatus()) {
                    MyUser user = eventItem.getAuthor();
                    if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                        try {
                            String message = "";
                            if (start) {
                                message = "Dear " + user.getLogin() + " your event " + eventItem.getName() + " waiting to complete";
                            } else {
                                message = "Dear " + user.getLogin() + " your event " + eventItem.getName() + " is over";
                            }
                            emailService.send(user.getEmail(), "Event APP", message);
                            logger.info("Email have been sent. User id: {}, Date: {}", user.getId(), date);
                            if (!start){
                                eventItem.setStatus(!eventItem.getStatus());
                            }
                            itemRepo.save(eventItem);
                        } catch (Exception e) {
                            logger.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                            logger.error("Email can't be sent", e);
                        }
                    }
                }
            });
        }
    }
}
