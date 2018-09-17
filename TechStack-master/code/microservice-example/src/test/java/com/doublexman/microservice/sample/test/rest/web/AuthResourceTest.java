package com.doublexman.microservice.sample.test.rest.web;


import com.doublexman.microservice.sample.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class AuthResourceTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLogin() throws Exception {
        ResultActions resultActions = mvc.perform(
                post("/web-api/auth/login")
                        .param("user_name","peter")
                        .param("password", "peter")
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        System.out.println("access_token:" + resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testLoginWithException() throws Exception {
        ResultActions resultActions = mvc.perform(
                post("/web-api/auth/login")
                        .param("user_name","peter")
                        .param("password", "peter1")
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isUnauthorized());
    }
}
