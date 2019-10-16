package com.example.net;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMVC;

    @Before
    public void initEnvironment() {
        mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void simpleGetTest() throws Exception {
        MvcResult result = mockMVC.perform(MockMvcRequestBuilders
                .get("/simpleGet"))
                .andReturn();
        MockHttpServletResponse mockHttpServletRequest = result.getResponse();
        String resultContent = mockHttpServletRequest.getContentAsString();
        System.out.println("result：" + resultContent);
    }

    @Test
    public void multiplicityGetTest() throws Exception {
        MvcResult result = mockMVC.perform(MockMvcRequestBuilders
                .get("/multiplicityGet")
                .param("id", "9")
                .param("name", "multiplicityGetTest"))
                .andReturn();
        MockHttpServletResponse mockHttpServletRequest = result.getResponse();
        String resultContent = mockHttpServletRequest.getContentAsString();
        System.out.println("result：" + resultContent);
    }

    @Test
    public void simplePostTest() throws Exception {
        MvcResult result = mockMVC.perform(MockMvcRequestBuilders
                .post("/simplePost")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":123}"))
                .andReturn();
        MockHttpServletResponse mockHttpServletRequest = result.getResponse();
        String resultContent = mockHttpServletRequest.getContentAsString();
        System.out.println("result：" + resultContent);
    }
}
