package com.jdon.sub.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class EmailSenderApplication implements CommandLineRunner {

	@Autowired
	public JavaMailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
	}


	public void sendSimpleMessage(
			String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(from);
		emailSender.send(message);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			System.out.printf("==" + args[0].toString());
			sendSimpleMessage(args[0], args[1], args[2], args[3]);
		}
	}
//java -jar .\target\emailsender-0.0.1-SNAPSHOT.jar 429722485@qq.com banq@163.com eee 123
}