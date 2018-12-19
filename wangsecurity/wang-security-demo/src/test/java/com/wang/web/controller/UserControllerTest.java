package com.wang.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 19:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testQueryUser() throws Exception {
        String result = mockMvc.perform(get("/user")
                .param("username", "Tom")
                .param("age", "10")
                .param("ageTo", "20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testQueryUserInfo() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                //mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void testCreateSuccess() throws Exception {
        Date date = new Date();
        date.getTime();

        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Tome\",\"password\":\"124\",\"birthday\":" + date.getTime() + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        date.getTime();
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"   \",\"password\":\"124\",\"birthday\":" + date.getTime() + "}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;
        System.out.println(result);
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
