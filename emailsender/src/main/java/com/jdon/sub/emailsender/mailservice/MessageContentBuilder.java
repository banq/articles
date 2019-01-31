package com.jdon.sub.emailsender.mailservice;

import java.util.Map;

public interface MessageContentBuilder {

	String buildMessage(String templateName, Map<String, Object> datas);

}
