package com.example.moduliths;

import com.example.moduliths.moduleA.MyComponentA;
import com.example.moduliths.moduleB.MyComponentB;
import de.olivergierke.moduliths.test.ModuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulithsApplicationTests {

	@Autowired
	public ApplicationContext context;
    @Test
    public void contextLoads() {
		MyComponentA myComponentA = context.getBean(MyComponentA.class);
		myComponentA.sayHelloA();


    }

}
