package com.blife.blife.global.util.mail.service


import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class MailService(
	private val javaMailSender: JavaMailSender,
	@Value("\${spring.mail.username}")
	private val userName: String,
) {
	fun sendMail(email: String, subject: String, content: String, isHtmlContent: Boolean = false) {
		val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
		val helper = MimeMessageHelper(mimeMessage, "utf-8")
		helper.setFrom(userName)
		helper.setTo(email)
		helper.setSubject(subject)
		helper.setText(content, isHtmlContent)

		javaMailSender.send(mimeMessage)

	}
}