package com.jdon.sub.emailsender.mailservice;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.util.List;

public class MailBatchItemWriter implements ItemWriter<MimeMessagePreparator> {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void write(List<? extends MimeMessagePreparator> messages) throws Exception {
		messages.stream().forEach((message) -> mailSender.send(message));
	}
}
