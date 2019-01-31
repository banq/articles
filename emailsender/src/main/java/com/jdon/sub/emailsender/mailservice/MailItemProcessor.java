package com.jdon.sub.emailsender.mailservice;

import com.jdon.sub.emailsender.model.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

public class MailItemProcessor implements ItemProcessor<Subscriber, MimeMessagePreparator> {

	private static final Logger logger = LoggerFactory.getLogger(MailItemProcessor.class);
	private static final String SUBJECT = "proba";
	private static final boolean ISHTML = true;
	private static final boolean ISMULTIPART = true;
	private static final String encoding = "UTF-8";

	@Autowired
	private MessageContentBuilder contentBuilder;
	@Value("${mail.from}")
	private String from;

	@Value("${mail.templateName}")
	private String templateName;

	//	@Value("${emailsender.batch.attachment}")
	private String[] attachments;

	public MailItemProcessor() {
	}

	private MimeMessagePreparator createMimeMessagePreparator(Subscriber subscriber) {
		String[] messages = {"Ez egy �zenet�������", "Valami", "m�sik", "Harmadik"};
		Map<String, Object> datas = new HashMap<>();
		datas.put("messages", messages);
		String[] recipients = new String[]{subscriber.getEmail()};
		String templateName = "MailTemplate1";
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
						ISMULTIPART,
						encoding);
				composeMessageHeader(recipients, SUBJECT, attachments, messageHelper);
				messageHelper.setText(contentBuilder.buildMessage(templateName, datas),
						ISHTML);
			}
		};
		return mimeMessagePreparator;
	}

	private void composeMessageHeader(String[] recipients, String subject, String[] attachments,
									  MimeMessageHelper messageHelper) throws MessagingException {
		messageHelper.setFrom(from);
		messageHelper.setTo(recipients);
		messageHelper.setSubject(subject);
		if (attachments != null) {
			for (String filename : attachments) {
				FileSystemResource file = new FileSystemResource(filename);
				messageHelper.addAttachment(file.getFilename(), file);
			}
		}
	}

	@Override
	public MimeMessagePreparator process(Subscriber subscriber) throws Exception {
		return createMimeMessagePreparator(subscriber);
	}

}
