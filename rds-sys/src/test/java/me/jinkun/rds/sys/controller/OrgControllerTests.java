package me.jinkun.rds.sys.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JinKun
 * @date 2017-11-23
 * @time 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrgControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void list() {
        String str = restTemplate.getForObject("/sys/org/list", String.class);
        System.out.println(str);
    }

    @Test
    public void tree() {
        String str = restTemplate.getForObject("/sys/org/tree", String.class);
        System.out.println(str);
    }
}
