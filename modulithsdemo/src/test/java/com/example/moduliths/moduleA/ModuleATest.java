package com.example.moduliths.moduleA;

import com.example.moduliths.moduleB.MyComponentB;
import de.olivergierke.moduliths.test.ModuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@ModuleTest
public class ModuleATest {

	@Autowired
	ApplicationContext context;

	@Test
	public void bootstrapsModuleAOnly() {

		context.getBean(MyComponentA.class);

//		assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
//				.isThrownBy(() -> context.getBean(MyComponentB.class));
	}
}