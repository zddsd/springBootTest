package com.wmyskxz.springboot.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static java.lang.System.out;
import static org.junit.Assert.*;
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void findById() {

        try {
            System.out.println(objectMapper.writeValueAsString(userRepository.findById(1)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally-------------");
        }
    }
}