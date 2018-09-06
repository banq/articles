package org.activiti;

import com.jayway.jsonpath.JsonPath;
import org.activiti.engine.delegate.DelegateExecution;

import java.io.Serializable;

public class ExpressSample implements Serializable {

	public void getResult(DelegateExecution execution, String jsonpath) {

		String response = (String) execution.getVariable("response");
		System.out.println("###########jsonpath" + jsonpath);
		String htmlurl = JsonPath.read(response, jsonpath);
		System.out.println("###########htmlurl=" + htmlurl);
	}
}
