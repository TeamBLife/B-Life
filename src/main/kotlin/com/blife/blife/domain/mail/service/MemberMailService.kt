package com.blife.blife.domain.mail.service

//import com.blife.blife.domain.mail.RedisUtils
import com.blife.blife.global.exception.MailSendException
import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MemberMailService(
//    private val redisUtils: RedisUtils,
    private val mailService: MailService
) {

    fun memberSendMail(email: String) {
        val certificationNumber: String = MailServiceUtils.certificationNum()
        val subject = "[B-life] 본인 인증 메일"
        val text = getText(certificationNumber)
//        redisUtils.setDataExpire(certificationNumber, email)
        mailService.sendMail(email, subject, text, true)
    }


//    fun checkCertification(certificationNumber: String) {
//        redisUtils.getData(certificationNumber)
//            ?: throw MailSendException("인증번호가 잘못되었거나 인증 시간이 초과되었습니다. 다시 확인해주세요.")
//        redisUtils.deleteData(certificationNumber)
//    }



	private fun getText(certificationNum: String): String {
		return certificationNum

	}
}