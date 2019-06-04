package com.star.mocktest;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MocktestApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = {"com.star:provider:+:stubs:8080"},stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class MocktestApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    /**
     * RestTemplate方式mock测试
     * @throws Exception
     */
    @Test
    public void should_be_test_rest_template_mock() throws Exception {
        MockHttpServletResponse mockHttpServletResponse =  mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("id","star"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        Assert.assertEquals("hello star",mockHttpServletResponse.getContentAsString().toString());

    }

    /**
     * FeignClient方式mock测试
     * @throws Exception
     */
    @Test
    public void should_be_test_feign_client_mock() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders.get("/hello2").param("id","star"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        Assert.assertEquals("hello star",mockHttpServletResponse.getContentAsString().toString());
    }

}
