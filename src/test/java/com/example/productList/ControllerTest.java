package com.example.productList;


import com.example.productList.Service.ShopService;
import com.example.productList.model.ShopItem;
import com.example.productList.repos.ItemRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    //junit тесты проще интеграционных
    @MockBean
    private ItemRepo itemRepo;

    @Autowired
    ShopService shopService;

    @Test
    public void createPostTest() throws Exception{
        ShopItem item =  shopService.create("test-post");
        Mockito.verify(itemRepo, Mockito.times(1)).save(item);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postRedirectTest() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/item/add");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void likeSetTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/item/delete/1");
        mockMvc.perform(request).andDo(print()).andExpect(status().isOk());
    }
}
