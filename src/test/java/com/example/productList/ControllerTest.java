package com.example.productList;


import com.example.productList.Service.EventService;
import com.example.productList.model.EventItem;
import com.example.productList.repos.ItemRepo;
import com.example.productList.repos.MyUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //указывает что класс использует средства SpringRunner
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    EventService eventService;
    @MockBean
    private ItemRepo itemRepo;

    @MockBean
    Authentication user;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testovik", password = "123", roles = "USER")
    public void createItemTest() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        EventItem item = eventService.create(ArgumentMatchers.anyString(), date.toString(), date.toString(), "testovik");
        Mockito.verify(itemRepo, Mockito.times(1)).save(item);
    }

    @Test
    @WithMockUser(username = "testovik", password = "123", roles = "USER")
    public void removeItemTest() throws Exception {
        itemRepo.deleteById(1L);
        Mockito.verify(itemRepo, Mockito.times(1)).deleteById(1L);
    }


    @Test
    @WithAnonymousUser
    public void registrationTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/registration");
        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void registrationPostTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration");
        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }
}
