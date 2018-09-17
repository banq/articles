package com.doublexman.microservice.sample.test.util;

import com.doublexman.microservice.sample.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TemplateTests {

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void testTemplate(){
        Context ctx = new Context();
        ctx.setVariable("message", "Hello World");
        String htmlContent = templateEngine.process("template", ctx);
        System.out.println(htmlContent);
    }
}
