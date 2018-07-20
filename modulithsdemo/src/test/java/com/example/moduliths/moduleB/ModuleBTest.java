package com.example.moduliths.moduleB;

import com.example.moduliths.moduleA.MyComponentA;
import de.olivergierke.moduliths.test.ModuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ModuleTest
public class ModuleBTest {

	@Autowired
	ApplicationContext context;

	@Test
	public void bootstrapsModuleBOnly() {

		context.getBean(MyComponentB.class);

//		assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
//				.isThrownBy(() -> context.getBean(MyComponentB.class));
	}
}
