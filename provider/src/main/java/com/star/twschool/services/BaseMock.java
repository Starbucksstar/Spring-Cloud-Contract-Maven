package com.star.twschool.services;


import com.star.twschool.controller.HelloController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class BaseMock {
    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new HelloController());
    }
}
