package org.activiti;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessResponseExecutionListener implements ExecutionListener {

	private ObjectMapper objectMapper = new ObjectMapper();

	public void notify(DelegateExecution execution) {

//		ExpressionFactory ef = new ExpressionFactoryImpl();
//		VariableScopeElResolver varResolver = new VariableScopeElResolver(execution);
//		ELContext elcont = new ActivitiElContext(varResolver);
//		ValueExpression ex = ef.createValueExpression(elcont, "${response}", Object.class);
//		String result = (String) ex.getValue(elcont); // the exception is from this line
//
//		Map<String, Object> procVars = execution.getVariables();
//		for (Map.Entry<String, Object> procVar : procVars.entrySet()) {
//			System.out.printf(" [" + procVar.getKey() + " = " + procVar.getValue() + "] ");
//		}

		List<String> searchResults = new ArrayList<String>();
		String response = (String) execution.getVariable("response");

		try {
			JsonNode jsonNode = objectMapper.readTree(response);
			JsonNode itemsNode = jsonNode.get("items");
			if (itemsNode != null && itemsNode.isArray()) {
				for (JsonNode itemNode : (ArrayNode) itemsNode) {
					String url = itemNode.get("html_url").asText();
					searchResults.add(url);

					if (searchResults.size() == 10) {
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		execution.setVariable("searchResults", searchResults);
		execution.setVariable("expressSample", new ExpressSample());
	}

}
