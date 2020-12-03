package com.example.productList;

import com.example.productList.controller.ShopController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //аннотация junit запускаем
@SpringBootTest
@AutoConfigureMockMvc //автоматически создает структуру подмены mvc
@Sql(value = {"/create-post-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-post-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ProductListApplicationTests {

	@Autowired
	private MockMvc mockMvc;
//интеграционное тестирование
	@Test
	 void contextLoads() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Список покупок")));
		this.mockMvc.perform(delete("/item/delete/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void itemsLoadsTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(xpath(".//div[@class='item__check']/div").nodeCount(2));
	}

}
