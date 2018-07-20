package com.example.moduliths;

import com.example.moduliths.moduleA.MyComponentA;
import de.olivergierke.moduliths.Module;
import de.olivergierke.moduliths.Modulith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Modulith
public class ModulithsApplication {

    public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ModulithsApplication
				.class,	args);
		MyComponentA myComponentA = context.getBean(MyComponentA.class);
		myComponentA.sayHelloA();
    }
}
