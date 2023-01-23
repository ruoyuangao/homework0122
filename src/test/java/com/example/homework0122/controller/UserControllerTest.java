package com.example.homework0122.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    private static MockMvc mockMvc;

    private static int idRecord = 0;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void insertUserWithAllPrarmsTest() throws Exception {
        this.idRecord++;
        String JSON = "{\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"middle_name\":\"M\",\"dob\":\"1999-06-26\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userinfo")
                .accept(MediaType.APPLICATION_JSON).content(JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        // check http status code
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        // check return value
        String expected = "{\"id\":"+idRecord+",\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"middle_name\":\"M\",\"dob\":\"1999-06-26T00:00:00.000+00:00\"}";
        logger.info(response.getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
        mockMvc.perform(delete("/userinfo"));
    }

    @Test
    public void insertUserWithNoMiddleNameTest() throws Exception {
        this.idRecord++;
        String JSON = "{\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"dob\":\"1999-06-26\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userinfo")
                .accept(MediaType.APPLICATION_JSON).content(JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        // check http status code
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        // check return value
        String expected = "{\"id\":"+idRecord+",\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"middle_name\":null,\"dob\":\"1999-06-26T00:00:00.000+00:00\"}";
        logger.info(response.getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
        mockMvc.perform(delete("/userinfo"));
    }
    @Test
    public void getAllUserInfoTest() throws Exception{
        this.idRecord++;
        String JSON = "{\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"middle_name\":\"M\",\"dob\":\"1999-06-26\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userinfo")
                .accept(MediaType.APPLICATION_JSON).content(JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder);

        requestBuilder = MockMvcRequestBuilders.get("/userinfo");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        // check http status code
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
        String expected = "[{\"id\":"+idRecord+",\"first_name\":\"Kelly\",\"last_name\":\"Gao\",\"middle_name\":\"M\",\"dob\":\"1999-06-26T00:00:00.000+00:00\"}]";
        logger.info(response.getContentAsString());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
        mockMvc.perform(delete("/userinfo"));
    }


}
