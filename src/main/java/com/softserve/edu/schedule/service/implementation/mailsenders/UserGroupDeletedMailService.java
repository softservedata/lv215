package com.softserve.edu.schedule.service.implementation.mailsenders;

import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * Provides mail notifications of group members when group has been deleted
 *
 * @version 1.0 04 February 2017
 *
 * @author Andrii Zhydenko
 */
@Component
public class UserGroupDeletedMailService implements MailConstants {

	/**
	 * JavaMailSender example to provide mail sending.
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Messages source for internationalization purposes.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * SpringTemplateEngine example for templates processing.
	 */
	@Autowired
	private SpringTemplateEngine templateEngine;

	/**
	 * Field for import from message attribute from mail.properties.
	 */
	@Value(DEFAULT_MESSAGE_FROM_ADDRESS)
	private String fromAddress;

	/**
	 * Send mail notifications to all group members when group has been deleted
	 *
	 * @param userGroup
	 *            a UserGroup object that contains mail message parameters.
	 *
	 * @param locale
	 *            current locale.
	 */
	@Async
	public void sendInfoMessageGroupDelete(final UserGroup userGroup, final Locale locale) {
		Context ctx = new Context(locale);
		ctx.setVariable(USERGROUP_MODEL_NAME, userGroup);

		List<User> groupMembers = userGroup.getUsers();

		for (User member : groupMembers) {
			ctx.setVariable(USERGROUP_USER, member);

			try {
				MimeMessage mimeMessage = this.mailSender.createMimeMessage();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, DEFAULT_MESSAGE_ENCODING);
				message.setTo(member.getMail());
				message.setFrom(new InternetAddress(fromAddress));
				message.setSubject(messageSource.getMessage(USERGROUP_DELETED_MESSAGE, new String[0], locale));
				String htmlContent = this.templateEngine.process(USERFROUP_DELETED_TEMPLATE, ctx);
				message.setText(htmlContent, true);
				this.mailSender.send(mimeMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
