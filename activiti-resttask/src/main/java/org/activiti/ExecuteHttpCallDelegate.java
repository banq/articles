package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ExecuteHttpCallDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {

		String keyword = (String) execution.getVariable("keyWord");
		String language = (String) execution.getVariable("language");

		String url = "https://api.github.com/search/repositories?q=%s+language:%s&sort=stars" +
				"&order" +
				"=desc";
		url = String.format(url, keyword, language);
		HttpGet httpget = new HttpGet(url);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			execution.setVariable("response", IOUtils.toString(response.getEntity()
					.getContent(), "UTF-8"));
			execution.setVariable("responseStatus", response.getStatusLine()
					.getStatusCode());
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
