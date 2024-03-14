package com.blife.blife.domain.mail.service


import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun sendMail(email: String, subject: String, text: String, isHtml: Boolean = false) {
        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, "utf-8")
        helper.setTo(email)
        helper.setSubject(subject)
        helper.setText(text, isHtml)

        javaMailSender.send(mimeMessage)
    }
}