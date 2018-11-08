package workflow.serviceTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;

public class HelloWorldServiceTask implements JavaDelegate {

	//流程变量
	private Expression text1;

	public void execute(DelegateExecution execution) {
		System.out.println("serviceTask已经执行已经执行！");
		String initiator = (String) execution.getVariable("initiator");
		System.out.println("发起人: " + initiator);
		if (text1 != null) {
			String value1 = (String) text1.getValue(execution);
			execution.setVariable("var1", new StringBuffer(value1).reverse().toString());
		}
		execution.setVariable("greeting", "Hello World!");
		execution.setVariableLocal("greetingLocal", "Hello World Local!");
		Map<String, Object> procVars = execution.getVariables();
		for (Map.Entry<String, Object> procVar : procVars.entrySet()) {
			System.out.printf(" [" + procVar.getKey() + " = " + procVar.getValue() + "] ");
		}

	}

	public Expression getText1() {
		return text1;
	}

	public void setText1(Expression text1) {
		this.text1 = text1;
	}
}
